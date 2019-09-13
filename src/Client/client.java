package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class client  {
	public static final int PORT=8098;
	public static final String HOST="localhost";
	public String STR="";
	talk t;
	JTextPane up;
	JTextArea down;
	button a;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	//FileInputStream fis;
	FileOutputStream fos;
	OutputStream out;
	InputStream in;
	Thread T;
	Thread curr;
	File file;
	String ip;
	public static void main(String[] args)
			throws UnknownHostException, IOException
	{
	new client();
	}
	public client()throws UnknownHostException,IOException{
		socket=new Socket(HOST,PORT);
		ip=socket.getInetAddress().getHostAddress();
		t=new talk();
		up=t.getUp();
		//连接上之后初始化：
		br=new BufferedReader
				(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter
				(socket.getOutputStream())),true);
		out=socket.getOutputStream();
		in=socket.getInputStream();
		down=t.getDown();
		JButton S=t.getsend();
		JButton C=t.getclose();
		JMenuItem D=t.getpic();
		JMenuItem V=t.getvideo();
		JMenuItem F=t.getfil();
		a=new button();
		S.addActionListener(a);
		C.addActionListener(a);
		D.addActionListener(a);
		V.addActionListener(a);
		F.addActionListener(a);
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
			//new send2().start();
			FileInputStream fis;
			pw.println("发送图片");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			try {
				if(f!=null) {
				file = getfile(ip);
				fis=new FileInputStream(f);
				fos=new FileOutputStream(file);
				byte []buf=new byte[1024];
				int len=0;
				
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
				}
				//socket.shutdownOutput();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("video")) {
			FileInputStream fis;
			pw.println("发送视频");
			JFileChooser fd=new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			try {
				if(f!=null) {
				file = getfile2(ip);
				fis=new FileInputStream(f);
				fos=new FileOutputStream(file);
				byte []buf=new byte[1024];
				int len=0;
				
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
				}
				//socket.shutdownOutput();
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
			try {
				if(f!=null) {
				file = getfile3(ip);
				fis=new FileInputStream(f);
				fos=new FileOutputStream(file);
				byte []buf=new byte[1024];
				int len=0;
				
				while((len=fis.read(buf))!=-1) {
					out.write(buf,0,len);
				}
				System.out.println("发送完成");
				fis.close();
				System.out.println("fis已关闭");
				}
				//socket.shutdownOutput();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
				System.out.println(STR);
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
				if(STR.equals("发送图片")) {
					System.out.println("发送图片条件判定成功");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("接受图片成功");
					fos.close();
				}
				if(STR.equals("发送视频")) {
					System.out.println("发送视频条件判定成功");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("接受视频成功");
					fos.close();
				}
				if(STR.equals("发送文件")) {
					System.out.println("发送文件条件判定成功");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("接受文件成功");
					fos.close();
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
private static File getfile(String ip) {
	File dir =new File("D:\\server_pic");
	if(!dir.exists()) {
		dir.mkdir();
	}
	
	int count=1;
	File file =new File(dir,ip+"("+count+")"+".jpg");
	while(file.exists()) {
		count++;
		file=new File(dir,ip+"("+count+")"+".jpg");
	}
	return file;
}
private static File getfile2(String ip) {
	File dir =new File("D:\\server_pic");
	if(!dir.exists()) {
		dir.mkdir();
	}
	
	int count=1;
	File file =new File(dir,ip+"("+count+")"+".mp4");
	while(file.exists()) {
		count++;
		file=new File(dir,ip+"("+count+")"+".mp4");
	}
	return file;
}
private static File getfile3(String ip) {
	File dir =new File("D:\\server_pic");
	if(!dir.exists()) {
		dir.mkdir();
	}
	
	int count=1;
	File file =new File(dir,ip+"("+count+")"+".txt");
	while(file.exists()) {
		count++;
		file=new File(dir,ip+"("+count+")"+".txt");
	}
	return file;
}
}