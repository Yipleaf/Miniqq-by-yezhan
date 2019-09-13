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
	//注册界面的注册按钮：
	JButton regs;
	//注册界面的账号框和两个密码框：
	JTextField pawd;
	JTextField pawd2;
	JTextField usr;
	//注册界面的提示框：
	JTextField pp;
	//登录界面的提示框：
	JTextField PP;
	
	//登录界面的账号密码：
	JTextField user;
	JTextField passwd;
	//获得登录界面的用户名：
	String use;
	//主界面的添加按钮：
	JMenuItem addd;
	//主界面的好友列表：
	JPanel jp1;
	//添加就饿面的按钮和文本：
	JTextField fri;
	JButton ADD;
	
	JTextPane up;
	JTextArea down;
	button a;
	Socket socket;
	//设置给服务器的socket：
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
	//建立数据库：
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
		//拿到登录界面的注册账号按钮：
		JButton reg= qq.getreg();
		//拿到登录界面的登录按钮：
		JButton log=qq.getlog();
		//拿到登录界面的账号密码：
		user=qq.getusr();
		passwd=qq.getpawd();
		//拿到登录界面的提示框：
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
	 
		if(e.getActionCommand().equals("发送"))
		{   
			pw.println("发送信息");
			System.out.println("成功发送指令");
			String msg="";
			msg=down.getText();
			pw.println(msg); 
			down.setText("");
		}
		if(e.getActionCommand().equals("关闭"))
		{
			t.frame.dispose();
		}
		if(e.getActionCommand().equals("picture"))
		{
			FileInputStream fis;
			pw.println("发送图片");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("创建图片socket成功");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("video")) {	
			FileInputStream fis;
			pw.println("发送视频");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("创建视频socket成功");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
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
			pw.println("发送文件");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String ffName=f.getName();
			System.out.println(f);
			String ffHZ=ffName.substring(ffName.lastIndexOf("."),ffName.length());
			System.out.println(ffHZ);
			pw.println(ffHZ);
			try {
				System.out.println("创建文件socket成功");
				OutputStream out=socket2.getOutputStream();
				if(f!=null) {
				fis=new FileInputStream(f);
				byte []buf=new byte[1024];
				int len=0;
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				socket2.shutdownOutput();
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("注册账号"))
		{
			try {
				Tt=new register();
				//拿到注册界面的注册按钮：
				regs=Tt.getregs();
				regs.addActionListener(a);
				//拿到注册界面的几个文本：
				pawd=Tt.getpawd();
				pawd2=Tt.getpawd2();
				pp=Tt.getpp();
				usr=Tt.getusr();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("注册"))
		{	
			if(pawd.getText().equals("")||pawd2.getText().equals(""))
			pp.setText("密码输入不能空");
			else if(pawd.getText().equals(pawd2.getText()))
			{
			try {
				pp.setText("注册成功");
				pw.println("注册成功");
				pw.println(usr.getText());
				pw.println(pawd.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
			else pp.setText("密码输入不一致");
		}
		if(e.getActionCommand().equals("登录")) {
			pw.println("登录");
			use=user.getText();
			pw.println(user.getText());
			pw.println(passwd.getText());
		}
		if(e.getActionCommand().equals("添加好友")) {
			TT=new friend();
			fri=TT.getname();
			ADD=TT.getADD();
			ADD.addActionListener(a);
		}
		if(e.getActionCommand().equals("添加")) {
			pw.println("添加好友");
			String Fri=fri.getText();
			pw.println(Fri);
		}
		if(e.getActionCommand().equals("TicTacToe")) {
			pw.println("井字棋");
		}
		for(int i=0;i<list2.size();i++) {
			if(e.getActionCommand().equals(list2.get(i).getText())) {
				pw.println("开始聊天");
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
				System.out.println("客户端接收到了反馈");
				if(STR.equals("发送信息")) {
					System.out.println("发送信息条件判定条件成功");
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
				if(STR.equals("井字棋")) {
					sleep(500);
					jinziqi jinziqi=new jinziqi();
				}
				if(STR.equals("发送图片")) {
					System.out.println("发送图片条件判定成功");
					String str=br.readLine();
					ip=str;
					System.out.println("接收到发送者的用户名");
					String LOL=br.readLine();
					System.out.println("接收到自己的用户名");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("连接图片服务器成功");
					}					
					String ffhz=br.readLine();
					System.out.println("接收到了文件后缀");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					String name=file.getName();
					fos=new FileOutputStream(file);
					System.out.println("监听到服务器");
					
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("接受图片成功");
					fos.close();
					socket2.close();
				}
				if(STR.equals("发送视频")) {
					System.out.println("发送视频条件判定成功");
					String str=br.readLine();
					ip=str;
					System.out.println("接收到发送者的用户名");
					String LOL=br.readLine();
					System.out.println("接收到自己的用户名");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("连接视频服务器成功");
					}					
					String ffhz=br.readLine();
					System.out.println("接收到了文件后缀");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					fos=new FileOutputStream(file);
					System.out.println("监听到服务器");
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("接受视频成功");
					fos.close();
					socket2.close();
				}
				if(STR.equals("发送文件")) {
					System.out.println("发送文件条件判定成功");
					String str=br.readLine();
					ip=str;
					System.out.println("接收到发送者的用户名");
					String LOL=br.readLine();
					System.out.println("接收到自己的用户名");
					if(LOL.equals(use)) {
						sleep(1000);
						socket2=new Socket(HOST,10007);
						System.out.println("连接文件服务器成功");
					}					
					String ffhz=br.readLine();
					System.out.println("接收到了文件后缀");
					FileOutputStream fos;
					File file = getfile4(ip,ffhz);
					fos=new FileOutputStream(file);
					System.out.println("监听到服务器");
					
					InputStream in=socket2.getInputStream();
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
					}
					System.out.println("接受文件成功");
					fos.close();
					socket2.close();
				}
				if(STR.equals("登录错误")) {
					PP.setText("账号或密码错误");
				}
				if(STR.equals("登录成功")) {
					System.out.println("登录成功");
					LOG=new mLogin();
					addd=LOG.getmenu1();
					jp1=LOG.getjp1();
					addd.addActionListener(a);
					qq.setVisible(false);
					pw.println("刷新好友列表");
				}
				if(STR.equals("添加好友成功")) {
					System.out.println("添加好友成功");
					String fri=br.readLine();
					JButton fri1=new JButton(fri);
					fri1.addActionListener(a);
					jp1.add(fri1);
					list2.add(fri1);
					jp1.revalidate();
				}
				if(STR.equals("刷新好友列表")) {
					System.out.println("收到刷新列表");
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
			System.out.println("客户端输出出错了！");
		}
		finally {
			if(socket!=null) {
				try {
					socket.close();
					System.out.println("客户端socket关闭");
				}
				catch(IOException e){
				System.out.println("客户端");
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