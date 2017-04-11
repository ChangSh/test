package com.test.mysys.common.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.TimeUtil;
import com.test.mysys.common.utils.SearchFilter.Operator;
import com.test.mysys.common.utils.reflection.ReflectionUtils;
import com.test.mysys.system.model.Code;
import com.test.mysys.system.model.Log;
import com.test.mysys.system.model.User;

public class BaseService {

	@Autowired
	private Dao dao;

	public int delete(Class<?> classOfT, long id) {

		Object oRecord = dao.fetch(classOfT, id);
		try {

			String sTableName = ReflectionUtils.getShowLogTablename(oRecord.getClass());
			List<String> lS = ReflectionUtils.getShowLogFieldName(oRecord.getClass());
			String name = "";
			for (String string : lS) {
				name += BeanUtils.getProperty(oRecord, string);
			}
			String sDescription = String.format("对%s信息执行了操作，记录为%s", sTableName, name);
			log("删除", sDescription, sTableName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return dao.delete(classOfT, id);
	}

	public Object insert(Object oRecord) {
		List<String> lS = ReflectionUtils.getShowLogFieldName(oRecord.getClass());
		String name = "";
		try {
			for (String string : lS) {
				name += BeanUtils.getProperty(oRecord, string);
			}
			String sTableName = ReflectionUtils.getShowLogTablename(oRecord.getClass());
			String sDescription = String.format("对%s信息执行了操作，记录为%s", sTableName, name);
			log("新增", sDescription, sTableName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return dao.insert(oRecord);
	}

	public void updateLog(Object oNew, boolean ignoreNull) {
		Object oOld = dao.fetch(oNew);
		String sRes = "";
		try {
			String sTableName = ReflectionUtils.getShowLogTablename(oNew.getClass());
			BeanInfo beanInfo = Introspector.getBeanInfo(oNew.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			Map<String, String> mNames = ReflectionUtils.getCommentName(oNew.getClass());
			sRes = String.format("对%s信息进行更新,前后记录对比:", sTableName);
			for (PropertyDescriptor pd : pds) {
				try {
					String sNewName = BeanUtils.getProperty(oNew, pd.getName()) + "";// 加""去除空的情况
					String sOldName = BeanUtils.getProperty(oOld, pd.getName()) + "";
					String sFiledName = mNames.get(pd.getName());
					if (!sNewName.equals(sOldName)) {
						// //如果是非空更新则需要跳过
						if (ignoreNull && sNewName.equals("null")) {
							continue;
						}
						sRes += String.format(" %s[%s =>更改为=> %s]", sFiledName, sOldName, sNewName);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			log("更新", sRes, sTableName);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

	}

	public int update(Object oNew) {
		updateLog(oNew, false);
		return dao.update(oNew);
	}

	public int updateIgnoreNull(Object oNew) {
		updateLog(oNew, true);
		return dao.updateIgnoreNull(oNew);
	}

	public void log(String type, String des, String method) {
		// -----------日志 ----------------
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 读取session中的用户
		User user = (User) session.getAttribute("currentUser");
		Log log = new Log();
		log.setCreateBy(user.getLoginname());// 设置管理员id
		log.setCreateDate(TimeUtil.getStringTimestamp());// 操作时间
		log.setRequestIp(ip);
		log.setType(type);
		log.setDescription(des);
		log.setMethod(method);
		dao.insert(log);
	}

	public List<Code> getAreaList() {
		return dao.query(Code.class, Cnd.wrap("section = '10039'"));
	}

	// 拼开查询条件
	public Criteria getCriteriaFromPage(NewPager page) {
		Iterator it = page.getFilters().entrySet().iterator();
		Criteria cri = Cnd.cri();
		while (it.hasNext()) {
			Map.Entry item = (Entry) it.next();
			SearchFilter sf = (SearchFilter) item.getValue();

			String op = "=";
			if (Operator.LIKE.equals(sf.operator)) {
				op = "LIKE";
				cri.where().andLike(sf.fieldName, sf.value.toString(), false);
			} else if (Operator.GT.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, ">", sf.value.toString()));
			} else if (Operator.GTE.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, ">=", sf.value.toString()));
			} else if (Operator.LT.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "<", sf.value.toString()));
			} else if (Operator.LTE.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "<=", sf.value.toString()));
			} else if (Operator.NOT.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "<>", sf.value.toString()));
			} else if (Operator.RLIKE.equals(sf.operator)) {
				cri.where().and(Cnd.exp(sf.fieldName, "like", sf.value.toString() + "%"));
			} else {
				cri.where().and(sf.fieldName, op, sf.value.toString());
			}
		}
		if (NewPager.DESC.equals(page.getOrder())) {
			cri.getOrderBy().desc(page.getOrderBy());
		}
		if (NewPager.ASC.equals(page.getOrder())) {
			cri.getOrderBy().asc(page.getOrderBy());
		}
		String[] s = page.getOrders();
		if (s != null) {
			for (int i = 0; i < s.length / 2; i++) {
				if (NewPager.DESC.equals(s[i * 2 + 1])) {
					cri.getOrderBy().desc(s[i * 2]);
				} else {
					cri.getOrderBy().asc(s[i * 2]);
				}
			}
		}
		return cri;
	}

	// 拼查询条件
	public String getStringSqlFromPage(NewPager page) {
		return getStringSqlFromPage(page, null);
	}

	// 拼查询条件
	public String getStringSqlFromPage(NewPager page, String tablename) {
		String sql = "";
		Iterator it = page.getFilters().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry item = (Entry) it.next();
			SearchFilter sf = (SearchFilter) item.getValue();
			String fieldName = sf.fieldName;
			if (StringUtils.isNotEmpty(tablename)) {
				fieldName = tablename + "." + sf.fieldName;
			}
			if (Operator.LIKE.equals(sf.operator)) {
				sql += String.format(" and %s like '%%%s%%' ", fieldName, sf.value.toString());// 2个%占位
			} else if (Operator.GT.equals(sf.operator)) {
				sql += String.format(" and %s > '%s' ", fieldName, sf.value.toString());
			} else if (Operator.GTE.equals(sf.operator)) {
				sql += String.format(" and %s >= '%s' ", fieldName, sf.value.toString());
			} else if (Operator.LT.equals(sf.operator)) {
				sql += String.format(" and %s < '%s' ", fieldName, sf.value.toString());
			} else if (Operator.LTE.equals(sf.operator)) {
				sql += String.format(" and %s <= '%s' ", fieldName, sf.value.toString());
			} else if (Operator.NOT.equals(sf.operator)) {
				sql += String.format(" and %s <> '%s' ", fieldName, sf.value.toString());
			} else if (Operator.RLIKE.equals(sf.operator)) {
				sql += String.format(" and %s like '%%%s' ", fieldName, sf.value.toString());
			} else {
				sql += String.format(" and %s = '%s' ", fieldName, sf.value.toString());
			}
		}
		if (page.getOrderBy() != null) {
			if (NewPager.DESC.equals(page.getOrder())) {
				sql += String.format(" order by %s desc ", page.getOrderBy());
			}
			if (NewPager.ASC.equals(page.getOrder())) {
				sql += String.format(" order by %s asc ", page.getOrderBy());
			}
		}

		return sql;
	}
}
