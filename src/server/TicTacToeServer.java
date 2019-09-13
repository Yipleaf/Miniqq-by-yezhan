package server;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TicTacToeServer extends JFrame{
	ServerSocket serverSocket;
	 public static int JOUEUR1 = 1;
	  public static int JOUEUR2 = 2;
	  public static int JOUEUR1_GAGNE = 1;
	  public static int JOUEUR2_GAGNE = 2;
	  public static int EGALITE = 3;
	  public static int CONTINUEZ = 4;
  public TicTacToeServer() {
    try {
      serverSocket = new ServerSocket(8000);
      //jtaLog.append(new Date() + ": Serveur demarre sur le socket 8000\n");
      System.out.println(new Date() + ": ������������������˿ںţ� 8000\n");
      int sessionNo = 1;
      //while (true) {
        
    	System.out.println(new Date() + ": �ȴ���ң�������Ϸ��ţ� " + sessionNo + '\n');
    	Socket joueur1 = serverSocket.accept();
        
        System.out.println(new Date() + ": ���1���룬������Ϸ��ţ� " + sessionNo + '\n');
    	
        System.out.println("���1��ip��" + joueur1.getInetAddress().getHostAddress() + '\n');
        new DataOutputStream(joueur1.getOutputStream()).writeInt(JOUEUR1);

        Socket joueur2 = serverSocket.accept();
        
        System.out.println(new Date() + ": ���2���룬������Ϸ��ţ� " + sessionNo + '\n');
     
        System.out.println("���2��ip��" + joueur2.getInetAddress().getHostAddress() + '\n');
        new DataOutputStream(joueur2.getOutputStream()).writeInt(JOUEUR2);
       
        System.out.println(new Date() + ": �����̱߳�ţ� " + sessionNo++ + '\n');
        EtablirSession thread = new EtablirSession(joueur1, joueur2);
        thread.start();
     // }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }
  class EtablirSession extends Thread implements TicTacToeConstants {
	  private Socket joueur1;
	    private Socket joueur2;

	    private char[][] cell = new char[3][3];

	    public EtablirSession(Socket joueur1, Socket joueur2) {
	      this.joueur1 = joueur1;
	      this.joueur2 = joueur2;

	      for (int i = 0; i<3; i++)
	        for (int j = 0; j<3; j++)
	          cell[i][j] = ' ';
	    }

	    private void envMouv(DataOutputStream out, int ligne, int colonne) throws IOException {
	  	    out.writeInt(ligne);
	  	    out.writeInt(colonne);
	  	  }

	    public void run() {
	      try {
	        DataInputStream deJoueur1 = new DataInputStream(joueur1.getInputStream());
	        DataOutputStream pourJoueur1 = new DataOutputStream(joueur1.getOutputStream());
	        DataInputStream deJoueur2 = new DataInputStream(joueur2.getInputStream());
	        DataOutputStream pourJoueur2 = new DataOutputStream(joueur2.getOutputStream());

	        pourJoueur1.writeInt(1);//��ʼ֮������һ���������ҷ�һ��1������֪������Ҽ����ˣ���Ϸ��ʼ
	        while (true) {
	          int ligne = deJoueur1.readInt();
	          int colonne = deJoueur1.readInt();
	          cell[ligne][colonne] = 'X';
	          
	          if (aGagne('X')) {
	            pourJoueur1.writeInt(JOUEUR1_GAGNE);
	            pourJoueur2.writeInt(JOUEUR1_GAGNE);
	            envMouv(pourJoueur2, ligne, colonne);
	            break;
	          }
	          else if (estPleine()) {
	            pourJoueur1.writeInt(EGALITE);
	            pourJoueur2.writeInt(EGALITE);
	            envMouv(pourJoueur2, ligne, colonne);
	            break;
	          }
	          else {
	            pourJoueur2.writeInt(CONTINUEZ);
	            envMouv(pourJoueur2, ligne, colonne);
	          }

	          ligne= deJoueur2.readInt();
	          colonne= deJoueur2.readInt();
	          cell[ligne][colonne] = 'O';

	          if (aGagne('O')) {
	            pourJoueur1.writeInt(JOUEUR2_GAGNE);
	            pourJoueur2.writeInt(JOUEUR2_GAGNE);
	            envMouv(pourJoueur1, ligne, colonne);
	            break;
	          }
	          else {
	            pourJoueur1.writeInt(CONTINUEZ);
	            envMouv(pourJoueur1, ligne, colonne);
	          }
	        }
	        serverSocket.close();
	        System.out.println("��Ϸ������������");
	      }
	      catch(IOException ex) {
	        System.err.println(ex);
	      }
	    }
	    
	    
	    private boolean estPleine() {
	      for (int i=0; i<3; i++)
	        for (int j=0; j<3; j++)
	          if (cell[i][j] == ' ')
	          	return false;
	      return true;
	    }
	   
	    private boolean aGagne(char jeton) {
	      for (int i=0; i<3; i++)
	        if ((cell[i][0] == jeton)&&(cell[i][1] == jeton)&&(cell[i][2] == jeton)) {
	           return true;
	        }
	   
	      for (int j=0; j<3; j++)
	        if ((cell[0][j] == jeton)&&(cell[1][j] == jeton)&&(cell[2][j] == jeton)) {
	           return true;
	        }

	      if ((cell[0][0] == jeton)&&(cell[1][1] == jeton)&&(cell[2][2] == jeton)) {
	        return true;
	      }

	      if ((cell[0][2] == jeton)&&(cell[1][1] == jeton)&&(cell[2][0] == jeton)) {
	        return true;
	      }
	      return false;
	    }
	  }     
}


