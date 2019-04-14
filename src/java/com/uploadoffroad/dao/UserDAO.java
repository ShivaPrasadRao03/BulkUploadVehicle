package com.uploadoffroad.dao;
import com.uploadoffroad.dbconnection.CreateConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO
{
  
	public boolean validate(String accountID,String username,String password)
	{ 
              System.out.println("UserDao");
		boolean flag=false;
		try
		{
                    CreateConnection con = new CreateConnection();
                    
                        
			Statement st=con.getStatement();
			ResultSet rs=st.executeQuery("select * from registration where accountID=\'"+ accountID+"\' and  userID=\'"+username+"\'  and password=\'"+password+"\'");
			                 
                        flag= rs.next();
                        System.out.println("Flag"+flag);
                         
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;    
	    }
	
}
