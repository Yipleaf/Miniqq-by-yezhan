package Client;

import java.awt.event.*;
import java.awt.Color;  
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.net.*;  
import javax.swing.JFrame;  
public class client3 extends JFrame{  
      
    public Socket socket;  
    public Graphics2D g;  
    public ClientControl control;  
    //�����ʼ����socket  
    public client3() {  
        try {  
            socket = new Socket("localhost",9090);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
    }  
      
    public void initFrame() {  
        //���ô�������  
        this.setTitle("���续��");  
        this.setSize(600, 500);  
        this.setDefaultCloseOperation(3);  
        this.setLocationRelativeTo(null);  
        this.setVisible(true);  
        //�õ�����Ļ���  
        g = (Graphics2D)this.getGraphics();  
        g.setColor(Color.RED);  
        //��������ʼ��  
        control = new ClientControl(g,socket);  
        //���������  
        DrawListener listener = new DrawListener(control);  
        this.addMouseListener(listener);  
        this.addMouseMotionListener(listener);  
        //�������������ݲ���������  
        control.receiveData();  
    } 
    public class DrawListener extends MouseAdapter {  
        
        public int x1,y1,x2,y2,x4,y4;  
        public ClientControl control;  
          
        //���캯�����ܿ���������  
        public DrawListener(ClientControl control) {  
            this.control=control;  
        }  
      
        //������갴���¼�  
        public void mousePressed(MouseEvent e) {  
            //��ȡ��갴�µ�����ֵ  
            x1=e.getX();  
            y1=e.getY();  
            x4=x1;  
            y4=y1;  
        } 
          
        //��������϶��¼���Ǧ�ʹ��ܣ�  
        public void mouseDragged(MouseEvent e) {  
            int x3=e.getX();  
            int y3=e.getY();  
            control.g.drawLine(x1, y1, x3, y3);  
            control.sendData(1,x1, y1, x3, y3,1);  
            x1=x3;  
            y1=y3;  
        }  
    }  
  
}  
