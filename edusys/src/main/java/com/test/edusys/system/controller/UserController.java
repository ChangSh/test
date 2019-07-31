package com.test.edusys.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.edusys.college.model.Major;
import com.test.edusys.common.Const;
import com.test.edusys.common.service.MonitorRealm.Principal;
import com.test.edusys.common.utils.Encryption;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.TimeUtil;
import com.test.edusys.common.utils.UserUtils;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.customer.model.Customer;
import com.test.edusys.system.model.User;
import com.test.edusys.system.model.UserRole;
import com.test.edusys.system.service.RoleService;
import com.test.edusys.system.service.UserService;

@Controller
@RequestMapping(value = "/system/user")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	@Autowired
	private Dao dao;

	/*
	 * 用户列表
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pagesize) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search@");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrder("desc");
		pager.setOrderBy("id");
		if (StringUtils.isNotBlank(request.getParameter("deptid"))
				&& StringUtils.isNotBlank(request.getParameter("deptname"))) {
			return service.queryPage(pager);
		}

		return service.queryPage(pager);

	}

	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") long id) {
		User u = service.fetch(id);
		Customer c = service.getCustomer(u.getLoginname());
		if (c != null) {
			Major m = service.getMajor(c.getMajor());
			request.setAttribute("major", m.getMajor());
		} else {
			request.setAttribute("major", "");
		}
		request.setAttribute("ob", u);
		request.setAttribute("oc", c);

		return "views/system/userInput";
	}

	/*
	 * 本人信息
	 */
	@RequestMapping("/inputMy")
	public String input(HttpServletRequest request) {

		User u = service.fetch(UserUtils.getUser().getId());
		// request.setAttribute("ob", u);
		// List<Department> l =
		// departmentService.query(Cnd.where("id","=",u.getDeptid()));
		// if(l.size()>0){
		// u.setDeptname(l.get(0).getDeptname());
		// }
		// request.setAttribute("readonly", true);
		// return "views/system/input-user";
		return "redirect:/dblz/representinfo/view.do?id=" + u.getId();
	}

	/*
	 * 录入
	 */
	@RequestMapping("/inputChangePwd")
	public String inputChangePwd(HttpServletRequest request) {
		User u = service.fetch(UserUtils.getUser().getId());
		request.setAttribute("ob", u);
		return "views/system/input-user-changePwd";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/inputLoginName")
	public String inputChangeLoginName(HttpServletRequest request) {
		User u = service.fetch(UserUtils.getUser().getId());
		request.setAttribute("ob", u);
		return "views/system/input-user-changeLoginName";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/forgetPwd")
	@ResponseBody
	public String forgetPwd(HttpServletRequest request, @RequestParam(value = "xm", defaultValue = "") String xm,
			@RequestParam(value = "yddh", defaultValue = "") String yddh) {
		if (StringUtils.isNotBlank(xm) && StringUtils.isNotBlank(yddh)) {
			// 调手机接口，发送密码
			User u = dao.fetch(User.class, Cnd.where("xm", "=", xm).and("yddh", "=", yddh));
			if (u == null) {
				return "nouser";
			} else {
				u.setPassword(Encryption.hashToMD5(u.getLoginname()));
				dao.updateIgnoreNull(u);

				return "ok";

			}
		}
		return "fail";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/changePwd")
	@ResponseBody
	public String changePwd(User user, @RequestParam(value = "oldpassword", defaultValue = "") String oldpassword,
			HttpServletRequest request) {
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)) {
			user.setPassword(Encryption.hashToMD5(user.getPassword()));
			service.updateIgnoreNull(user);
			// 重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User) currentUser.getSession().getAttribute("currentUser");
			user_current.setPassword(user.getPassword());
		} else {
			return "false";
		}
		return "true";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/changeLoginName")
	@ResponseBody
	public String changeLoginName(User user, @RequestParam(value = "oldpassword", defaultValue = "") String oldpassword,
			HttpServletRequest request) {
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)) {
			service.updateIgnoreNull(user);
			// 重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User) currentUser.getSession().getAttribute("currentUser");
			user_current.setLoginname(user.getLoginname());
		} else {
			return "fail";
		}
		return "ok";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/checkRepeak-loginname")
	@ResponseBody
	public boolean checkRepeakByLoginname(@RequestParam(value = "loginname") String loginname,
			HttpServletRequest request) {

		// 如果是修改页的话，不修改，点保存不查重
		if (loginname.equals(request.getParameter("oldloginname"))) {
			return true;
		}
		List<User> list = service.query(Cnd.where("loginname", "=", loginname));
		if (!list.isEmpty())
			return false;

		return true;
	}

	@RequestMapping("/register")
	@ResponseBody
	public boolean register(User user, HttpServletRequest request) {
		user.setPassword(Encryption.hashToMD5(user.getPassword()));
		user.setRealname(user.getLoginname());
		user.setCjsj(TimeUtil.getCurrentTimestamp());
		User u = service.insert(user);
		long id = u.getId();
		UserRole ur = new UserRole();
		ur.setRoleid(4);
		ur.setUserid(id);
		service.customer(user.getLoginname());
		service.insert(ur);
		return true;

	}

	/*
	 * 录入
	 */
	@RequestMapping("/checkRepeak-loginname-id")
	@ResponseBody
	public String checkRepeakById(@RequestParam(value = "loginname") String loginname,
			@RequestParam(value = "id") Long id, HttpServletRequest request) {
		List<User> list = service.query(Cnd.where("loginname", "=", loginname).and("id", "!=", id));
		if (list.size() > 0) {
			return "true";
		} else {
			return "false";
		}
	}

	/*
	 * 录入
	 */
	@RequestMapping("/changeUser")
	public String changeUser(User user, @RequestParam(value = "oldpassword", defaultValue = "") String oldpassword,
			HttpServletRequest request) {
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)) {
			user.setPassword(Encryption.hashToMD5(user.getPassword()));
			service.updateIgnoreNull(user);
			// 重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User) currentUser.getSession().getAttribute("currentUser");
			user_current.setLoginname(user.getLoginname());
			user_current.setPassword(user.getPassword());
			// currentUser.getSession().setAttribute("currentUser", user);
		} else {
			return "redirect:/views/common/login.jsp";
		}
		return "redirect:/main.jsp";
	}

	/*
	 * 录入
	 */
	@RequestMapping("/inputRole")
	public String inputRole(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") long id) {
		request.setAttribute("ob", service.fetchUserRoles(id));

		request.setAttribute("roles", roleService.query(null));
		return "views/system/input-user-role";
	}

	/*
	 * 保存用户的角色
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") int id,
			@RequestParam(value = "role") int[] roles) {
		// 先删除后插入
		service.deleteUserRole(id);
		service.insertUserRole(roles, id);

		return "ok";
	}

	@RequestMapping("loginindex")
	public String loginindex(HttpServletRequest request) {
		return "views/common/login";
	}

	/*
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam(value = "loginname") String ln,
			@RequestParam(value = "password", defaultValue = "") String pw, HttpServletRequest request,
			RedirectAttributes attr) {

		String yzcode = request.getParameter("code");
		if (!(yzcode.equalsIgnoreCase(request.getSession().getAttribute("code").toString()))) {
			return "codeFail";
		}
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(ln, Encryption.hashToMD5(pw));
		// token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "error";
		}
		if (currentUser.isAuthenticated()) {
			Principal principal = (Principal) currentUser.getPrincipal();
			Condition c = Cnd.where("loginname", "=", principal.getloginname());
			List<User> users = service.query(c);
			currentUser.getSession().setAttribute("currentUser", service.fetchUserRoles(users.get(0).getId()));
			currentUser.getSession().setAttribute("currentUserId", users.get(0).getId());
			return "success";
		} else {
			return "loginFail";
		}

	}

	/*
	 * 登录
	 */
	@RequestMapping("/loginFront")
	@ResponseBody
	public String loginFront(@RequestParam(value = "loginname") String ln,
			@RequestParam(value = "password", defaultValue = "") String pw, HttpServletRequest request,
			RedirectAttributes attr) {

		String yzcode = request.getParameter("code");
		if (!(yzcode.equalsIgnoreCase(request.getSession().getAttribute("code").toString()))) {
			return "codeFail";
		}
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(ln, Encryption.hashToMD5(pw));
		// token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "loginFail";
		}
		if (currentUser.isAuthenticated() && (currentUser.hasRole("普通用户"))) {
			Principal principal = (Principal) currentUser.getPrincipal();
			Condition c = Cnd.where("loginname", "=", principal.getloginname());
			List<User> users = service.query(c);
			currentUser.getSession().setAttribute("currentUser", service.fetchUserRoles(users.get(0).getId()));
			currentUser.getSession().setAttribute("currentUserId", users.get(0).getId());
			return "ok";
		}

		else {
			request.setAttribute("message", "登录失败：用户没有权限");
			System.out.println(request.getAttribute("message"));
		}
		return "loginFail";
	}

	/*
	 * 登录
	 */
	@RequestMapping("/quicklogin")
	public String quicklogin(@RequestParam(value = "loginname") String ln,
			@RequestParam(value = "password", defaultValue = "") String pw, HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(ln, Encryption.hashToMD5(pw));
		// token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			request.setAttribute("message", "登录失败：用户名或密码输入错误！");
			e.printStackTrace();
			return "redirect:/index.jsp";
		}
		if (currentUser.isAuthenticated()) {
			Principal principal = (Principal) currentUser.getPrincipal();
			Condition c = Cnd.where("loginname", "=", principal.getloginname());
			List<User> users = service.query(c);
			currentUser.getSession().setAttribute("currentUser", service.fetchUserRoles(users.get(0).getId()));
			currentUser.getSession().setAttribute("currentUserId", users.get(0).getId());
			// if(users.get(0).getPassword().equals(Encryption.hashToMD5(users.get(0).getLoginname()))){
			// return "redirect:/repassword.jsp";//修改密码
			// }else{
			// return "redirect:/index.jsp";
			// }
			return "redirect:/main.jsp";

		} else {
			request.setAttribute("message", "登录失败：用户没有权限");
		}
		return "redirect:/index.jsp";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return "redirect:/views/system/login.jsp";
	}

	@RequestMapping("/frontlogout")
	public String frontLogout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return "redirect:/views/front/index.jsp";
	}

	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(User user, HttpServletRequest request, String college, String major,
			@RequestParam(value = "sf", defaultValue = "") String[] sf) {
		StringBuffer representflag = new StringBuffer();
		// 先置入用户身份
		for (String s : sf) {
			representflag.append(s + ";");
		}
		Customer customer = new Customer();
		customer.setLoginname(user.getLoginname());
		customer.setCollege(college);
		customer.setMajor(major);
		customer.setName(user.getRealname());
		customer.setPhone(user.getPhone());
		user.setPassword(Encryption.hashToMD5(Const.DEFAULT_PASSWORD));// 初始默认密码

		if (user.getId() == null) {
			customer.setRegistration_date(TimeUtil.getCurrentTimestamp().toString());
			customer.setCode(TimeUtil.getCurYear() + UUID.randomUUID().toString().substring(0, 4));
			user.setCjsj(TimeUtil.getCurrentTimestamp());
			customer.setId(UUID.randomUUID().toString());
			service.insert(user, customer);
		} else {
			user.setXgsj(TimeUtil.getCurrentTimestamp());
			service.updateIgnoreNull(user, customer);
		}
		return "ok";
	}

	/*
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") int id) {
		service.delete(id);
		return "ok";
	}

	// *************************************************************************

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/import1")
	@ResponseBody
	public String userBatchImport(HttpServletRequest request, HttpServletResponse response, String type, String typess)
			throws Exception {

		// Map<String,Object> errmap=new HashMap<String,Object>();
		Map<String, Object> saveUserList = new HashMap<String, Object>();
		List<User> userList = new ArrayList<User>();

		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file1 = multipartRequest.getFile("file1");
		long fileLong1 = 0;
		if (file1 != null) {
			fileLong1 = file1.getSize();
		}

		// 用戶信息批量
		if (file1 != null && fileLong1 != 0) {
			Map<String, Object> map = service.deskCounty1(file1, type, typess);

			userList = (List<User>) map.get("userList");

			saveUserList.put("userList", userList);

			service.saveUserList(saveUserList);
		}
		return "ok!";// 此处后面要加返回页，与其他上传返回保持一致
	}

	/*
	 * 重置密码
	 */
	@RequestMapping("/reset")
	@ResponseBody
	public String resetpwd(HttpServletRequest request, Long id) {
		User user = service.fetch(id);
		if (user != null) {
			user.setPassword(Encryption.hashToMD5("1"));// 初始默认密码都是1 重置为初始密码、
			service.updateIgnoreNull(user);
		}
		return "ok";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/updatepsw")
	@ResponseBody
	public Map<String, Object> updatepsw(HttpServletRequest request, String rpsw) {

		User user = UserUtils.getUser();
		Long id = user.getId();
		user.setPassword(Encryption.hashToMD5(rpsw));
		service.updateIgnoreNull(user);

		return null;

	}
	// *************************************************************************

}
