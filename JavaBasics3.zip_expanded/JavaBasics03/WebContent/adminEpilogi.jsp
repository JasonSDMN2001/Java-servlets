<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body class="welcome">
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.

if(session.getAttribute("username")==null)
{
	response.sendRedirect("patientlogin.jsp");
}
%>
<h3 >Καλώς Ήρθατε</h3>
<!-- <ul><li><form action="/JavaBasics03/AdminServlet" method="GET"><input type="Submit" value="Δημιουργία Ιατρού" name="requestType" /></form></li> -->
<ul><li><form action="registerdoc.jsp"><input type="Submit" value="Δημιουργία Ιατρού" /></form></li>
<li><form action="diagrafigiatrou.jsp"><input type="Submit" value="Διαγραφή Ιατρού"/></form></li>
<li><form action="logout.jsp"><input type="Submit" value="Έξοδος" /></form></li>
</ul>
</body>
</html>