package Client;
import java.sql.*;
import java.util.List;
public class QQDB {
	String user="root";
	String passwd="123";
	String dbURL="jdbc:mysql://localhost:3306/javabook?useSSL=false";
	Connection c;
	Statement stmt;
	public void setvalue() throws SQLException,ClassNotFoundException  {
		Class.forName("com.mysql.jdbc.Driver");
		try {
		c=DriverManager.getConnection(dbURL,user,passwd);
		stmt=c.createStatement();
		}
		catch (Exception e)
		{
			System.out.println("够了！");
		}
}
	public void regs(String a,String b)throws SQLException,
	ClassNotFoundException{
		try {
		String sql="insert INTO qqusr(user,passwd) VALUES "
				+ "('"+a+"','"+b+"')";
		int rs=stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println("注册时出错了");
		}
	}
	public void friend(String a)throws SQLException,
	ClassNotFoundException{
		try {
		String sql="CREATE TABLE "+a+"(usr CHAR(10))";
		int rs=stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		}
	catch(Exception e)
	{
		System.out.println("创建好友列表时出错了！！！");
	}
	}
	public void addfri(String a,String b)throws SQLException,
	ClassNotFoundException{
		try {
		String sql="insert INTO "+a+"(usr)"+"VALUES "
				+ "('"+b+"')";
		int rs=stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println("添加好友时出错了");
		}
	}
	public List find(String a,List<String>list)throws SQLException,
	ClassNotFoundException{
		try {
		String sql="select usr from "+a;
		ResultSet rs =stmt.executeQuery(sql);
		while(rs.next()) {
			list.add(rs.getString("usr"));
		}
		//int rs=stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println("添加好友时出错了");
		}
		return list;
	}
	public void inIP(String a,String b)throws SQLException,
	ClassNotFoundException{
		try {
		String sql="insert INTO ONL(user,IP) VALUES "
				+ "('"+a+"','"+b+"')";
		int rs=stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		}
	catch(Exception e)
	{
		System.out.println("创建在线列表时出错了！！！");
	}
	}
	public boolean match(String a,String b)throws SQLException,
	ClassNotFoundException{
		String sql="SELECT user,passwd FROM qqusr";
		try {
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			if(rs.getString("user").equals(a)&&rs.getString("passwd").equals(b))
				return true;
		}
		rs.close();
		stmt.close();
		c.close();
		}
		catch(Exception e)
		{
			System.out.println("配对出错了！");
		}
		return false;
	}
}
