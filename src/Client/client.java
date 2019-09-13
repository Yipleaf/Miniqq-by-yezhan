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
		//������֮���ʼ����
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
			//new send2().start();
			FileInputStream fis;
			pw.println("����ͼƬ");
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
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
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
			pw.println("������Ƶ");
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
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
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
			pw.println("�����ļ�");
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
				System.out.println("�������");
				fis.close();
				System.out.println("fis�ѹر�");
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
				System.out.println("�ͻ��˽��յ��˷���");
				System.out.println(STR);
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
				if(STR.equals("����ͼƬ")) {
					System.out.println("����ͼƬ�����ж��ɹ�");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("����ͼƬ�ɹ�");
					fos.close();
				}
				if(STR.equals("������Ƶ")) {
					System.out.println("������Ƶ�����ж��ɹ�");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("������Ƶ�ɹ�");
					fos.close();
				}
				if(STR.equals("�����ļ�")) {
					System.out.println("�����ļ������ж��ɹ�");
					byte []buf=new byte[1024];
					int len=0;
					while((len=in.read(buf))!=-1) {
						System.out.println(".");
						fos.write(buf,0,len);
						if(len<1024)break;	
					}
					System.out.println("�����ļ��ɹ�");
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