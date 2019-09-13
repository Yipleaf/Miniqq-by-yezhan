package Client;
import java.sql.*;

import javax.swing.text.Document;
public class test  {
public static void main(String[]args)throws SQLException,ClassNotFoundException
{	String user="root";
	String passwd="123";
	String dbURL="jdbc:mysql://localhost:3306/javabook";
	String a="leaf",b="123";
		Class.forName("com.mysql.jdbc.Driver");
		try {
		Connection c=DriverManager.getConnection(dbURL,user,passwd);
		Statement stmt=c.createStatement();
		String sql="insert INTO yzh VALUES "
				+ "('"+a+"','"+b+"')";;
		int rs=stmt.executeUpdate(sql);
		System.out.println("1");
		stmt.close();
		c.close();
		}
		catch (Exception e)
		{
			System.out.println("ดํมห");
		}
	
}
}
