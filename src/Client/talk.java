package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
public class talk  {
//�����ı�����
public JTextArea A=new JTextArea(10,10);
public JTextPane AA=new JTextPane();
//���ô��ڣ�
JFrame frame;
//���ð�ť��
JButton b1=new JButton("����");
JButton b2=new JButton("�ر�");
JMenuItem pic;
JMenuItem file;
JMenuItem txt;
JMenuItem Tic;
//���ñ��鰴ť��
JButton a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,
a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35,a36;
public talk()
{	
	frame=new JFrame();
	//���û�������
	frame.setSize(700,700);
	Container a=frame.getContentPane();
	a.setLayout(new BorderLayout());
	frame.setLocation(500,100);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//��������
	A.setFont(new Font("����",Font.BOLD,16));
	A.setLineWrap(true);
	AA.setFont(new Font("����",Font.BOLD,16));
	AA.setEditable(false);
	//AA.setLineWrap(true);
	//����ͼƬ
	ImageIcon ph=new ImageIcon("5.png");
	JLabel yzh3=new JLabel(ph);
	//���ò˵�
	JMenuBar mBar=new JMenuBar();
	file=new JMenuItem("video");
	pic=new JMenuItem("picture");
	txt=new JMenuItem("File");
	Tic=new JMenuItem("TicTacToe");
	JMenuItem face=new JMenuItem("face");
	//��������Ӽ�����
	faceMenu f=new faceMenu();
	face.addActionListener(f);
	mBar.add(file);
	mBar.add(pic);
	mBar.add(txt);
	mBar.add(Tic);
	mBar.add(face);
	//���ù�����:
	JScrollPane sp=new JScrollPane(AA);
	//���ð�ť
	Color c=new Color(104,119,249);
	b1.setBackground(c);
	b2.setBackground(c);
	//�������
	JPanel b=new JPanel();
	JPanel B=new JPanel();
	JPanel bb=new JPanel();
	//������
	a.add(b,BorderLayout.SOUTH);
	a.add(B,BorderLayout.CENTER);
	//�ȸ�B��b�����ֹ�����
	b.setLayout(new BorderLayout());
	B.setLayout(new BorderLayout());
	//��b��B������
	b.add(A,BorderLayout.CENTER);
	b.add(bb,BorderLayout.SOUTH);
	b.add(mBar,BorderLayout.NORTH);
	bb.add(b1);
	bb.add(b2);
	//B.add(AA,BorderLayout.CENTER);
	B.add(sp,BorderLayout.CENTER);
	sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	DefaultCaret caret = (DefaultCaret)AA.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	B.add(yzh3,BorderLayout.EAST);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(3);
}
public void face() {
	JFrame facemenu =new JFrame();
	facemenu.setSize(500,260);
	facemenu.setLocation(500,290);
	facemenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	Container a=facemenu.getContentPane();
	//���ò��ֹ�������
	a.setLayout(new FlowLayout(5));
	//���ñ��鰴ť�ļ�������
	faceButton fb=new faceButton();
	//��������ӱ��飺
	ImageIcon ph1=new ImageIcon("#01.png");
	a1=new JButton(ph1);
	a1.addMouseListener(fb);
	
	ImageIcon ph2=new ImageIcon("#02.png");
	a2=new JButton(ph2);
	a2.addMouseListener(fb);
	
	ImageIcon ph3=new ImageIcon("#03.png");
	a3=new JButton(ph3);
	a3.addMouseListener(fb);
	
	ImageIcon ph4=new ImageIcon("#04.png");
	a4=new JButton(ph4);
	a4.addMouseListener(fb);
	
	ImageIcon ph5=new ImageIcon("#05.png");	
	a5=new JButton(ph5);
	a5.addMouseListener(fb);
	
	ImageIcon ph6=new ImageIcon("#06.png");
	a6=new JButton(ph6);
	a6.addMouseListener(fb);
	
	ImageIcon ph7=new ImageIcon("#07.png");
	a7=new JButton(ph7);
	a7.addMouseListener(fb);
	
	ImageIcon ph8=new ImageIcon("#08.png");
	a8=new JButton(ph8);
	a8.addMouseListener(fb);
	
	ImageIcon ph9=new ImageIcon("#09.png");
	a9=new JButton(ph9);
	a9.addMouseListener(fb);
	
	ImageIcon ph10=new ImageIcon("#10.png");
	a10=new JButton(ph10);
	a10.addMouseListener(fb);
	
	ImageIcon ph11=new ImageIcon("#11.png");
	a11=new JButton(ph11);
	a11.addMouseListener(fb);
	
	ImageIcon ph12=new ImageIcon("#12.png");
	a12=new JButton(ph12);
	a12.addMouseListener(fb);
	
	ImageIcon ph13=new ImageIcon("#13.png");
	a13=new JButton(ph13);
	a13.addMouseListener(fb);
	
	ImageIcon ph14=new ImageIcon("#14.png");
	a14=new JButton(ph14);
	a14.addMouseListener(fb);
	
	ImageIcon ph15=new ImageIcon("#15.png");
	a15=new JButton(ph15);
	a15.addMouseListener(fb);
	
	ImageIcon ph16=new ImageIcon("#16.png");
	a16=new JButton(ph16);
	a16.addMouseListener(fb);
	
	ImageIcon ph17=new ImageIcon("#17.png");
	a17=new JButton(ph17);
	a17.addMouseListener(fb);
	
	ImageIcon ph18=new ImageIcon("#18.png");
	a18=new JButton(ph18);
	a18.addMouseListener(fb);
	
	ImageIcon ph19=new ImageIcon("#19.png");
	a19=new JButton(ph19);
	a19.addMouseListener(fb);
	
	ImageIcon ph20=new ImageIcon("#20.png");
	a20=new JButton(ph20);
	a20.addMouseListener(fb);
	
	ImageIcon ph21=new ImageIcon("#21.png");
	a21=new JButton(ph21);
	a21.addMouseListener(fb);
	
	ImageIcon ph22=new ImageIcon("#22.png");
	a22=new JButton(ph22);
	a22.addMouseListener(fb);
	
	ImageIcon ph23=new ImageIcon("#23.png");
	a23=new JButton(ph23);
	a23.addMouseListener(fb);
	
	ImageIcon ph24=new ImageIcon("#24.png");
	a24=new JButton(ph24);
	a24.addMouseListener(fb);
	
	ImageIcon ph25=new ImageIcon("#25.png");
	a25=new JButton(ph25);
	a25.addMouseListener(fb);
	
	ImageIcon ph26=new ImageIcon("#26.png");
	a26=new JButton(ph26);
	a26.addMouseListener(fb);
	
	ImageIcon ph27=new ImageIcon("#27.png");
	a27=new JButton(ph27);
	a27.addMouseListener(fb);
	
	ImageIcon ph28=new ImageIcon("#28.png");
	a28=new JButton(ph26);
	a28.addMouseListener(fb);
	
	ImageIcon ph29=new ImageIcon("#29.png");
	a29=new JButton(ph29);
	a29.addMouseListener(fb);
	
	ImageIcon ph30=new ImageIcon("#30.png");
	a30=new JButton(ph30);
	a30.addMouseListener(fb);
	
	ImageIcon ph31=new ImageIcon("#31.png");
	a31=new JButton(ph31);
	a31.addMouseListener(fb);
	
	ImageIcon ph32=new ImageIcon("#32.png");
	a32=new JButton(ph32);
	a32.addMouseListener(fb);
	
	ImageIcon ph33=new ImageIcon("#33.png");
	a33=new JButton(ph33);
	a33.addMouseListener(fb);
	
	ImageIcon ph34=new ImageIcon("#34.png");
	a34=new JButton(ph34);
	a34.addMouseListener(fb);
	
	ImageIcon ph35=new ImageIcon("#35.png");
	a35=new JButton(ph35);
	a35.addMouseListener(fb);
	
	ImageIcon ph36=new ImageIcon("#36.png");
	a36=new JButton(ph36);
	a36.addMouseListener(fb);

	a.add(a1);
	a.add(a2);
	a.add(a3);
	a.add(a4);
	a.add(a5);
	a.add(a6);
	a.add(a8);
	a.add(a9);
	a.add(a10);
	a.add(a11);
	a.add(a12);
	a.add(a13);
	a.add(a14);
	a.add(a15);
	a.add(a16);
	a.add(a17);
	a.add(a18);
	a.add(a19);
	a.add(a20);
	a.add(a21);
	a.add(a22);
	a.add(a23);
	a.add(a24);
	a.add(a25);
	a.add(a26);
	a.add(a27);
	a.add(a28);
	a.add(a29);
	a.add(a30);
	a.add(a31);
	a.add(a32);
	a.add(a33);
	a.add(a34);
	a.add(a35);
	a.add(a36);
	facemenu.setVisible(true);
}
//���ô򿪱���ļ�������
public class faceMenu implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("face")) {
			face();
		}
	}
}
//���ðѱ���ת���ɱ�ʶ��������������
public class faceButton extends MouseAdapter { 
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==a1){
			A.append("#01");
		}
		if(e.getSource()==a2) {
			A.append("#02");
		}
		if(e.getSource()==a3) {
			A.append("#03");
		}
		if(e.getSource()==a4) {
			A.append("#04");
		}
		if(e.getSource()==a5) {
			A.append("#05");
		}
		if(e.getSource()==a6) {
			A.append("#06");
		}
		if(e.getSource()==a7) {
			A.append("#07");
		}
		if(e.getSource()==a8) {
			A.append("#08");
		}
		if(e.getSource()==a9) {
			A.append("#09");
		}
		if(e.getSource()==a10) {
			A.append("#10");
		}
		if(e.getSource()==a11) {
			A.append("#11");
		}
		if(e.getSource()==a12) {
			A.append("#12");
		}
		if(e.getSource()==a13) {
			A.append("#13");
		}
		if(e.getSource()==a14) {
			A.append("#14");
		}
		if(e.getSource()==a15) {
			A.append("#15");
		}
		if(e.getSource()==a16) {
			A.append("#16");
		}
		if(e.getSource()==a17) {
			A.append("#17");
		}
		if(e.getSource()==a18) {
			A.append("#18");
		}
		if(e.getSource()==a19) {
			A.append("#19");
		}
		if(e.getSource()==a20) {
			A.append("#20");
		}
		if(e.getSource()==a21) {
			A.append("#21");
		}
		if(e.getSource()==a22) {
			A.append("#22");
		}
		if(e.getSource()==a23) {
			A.append("#23");
		}
		if(e.getSource()==a24) {
			A.append("#24");
		}
		if(e.getSource()==a25) {
			A.append("#25");
		}
		if(e.getSource()==a26) {
			A.append("#26");
		}
		if(e.getSource()==a27) {
			A.append("#27");
		}
		if(e.getSource()==a28) {
			A.append("#28");
		}
		if(e.getSource()==a29) {
			A.append("#29");
		}
		if(e.getSource()==a30) {
			A.append("#30");
		}
		if(e.getSource()==a31) {
			A.append("#31");
		}
		if(e.getSource()==a32) {
			A.append("#32");
		}
		if(e.getSource()==a33) {
			A.append("#33");
		}
		if(e.getSource()==a34) {
			A.append("#34");
		}
		if(e.getSource()==a35) {
			A.append("#35");
		}
		if(e.getSource()==a36) {
			A.append("#36");
		}
	}
}
public JTextPane getUp() {
	return AA;
}
public JTextArea getDown() {
	return A;
}
public JButton getsend() {
	return b1;
}
public JButton getclose() {
	return b2;
}
public JMenuItem getpic() {
	return pic;
}
public JMenuItem getvideo() {
	return file;
}
public JMenuItem getfil() {
	return txt;
}
public JMenuItem getTic() {
	return Tic;
}
public void manageinfo(String info) {
	 int length=info.length();//��ȡString ����
	  char[] every=new char[length];
	  int count=0;//��ʼ�ַ���λ�ã��仯
	  String path="D://abcd//";	  
	             //ʵ��insertString()�ı�Ҫǰ��
	  Document doc = AA.getStyledDocument();//AAAAA   ����insertStirn                                 �õ�doc,insertIcon�õ�MsgArea
	  SimpleAttributeSet attr=new SimpleAttributeSet();
	            
	  Boolean hadjin=false;
	   
	   for(int i=0;i<length;i++)
	  {
	   every[i]=info.charAt(i);
	     if(every[i]=='#') {  //ʶ����Ϣ���Ƿ���#��
	    hadjin=true;
	    System.out.println("��⵽#��");
	    }	    
	  }
	                             //��ʼ�������Ϸ  
	  for(int i=0;i<length;i++)
	  {
	   if(!hadjin)
	    break;
	   if(every[i]=='#')
	   {
	    String str=null;
	       str=info.substring(count,i);      //�õ�����ǰ������    
	    try{
	     if(str!=null)
	       doc.insertString(doc.getLength(), str, attr);//��ӱ���ǰ������
	     System.out.println("������ǰ�������");
	    }catch(Exception e){
	     System.out.println("a big error here");
	    }
	    String icName;
	    icName=info.substring(i, i+3);                   //�õ����������  #01
	    Icon ic=new ImageIcon(path+icName+".png");//������ת��Ϊicon
	    AA.setCaretPosition(doc.getLength());

	//��ȡ��ǰ��λ�ã���������뵽��ǰ����~~~~

	//û�д˱��齫������JTextPane�����Ϸ����޷������ڵ�ǰ��
	   AA.insertIcon(ic); //�������               
	  System.out.println("������ͼƬ");

	   count=i+3;//���ַ���ʼλ������������һλ��
	    }
	  }
	  if(count<=length)
	  {
	   String theLast=null;
	   theLast=info.substring(count, length);
	   try{
	    doc.insertString(doc.getLength(), theLast, attr);
	    }catch(Exception e){
	     System.out.println("a big error here");
	    }
	  }
}
public void manageinfo2(String info) {
	String path="D://server_pic//";	  //ͼƬ�����·��
Document doc = AA.getStyledDocument();
Icon ic=new ImageIcon(path+info);
AA.setCaretPosition(doc.getLength());
AA.insertIcon(ic);
}
}
