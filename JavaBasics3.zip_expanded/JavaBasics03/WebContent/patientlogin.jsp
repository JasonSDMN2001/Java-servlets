<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<title>Login</title>
			<style>
				table{					
					width:80%
				}
			</style>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body class="body1">
		<form action="/JavaBasics03/PatientServlet" method="post"> 
		<input type="hidden" name="requestType" value="login" />
			<fieldset>							
				<table>
				<tr><td style="background-color:orange;"><h3 style="text-align:center">
				Φόρμα Συνδεσης</h3></td><td style="background-color:orange"></td></tr>
				
				<tr><td><label for="name">Username</label></td><td><input type="text" id="name1!" name="name"
				placeholder="Enter your username" maxlength="16"></td></tr> 
				
				<tr><td><label for="pwd">Password
				</label></td><td><input type="password" placeholder="Enter your password" id="pwd!" name="pwd" 
				maxlength="16"></td></tr>
				
				<td><input type="submit" value="Login"></td>
				</table>
			</fieldset>
		</form>
</body>
</html>