package com.test.edusys.topic.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.edusys.college.model.College;
import com.test.edusys.college.model.Major;
import com.test.edusys.common.service.BaseService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.UserUtils;
import com.test.edusys.customer.model.Customer;
import com.test.edusys.goods.model.PicFile;
import com.test.edusys.system.model.User;
import com.test.edusys.topic.model.Topic;

@Service
public class TopicService extends BaseService {
	@Autowired
	private Dao dao;

	public Map<String, Object> queryTopicPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		User user = UserUtils.getUser();
		cri.where().and("tealoginname", "=", user.getLoginname());
		StringBuffer sb = new StringBuffer();
		sb.append(
				"SELECT t_topic.*,t_file.filepath FROM t_topic LEFT JOIN t_file ON t_topic.stuloginname = t_file.fileid ");
		sb.append(cri);
		int pageNumber = page.getPageNumber() * page.getPageSize() - page.getPageSize();
		String ss = "Limit " + pageNumber + "," + page.getPageSize();
		Sql sqlList = Sqls.create(sb.toString());
		sb.append(ss);
		Sql sqlCount = Sqls.create(sb.toString());
		sqlList.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Topic> list = new LinkedList<Topic>();
				while (rs.next()) {
					Topic topic = new Topic();
					topic.setId(rs.getString("id"));
					topic.setTname(rs.getString("tname"));
					topic.setTbackinfo(rs.getString("tbackinfo"));
					topic.setTdetailinfo(rs.getString("tdetailinfo"));
					topic.setTfunction(rs.getString("tfunction"));
					topic.setSturealname(rs.getString("sturealname"));
					topic.setStuloginname(rs.getString("stuloginname"));
					topic.setTearealname(rs.getString("tearealname"));
					topic.setCode(rs.getString("code"));
					topic.setTealoginname(rs.getString("tealoginname"));
					topic.setFileName(rs.getString("filepath"));
					list.add(topic);
				}
				return list;
			}
		});
		sqlCount.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Topic> list = new LinkedList<Topic>();
				while (rs.next()) {
					Topic topic = new Topic();
					topic.setId(rs.getString("id"));
					list.add(topic);
				}
				return list;
			}
		});
		dao.execute(sqlList);
		dao.execute(sqlCount);
		List<Topic> list = sqlList.getList(Topic.class);
		List<Topic> list1 = sqlList.getList(Topic.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", list1.size());
		map.put("Rows", list);
		return map;
	}

	public Map<String, Object> queryMajorPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM t_college ");
		sb.append("RIGHT JOIN  t_college_major ON t_college.id = t_college_major.collegeid ");
		sb.append("RIGHT JOIN t_major ON t_major.id = t_college_major.majorid ");
		sb.append("%s %s");
		int pageNumber = page.getPageNumber() * page.getPageSize() - page.getPageSize();
		String ss = "Limit " + pageNumber + "," + page.getPageSize();
		Sql sqlList = Sqls.create(String.format(sb.toString(), cri, ss));
		Sql sqlCount = Sqls.create(String.format(sb.toString(), cri, ""));
		sqlList.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<College> list = new LinkedList<College>();
				while (rs.next()) {
					College college = new College();
					college.setId(rs.getString("majorid"));
					college.setCollege(rs.getString("college"));
					college.setMajor(rs.getString("major"));
					list.add(college);
				}
				return list;
			}
		});
		sqlCount.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<College> list = new LinkedList<College>();
				while (rs.next()) {
					College college = new College();
					college.setCollege(rs.getString("college"));
					college.setMajor(rs.getString("major"));
					list.add(college);
				}
				return list;
			}
		});
		dao.execute(sqlList);
		dao.execute(sqlCount);
		List<College> list = sqlList.getList(College.class);
		List<College> list1 = sqlList.getList(College.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", list1.size());
		map.put("Rows", list);
		return map;
	}

	public int insertTopic(Topic topic) {
		topic.setId(UUID.randomUUID().toString());
		User user = UserUtils.getUser();
		Customer customer = dao.fetch(Customer.class, Cnd.where("loginname", "=", user.getLoginname()));
		topic.setTealoginname(user.getLoginname());
		topic.setTearealname(user.getRealname());
		topic.setCode(customer.getCode());
		dao.insert(topic);
		return 1;

	}

	public Topic fetchTopic(String id) {
		return dao.fetch(Topic.class, id);
	}

	public int deleteTopic(String id) {
		return dao.delete(Topic.class, id);
	}

	public int updateIgnoreNull(Topic record) {
		return dao.updateIgnoreNull(record);
	}

	public Major fetchMajor(String id) {
		String collegeid = dao.fetch("t_college_major", Cnd.where("majorid", "=", id)).get("collegeid").toString();
		College c = dao.fetch(College.class, collegeid);
		Major m = dao.fetch(Major.class, id);
		m.setCollege(c.getCollege());
		return m;
	}

	public int updateIgnoreNullMajor(Major record) {
		dao.update("t_college_major", Chain.make("collegeid", record.getCollege()),
				Cnd.where("majorid", "=", record.getId()));
		dao.updateIgnoreNull(record);
		return 1;
	}

	public int deleteMajor(String id) {
		String statement = "DELETE FROM t_college_major WHERE majorid = '" + id + "'";
		Sql sql = Sqls.create(statement);
		dao.execute(sql);
		return dao.delete(Major.class, id);
	}

	public int insertMajor(Major major) {
		String uuid = UUID.randomUUID().toString();
		major.setId(uuid);
		dao.insert(major);
		dao.insert("t_college_major", Chain.make("collegeid", major.getCollege()).add("majorid", uuid));
		return 1;

	}

	public List<Major> queryMajorByCollegeId(String id) {
		String statement = " SELECT * FROM t_major a LEFT JOIN t_college_major b ON a.id = b.majorid WHERE b.collegeid =  '"
				+ id + "'";
		Sql sql = Sqls.create(statement);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Major> list = new LinkedList<Major>();
				while (rs.next()) {
					Major major = new Major();
					major.setMajor(rs.getString("major"));
					major.setId(rs.getString("id"));
					list.add(major);
				}
				return list;
			}
		});
		dao.execute(sql);
		return sql.getList(Major.class);

	}

	public PicFile fetchFile(String stuloginname) {
		return dao.fetch(PicFile.class, Cnd.where("fileid", "=", stuloginname));
	}

}
