package model;
import java.sql.*;

public class Employees{
	private Connection connect()
	{
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, user name, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");	
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertEmployees(int eid,String ename, String mobile, String email, String emp_username, String emp_password)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting";
			
			}
			
			//create a prepared statement
			String query = "insert into employees('ename', 'mobile', 'email', 'status', 'emp_username', 'emp_password') "
					+ "values( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			//need to modify the data types
			preparedStmt.setString(1, ename);
			preparedStmt.setInt(2, mobile);
			preparedStmt.setInt(3, email);
			preparedStmt.setInt(4, status);
			preparedStmt.setInt(5, emp_username);
			preparedStmt.setInt(6,emp_password);
			
			preparedStmt.execute();
			con.close();
			
			output = "Values Inserted Successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the employee";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	public String readEmployees()
	{
		String output ="";
		
		try 
		{
			Connection con = connect();
			
			if(con==null)
			{
				return "Error while connecting tot he database for reading";
			}
			
			//prepare the html table to be displayed
			output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th>" +
					 "<th>Mobile</th>" +
					 "<th>Email</th>" +
					 "<th>Status</th><th>User Name</th>"
					 + "<th> Password </th>"
					 + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from Employees";
			Statement stat = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			 while (rs.next())
			 {
			 String eid = Integer.toString(rs.getInt("eid"));
			 String ename = rs.getString("ename");
			 String mobile = rs.getString("mobile");
			 String email = rs.getString("email")
			 String status = rs.getString("status");
			 String emp_username = rs.getString("emp_username");
			 String emp_password = rs.getString("emp_password");
			 
			 // Add into the html table
			 output += "<tr><td>" + eid + "</td>";
			 output += "<td>" + ename + "</td>";
			 output += "<td>" + mobile + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<td>" + status + "</td>";
			 output += "<td>" + emp_username + "</td>";
			 output += "<td>" + emp_password + "</td>";
			 
			// buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'
			 class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'
			 class='btn btn-danger'>"
			 + "<input name='eid' type='hidden' value='" + eid
			 + "'>" + "</form></td></tr>";
		}
		con.close();
		
		//complete the html table
		output += "</table>"
		
	}
	catch (Exception e) 
	{
		output = "Error while reading the employees";
		System.err.println(e.getMessage());
	}
	return output;
	
   }
	public String updateEmployees(int eid,String ename, String mobile, String email,String status, String emp_username, String emp_password)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating";
			
			}
			
			//create a prepared statement
			String query = "UPDATE employees SET ename=?,mobile=?,email=?,status=?"
					+ "emp_username=?, emp_password=?"
					+ "wHERE eid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			//need to modify he data types
			
			preparedStmt.setString(1, ename);
			preparedStmt.setString(2, mobile);
			preparedStmt.setString(3, email);
			preparedStmt.setInt(4, status);
			preparedStmt.setString(5, emp_username);
			preparedStmt.setString(6,emp_password);
			//preparedStmt.setInt(7, eid);
			
			preparedStmt.execute();
			con.close();
			
			output = "Values Updated Successfully";
		}
		catch (Exception e) {
			output = "Error while updating the employee";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	public String deleteEmployees(String eid)
	{
		String output ="";
		
		try
		{
			Connection con = connect();
			
			if(con ==null)
			{
				return "Error while connecting to the database for deleting";
			}
			
			String query = "delete from employees where eid=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setInt(1, Integer.parseInt(eid));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the employee.";
			 System.err.println(e.getMessage());
			 }
		

		return output;
     }
		
}
	 
