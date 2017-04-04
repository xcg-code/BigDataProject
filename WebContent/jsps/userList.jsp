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
Response from : ${server_addr}<br>
<table border="1">
		<tr>
			<td>ID</td><td>NAME</td><td>AGE</td><td>查看</td><td>删除</td>
		</tr>
		<c:forEach items="${userList}" var="u">
			<tr>
				<td><c:out value="${u.id}"></c:out></td>
				<td><c:out value="${u.name}"></c:out></td>
				<td><c:out value="${u.age}"></c:out></td>
				<td><a href='<c:url value="/user/viewone?id=${u.id}"/>'>查看</a></td>
				<td><a href='<c:url value="/user/deleteone?id=${u.id}"/>'>删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>