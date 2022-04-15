package model;
import java.sql.*;

public class Customers {
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
	
	public String insertCustomers(int cid,String fname, String lname, String contact_number, String address, String cus_email, String cus_status)
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
			String query = "insert into customers('fname','lname', 'contact_number','address', 'cus_email', 'cus_status') "
					+ "values( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			//need to modify the data types
			preparedStmt.setString(1, fname);
			preparedStmt.setInt(2, lname);
			preparedStmt.setInt(3, contact_number);
			preparedStmt.setInt(4, address);
			preparedStmt.setInt(5, cus_email);
			preparedStmt.setInt(6,cus_status);
			
			preparedStmt.execute();
			con.close();
			
			output = "Values Inserted Successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the customers";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	public String readCustomers()
	{
		String output ="";
		
		try 
		{
			Connection con = connect();
			
			if(con==null)
			{
				return "Error while connecting to the database for reading";
			}
			
			//prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer ID</th><th>First Name</th><th>Last Name</th>" +
					 "<th>Contact Number</th>" +
					 "<th>Address</th><th>Email</th>" +
					 "<th>Status</th>"
					 + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from Customers";
			Statement stat = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			 while (rs.next())
			 {
			 String cid = Integer.toString(rs.getInt("cid"));
			 String fname = rs.getString("fname");
			 String lname = rs.getString("lname");
			 String contact_number = rs.getString("contact_number");
			 String address = rs.getString("address");
			 String cus_email = rs.getString("cus_email")
			 String cus_status = rs.getString("cus_status");
			
			 
			 // Add into the html table
			 output += "<tr><td>" + cid + "</td>";
			 output += "<td>" + fname + "</td>";
			 output += "<td>" + lname + "</td>";
			 output += "<td>" + contact_number + "</td>";
			 output += "<td>" + address + "</td>";
			 output += "<td>" + cus_email + "</td>";
			 output += "<td>" + cus_status + "</td>";
			 
			 
			// buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'
			 class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'
			 class='btn btn-danger'>"
			 + "<input name='cid' type='hidden' value='" + cid
			 + "'>" + "</form></td></tr>";
		}
		con.close();
		
		//complete the html table
		output += "</table>"
		
	}
	catch (Exception e) 
	{
		output = "Error while reading the customers";
		System.err.println(e.getMessage());
	}
	return output;
	
   }
	public String updateCustomers(int cid,String fname,String lname, String contact_number, String address , String cus_email, String cus_status)
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
			String query = "UPDATE employees SET fname=?,lname=?,contact_number=?,cus_email=?,cus_status=?"
			+ "wHERE cid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			//need to modify he daata types
			
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, contact_number);
			preparedStmt.setString(4, address);
			preparedStmt.setInt(5, cus_email);
			preparedStmt.setString(6, cus_status);
			//preparedStmt.setInt(7, eid);
			
			preparedStmt.execute();
			con.close();
			
			output = "Values Updated Successfully";
		}
		catch (Exception e) {
			output = "Error while updating the customer";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	public String deleteCustomers(String eid)
	{
		String output ="";
		
		try
		{
			Connection con = connect();
			
			if(con ==null)
			{
				return "Error while connecting to the database for deleting";
			}
			
			String query = "delete from customers where cid=?";
			
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
			 output = "Error while deleting the customer.";
			 System.err.println(e.getMessage());
			 }
		

		return output;
     }
		
}
	 


}
