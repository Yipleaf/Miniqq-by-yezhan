package server;
import java.io.*;
import java.util.*;

import Client.QQDB;

import java.net.*;
public class MultiServer4 {
static final int PORT=8098;
public static List<qqsit>clients=new ArrayList<qqsit>();
public static List<String>list=new ArrayList<String>();
static qqsit C;
static String STR="";
static boolean flag=true;
static ServerSocket s;
static ServerSocket s2;
public static void main(String[]args) throws IOException{
	s=new ServerSocket(PORT);
	s2=new ServerSocket(10007);
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

static class qqsit extends Thread{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private OutputStream Out;
	private QQDB qqdb;
	private String user="";
	private String fri="";
	public qqsit(Socket s)throws IOException {
		socket=s;
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter
				(socket.getOutputStream())),true);
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
						out.println("登录成功");
						System.out.println("登录成功");
						//qqdb.setvalue();
						//qqdb.inIP(user,socket.getInetAddress().getHostAddress());
					}
					else out.println("登录失败");
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
				if(STR.equals("井字棋")) {
					TicTacToeServer frame = new TicTacToeServer();
				}
				if(STR.equals("发送图片")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("服务器判别图片条件成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
							socket=s2.accept();
						}
					}
					System.out.println("服务器发送者监听成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("服务器接受者监听成功");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
					System.out.println("设置output成功");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					//！！！！！！！！！
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 out1.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 out2.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.shutdownOutput();
					}
					}
					System.out.println("图片上传成功");
					socket.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.close();
					}
					}
				}
				if(STR.equals("发送视频")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("服务器判别视频条件成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
							socket=s2.accept();
						}
					}
					System.out.println("服务器发送者监听成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("服务器接受者监听成功");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
					System.out.println("设置output成功");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					//！！！！！！！！！
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 out1.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 out2.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.shutdownOutput();
					}
					}
					System.out.println("视频上传成功");
					socket.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.close();
					}
					}
				}
				if(STR.equals("发送文件")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("服务器判别文件条件成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
							socket=s2.accept();
						}
					}
					System.out.println("服务器发送者监听成功");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("服务器接受者监听成功");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
					System.out.println("设置output成功");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					//！！！！！！！！！
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 out1.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 out2.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.shutdownOutput();
					}
					}
					System.out.println("上传成功");
					socket.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.close();
					}
					}
				}
			      STR="";		    
			}
		}
		}     catch(SocketException e) {
		    	try { 
		    		MultiServer4.clients.remove(this);
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


