package Client;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class client7  {
	public static final int PORT=10007;
	public static final String HOST="121.250.217.21";
	public String STR="";
	public static List<JButton>list2=new ArrayList<JButton>();
	public static ServerSocket s;
	talk t;
	QQ2 qq;
	mLogin LOG; 
	register Tt;
	friend TT;
	//ע������ע�ᰴť��
	JButton regs;
	//ע�������˺ſ�����������
	JTextField pawd;
	JTextField pawd2;
	JTextField usr;
	//ע��������ʾ��
	JTextField pp;
	//��¼�������ʾ��
	JTextField PP;
	
	//��¼������˺����룺
	JTextField user;
	JTextField passwd;
	//��õ�¼������û�����
	String use;
	//���������Ӱ�ť��
	JMenuItem addd;
	//������ĺ����б�
	JPanel jp1;
	//��ӾͶ���İ�ť���ı���
	JTextField fri;
	JButton ADD;
	
	JTextPane up;
	JTextArea down;
	button a;
	Socket socket;
	//���ø���������socket��
	Socket socket2;
	OutputStream out2;
	BufferedReader br;
	PrintWriter pw;
	//FileInputStream fis;
	//FileOutputStream fos;
	OutputStream out;
	InputStream in;
	Thread T;
	Thread curr;
	String ip;
	//�������ݿ⣺
	QQDB qqdb1;
	public static void main(String[] args)
			throws UnknownHostException, IOException
	{
			new client7();
	}
	public client7()throws UnknownHostException,IOException{
		socket=new Socket(HOST,PORT);
		//ip=socket.getInetAddress().getHostAddress();
		qq=new QQ2();
		qqdb1=new QQDB();
		//�õ���¼�����ע���˺Ű�ť��
		JButton reg= qq.getreg();
		//�õ���¼����ĵ�¼��ť��
		JButton log=qq.getlog();
		//�õ���¼������˺����룺
		user=qq.getusr();
		passwd=qq.getpawd();
		//�õ���¼�������ʾ��
		PP=qq.getcorr();
		br=new BufferedReader
				(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter
				(socket.getOutputStream())),true);
		out=socket.getOutputStream();
		in=socket.getInputStream();
		a=new button();
		reg.addActionListener(a);
		log.addActionListener(a);
		T=new Recv();
		T.start();
	}
