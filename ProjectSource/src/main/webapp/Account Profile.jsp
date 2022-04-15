<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
if (request.getParameter("accid") != null)
{
 session.setAttribute("accid", request.getParameter("accid"));
 session.setAttribute("serial", request.getParameter("serial"));
 session.setAttribute("connection_id", request.getParameter("connection_id"));
 session.setAttribute("install_date", request.getParameter("install_date"));
 session.setAttribute("register_date", request.getParameter("register_date"));
 session.setAttribute("connection_type", request.getParameter("connection_type"));
 session.setAttribute("status", request.getParameter("status"));
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account Profile</title>
</head>
<body>
	<h1>Add Account Profile</h1>
	<form>
		Account ID: <input name="accid" type="text"><br>
		Serial Number: <input name="serial" type="text"><br>
		Connection ID: <input name="connection_id" type="text"><br>
		Installed Date: <input name="install_date" type="date"><br>
		Registered Date: <input name="register_date" type="date"><br>
		Connection Type: <input name="connection_type" type="text"> <br> //need to add radio button
		Account Status:<input name="status" type="text"><br>
		<input name="btnSubmit" type="submit" value="Save">
		
	</form>

</body>
</html>