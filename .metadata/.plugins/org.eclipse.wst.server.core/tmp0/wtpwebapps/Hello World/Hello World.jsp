<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
out.write("\r\n");
out.write("<html>\r\n");
out.write("<head><title>Hello World dynamic HTML</title></head>\r\n");
out.write("<body>\r\n");
out.write("Hello World!\r\n");
out.write('\r');
out.write('\n');

out.println("<br/>Your IP address is " + request.getRemoteAddr());

String userAgent = request.getHeader("user-agent");
String browser = "unknown";

out.print("<br/>and your browser is ");
if (userAgent != null) {
	if (userAgent.indexOf("MSIE") > -1) {
	browser = "MS Internet Explorer";
	}
	else if (userAgent.indexOf("Firefox") > -1) {
	browser = "Mozilla Firefox";
	}
	else if (userAgent.indexOf("Opera") > -1) {
	browser = "Opera";
	}
	else if (userAgent.indexOf("Chrome") > -1) {
	browser = "Google Chrome";
	}
	else if (userAgent.indexOf("Safari") > -1) {
	browser = "Apple Safari";
	}
}
out.println(browser);
out.write("\r\n");
out.write("</body>\r\n");
out.write("</html>\r\n");
%>