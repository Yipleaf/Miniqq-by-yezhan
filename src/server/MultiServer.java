package server;
import java.io.*;
import java.util.*;
import java.net.*;
public class MultiServer {
static final int PORT=8098;
public static List<qqserver>clients=new ArrayList<qqserver>();
static qqserver c;
public static void main(String[]args) throws IOException{
	ServerSocket s=new ServerSocket(PORT);
	System.out.println("服务器启动");
	try {
		while(true) {
			Socket socket=s.accept();
			
			try {
				c=new qqserver(socket);
				System.out.println("客户端连接。。。");
				clients.add(c);
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
private InputStream In;
private FileOutputStream dos;
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
	      while (true) {  
	        String str = in.readLine();
	        if (str.equals("END")) break;
	        for(int i=0;i<clients.size();i++) {
	        	qqserver c=clients.get(i);
	        	c.send(str,socket);
	        }
	      }
	    } catch(SocketException e) {
	    	try { 
	    		MultiServer.clients.remove(this);
	    		this.socket.close();
	    		System.out.println("客户端已经退出。。。");    	
	    	}
		     catch(IOException e1) {
		    	  e1.printStackTrace(); 
		     }  
	    	}
	    	catch(Exception e2) {
	        System.err.println("Socket not closed");
	      }
}
	public void send(String str,Socket c) {
		   System.out.println("Echoing: " + str);
	       out.println(c.getInetAddress()+":"+str);
	}	
}

}
