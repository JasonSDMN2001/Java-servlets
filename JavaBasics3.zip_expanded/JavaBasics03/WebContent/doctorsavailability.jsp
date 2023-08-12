<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Προσθήκη διαθέσιμων ωρών</title>
</head>
<body style="background-color:#FFE5B4">
<h3>Προσθέστε διαθέσιμες ώρες και ημερομηνίες για ραντεβού</h3>
<% String doctoruser = session.getAttribute("username").toString(); %>
<form action="/JavaBasics03/DoctorServlet" method="post"> 
	<input type="hidden" name="doctorusername" value="<%= doctoruser %>" />
	<fieldset>							
		<table class="body2">
		<tr style="font-size:14px;"><td style="padding-right:25px;"> Ημερομηνία </td><td style="padding-right:25px;"> Ώρα </td></tr>
		<tr style="font-size:14px;"><td style="padding-right:25px;"><input type="text" name="date" placeholder="e.g 2021-10-11"></td>
		<td style="padding-right:25px;"><input type="text" name="time" placeholder="e.g 13:30:00"></td>
		<td style="padding-right:25px;"><input type="Submit" value="Προσθήκη" /></td></tr></table>
	</fieldset>
</form>
<form action="doctorEpilogi.jsp"><input class="body2" type="Submit" value="Έπιστροφή" /></form>
</body>
</html>