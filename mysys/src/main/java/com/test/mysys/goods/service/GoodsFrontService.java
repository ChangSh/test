package com.test.mysys.goods.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.goods.model.BuyCart;
import com.test.mysys.goods.model.CartGoods;
import com.test.mysys.goods.model.GoodsDetailVo;
import com.test.mysys.goods.model.Order;
import com.test.mysys.system.model.Code;
import com.test.mysys.system.model.User;

@Service
public class GoodsFrontService extends BaseService {
	@Autowired
	private Dao dao;

	public List<GoodsDetailVo> findFocus() {
		String str = " SELECT t_goods.id,t_goods.gname,t_file.filepath FROM t_goods LEFT JOIN t_file ON t_goods.id=t_file.fileid WHERE gisfocus=1 and status=1";
		Sql sql = Sqls.create(str);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {

				List<GoodsDetailVo> list = new ArrayList<GoodsDetailVo>();
				while (rs.next()) {
					GoodsDetailVo v = new GoodsDetailVo();
					v.setId(rs.getString("id"));
					v.setGname(rs.getString("gname"));
					v.setFilepath(rs.getString("filepath"));
					list.add(v);
				}
				return list;
			}

		});
		dao.execute(sql);
		List<GoodsDetailVo> list = sql.getObject(null);
		return list;

	}

	public Map<String, Object> fenlei_Index(NewPager page, String code) {
		Criteria cri = getCriteriaFromPage(page);
		cri.where().and("gtype", "=", code);
		List<GoodsDetailVo> list = dao.query(GoodsDetailVo.class, cri, page);
		page.setRecordCount(dao.count(GoodsDetailVo.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public List<GoodsDetailVo> findCart() {
		String str = " SELECT * FROM (SELECT * FROM t_buycart WHERE loginname= '" + UserUtils.getUser().getLoginname()
				+ "' AND issettle=0) t LEFT JOIN t_goods ON t.gid=t_goods.id LEFT JOIN t_file ON t.gid=t_file.fileid ";
		Sql sql = Sqls.create(str);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {

				List<GoodsDetailVo> list = new ArrayList<GoodsDetailVo>();
				while (rs.next()) {
					GoodsDetailVo v = new GoodsDetailVo();
					v.setId(rs.getString("gid"));
					v.setCartId(rs.getString("id"));
					v.setGname(rs.getString("gname"));
					v.setFilepath(rs.getString("filepath"));
					v.setGunitprice(rs.getString("gunitprice"));
					list.add(v);
				}
				return list;
			}

		});
		dao.execute(sql);
		List<GoodsDetailVo> list = sql.getObject(null);
		return list;
	}

	public List<GoodsDetailVo> findOrder() {
		String str = " SELECT * FROM t_order  LEFT JOIN t_goods ON t_order.gid=t_goods.id LEFT JOIN t_file ON t_order.gid=t_file.fileid WHERE loginname= '"
				+ UserUtils.getUser().getLoginname() + "'";
		Sql sql = Sqls.create(str);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {

				List<Order> list = new ArrayList<Order>();
				while (rs.next()) {
					Order v = new Order();
					v.setId(rs.getString("id"));
					v.setLoginname(rs.getString("loginname"));
					v.setGid(rs.getString("gid"));
					v.setOrdercount(rs.getInt("ordercount"));
					v.setOrdernum(rs.getString("ordernum"));
					v.setUnitprice(rs.getDouble("unitprice"));
					v.setStatu(rs.getInt("statu"));
					v.setTotalprice(rs.getDouble("totalprice"));
					v.setGname(rs.getString("gname"));
					v.setFilepath(rs.getString("filepath"));
					list.add(v);
				}
				return list;
			}

		});
		dao.execute(sql);
		List<GoodsDetailVo> list = sql.getObject(null);
		return list;
	}

	public Map<String, Object> findIndex(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		List<GoodsDetailVo> list = dao.query(GoodsDetailVo.class, cri, page);
		page.setRecordCount(dao.count(GoodsDetailVo.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public GoodsDetailVo fetchDetail(String goodsid) {
		Condition c = Cnd.wrap(" where id = " + "'" + goodsid + "'");
		return dao.fetch(GoodsDetailVo.class, c);

	}

	public BuyCart cartIsSettle() {
		User user = UserUtils.getUser();
		String loginname = user.getLoginname();
		Condition c = Cnd.wrap(" where loginname = " + "'" + loginname + "' AND issettle = 1 ");
		return dao.fetch(BuyCart.class, c);

	}

	public int insertCart(CartGoods cartgoods) {
		UUID uuid = UUID.randomUUID();
		cartgoods.setId(uuid.toString());
		dao.insert(cartgoods);

		return 1;
	}

	public int updateIgnoreNull(BuyCart bc) {
		return dao.updateIgnoreNull(bc);
	}

	public List<GoodsDetailVo> guess() {
		Condition c = Cnd.wrap(" WHERE 1=1  ORDER BY gclick  DESC LIMIT 0,4 ");
		return dao.query(GoodsDetailVo.class, c);

	}

	public GoodsDetailVo find(String content) {
		Condition cnd = Cnd.wrap(" gname like '%" + content + "%'");
		return dao.fetch(GoodsDetailVo.class, cnd);
	}

	public List<Code> fenleiList() {
		Condition cnd = Cnd.wrap(" sectionname = '分类' ");
		return dao.query(Code.class, cnd);
	}
}
