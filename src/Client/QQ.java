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
public class QQ extends JFrame implements ActionListener {
	public static final int width=370;
	public static final int heigh=320;
	//�����˺�����
	JTextField t=new JTextField(15);
	JTextField s=new JPasswordField(15);
	//������ʾ��
	JTextField corr=new JTextField(10);
	public static void main(String[]args)
	{	
		QQ qq=new QQ();
	}
	
	public QQ()
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
		JButton X=new JButton("ע���˺�");
		JButton signin=new JButton("��¼");
		Color c=new Color(104,119,249);
		signin.setBackground(c);
		X.addActionListener(this);
		signin.addActionListener(this);
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
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("��¼"))
				{
				try {
					QQDB qqdb2=new QQDB();
					qqdb2.setvalue();
					if(qqdb2.match(t.getText(),s.getText()))
						System.out.println("��½�ɹ�");
					else corr.setText("�˺Ż��������");
				} 
				catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
					
				}
			if(e.getActionCommand().equals("ע���˺�"))
				{
					try {
						new register();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			
		}
}