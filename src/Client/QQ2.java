package Client;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import server.*;
import javax.swing.*;
public class QQ2 extends JFrame {
	public static final int width=370;
	public static final int heigh=320;
	//设置账号密码
	JTextField t=new JTextField(15);
	JTextField s=new JPasswordField(15);
	//设置提示：
	JTextField corr=new JTextField(10);
	//返回按钮：
	JButton X;
	JButton signin;
	public static void main(String[]args)
	{	
		QQ2 qq=new QQ2();
	}
	
	public QQ2()
		{
		//设置基本参数
		setSize(width,heigh);
		setLocation(500,220);
		//设置标题
		ImageIcon yzh1=new ImageIcon("2.png");
		JLabel myLabel=new JLabel(yzh1);
		//设置图片
		ImageIcon yzh=new ImageIcon("1.jpg");
		JLabel wsz=new JLabel(yzh);
		//设置账号密码的键入框
		JTextField A=new JTextField(5);
		JTextField B=new JTextField(5);
		A.setText("用户：");
		B.setText("密码：");
		A.setEditable(false);
		B.setEditable(false);
		t.setEditable(true);
		s.setEditable(true);
		//设置提示
		corr.setEditable(false);
		//复选框
		JCheckBox box=new JCheckBox("记住密码");
		//获取窗口的面板
		JPanel panel=new JPanel();
		Container a=getContentPane();
		//设置按钮
		X=new JButton("注册账号");
		signin=new JButton("登录");
		Color c=new Color(104,119,249);
		signin.setBackground(c);
		//X.addActionListener(this);
		//将面板加到BorderLayout的一个区域中
		a.setLayout(new BorderLayout(10,10));
		a.add(signin,BorderLayout.SOUTH);
		a.add(myLabel,BorderLayout.NORTH);
		a.add(panel,BorderLayout.CENTER);
		a.add(wsz,BorderLayout.WEST);
		panel.add(A);
		panel.add(t);
		panel.add(B);
		panel.add(s);
		panel.add(X);
		panel.add(box);
		panel.add(corr);
		//设置窗口的后事
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
		public JButton getreg() {
			return X;
		}
		public JButton getlog() {
			return signin;
		}
		public JTextField getusr() {
			return t;
		}
		public JTextField getpawd() {
			return s;
		}
		public JTextField getcorr() {
			return corr;
		}
}