<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head><title>Conditional Code On</title></head>
<body>
Conditional code
<%
	application.setAttribute("do_it", "");
	if (application.getAttribute("do_it") == null) {
		out.print(" not ");
	}
%>
enabled
</body>
</html>