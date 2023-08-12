<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.sql.*"
    import="java.io.IOException"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Patient Appointment History</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body style="background-color:#FFE5B4">
<h1 style="text-align:center;font-size:35px;">Προβολή ιστορικού Ασθενή</h1>
<%
try
{
Class.forName("com.mysql.jdbc.Driver");
Object user = request.getAttribute("user");
String patient = user.toString();
String histQuery="SELECT * FROM appointment INNER JOIN doctor ON appointment.Availability_doctorID = doctor.doctorID WHERE patientID = (SELECT patientID FROM patient WHERE username='"+patient+"') AND doctorID IN (SELECT Availability_doctorID FROM appointment WHERE patientID = (SELECT patientID FROM patient WHERE username='"+patient+"'))";
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","test123");
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(histQuery);
while(rs.next())
{
%>      
<table class="body2"><tr style="font-size:30px;"><td style="padding-right:25px;"> Ημερομηνία </td><td style="padding-right:25px;"> Ώρα </td><td style="padding-right:25px;"> Γιατρός </td></tr>
<tr><td><%=rs.getDate("Availability_date") %></td>
    <td><%=rs.getTime("Availability_time") %></td>
    <td><%=rs.getString("surname") %></td>
    <td><form action="/JavaBasics03/PatientOptions" method="get">
    <input type="hidden" name="patientcancel" value="<%= rs.getDate("Availability_date")%>,<%=rs.getTime("Availability_time")%>,<%=rs.getString("Availability_doctorID")%>" /><input type="Submit" value="Ακύρωση Ραντεβού" >
    </form></td></tr>
    <%} %>
</table>
<form action="epilogi.jsp"><input class="body2" type="Submit" value="Έπιστροφή" /></form>
<% rs.close();
stmt.close();
conn.close();
}
catch (Exception e){
	e.printStackTrace();
	} %>
</body>
</html>