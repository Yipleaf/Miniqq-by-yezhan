package Client;
import java.awt.*;
import javax.swing.*;
public class mLogin  {
	JMenuItem menu1;
	JPanel jp2;
public  mLogin(){
	JPanel jp1=new JPanel();
	jp2=new JPanel();
	jp1.setLayout(new BorderLayout());
   	jp1.add(jp2,BorderLayout.CENTER);
   	JPanel jp2=new JPanel();
   	jp2.setBounds(0, 0, 345, 565);
   	jp2.setLayout(new GridLayout());
   	JFrame jf=new JFrame();
	   jf.setLocation(650,50);
	   jf.setSize(345,745);
	   jf.setResizable(false); 
	   jf.setLayout(new BorderLayout());
	   JPanel p1=new JPanel();
	   ImageIcon img1=new ImageIcon("1.jpg");
	   JLabel lab1=new JLabel(img1);
	   p1.setPreferredSize(new Dimension(0,160));
	   lab1.setBounds(0,0,345,160);
	   p1.add(lab1);
	   jf.add(p1,BorderLayout.NORTH);
	   jf.setVisible(true);
	   jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   JTabbedPane jtp= new JTabbedPane();
	   jtp.addTab("联系人",jp1);
	   jtp.addTab("群",jp2); 
	   JMenuBar jam=new JMenuBar();
	   menu1=new JMenuItem("添加好友");
	    JMenuItem menu2=new JMenuItem("删除好友");
	    	jam.add(menu1);
	    	jam.add(menu2);
	   jf.add(jam,BorderLayout.SOUTH);
	   jf.add(jtp,BorderLayout.CENTER);
	   jtp.setFont(new Font("微软雅黑",0,15));
}
public JMenuItem getmenu1() {
	return menu1;
}
public JPanel getjp1() {
	return jp2;
}
}
