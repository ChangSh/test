package com.test.mysys.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.system.model.Role;
import com.test.mysys.system.model.RoleMenu;
import com.test.mysys.system.model.UserRole;



@Service
public class RoleService extends BaseService{
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return super.delete(Role.class,id);
	}

	public Role insert(Role record) {
		return (Role)super.insert(record);
	}

	public Role fetch(Integer id) {
		return dao.fetch(Role.class,id);
	}

	public int updateIgnoreNull(Role record) {
		return super.updateIgnoreNull(record);
	}

	public int update(Role record) {
		return super.update(record);
	}

	public List<Role> query(Condition c){
		return dao.query(Role.class,c, null);
	}
	
	public Map<String,Object> queryPage(NewPager page){
		//不查
		String str = "";
		if (!"admin".equals(UserUtils.getUser().getLoginname())){
			str = " and id not in ( select a.id from t_system_role a,t_system_user_role b,t_system_user c"
					+ " where a.id=b.roleid and b.userid=c.id and c.loginname='admin') ";
		}
		String where = " where 1=1 "+str+getStringSqlFromPage(page);
		Sql sql = Sqls.create(" SELECT * from t_system_role "+where);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql);   
		List<Record> lRecords =  (List<Record>)sql.getResult(); 
		
		Sql sql2 = Sqls.create(" SELECT count(*) from t_system_role "+where);
		sql2.setCallback(Sqls.callback.integer());
		dao.execute(sql2);   
		
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", sql2.getResult());
		map.put("Rows", lRecords);
		
	    return map;
	}
	//删除子表
	public void deleteRoleMenu(int id) {
		Role role = dao.fetch(Role.class, id);
		dao.clearLinks(role, "roleMenus");
		
	}
	//删除子表
	public void deleteRoleUsers(int id) {
		Role role = dao.fetch(Role.class, id);
		dao.clearLinks(role, "userRoles");
		
	}
	//插入子表信息
	public void insertRoleMenu(String[] menus, int id) {
		Role role = new Role();
		role.setId(id);
		List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
		for (String menuid : menus) {
			roleMenus.add(new RoleMenu(id,Integer.parseInt(menuid)));
		}
		role.setRoleMenus(roleMenus);
		dao.insertLinks(role,"roleMenus");
	}
	//插入子表信息
	public void insertRoleUsers(String[] users, int id) {
		Role role = new Role();
		role.setId(id);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (String uid : users) {
			userRoles.add(new UserRole(Integer.parseInt(uid),id));
		}
		role.setUserRoles(userRoles);
		dao.insertLinks(role,"userRoles");
	}
	public Role fetchRoleUsers(Integer id) {
		return dao.fetchLinks(dao.fetch(Role.class, id), "userRoles");
	}

	public Role fetchRoleAuthorize(Integer id) {
		return dao.fetchLinks(dao.fetch(Role.class, id), "roleAuthorizes");
	}
}
