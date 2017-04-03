<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--引入标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>home.jsp</title>
</head>
<body>
	<c:if test="${error!=null}">
		<c:out value="${error}"></c:out>
	</c:if>
	<form method="post" action='<c:url value="/user/dologin"/>' >
		username:<input type="text" name="name"/><br>
		password:<input type="password" name="password"/><br>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>