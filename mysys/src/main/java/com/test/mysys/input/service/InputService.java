package com.test.mysys.input.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.TimeUtil;
import com.test.mysys.goods.model.Goods;
import com.test.mysys.goods.model.PicFile;
import com.test.mysys.input.model.CargoChild;
import com.test.mysys.input.model.CargoGoodsView;
import com.test.mysys.input.model.CargoParent;

@Service
public class InputService extends BaseService {
	@Autowired
	private Dao dao;

	public Map<String, Object> queryPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		List<CargoParent> list = dao.query(CargoParent.class, cri, page);
		page.setRecordCount(dao.count(CargoParent.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public Map<String, Object> queryDetail(NewPager page, String pid) {
		Criteria cri = getCriteriaFromPage(page);
		cri.where().and("pid", "=", pid);
		List<CargoGoodsView> list = dao.query(CargoGoodsView.class, cri, page);
		page.setRecordCount(dao.count(CargoGoodsView.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	// 计算一次进货总额
	public void sumTotal(String pid, int amount, double inputprice) {

		CargoParent c = new CargoParent();
		c = fetchCargo(pid);
		double orgsum = c.getCargosum();

		double newsum = orgsum + amount * inputprice;

		c.setCargosum(newsum);

		dao.updateIgnoreNull(c);

	}

	public CargoChild fetchCargoChild(String id) {
		return dao.fetch(CargoChild.class, id);
	}

	public CargoParent fetchCargo(String id) {
		return dao.fetch(CargoParent.class, id);
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

	public CargoParent insert() {
		UUID uuid = UUID.randomUUID();
		CargoParent cargo = new CargoParent();
		cargo.setId(uuid.toString());
		cargo.setCargotime(TimeUtil.getCurrentTimestamp().toString());
		dao.insert(cargo);
		return cargo;
	}

	public CargoChild insertChild(CargoChild cargo) {
		UUID uuid = UUID.randomUUID();
		cargo.setId(uuid.toString());
		dao.insert(cargo);
		return cargo;
	}

	// 删除一次进货
	public int delete(String id) {
		Condition cnd = Cnd.wrap("pid = '" + id + "'");
		dao.clear(CargoChild.class, cnd);
		dao.delete(CargoParent.class, id);
		return 0;

	}

	// 移除某个商品
	public int remove(String id) {
		// 根据子表id查出商品并获取主表id pid
		CargoChild child = new CargoChild();
		child = fetchCargoChild(id);
		String pid = child.getPid();
		// 根据pid 查出主表信息 并获取进货总额
		CargoParent c = new CargoParent();
		c = fetchCargo(pid);
		double orgsum = c.getCargosum();
		double newsum = orgsum - child.getCargoamount() * child.getCargounitprice();
		c.setCargosum(newsum);
		dao.updateIgnoreNull(c);
		dao.delete(CargoChild.class, id);

		return 1;

	}

}
