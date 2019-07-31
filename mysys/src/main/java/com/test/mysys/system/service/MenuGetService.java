package com.test.mysys.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.sql.SqlTemplate;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.system.model.Menu;

@Service
public class MenuGetService {
	@Autowired
	private Dao dao;
	@Autowired
	private SqlTemplate sqlTemplate;

	public List<Menu> QuerymenuGenbasicuserid(String userid) {
		Sql sql = Sqls
				.create("select * from  t_system_user_role a  left join  t_system_role_menu b on a.roleid=b.roleid left join t_system_menu c on b.menuid=c.id where a.userid='"
						+ userid + "' and c.pid='0' order by c.sx asc ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Menu> list = new LinkedList<Menu>();
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenuname(rs.getString("menuname"));
					menu.setUrl(rs.getString("url"));
					menu.setId(rs.getLong("c.id"));
					list.add(menu);

				}
				return list;
			}
		});

		dao.execute(sql);
		return sql.getList(Menu.class);

	}

	public List<Menu> QuerymenuGetChild(String userid, long fatherid) {
		Sql sql = Sqls
				.create("select * from  t_system_user_role a  left join  t_system_role_menu b on a.roleid=b.roleid left join t_system_menu c on b.menuid=c.id where a.userid='"
						+ userid + "' and c.pid='" + fatherid + "' order by c.sx asc ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Menu> list = new LinkedList<Menu>();
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenuname(rs.getString("menuname"));
					menu.setUrl(rs.getString("url"));
					menu.setId(rs.getLong("c.id"));
					list.add(menu);

				}
				return list;
			}
		});

		dao.execute(sql);
		return sql.getList(Menu.class);

	}

}
