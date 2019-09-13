package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
public class register extends JFrame  {
public static final int width=370;
public static final int height=320;
public JTextField pawd=new JPasswordField(15);
public JTextField pawd2=new JPasswordField(15);
//设置要返回的按钮：
public JButton reg;
//设置密码是否正确
public JTextField pp=new JTextField(9);
JTextField usr=new JTextField(15);
QQDB qqdb1=new QQDB();
//设置注册成功时的窗口
JDialog jdialog=new JDialog();
public register()throws SQLException,ClassNotFoundException
{
	//设置参数
	setSize(width,height);
	setLocation(500,220);
	//设置成功是的窗口
	JButton success=new JButton("确认");
	JLabel suc=new JLabel("注册成功");
	JPanel succ=new JPanel();
	jdialog.setSize(50,100);
	jdialog.setLocation(600,400);
	jdialog.add(succ);
	succ.add(suc);
	succ.add(success);
	//给按钮设监听器
	//设置窗口
	Container a=getContentPane();
	a.setLayout(new BorderLayout(10,10));
	//设置图片
	ImageIcon yzh1=new ImageIcon("3.png");
	JLabel myLabel=new JLabel(yzh1);
	ImageIcon yzh2=new ImageIcon("4.png");
	JLabel myLabel2=new JLabel(yzh2);
	//设置文字和输入框
	JTextField user=new JTextField(5);
	JTextField passwd=new JTextField(5);
	JTextField passwd2=new JTextField(5);
	user.setEditable(false);
	passwd.setEditable(false);
	passwd2.setEditable(false);
	user.setText("新账号");
	passwd.setText("新密码");
	passwd2.setText("确认密码");
	pp.setEditable(false);
	//设置按钮
	reg=new JButton("注册");
	JButton cancel=new JButton("取消");
	Color c=new Color(104,119,249);
	reg.setBackground(c);
	cancel.setBackground(c);
	//往border中加panel
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	//往panel1中加入各个组件
	a.add(panel1,BorderLayout.CENTER);
	panel1.add(user);
	panel1.add(usr);
	panel1.add(passwd);
	panel1.add(pawd);
	panel1.add(passwd2);
	panel1.add(pawd2);
	panel1.add(pp);
	//往panel2中设置网格，然后加入组件
	panel2.setLayout(new GridLayout(1,2,3,3));
	a.add(panel2,BorderLayout.SOUTH);
	panel2.add(reg);
	panel2.add(cancel);
	//插入图片
	a.add(myLabel,BorderLayout.NORTH);
	a.add(myLabel2,BorderLayout.WEST);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
}
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("注册"))
		{	
			if(pawd.getText().equals("")||pawd2.getText().equals(""))
			pp.setText("密码输入不能空");
			else if(pawd.getText().equals(pawd2.getText()))
			{
			try {
				qqdb1.setvalue();
				qqdb1.regs(usr.getText(),pawd.getText());
				jdialog.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
			else pp.setText("密码输入不一致");
		}
		if(e.getActionCommand().equals("确认"))
		{jdialog.setVisible(false);	
		setVisible(false);
		}
		if(e.getActionCommand().equals("取消"))
		{
			setVisible(false);
		}
		
	}*/
	public JTextField getpawd() {
		return pawd;
	}
	public JTextField getpawd2() {
		return pawd2;
	}
	public JTextField getpp() {
		return pp;
	}
	public JButton getregs() {
		return reg;
	}
	public JTextField getusr() {
		return usr;
	}
}
