<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
	//闭包
	function f1() {
		var n = 99;
		function f2() {
			alert(n);
		}
		return f2();
	}
	f1();
</script>
</head>
<body>
	<table>

		<tr>
			<td><h4>response</h4></td>
		</tr>
		<tr>
			<td>${get}</td>
		</tr>
		<tr>
			<td>${post}</td>
		</tr>
	</table>
</body>
</html>