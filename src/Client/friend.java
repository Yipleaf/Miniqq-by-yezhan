package Client;
import java.awt.BorderLayout;
import javax.swing.*;
public class friend {
	JButton ADD;
	 JTextField name;
public friend() {
	JFrame jf=new JFrame();
	   jf.setLocation(650,50);
	   jf.setSize(300,100);
	   jf.setLayout(new BorderLayout());
	   JLabel label=new JLabel("’À∫≈");
	   name=new JTextField(15);
	   name.setEditable(true);
	   ADD=new JButton("ÃÌº”");
	   jf.add(label,BorderLayout.WEST);
	   jf.add(name,BorderLayout.CENTER);
	   jf.add(ADD,BorderLayout.SOUTH);
	   jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   jf.setVisible(true);
}
public JButton getADD() {
	return ADD;
}
public JTextField getname() {
	return name;
}
}
