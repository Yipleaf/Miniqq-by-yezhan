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
//����Ҫ���صİ�ť��
public JButton reg;
//���������Ƿ���ȷ
public JTextField pp=new JTextField(9);
JTextField usr=new JTextField(15);
QQDB qqdb1=new QQDB();
//����ע��ɹ�ʱ�Ĵ���
JDialog jdialog=new JDialog();
public register()throws SQLException,ClassNotFoundException
{
	//���ò���
	setSize(width,height);
	setLocation(500,220);
	//���óɹ��ǵĴ���
	JButton success=new JButton("ȷ��");
	JLabel suc=new JLabel("ע��ɹ�");
	JPanel succ=new JPanel();
	jdialog.setSize(50,100);
	jdialog.setLocation(600,400);
	jdialog.add(succ);
	succ.add(suc);
	succ.add(success);
	//����ť�������
	//���ô���
	Container a=getContentPane();
	a.setLayout(new BorderLayout(10,10));
	//����ͼƬ
	ImageIcon yzh1=new ImageIcon("3.png");
	JLabel myLabel=new JLabel(yzh1);
	ImageIcon yzh2=new ImageIcon("4.png");
	JLabel myLabel2=new JLabel(yzh2);
	//�������ֺ������
	JTextField user=new JTextField(5);
	JTextField passwd=new JTextField(5);
	JTextField passwd2=new JTextField(5);
	user.setEditable(false);
	passwd.setEditable(false);
	passwd2.setEditable(false);
	user.setText("���˺�");
	passwd.setText("������");
	passwd2.setText("ȷ������");
	pp.setEditable(false);
	//���ð�ť
	reg=new JButton("ע��");
	JButton cancel=new JButton("ȡ��");
	Color c=new Color(104,119,249);
	reg.setBackground(c);
	cancel.setBackground(c);
	//��border�м�panel
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	//��panel1�м���������
	a.add(panel1,BorderLayout.CENTER);
	panel1.add(user);
	panel1.add(usr);
	panel1.add(passwd);
	panel1.add(pawd);
	panel1.add(passwd2);
	panel1.add(pawd2);
	panel1.add(pp);
	//��panel2����������Ȼ��������
	panel2.setLayout(new GridLayout(1,2,3,3));
	a.add(panel2,BorderLayout.SOUTH);
	panel2.add(reg);
	panel2.add(cancel);
	//����ͼƬ
	a.add(myLabel,BorderLayout.NORTH);
	a.add(myLabel2,BorderLayout.WEST);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
}
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("ע��"))
		{	
			if(pawd.getText().equals("")||pawd2.getText().equals(""))
			pp.setText("�������벻�ܿ�");
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
			else pp.setText("�������벻һ��");
		}
		if(e.getActionCommand().equals("ȷ��"))
		{jdialog.setVisible(false);	
		setVisible(false);
		}
		if(e.getActionCommand().equals("ȡ��"))
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
