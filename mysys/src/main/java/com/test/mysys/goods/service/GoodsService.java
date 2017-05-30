package com.test.mysys.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.TimeUtil;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.goods.model.BuyCart;
import com.test.mysys.goods.model.Goods;
import com.test.mysys.goods.model.Msg;
import com.test.mysys.goods.model.Order;
import com.test.mysys.goods.model.PicFile;
import com.test.mysys.system.model.Code;
import com.test.mysys.system.model.User;

@Service
public class GoodsService extends BaseService {
	@Autowired
	private Dao dao;

	public Map<String, Object> queryPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		List<Goods> list = dao.query(Goods.class, cri, page);
		page.setRecordCount(dao.count(Goods.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public Goods fetch(String id) {
		return dao.fetch(Goods.class, id);
	}

	public PicFile getPath(String fileId) {
		Condition c = Cnd.wrap(" where fileid = " + "'" + fileId + "'");
		return dao.fetch(PicFile.class, c);
	}

	public int updateIgnoreNull(Goods record) {
		return dao.updateIgnoreNull(record);
	}

	public int updatePic(PicFile p) {
		return dao.updateIgnoreNull(p);
	}

	public void insert(Goods record, String filePath) {
		UUID uuid = UUID.randomUUID();
		PicFile pic = new PicFile();
		record.setId(uuid.toString());
		UUID picuuid = UUID.randomUUID();
		User u = UserUtils.getUser();
		record.setLoginname(u.getLoginname());
		pic.setId(picuuid.toString());
		pic.setFileid(uuid.toString());
		pic.setFilepath(filePath);
		dao.insert(record);
		dao.insert(pic);
	}

	public int delete(String id) {
		return dao.delete(Goods.class, id);

	}

	public String msg(String m) {
		Msg msg = new Msg();
		msg.setMsg(m);
		msg.setLoginname(UserUtils.getUser().getLoginname());
		UUID uuid = UUID.randomUUID();
		msg.setId(uuid.toString());
		msg.setMsgTime(TimeUtil.getCurrentTimestamp().toString());
		dao.insert(msg);
		return "ok";
	}

	public String fenlei(Code code) {

		code.getCodename();
		code.setSectionname("分类");
		code.setCode("0" + String.valueOf(Math.floor(Math.random() * 100)));
		dao.insert(code);
		return "ok";
	}

	public String jiesuan(List<Order> l, String[] cartId) {
		for (int i = 0; i < cartId.length; i++) {
			BuyCart buyCart = dao.fetch(BuyCart.class, cartId[i]);
			buyCart.setIssettle(1);
			dao.updateIgnoreNull(buyCart);
		}
		dao.insert(l);
		return "ok";

	}

	public String addCart(String id) {
		Condition c = Cnd.wrap(" where gid = " + "'" + id + "' AND loginname ='" + UserUtils.getUser().getLoginname()
				+ "' AND issettle=-1");
		BuyCart rs = dao.fetch(BuyCart.class, c);
		if (rs != null) {
			return "ok";
		} else {
			BuyCart cart = new BuyCart();
			cart.setGid(id);
			cart.setIssettle(0);
			cart.setLoginname(UserUtils.getUser().getLoginname());
			cart.setId(UUID.randomUUID().toString());
			dao.insert(cart);
			return "ok";
		}
	}

	public int removeCart(String id) {
		return dao.delete(BuyCart.class, id);

	}

	public String change(String id) {
		Order o = dao.fetch(Order.class, id);
		int statu = o.getStatu();
		if (statu == -1) {
			o.setStatu(-2);
		} else if (statu == -2) {

			o.setStatu(-3);
		} else if (statu == -3) {
			o.setStatu(3);
		}

		dao.updateIgnoreNull(o);
		return "ok";

	}

	public int updateStatus(String id, int status) {
		Goods g = dao.fetch(Goods.class, id);
		g.setStatus(status);
		dao.update(Goods.class, Chain.make("status", status), Cnd.where("id", "=", id));
		return 1;
	}
}