public class button implements ActionListener{
	public void actionPerformed(ActionEvent e) {
	 
		if(e.getActionCommand().equals("����"))
		{   
			pw.println("������Ϣ");
			System.out.println("�ɹ�����ָ��");
			String msg="";
			msg=down.getText();
			pw.println(msg); 
			down.setText("");
		}
		if(e.getActionCommand().equals("�ر�"))
		{
			t.frame.dispose();
		}
		if(e.getActionCommand().equals("picture"))
		{
			FileInputStream fis;
			pw.println("����ͼƬ");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("����ͼƬsocket�ɹ�");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("video")) {	
			FileInputStream fis;
			pw.println("������Ƶ");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("������Ƶsocket�ɹ�");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("File")) {
			FileInputStream fis;
			pw.println("�����ļ�");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("�����ļ�socket�ɹ�");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("ע���˺�"))
		{
			try {
				Tt=new register();
				//�õ�ע������ע�ᰴť��
				regs=Tt.getregs();
				regs.addActionListener(a);
				//�õ�ע�����ļ����ı���
				pawd=Tt.getpawd();
				pawd2=Tt.getpawd2();
				pp=Tt.getpp();
				usr=Tt.getusr();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("ע��"))
		{	
			if(pawd.getText().equals("")||pawd2.getText().equals(""))
			pp.setText("�������벻�ܿ�");
			else if(pawd.getText().equals(pawd2.getText()))
			{
			try {
				pp.setText("ע��ɹ�");
				pw.println("ע��ɹ�");
				pw.println(usr.getText());
				pw.println(pawd.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
			else pp.setText("�������벻һ��");
		}
		if(e.getActionCommand().equals("��¼")) {
			pw.println("��¼");
			use=user.getText();
			pw.println(user.getText());
			pw.println(passwd.getText());
		}
		if(e.getActionCommand().equals("��Ӻ���")) {
			TT=new friend();
			fri=TT.getname();
			ADD=TT.getADD();
			ADD.addActionListener(a);
		}
		if(e.getActionCommand().equals("���")) {
			pw.println("��Ӻ���");
			String Fri=fri.getText();
			pw.println(Fri);
		}
		if(e.getActionCommand().equals("TicTacToe")) {
			pw.println("������");
		}
		for(int i=0;i<list2.size();i++) {
			if(e.getActionCommand().equals(list2.get(i).getText())) {
				pw.println("��ʼ����");
				pw.println(list2.get(i).getText());
				t=new talk();
				up=t.getUp();
				down=t.getDown();
				JButton S=t.getsend();
				JButton C=t.getclose();
				JMenuItem D=t.getpic();
				JMenuItem V=t.getvideo();
				JMenuItem F=t.getfil();
				JMenuItem Z=t.getTic();
				S.addActionListener(a);
				C.addActionListener(a);
				D.addActionListener(a);
				V.addActionListener(a);
				F.addActionListener(a);
				Z.addActionListener(a);
			}
		}
	}
}

private class Recv extends Thread {
	public void run() {
		try {
		while(true) {
				STR=br.readLine();
				System.out.println("�ͻ��˽��յ��˷���");
				if(STR.equals("������Ϣ")) {
					System.out.println("������Ϣ�����ж������ɹ�");
					String hf;
					try {
						hf = br.readLine();
						 t.manageinfo(hf);
						 t.manageinfo("\n");
						 System.out.println("*"+hf);
					} catch (IOException e) {
						e.printStackTrace();
					}
					}
				if(STR.equals("������")) {
					sleep(500);
					jinziqi jinziqi=new jinziqi();
				}
				if(STR.equals("����ͼƬ")) {
					System.out.println("����ͼƬ�����ж��ɹ�");
					String str=br.readLine();
					ip=str;
					System.out.println("���յ������ߵ��û���");
					String LOL=br.readLine();
					System.out.println("���յ��Լ����û���");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("����ͼƬ�������ɹ�");
					}					
					String ffhz=br.readLine();
					System.out.println("���յ����ļ���׺");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					String name=file.getName();
					fos=new FileOutputStream(file);
					System.out.println("������������");
					
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("����ͼƬ�ɹ�");
					fos.close();
					socket2.close();
				}
				if(STR.equals("������Ƶ")) {
					System.out.println("������Ƶ�����ж��ɹ�");
					String str=br.readLine();
					ip=str;
					System.out.println("���յ������ߵ��û���");
					String LOL=br.readLine();
					System.out.println("���յ��Լ����û���");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("������Ƶ�������ɹ�");
					}					
					String ffhz=br.readLine();
					System.out.println("���յ����ļ���׺");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					fos=new FileOutputStream(file);
					System.out.println("������������");
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("������Ƶ�ɹ�");
					fos.close();
					socket2.close();
				}
				if(STR.equals("�����ļ�")) {
					System.out.println("�����ļ������ж��ɹ�");
					String str=br.readLine();
					ip=str;
					System.out.println("���յ������ߵ��û���");
					String LOL=br.readLine();
					System.out.println("���յ��Լ����û���");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("�����ļ��������ɹ�");
					}					
					String ffhz=br.readLine();
					System.out.println("���յ����ļ���׺");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					fos=new FileOutputStream(file);
					System.out.println("������������");
					
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("�����ļ��ɹ�");
					fos.close();
					socket2.close();
				}
				if(STR.equals("��¼����")) {
					PP.setText("�˺Ż��������");
				}
				if(STR.equals("��¼�ɹ�")) {
					System.out.println("��¼�ɹ�");
					LOG=new mLogin();
					addd=LOG.getmenu1();
					jp1=LOG.getjp1();
					addd.addActionListener(a);
					qq.setVisible(false);
					pw.println("ˢ�º����б�");
				}
				if(STR.equals("��Ӻ��ѳɹ�")) {
					System.out.println("��Ӻ��ѳɹ�");
					String fri=br.readLine();
					JButton fri1=new JButton(fri);
					fri1.addActionListener(a);
					jp1.add(fri1);
					list2.add(fri1);
					jp1.revalidate();
				}
				if(STR.equals("ˢ�º����б�")) {
					System.out.println("�յ�ˢ���б�");
					String str;
					int len=br.read();
					for(int i=0;i<len;i++) {
						str=br.readLine();
						JButton fri1=new JButton(str);
						fri1.addActionListener(a);
						list2.add(fri1);
						jp1.add(fri1);
					}
					jp1.revalidate();
				}
				STR="";
			} 
		}
		catch (IOException e) {
				e.printStackTrace();
			}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("�ͻ�����������ˣ�");
		}
		finally {
			if(socket!=null) {
				try {
					socket.close();
					System.out.println("�ͻ���socket�ر�");
				}
				catch(IOException e){
				System.out.println("�ͻ���");
				}
			}
		}
	}
	
}

private static File getfile4(String ip,String hz) {
	File dir =new File("D:\\server_pic");
	if(!dir.exists()) {
		dir.mkdir();
	}
	int count=1;
	File file =new File(dir,ip+"("+count+")"+hz);
	while(file.exists()) {
		count++;
		file=new File(dir,ip+"("+count+")"+hz);
	}
	return file;
}
}