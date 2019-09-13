package Client;
import java.awt.*;
import javax.swing.*;
public class login extends JFrame {
	public static final int width=700;
	public static final int height=700;
	public static void main()
	{
		login L=new login();
	}
	public login()
	{
		setSize(width,height);
		setLocation(500,220);
		Container content=getContentPane();
		JLabel a=new JLabel("µÇÂ¼³É¹¦");
		content.add(a);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
