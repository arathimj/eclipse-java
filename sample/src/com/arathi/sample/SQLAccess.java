package com.arathi.sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class SQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	final private String host = "localhost:3306";
	final private String user = "root";
	final private String passwd = "destinyplays1!";
	
	public void createNewRow() throws Exception 
	{
	    try 
	    {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/Student?" + "user=" + user + "&password=" + passwd );
	      preparedStatement = connect.prepareStatement("insert into  StudentTable values (default, ?, ?, ?)");
	      String LastName="Elen";
	      preparedStatement.setString(2, LastName);
	      String FirstName="Jennifer";
	      preparedStatement.setString(1, FirstName);
	      String Sex="F";
	      preparedStatement.setString(3, Sex);
	      preparedStatement.executeUpdate();
	      readDataBase();
	    }
	    catch (Exception e)
	    {
	    	
	    }
	}
	public void deleteRow() throws Exception 
	{
	    try 
	    {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/Student?" + "user=" + user + "&password=" + passwd );
	      preparedStatement = connect.prepareStatement("delete from StudentTable where StudentID= ? ; ");
	      Integer StudentID=3;
	      preparedStatement.setInt(1, StudentID);
	      preparedStatement.executeUpdate();
	      System.out.println("Deleted!");
	    }
	    catch (Exception e) 
	    {
	      throw e;
	    } 
	    finally 
	    {
	      close();
	    }
	}
	public void readDataBase() throws Exception 
	{
	    try 
	    {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/Student?" + "user=" + user + "&password=" + passwd );
	      statement = connect.createStatement();
	      resultSet = statement.executeQuery("select * from StudentTable");
	      writeResultSet(resultSet);
	    } 
	    catch (Exception e) 
	    {
	      throw e;
	    } 
	    finally 
	    {
	      close();
	    }
	}
	private void writeResultSet(ResultSet resultSet) throws Exception 
	{
	    while (resultSet.next()) 
	    {
	      String StudentID = resultSet.getString("StudentID");
	      String LastName = resultSet.getString("LastName");
	      String FirstName = resultSet.getString("FirstName");
	      String Sex = resultSet.getString("Sex");
	      
	      System.out.println("Student ID: " + StudentID);
	      System.out.println("Last Name: " + LastName);
	      System.out.println("First Name: " + FirstName);
	      System.out.println("Sex: " + Sex);
	    }
	}
	private void close() 
	{
	    try 
	    {
	      if (resultSet != null) 
	      {
	        resultSet.close();
	      }

	      if (statement != null) 
	      {
	        statement.close();
	      }

	      if (connect != null) 
	      {
	        connect.close();
	      }
	    } 
	    catch (Exception e) 
	    {

	    }
	}
}
