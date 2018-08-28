<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 引入struts2的标签库 -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	跳转错误页面。。
	<br>
	<!-- fielderror标签输出action的fieldErrors属性保存的字段错误，fieldErrors是一个map类型的属性。 -->
	<s:fielderror />
	<!-- 生产一个查看debug信息的链接 -->
	<s:debug />

</body>
</html>