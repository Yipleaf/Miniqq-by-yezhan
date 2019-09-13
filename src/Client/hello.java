package Client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.JFileChooser;

public class hello {
	public static int a;
public static void main(String[]arg0) throws IOException {
	InetAddress ia=InetAddress.getLocalHost();	
	System.out.println(ia);
	JFileChooser fd=new JFileChooser();
	fd.showOpenDialog(null);
	File ff = fd.getSelectedFile();
	String ffName=ff.getName();
	System.out.println(ffName);
	String ffHouZhui=ffName.substring(ffName.lastIndexOf("."),ffName.length());
	System.out.println(ffHouZhui);
}
}
