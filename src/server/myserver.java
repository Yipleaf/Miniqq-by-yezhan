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
            System.out.println("�������Ѿ�����......");  
            while(true){  
                Socket socket =server.accept();  
                System.out.println("�пͻ������ӽ�����......");  
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
                //��ȡ���������  
                InputStream ins =socket.getInputStream();  
                OutputStream ous = socket.getOutputStream();  
                //���ֽ�����װ��������  
                dis=new DataInputStream(ins);  
                dos=new DataOutputStream(ous);  
                while(true){  
                    int value=dis.read();  
//                  System.out.println(value);  
                    //������ת����Ϣ  
                    for (int i = 0; i <myserver.list.size(); i++) {  
                        ServerThread st =myserver.list.get(i);  
                        if(st!=this){  
                            st.dos.write(value);  
                            st.dos.flush();  
                        }  
                    }  
//                  System.out.println("�������Ѿ�������Ϣ");  
                }  
            }catch (SocketException e) {  
                try {  
                    myserver.list.remove(this);  
                    this.socket.close();  
                    System.out.println("�ͻ����Ѿ��˳�......");  
//                  JOptionPane.showMessageDialog(null, "�ͻ����˳���");  
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



  

