<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="elf" uri="/elf"%>
<%@ taglib prefix="f" uri="/free"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% 

	//if (request.getSession().getAttribute("userSession")==null){
	//	response.sendRedirect("login.htm");
	//}
//out.print(request.getServletPath()); %> 