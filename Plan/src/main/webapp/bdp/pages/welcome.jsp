<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/statics.jsp"%>
<style type="text/css">
ul,li {
		list-style: none;
		margin: 0;
		padding: 0;
}

.box {
		width: 800px;
		margin: auto;
}

.nav_link {
        margin-top: 15px;
		margin-left: 20px;
		overflow: hidden;
}

.nav_link span {
		float: left;
		line-height: 30px;
		font-size: 20px;
		font-weight: bold;
}

.nav_link li {
		float: left;
		margin-right: 25px;
		margin-left: 15px;
		border-radius: 15px;
		width: 130px;
		height: 30px;
		/* background-color: #555; */
		line-height: 30px;
		text-align: center;
}

.nav_link li a {
		color: #333;
		text-decoration: none;
		font-size: 15px;
}

.nav_link li a:hover {
		color: #2a6496;
}

.nav_link li a img{
		height: 26px;
		margin-right: 2px;
}
.main_ten {
		overflow: hidden;
		zoom: 1;
		margin-top: 30px;
}

.title {
	margin-left: 20px;
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 20px;
}

.caption {
	text-align: center;
}

.thumbnail > img {
   height: 100px;
}

html {
  position: relative;
  min-height: 100%;
}
#footer {
  /* position: absolute; */
  bottom: 0;
  width: 100%;
  height: 10px;
  margin-top: 50px;
}
</style>
</head>
<body>
<div class="nav_link">
	<ul>
		<span>快速导航：</span>
		<li>
			<a href="#" onclick="openLink('用户管理','UserInit/main.do')">
				<img src="<%=contextPath%>/resources/bdp/images/user.png">用户管理
			</a>
		</li>
		<li>
			<a href="#" onclick="openLink('角色管理','Role/main.do')">
				<img src="../../resources/bdp/images/roles.png">角色管理
			</a>
		</li>
    <li>
      <a href="#" onclick="openLink('广告位管理','AdPlace/main.do')">
        <img src="../../resources/bdp/images/roles.png">广告位管理
      </a>
    </li>
    <li>
      <a href="#" onclick="openLink('刊例价','AdPlacePrice/main.do')">
        <img src="../../resources/bdp/images/roles.png">刊例价
      </a>
    </li>
    <li>
      <a href="#" onclick="openLink('节假日折扣','HolidayDiscount/main.do')">
        <img src="../../resources/bdp/images/roles.png">节假日折扣
      </a>
    </li>
	</ul>
</div>
<script type="text/javascript">
	//打开链接
	function openLink(title,url) {
		window.parent.window.addTab(title,url);
	}
</script>
</body>
</html>

