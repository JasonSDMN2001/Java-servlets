<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Λειτουργίες Διαχειριστή</title>
</head>
<body class="body1">
<h2>Διαγραφή Ιατρού</h2>
<form method="get" action="/JavaBasics03/AdminServlet">
<br>doctorID :<input type="number" name="doctorID">
<br><input type="submit" value="Delete">
</form>
<br><form action="adminEpilogi.jsp"><input type="Submit" value="Έπιστροφή" /></form>
</body>
</html>