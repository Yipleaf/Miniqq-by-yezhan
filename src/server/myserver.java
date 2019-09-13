package server;

import java.io.*; 
import java.net.*;  
import java.net.Socket;  
import java.util.ArrayList;
  
public class myserver {  
      
    public ServerSocket server;  
      
    public static ArrayList<ServerThread> list = new ArrayList<ServerThread>();
    
    public static void main(String[] args) {  
        myserver ms = new myserver();  
        ms.startServer();  
    }
      
    public void startServer() {  
          
        try {  
            server = new ServerSocket(9090);  
            System.out.println("服务器已经建立......");  
            while(true){  
                Socket socket =server.accept();  
                System.out.println("有客户端连接进来了......");  
                ServerThread st = new ServerThread(socket);  
                st.start();  
                list.add(st);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
    public class ServerThread extends Thread{  
        
        public Socket socket;  
        public DataInputStream dis ;  
        public DataOutputStream dos;  
          
        public ServerThread(Socket socket) {  
            this.socket=socket;  
        }  
      
        public void run() {  
            try {  
                //获取输入输出流  
                InputStream ins =socket.getInputStream();  
                OutputStream ous = socket.getOutputStream();  
                //将字节流包装成数据流  
                dis=new DataInputStream(ins);  
                dos=new DataOutputStream(ous);  
                while(true){  
                    int value=dis.read();  
//                  System.out.println(value);  
                    //服务器转发消息  
                    for (int i = 0; i <myserver.list.size(); i++) {  
                        ServerThread st =myserver.list.get(i);  
                        if(st!=this){  
                            st.dos.write(value);  
                            st.dos.flush();  
                        }  
                    }  
//                  System.out.println("服务器已经发送消息");  
                }  
            }catch (SocketException e) {  
                try {  
                    myserver.list.remove(this);  
                    this.socket.close();  
                    System.out.println("客户端已经退出......");  
//                  JOptionPane.showMessageDialog(null, "客户端退出！");  
                } catch (IOException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }  
//              e.printStackTrace();  
            }   
            catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }   
}  



  

