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
				if(STR.equals("������")) {
					TicTacToeServer frame = new TicTacToeServer();
				}
				if(STR.equals("����ͼƬ")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("�������б�ͼƬ�����ɹ�");
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
					System.out.println("�����������߼����ɹ�");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("�����������߼����ɹ�");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
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
					System.out.println("ͼƬ�ϴ��ɹ�");
					socket.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.close();
					}
					}
				}
				if(STR.equals("������Ƶ")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("�������б���Ƶ�����ɹ�");
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
					System.out.println("�����������߼����ɹ�");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("�����������߼����ɹ�");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
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
					System.out.println("��Ƶ�ϴ��ɹ�");
					socket.close();
					for(int i=0;i<clients.size();i++) {
					if( clients.get(i).user.equals(fri)) {
					socket2.close();
					}
					}
				}
				if(STR.equals("�����ļ�")){
					Socket socket = null;
					Socket socket2 =null;
					System.out.println("�������б��ļ������ɹ�");
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
					System.out.println("�����������߼����ɹ�");
					for(int i=0;i<clients.size();i++) {
						if( clients.get(i).user.equals(fri)) {
							qqsit c=clients.get(i);
				        	c.send2(fri);
							socket2=s2.accept();
						}
					}	
					System.out.println("�����������߼����ɹ�");
					InputStream in1=socket.getInputStream();
					OutputStream out1=socket.getOutputStream();
					OutputStream out2=socket2.getOutputStream();
					int len=0;
					byte []buf=new byte[1024];
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
					System.out.println("�ϴ��ɹ�");
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


