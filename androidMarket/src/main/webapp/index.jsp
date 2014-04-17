<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Android Market</title>
</head>
<body>
	<h3>Android Market</h3>
	<form action="<c:url value='/getRequest.html'/>">
		<table>
			<tr>
				<td>frequency</td>
				<td><input name="frequency" type="text" value="1"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Send get request"></td>
			</tr>
		</table>
	</form>
	<br />
	<form action="<c:url value='/postRequest.html'/>">
		<table>
			<tr>
				<td>frequency</td>
				<td><input name="frequency" type="text" value="1"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Send post request"></td>
			</tr>
		</table>
	</form>
</body>
</html>