package server;
import java.io.*;
import java.util.*;

import Client.QQDB;

import java.net.*;
public class MultiServer3 {
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
	s2=new ServerSocket(10009);
	System.out.println("����������");
	try {
		while(true) {
			Socket socket=s.accept();
			try {
				//�����̣߳�	
				//c=new qqserver(socket);
				C=new qqsit(socket);
				System.out.println("�ͻ������ӡ�����");
				clients.add(C);
			}
			catch(Exception e){
				System.out.println("�ͻ��˶Ͽ�����");
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
	      if(STR.equals("������Ϣ")) {
	    	  System.out.println("�������б������ɹ�");
	        String str = in.readLine();
	        for(int i=0;i<clients.size();i++) {
	        	qqsit c=clients.get(i);
	        	c.send(str,socket);
	        }
	      }
	      STR="";
	    } catch(SocketException e) {
	    	MultiServer3.clients.remove(this);
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
	private Socket socket3;
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
				if(STR.equals("ע��ɹ�"))
				{	
					System.out.println("���������յ�ע��ɹ�");
					String usr=in.readLine();
					String pawd=in.readLine();
					qqdb.setvalue();
					qqdb.regs(usr,pawd);
					qqdb.setvalue();
					qqdb.friend(usr);
					STR="";
				}
				else if(STR.equals("��¼")) {
					System.out.println("���������յ���¼");
					user=in.readLine();
					String pawd=in.readLine();
					qqdb.setvalue();
					if(qqdb.match(user,pawd)) {
						out.println("��¼�ɹ�");
						System.out.println("��¼�ɹ�");
						//qqdb.setvalue();
						//qqdb.inIP(user,socket.getInetAddress().getHostAddress());
					}
					else out.println("��¼ʧ��");
				}
				else if(STR.equals("��Ӻ���")) {
					String fri=in.readLine();
					qqdb.setvalue();
					qqdb.addfri(user, fri);
					out.println("��Ӻ��ѳɹ�");
					out.println(fri);
				}
				else if(STR.equals("ˢ�º����б�")) {
					qqdb.setvalue();
					list=qqdb.find(user, list);
					int a=list.size();
					out.println("ˢ�º����б�");
					out.write(a);
					for(int i=0;i<list.size();i++) {
						out.println(list.get(i));
					}
					list.clear();
				}else if(STR.equals("��ʼ����")) {
					fri=in.readLine();
					System.out.println(fri);	
				}
				else {
					//STR=in.readLine();
				System.out.println("���������յ���ָ��");
				for(int i=0;i<clients.size();i++) {
					if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
		        	qqsit c=clients.get(i);
		        	c.send2(STR);
					}
		        }
				System.out.println("������������ָ��");
				if(STR.equals("������Ϣ")) {
					System.out.println("�������б���Ϣ�����ɹ�");
			        String str = in.readLine();
			        for(int i=0;i<clients.size();i++) {
			        	if(clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
			        	qqsit c=clients.get(i);
			        	c.send(str,socket);
			        	}
			        }
			      }
				if(STR.equals("����ͼƬ")){
					System.out.println("�������б�ͼƬ�����ɹ�");
					OutputStream Oo2 = null;
					OutputStream Oo3 = null;

					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					Socket socket=s2.accept();
					System.out.println("�����������ɹ�");
				
					InputStream in1=socket.getInputStream();
					int len=0;
					byte []buf=new byte[1024];
				
					for(int i=0;i<clients.size();i++) {
						 if(clients.get(i).user.equals(user)) {
							 qqsit c=clients.get(i);
							 sleep(1000);
							 socket2=new Socket(c.socket.getInetAddress(),10006);
							 Oo2=socket2.getOutputStream();
						 }
						if( clients.get(i).user.equals(fri)) {
							 sleep(1000);
							qqsit c=clients.get(i);
							socket3=new Socket(c.socket.getInetAddress(),10006);
							 Oo3=socket3.getOutputStream();
						}	
					}
					System.out.println("����output�ɹ�");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					//������������������
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 Oo2.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 Oo3.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket2.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket3.shutdownOutput();
					}
					}
					System.out.println("ͼƬ�ϴ��ɹ�");
					socket2.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket3.close();
					}
					}
				}
				if(STR.equals("������Ƶ")){
					System.out.println("�������б���Ƶ�����ɹ�");
					OutputStream Oo2 = null;
					OutputStream Oo3 = null;
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					Socket socket=s2.accept();
					System.out.println("�����������ɹ�");
					InputStream in1=socket.getInputStream();
					for(int i=0;i<clients.size();i++) {
						 if(clients.get(i).user.equals(user)) {
							 qqsit c=clients.get(i);
							 sleep(1000);
							 socket2=new Socket(c.socket.getInetAddress(),10006);
							 Oo2=socket2.getOutputStream();
						 }
						if( clients.get(i).user.equals(fri)) {
							 sleep(1000);
							qqsit c=clients.get(i);
							socket3=new Socket(c.socket.getInetAddress(),10006);
							 Oo3=socket3.getOutputStream();
						}	
					}
					System.out.println("����output�ɹ�");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					int len=0;
					byte []buf=new byte[1024];
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 Oo2.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 Oo3.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket2.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
						socket3.shutdownOutput();
						}
					}
					socket2.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
						socket3.close();
						}
					}
					System.out.println("��Ƶ�ϴ��ɹ�");
				}
				if(STR.equals("�����ļ�")){
					System.out.println("�������б��ļ������ɹ�");
					OutputStream Oo2 = null;
					OutputStream Oo3 = null;
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(user);
						}
					}
					Socket socket=s2.accept();
					System.out.println("�����������ɹ�");
					InputStream in1=socket.getInputStream();
					int len=0;
					byte []buf=new byte[1024];
					for(int i=0;i<clients.size();i++) {
						 if(clients.get(i).user.equals(user)) {
							 qqsit c=clients.get(i);
							 sleep(1000);
							 socket2=new Socket(c.socket.getInetAddress(),10006);
							 Oo2=socket2.getOutputStream();
						 }
						if(clients.get(i).user.equals(fri)) {
							 sleep(1000);
							qqsit c=clients.get(i);
							socket3=new Socket(c.socket.getInetAddress(),10006);
							 Oo3=socket3.getOutputStream();
						}	
					}
					System.out.println("����output�ɹ�");
					String ffhz=in.readLine();
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(user)||clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(ffhz);
						}
					}
					while((len=in1.read(buf))!=-1) {
						for(int i=0;i<clients.size();i++) {
							 if(clients.get(i).user.equals(user)) {
								 Oo2.write(buf,0,len);
							 }
							 if( clients.get(i).user.equals(fri)) {
								 Oo3.write(buf,0,len);
							 }
						 System.out.println("..");
						}
						}
					socket2.shutdownOutput();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
						socket3.shutdownOutput();
						}
					}
					socket2.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
						socket3.close();
						}
					}
					System.out.println("�ļ��ϴ��ɹ�");
				}
			      STR="";		    
			}
		}
		}     catch(SocketException e) {
		    	try { 
		    		MultiServer3.clients.remove(this);
		    		this.socket.close();
		    		System.out.println("�ͻ����Ѿ��˳�������");    	
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


