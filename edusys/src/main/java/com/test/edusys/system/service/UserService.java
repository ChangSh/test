package com.test.edusys.system.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.test.edusys.common.service.BaseService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.TimeUtil;
import com.test.edusys.customer.model.Customer;
import com.test.edusys.system.model.User;
import com.test.edusys.system.model.UserRole;

@Service
public class UserService extends BaseService {

	@Autowired
	private Dao dao;
	private InputStream excelFile;

	public void create() {
		dao.create(User.class, false);
	}

	public int delete(Integer id) {
		return super.delete(User.class, id);
	}

	public User insert(User record) {
		// 首先保存用户生成id
		return dao.insert(record);
	}

	public UserRole insert(UserRole record) {
		return dao.insert(record);
	}

	// 删除子表
	public void deleteUserRole(int id) {
		User user = dao.fetch(User.class, id);
		dao.clearLinks(user, "userRoles");
	}

	// 插入子表信息
	public void insertUserRole(int[] roles, long id) {
		User user = new User();
		user.setId(id);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (int roleid : roles) {
			userRoles.add(new UserRole(id, roleid));
		}
		user.setUserRoles(userRoles);
		dao.insertLinks(user, "userRoles");

	}

	public User fetchUserRoles(Long id) {
		return dao.fetchLinks(dao.fetch(User.class, id), "userRoles");
	}

	public User fetch(Long id) {
		return dao.fetch(User.class, id);
	}

	public List<User> query(Condition c) {
		return dao.query(User.class, c, null);
	}

	public int update(User record) {
		return super.update(record);
	}

	public int updateIgnoreNull(User record) {
		return super.updateIgnoreNull(record);
	}

	public Map<String, Object> queryPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);

		List<User> list = dao.query(User.class, cri, page);
		page.setRecordCount(dao.count(User.class, cri));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	// **************************************************************************************************

	public Map<String, Object> deskCounty1(MultipartFile file1, String type, String typess) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 获得文件：
		CommonsMultipartFile cf = (CommonsMultipartFile) file1; // 这个myfile是MultipartFile的
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File newfile = fi.getStoreLocation();

		List<User> userList = new ArrayList<User>();

		// FileInputStream读取文件，字节流
		try {
			excelFile = new FileInputStream(newfile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 转换成excel
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(excelFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();

		for (int j = 2; j < rowNum + 1; j++) {
			HSSFRow row = sheet.getRow(j);// 数据的第j行
			User user = new User();
			for (int mm = 0; mm < 5; mm++) {

				Cell cell = row.getCell(mm);

				// 数据格式转换
				String cellValue = null;
				if (cell == null) {
					cellValue = "";
				} else {
					switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库

					case Cell.CELL_TYPE_NUMERIC: // 数字类型
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.toString();
						break;
					case Cell.CELL_TYPE_STRING: // 字符串型
						cellValue = cell.getStringCellValue();
						break;
					case 2: // 公式类型
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.toString();
						break;
					case Cell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 布尔型
						cellValue = cell.getStringCellValue();
						break;
					}
				}

				switch (mm) {

				case 0:
					user.setRealname(null != cellValue ? cellValue : null);
					break;
				case 1:
					user.setLoginname(null != cellValue ? cellValue : null);
					break;
				case 2:
					user.setEmail(null != cellValue ? cellValue : null);
					break;
				}

				/*
				 * if(mm = "1"){
				 * user.setLoginname(null!=cellValue?cellValue:null); }else
				 * if(mm = 2){ user.setEmail(null!=cellValue?cellValue:null);
				 * }else if(mm == 3){
				 * user.setAreaname(null!=cellValue?cellValue:null); }
				 */

				// userList.add(user);
			}
			userList.add(user);
		}

		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("userList", userList);// 用户list

		return map;

	}
	// **************************************************************************************************

	// **************************************************************************************
	@SuppressWarnings("unchecked")
	public String saveUserList(Map<String, Object> saveUserList) {
		// 区县
		List<User> userList = (List<User>) saveUserList.get("userList");

		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {

				User user = new User();
				user = userList.get(i);
				dao.insert(user);

			}

		}

		return "1";

	}
	// **************************************************************************************

	public int customer(String loginname) {
		Condition c = Cnd.wrap(" where loginname = '" + loginname + "'");
		Customer customer = dao.fetch(Customer.class, c);
		if (customer == null) {
			UUID uuid = UUID.randomUUID();
			Customer ctr = new Customer();
			ctr.setId(uuid.toString());
			ctr.setRegistration_date(TimeUtil.getCurrentTimestamp().toString());
			ctr.setLoginname(loginname);
			dao.insert(ctr);
		}
		return 0;

	}

}
