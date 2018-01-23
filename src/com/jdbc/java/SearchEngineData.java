package com.jdbc.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchEngineData 
{
	public static Connection conn; 
	public static Statement stmt; 
	public static ResultSet rset;
	
	public static void main(String[] args) 
	{		
		try 
		{
			// Step1: Loading the PostgreSQL drivers 
			Class.forName("org.postgresql.Driver");
			
			// Step2: Establish the Database Connection 
			String dbURL = "jdbc:postgresql://localhost:5433/MyDB"; 
			String user = "postgres"; 
			String pass = "postgres"; 
			conn = DriverManager.getConnection(dbURL, user, pass);

			// Step3: Defining a statement object 
			stmt = conn.createStatement();
			
			// Step4: Execute the query to retrieve the data 
			String query = "Select * from SEARCH_ENGINE"; 
			rset = stmt.executeQuery(query);
			
			// Step5: Read the data from the result set 
			while (rset.next())
			{ 
				System.out.println( "Id: " + rset.getString(1) + "\t Name: " + rset.getString(2) + "\t Url: " + rset.getString(3));
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	finally 
	{ 
		// Step6: Close all the objects 
		try { 
			rset.close(); 
			stmt.close(); 
			conn.close(); 
			}
		catch (SQLException e) 
		{ 
			e.printStackTrace(); 
		} 
		
	}
}
}

