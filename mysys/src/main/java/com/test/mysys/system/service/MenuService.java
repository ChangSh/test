package com.test.mysys.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.sql.SqlTemplate;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.system.model.Menu;
import com.test.mysys.system.model.User;


@Service
public class MenuService extends BaseService {
	
	@Autowired
	private Dao dao;
	
	@Autowired
	private SqlTemplate sqlTemplate;

	public int delete(Integer id) {
		return super.delete(Menu.class,id);
	}

	public Menu insert(Menu record) {
		return (Menu)super.insert(record);
	}

	public Menu fetch(Integer id) {
		return dao.fetch(Menu.class,id);
	}

	public int update(Menu record) {
		return super.update(record);
	}
	
	public List<Menu> query(Condition c){
		return dao.query(Menu.class,c, null);
	}
	
	public QueryResult queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Menu> list = dao.query(Menu.class, cri, page);
	    page.setRecordCount(dao.count(Menu.class, cri));
	    return new QueryResult(list, page);
	}
	
	//按当前用户角色查菜单
	public List<Menu> queryMenusByCurrentUserRole(String pid) {
		User user = UserUtils.getUser();
		String loginname = user.getLoginname();
		String sSql="SELECT a.* FROM t_system_user d,t_system_user_role c,t_system_role_menu b,t_system_menu a "
				+ " WHERE d.id = c.userid"
				+ " AND a.id=b.menuid"
				+ " AND c.roleid=b.roleid"
				+ " AND d.loginname=@loginname "
				+ " AND a.visible=1 ";
		if(StringUtils.isNoneEmpty(pid)){
			sSql+=" and a.pid = @pid ";
		}

		sSql+=" group by a.id order by sx asc";
		//nutz仿spring template方法
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("loginname", loginname);
		if(StringUtils.isNoneEmpty(pid)){
			params.put("pid", pid);
		}

		List<Menu> menus = sqlTemplate.query(sSql, params,dao.getEntity(Menu.class));

	   return menus;
	}
	
	public List<Menu> queryMenusByRole(String roleid) {
	    Sql sql = Sqls.create("SELECT a.*,b.id as bid FROM t_system_menu a LEFT JOIN t_system_role_menu b ON b.menuid =a.id AND b.roleid="+roleid+" order by sx");
	    sql.setCallback(new SqlCallback() {
	        @Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<Menu> list = new LinkedList<Menu>();
	            while (rs.next()){
	            	Menu menu = new Menu();
	            	menu.setMenuname(rs.getString("menuname"));
	            	menu.setPid(rs.getLong("pid"));
	            	menu.setId(rs.getLong("id"));
	            	
	            	if (rs.getString("bid")!=null)
	            		menu.setChecked(true);
	            	menu.setOpen(true);
	                list.add(menu);
	            }
	            return list;
	        }
	    });
	    dao.execute(sql);
	    return sql.getList(Menu.class);
	    // Nutz内置了大量回调, 请查看Sqls.callback的属性
	}
	
	
}
