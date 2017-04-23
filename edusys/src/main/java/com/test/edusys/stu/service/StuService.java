package com.test.edusys.stu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.edusys.common.service.BaseService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.UserUtils;
import com.test.edusys.customer.model.Customer;
import com.test.edusys.goods.model.PicFile;
import com.test.edusys.system.model.User;
import com.test.edusys.topic.model.Topic;

@Service
public class StuService extends BaseService {
	@Autowired
	private Dao dao;

	public Map<String, Object> queryTopicPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		cri.where().andIsNull("stuloginname");
		List<Topic> list = dao.query(Topic.class, cri, page);
		page.setRecordCount(dao.count(Topic.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public Customer fetchStuInfo() {
		User u = UserUtils.getUser();
		Customer c = dao.fetch(Customer.class, Cnd.where("loginname", "=", u.getLoginname()));
		return c;
	}

	public int insertUpload(PicFile file) {
		PicFile p = dao.fetch(PicFile.class, Cnd.where("fileid", "=", file.getFileid()));
		if (p != null) {
			dao.update(PicFile.class, Chain.make("filepath", file.getFilepath()),
					Cnd.where("fileid", "=", file.getFileid()));
		} else {
			file.setId(UUID.randomUUID().toString());
			dao.insert(file);
		}
		return 1;

	}

	public int checkTopic(String topicid) {
		User u = UserUtils.getUser();
		Customer c = dao.fetch(Customer.class, Cnd.where("loginname", "=", u.getLoginname()));
		Topic t = dao.fetch(Topic.class, topicid);
		String topic = t.getTname();
		String tutor = t.getTearealname();
		c.setTopic(topic);
		c.setTutor(tutor);
		t.setStuloginname(u.getLoginname());
		t.setSturealname(u.getRealname());
		dao.update(c);
		dao.update(t);
		return 1;

	}
}
