<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<ul><li><form action="/JavaBasics03/PatientServlet" method="GET"><input type="Submit" value="Στοιχεία Χρήστη" name="requestType" /></form></li>
<li><form action="/JavaBasics03/PatientServlet" method="GET"><input type="Submit" value="Προβολή Ιστορικών Ασθενή" name="requestType" /></form></li>
<li><form action="searchbydoctor.jsp"><input type="Submit" value="Προβολή διαθέσιμων ωρών ραντεβού ανά ειδικότητα" /></form></li>
<li><form action="logout.jsp"><input type="Submit" value="Έξοδος" /></form></li>
</ul>
</body>
</html>