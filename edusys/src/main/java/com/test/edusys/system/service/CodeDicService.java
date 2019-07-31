package com.test.edusys.system.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.edusys.college.model.College;
import com.test.edusys.college.model.Major;
import com.test.edusys.common.service.BaseService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.system.model.Code;

@Service
public class CodeDicService extends BaseService {

	@Autowired
	private Dao dao;

	public Code fetch(Integer id) {
		return dao.fetch(Code.class, id);
	}

	public Code insert(Code record) {
		return (Code) super.insert(record);
	}

	public int delete(Integer id) {
		return super.delete(Code.class, id);
	}

	public int updateIgnoreNull(Code record) {
		return super.updateIgnoreNull(record);
	}

	public Map<String, String> getRealCodeMapBySectionName(String sectionname) {
		Map<String, String> codeMap = new LinkedHashMap<String, String>();

		if (sectionname.equals("学院")) {
			List<College> college = dao.query(College.class, null);
			for (College c : college) {
				codeMap.put(c.getId(), c.getCollege());
			}

		} else {
			List<Code> list = dao.query(Code.class, Cnd.where("sectionname", "=", sectionname).asc("code"));
			for (Code cde : list) {
				codeMap.put(cde.getCode(), cde.getCodename());
			}
		}

		return codeMap;
	}

	/**
	 * 将code表中的内容map按分类放放总maps中
	 * 
	 * @return
	 */
	public Map<String, Map> getAllMap() {
		List<Code> sectionnames = dao.query(Code.class, Cnd.where(null).groupBy("sectionname"));
		List<Code> list = dao.query(Code.class, Cnd.where(null).asc("sectionname").asc("code"));
		Map maps = new HashMap();
		for (Code sectionname : sectionnames) {
			Map map = new LinkedHashMap();
			for (Code code : list) {
				if (code.getSectionname().equals(sectionname.getSectionname())) {
					map.put(code.getCode(), code.getCodename());
				}
			}
			maps.put(sectionname.getSectionname(), map);
		}
		return maps;
	}

	public Map<String, Object> queryPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		cri.where().and("sectionname", "=", "分类");
		List<Code> list = dao.query(Code.class, cri, page);
		page.setRecordCount(dao.count(Code.class, cri));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}
}
