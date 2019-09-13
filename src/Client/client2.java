package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class client2  {
	public static final int PORT=30008;
	public static final String HOST="192.168.1.105";
	talk t;
	JTextPane up;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	public static void main(String[] args)
			throws UnknownHostException, IOException
	{
	new client2().send(); 
	}
	public client2()throws UnknownHostException,IOException{
		socket=new Socket(HOST,PORT);
		t=new talk();
		up=t.getUp();
		//连接上之后初始化：
		br=new BufferedReader
				(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter
				(socket.getOutputStream())),true);
		//连接上之后建立一个线程：
		new Thread(new Recv()).start();
	}
	
	public void send() {
		try {
			JTextArea down=t.getDown();
			JButton S=t.getsend();
			JButton C=t.getclose();
			button a=new button();
			S.addActionListener(a);
			C.addActionListener(a);
			String msg="";
			while((msg=down.getText())!="quit")
			{  
				
				if(a.getbn())
				{
				  pw.println(msg); 
				  a.setbn();
				  down.setText("");
			    }
			}
		}
		catch(Exception e){
			System.out.println("客户端输出出错了！");
		}
		finally {
			if(null!=socket) {
				try {
					socket.close();
				}
				catch(IOException e){
				System.out.println("客户端");
				}
			}
		}
	}
public class button implements ActionListener{
	public boolean bn=false;
	public void actionPerformed(ActionEvent e) {
	 
		if(e.getActionCommand().equals("发送"))
		{   
			bn=true;
		}
		if(e.getActionCommand().equals("关闭"))
		{
			t.frame.dispose();
		}
	}
	public boolean getbn() {
		return bn;
	}
	public void setbn() {
		bn=false;
	}
}

private class Recv implements Runnable {
	public void run() {
		try {
		while(true) {
				String hf=br.readLine();
				t.manageinfo(hf);
				t.manageinfo("\n");
				//up.setText(up.getText().toString()+hf+"\n");
				System.out.println("*"+hf);
			} 
		}
		catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}
}