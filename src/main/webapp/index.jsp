<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iCloud云网盘</title>
</head>
<body>
  <div>
      <c:choose>
          <c:when test="${user_name!=null}">
              <a href="" >${user_name}</a>  &nbsp;
              <a href="${pageContext.request.contextPath}/requestout.action" >log out</a> &nbsp;
          </c:when>
          <c:otherwise>
              <a href="${pageContext.request.contextPath}/requestin.action" >log in</a>  &nbsp;
              <a href="${pageContext.request.contextPath}/requestup.action" >log up</a>  &nbsp;
          </c:otherwise>
      </c:choose>

       <a href="${pageContext.request.contextPath}/autologin.action?user_name=${user_name}" >我的主页</a>&nbsp;
       <a href="${pageContext.request.contextPath}/help.jsp">帮助</a> 
  </div> 
  <center>
        <div style="font-size: 25px ; padding-right: 80px ;padding-top: 200px">Log in to upload files to iCloud !</div>
	   <form action="${pageContext.request.contextPath}/searchfile.action" method="post"  style="margin: 10px">
	     <input type="text" name="searchcontent" maxlength="50" size="40" style="font-size: 20px;padding-left:3px; padding-top: 5px; padding-bottom: 3px; text-shadow: blue;" >
	     <input type="submit" style=" font-size: 24px;cursor: pointer" value="Search">
	   </form>
  </center>
</body>
</html>