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
	//�����˺�����
	JTextField t=new JTextField(15);
	JTextField s=new JPasswordField(15);
	//������ʾ��
	JTextField corr=new JTextField(10);
	//���ذ�ť��
	JButton X;
	JButton signin;
	public static void main(String[]args)
	{	
		QQ2 qq=new QQ2();
	}
	
	public QQ2()
		{
		//���û�������
		setSize(width,heigh);
		setLocation(500,220);
		//���ñ���
		ImageIcon yzh1=new ImageIcon("2.png");
		JLabel myLabel=new JLabel(yzh1);
		//����ͼƬ
		ImageIcon yzh=new ImageIcon("1.jpg");
		JLabel wsz=new JLabel(yzh);
		//�����˺�����ļ����
		JTextField A=new JTextField(5);
		JTextField B=new JTextField(5);
		A.setText("�û���");
		B.setText("���룺");
		A.setEditable(false);
		B.setEditable(false);
		t.setEditable(true);
		s.setEditable(true);
		//������ʾ
		corr.setEditable(false);
		//��ѡ��
		JCheckBox box=new JCheckBox("��ס����");
		//��ȡ���ڵ����
		JPanel panel=new JPanel();
		Container a=getContentPane();
		//���ð�ť
		X=new JButton("ע���˺�");
		signin=new JButton("��¼");
		Color c=new Color(104,119,249);
		signin.setBackground(c);
		//X.addActionListener(this);
		//�����ӵ�BorderLayout��һ��������
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
		//���ô��ڵĺ���
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