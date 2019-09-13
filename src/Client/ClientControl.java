package Client;
import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Graphics2D;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;   
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
public class ClientControl {  
      
    private Socket socket;  
    public int x1,y1,x2,y2,color;  
    public byte type,strock; 
    public Graphics2D g;  
    public static void main(String[] args) {  
        client3 ui = new client3();  
        ui.initFrame();  
    }    
    public ClientControl(Graphics2D g,Socket socket) {  
        this.g=g;  
        this.socket=socket;  
    }  
  
    //���Ͻ��ܷ��������͹�������Ϣ  
    public void receiveData(){  
        new Thread(){  
            public void run() {  
                try {  
                while(true){  
                        //��ȡ���������  
                        InputStream ins = socket.getInputStream();  
                        //���ֽ�����װ��������  
                        DataInputStream dis=new DataInputStream(ins);    
                        //��ͼ������  
                        type=dis.readByte();  
                          
                        //������  
                        x1=dis.readInt();  
                        y1=dis.readInt();  
                        x2=dis.readInt();  
                        y2=dis.readInt();  
                        //����ϸ  
                        strock=dis.readByte();  
                        //����ɫ  
                        color=dis.readInt();  
                        drawGra();  
                }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            };  
        }.start();  
    }  
      
    //����ͼ�����ݸ�������  
    public void sendData(int type,int x1,int y1,int x2,int y2,int strock){  
        try {  
            OutputStream ous=socket.getOutputStream(); 
            DataOutputStream dos= new DataOutputStream(ous);  
            //дͼ������  
            dos.writeByte(type);  
            //д����  
            dos.writeInt(x1);  
            dos.writeInt(y1);  
            dos.writeInt(x2);  
            dos.writeInt(y2);  
            //д��ϸ  
            dos.writeByte(strock);  
            //д��ɫ  
            dos.writeInt(g.getColor().getRGB());  
            dos.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    //����ͼ��  
    public void drawGra() {  
        g.setColor(new Color(color));  
        g.setStroke(new BasicStroke(strock));  
        if(type==1){  
            g.drawLine(x1, y1, x2, y2);  
        }  
    }  
  
}  
