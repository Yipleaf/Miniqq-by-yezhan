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
			System.out.println("���ˣ�");
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
			System.out.println("ע��ʱ������");
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
		System.out.println("���������б�ʱ�����ˣ�����");
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
			System.out.println("��Ӻ���ʱ������");
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
			System.out.println("��Ӻ���ʱ������");
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
		System.out.println("���������б�ʱ�����ˣ�����");
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
			System.out.println("��Գ����ˣ�");
		}
		return false;
	}
}
