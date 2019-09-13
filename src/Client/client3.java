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
    //界面初始化的socket  
    public client3() {  
        try {  
            socket = new Socket("localhost",9090);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
    }  
      
    public void initFrame() {  
        //设置窗体属性  
        this.setTitle("网络画板");  
        this.setSize(600, 500);  
        this.setDefaultCloseOperation(3);  
        this.setLocationRelativeTo(null);  
        this.setVisible(true);  
        //拿到窗体的画笔  
        g = (Graphics2D)this.getGraphics();  
        g.setColor(Color.RED);  
        //控制器初始化  
        control = new ClientControl(g,socket);  
        //添加鼠标监听  
        DrawListener listener = new DrawListener(control);  
        this.addMouseListener(listener);  
        this.addMouseMotionListener(listener);  
        //控制器接收数据并处理数据  
        control.receiveData();  
    } 
    public class DrawListener extends MouseAdapter {  
        
        public int x1,y1,x2,y2,x4,y4;  
        public ClientControl control;  
          
        //构造函数接受控制器对象  
        public DrawListener(ClientControl control) {  
            this.control=control;  
        }  
      
        //监听鼠标按下事件  
        public void mousePressed(MouseEvent e) {  
            //获取鼠标按下的坐标值  
            x1=e.getX();  
            y1=e.getY();  
            x4=x1;  
            y4=y1;  
        } 
          
        //监听鼠标拖动事件（铅笔功能）  
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
