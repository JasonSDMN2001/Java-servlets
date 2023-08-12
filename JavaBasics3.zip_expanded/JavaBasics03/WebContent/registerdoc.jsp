<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register a Doctor</title>
</head>
<body  style="background-color:#FFE5B4">
<% String adminuser = session.getAttribute("username").toString(); %>
<form action="/JavaBasics03/AdminServlet" method="post"> 
		<input type="hidden" name="adminusername" value="<%= adminuser %>" />
			<fieldset>							
				<table>
				<tr><td style="background-color:orange; border:1px solid orange"><h3 style="text-align:center">
				Εισάγετε τα στοιχεία του νέου Ιατρού</h3></td><td style="background-color:orange"></td></tr>
				
				<tr><td><label for="username">Username</label></td><td><input type="text" id="name1!" name="username"
				placeholder="Enter the username" maxlength="16"></td></tr> 
				
				<tr><td><label for="password">Password
				</label></td><td><input type="password" placeholder="Enter the password" id="pwd!" name="password" 
				maxlength="16"></td></tr>
				
				<tr><td><label for="firstName">First Name
				</label></td><td><input type="text" placeholder="Enter the first name" id="first!" name="firstName"
				maxlength="45"></td></tr>
				
				<tr><td><label for="surname">Surname
				</label></td><td><input type="text" placeholder="Enter the surname" id="surname!" name="surname" 
				maxlength="45"></td></tr>
				
				<tr><td><label for="address">Address
				</label></td><td><input type="text" placeholder="Enter the address" id="address!" name="address" 
				maxlength="45"></td></tr>
				
				<tr><td><label for="phoneNumber">Phone number
				</label></td><td><input type="number" placeholder="Enter the phone number" id="phone!" name="phoneNumber" 
				maxlength="10"></td></tr>
				
				<tr><td><label for="specialty">Specialty
				</label></td><td><input type="text" placeholder="Enter the specialty" id="special!" name="specialty" 
				maxlength="45"></td></tr>
				
				<tr><td><input type="submit" value="Register"></td><td></td></tr>
				</table>
			</fieldset>
		</form>
		<form action="adminEpilogi.jsp"><input type="Submit" value="Έπιστροφή" /></form>
</body>
</html>