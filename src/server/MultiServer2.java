package server;
import java.io.*;
import java.util.*;

import Client.QQDB;

import java.net.*;
public class MultiServer2 {
static final int PORT=8098;
public static List<qqsit>clients=new ArrayList<qqsit>();
public static List<String>list=new ArrayList<String>();
static qqsit C;
static String STR="";
static boolean flag=true;
public static void main(String[]args) throws IOException{
	ServerSocket s=new ServerSocket(PORT);
	System.out.println("服务器启动");
	try {
		while(true) {
			Socket socket=s.accept();
			try {
				//两个线程：	
				//c=new qqserver(socket);
				C=new qqsit(socket);
				System.out.println("客户端连接。。。");
				clients.add(C);
			}
			catch(Exception e){
				System.out.println("客户端断开连接");
				socket.close();
			}
		}
	}
	finally {
		s.close();
	}
}
static class qqserver extends Thread {
private Socket socket;
private BufferedReader in;
private PrintWriter out;
public qqserver(Socket s) throws IOException
{
	socket=s;
	in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	out=new PrintWriter(new BufferedWriter(new OutputStreamWriter
			(socket.getOutputStream())),true);
	start();
}
	public void run() {
	    try {
	      if(STR.equals("发送信息")) {
	    	  System.out.println("服务器判别条件成功");
	        String str = in.readLine();
	        for(int i=0;i<clients.size();i++) {
	        	qqsit c=clients.get(i);
	        	c.send(str,socket);
	        }
	      }
	      STR="";
	    } catch(SocketException e) {
	    	MultiServer2.clients.remove(this);
			//this.socket.close();  
	    	}
	    	catch(Exception e2) {
	    		 e2.printStackTrace(); 
	      }
}
	public void send(String str,Socket c) {
		   System.out.println("Echoing: " + str);
	       out.println(c.getInetAddress()+":"+str);
	}
	public void send2(String str) {
		out.println(str);
	}
}
static class qqsit extends Thread{
	private Socket socket;
	private Socket socket2;
	private BufferedReader in;
	private PrintWriter out;
	private OutputStream Out;
	private InputStream In;
	private QQDB qqdb;
	private String user="";
	private String fri="";
	public qqsit(Socket s)throws IOException {
		socket=s;
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter
				(socket.getOutputStream())),true);
		//Out=socket.getOutputStream();
		In=socket.getInputStream();
		Out=socket.getOutputStream();
		qqdb=new QQDB();
		start();
		}
	public void run() {
		try {
		while (true) {
				STR=in.readLine();
				if(STR.equals("注册成功"))
				{	
					System.out.println("服务器接收到注册成功");
					String usr=in.readLine();
					String pawd=in.readLine();
					qqdb.setvalue();
					qqdb.regs(usr,pawd);
					qqdb.setvalue();
					qqdb.friend(usr);
					STR="";
				}
				else if(STR.equals("登录")) {
					System.out.println("服务器接收到登录");
					user=in.readLine();
					String pawd=in.readLine();
					qqdb.setvalue();
					if(qqdb.match(user,pawd)) {
						C.send2("登录成功");
						System.out.println("登录成功");
						//qqdb.setvalue();
						//qqdb.inIP(user,socket.getInetAddress().getHostAddress());
					}
					else C.send2("登录错误");
				}
				else if(STR.equals("添加好友")) {
					String fri=in.readLine();
					qqdb.setvalue();
					qqdb.addfri(user, fri);
					out.println("添加好友成功");
					out.println(fri);
				}
				else if(STR.equals("刷新好友列表")) {
					qqdb.setvalue();
					list=qqdb.find(user, list);
					int a=list.size();
					out.println("刷新好友列表");
					out.write(a);
					for(int i=0;i<list.size();i++) {
						out.println(list.get(i));
					}
					list.clear();
				}else if(STR.equals("开始聊天")) {
					fri=in.readLine();
					System.out.println(fri);	
				}
				else {
					//STR=in.readLine();
				System.out.println("服务器接收到了指令");
				for(int i=0;i<clients.size();i++) {
					if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
		        	qqsit c=clients.get(i);
		        	c.send2(STR);
					}
		        }
				System.out.println("服务器反馈了指令");
				if(STR.equals("发送信息")) {
					System.out.println("服务器判别信息条件成功");
			        String str = in.readLine();
			        for(int i=0;i<clients.size();i++) {
			        	if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
			        	qqsit c=clients.get(i);
			        	c.send(str,socket);
			        	}
			        }
			      }
				if(STR.equals("发送图片")){
					System.out.println("服务器判别图片条件成功");
					int len=0;
					byte []buf=new byte[1024];
					while((len=In.read(buf))!=-1) {
						 for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
					        	qqsit c=clients.get(i);
					        	c.send3(len,buf);
						 }
						 }
						 System.out.println("..");
						 if(len<1024)break;
					}
					System.out.println("图片上传成功");
				}
				if(STR.equals("发送视频")){
					System.out.println("服务器判别视频条件成功");
					int len=0;
					byte []buf=new byte[1024];
					while((len=In.read(buf))!=-1) {
						 for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
					        	qqsit c=clients.get(i);
					        	c.send3(len,buf);
						 }
						 }
						 System.out.println("..");
						 if(len<1024)break;
					}
					
					System.out.println("视频上传成功");
				}
				if(STR.equals("发送文件")){
					System.out.println("服务器判别文件条件成功");
					int len=0;
					byte []buf=new byte[1024];
					while((len=In.read(buf))!=-1) {
						 for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
					        	qqsit c=clients.get(i);
					        	c.send3(len,buf);
						 }
						 }
						 System.out.println("..");
						 if(len<1024)break;
					}
					
					System.out.println("文件上传成功");
				}
			      STR="";		    
			}
		}
		}     catch(SocketException e) {
		    	try { 
		    		MultiServer2.clients.remove(this);
		    		this.socket.close();
		    		System.out.println("客户端已经退出。。。");    	
		    	}
			     catch(IOException e1) {
			    	  e1.printStackTrace(); 
			     } 
			}
				catch(Exception e2) {
					e2.printStackTrace(); 
		      }
		}
	public void send(String str,Socket c) {
		   System.out.println("Echoing: " + str);
	       out.println(c.getInetAddress()+":"+str);
	}
	
	public void send2(String str) {
		out.println(str);
	}
	
	public void send3(int Len,byte[]Buf) {
		try {
			Out.write(Buf,0,Len);
			//Out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	}
	}


