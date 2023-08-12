<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.sql.*"
    import="java.io.IOException"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search Available Appointments by Specialty</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body style="background-color:#FFE5B4">
<h1 style="text-align:center;font-size:35px;">Προβολή διαθέσιμων ωρών ραντεβού ανά ειδικότητα</h1>
<%
try
{
String user = session.getAttribute("username").toString();
Class.forName("com.mysql.jdbc.Driver");
user = "SELECT patientID FROM mydb.patient WHERE username= \""+user+"\"";
String histQuery="SELECT date,time,specialty,doctorID FROM mydb.availability NATURAL JOIN mydb.doctor WHERE available=true AND availability.date>current_date() ORDER BY doctor.specialty;";
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","test123");
Statement stmt= conn.createStatement();
Statement stmt2= conn.createStatement();
ResultSet rs = stmt.executeQuery(histQuery);
ResultSet rs2 = stmt2.executeQuery(user);
if(rs2.next()){
while(rs.next())
{
%> 
<table class="body2"><tr style="font-size:30px;"><td style="padding-right:25px;"> Ημερομηνία </td><td style="padding-right:25px;"> Ώρα </td><td style="padding-right:25px;"> Γιατρός </td></tr>
<tr>    
<td><%=rs.getDate("date") %></td>
    <td><%=rs.getTime("time") %></td>
    <td><%=rs.getString("specialty") %></td>
    <td><form action="/JavaBasics03/PatientOptions" method="post">
    <input type="hidden" name="patientusername" value="<%=rs2.getString("patientID") %>,<%=rs.getDate("date") %>,<%=rs.getTime("time") %>,<%=rs.getString("doctorID") %>" /><input type="Submit" value="Κλείσιμο Ραντεβού" >
    </form></td></tr>
    <%} }%>
</table>
<form action="epilogi.jsp"><input class="body2" type="Submit" value="Έπιστροφή" /></form>
<% rs.close();
rs2.close();
stmt.close();
conn.close();
}
catch (Exception e){
	e.printStackTrace();
	} %>
</body>
</html>