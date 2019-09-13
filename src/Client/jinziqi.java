package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class jinziqi extends JFrame implements Runnable {
	  public static int JOUEUR1 = 1;
	  public static int JOUEUR2 = 2;
	  public static int JOUEUR1_GAGNE = 1;
	  public static int JOUEUR2_GAGNE = 2;
	  public static int EGALITE = 3;
	  public static int CONTINUEZ = 4;
	  private boolean monTour = false;
	  private char monJeton = ' ';
	  private char autreJeton = ' ';
	  private Cell[][] cell = new Cell[3][3];
	  private JLabel jlblTitre = new JLabel();
	  private JLabel jlblStatus = new JLabel();
	  private int ligneChoisie;
	  private int colonneChoisie;
	  private DataInputStream deServer;
	  private DataOutputStream pourServer;
	  private boolean continueDeJouer = true;
	  private boolean attend = true;
	  //private boolean isStandAlone = false;
	  private String host = "localhost";
	  
	 
public jinziqi() {
	setSize(300,300);
	JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 3, 0, 0));
    for (int i=0; i<3; i++)
      for (int j=0; j<3; j++)
        p.add(cell[i][j] = new Cell(i, j));

    p.setBorder(new LineBorder(Color.black, 1));
    jlblTitre.setHorizontalAlignment(JLabel.CENTER);
    jlblTitre.setFont(new Font("SansSerif", Font.BOLD, 16));
    jlblTitre.setBorder(new LineBorder(Color.black, 1));
    jlblStatus.setBorder(new LineBorder(Color.black, 1));

    this.getContentPane().add(jlblTitre, BorderLayout.NORTH);
    this.getContentPane().add(p, BorderLayout.CENTER);
    this.getContentPane().add(jlblStatus, BorderLayout.SOUTH);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    connexionAuServer();
}
	  private void connexionAuServer() {
	    try {
	      Socket socket;
	      //if (isStandAlone)
	        socket = new Socket(host, 8000);
	     // else
	       // socket = new Socket(getCodeBase().getHost(), 8000);
	      deServer = new DataInputStream(socket.getInputStream());
	      pourServer = new DataOutputStream(socket.getOutputStream());
	    }
	    catch (Exception ex) {
	      System.err.println(ex);
	    }
	    Thread thread = new Thread(this);
	    thread.start();
	  }
	  public void run() {
	    try {
	      int joueur = deServer.readInt();
	      if (joueur == JOUEUR1) {
	        monJeton = 'X';
	        autreJeton = 'O';
	        jlblTitre.setText("我是玩家1，我的符号是'X' ");
	        jlblStatus.setText("我的对手是玩家2");
	        deServer.readInt();//接收服务器发过来的jourer1或2
	        jlblStatus.setText("玩家2加入了战斗");
	        monTour = true;
	      }
	      else if (joueur == JOUEUR2) {
	        monJeton = 'O';
	        autreJeton = 'X';
	        jlblTitre.setText("我是玩家2，我的符号是'O' ");
	        jlblStatus.setText("我的对手是玩家1");
	      }
	      
	      while (continueDeJouer) {
	        if (joueur == JOUEUR1) {
	          waitForPlayerAction();
	          envMouv();
	          recevoirInfoDuServer();
	        }
	        else if (joueur == JOUEUR2) {
	          recevoirInfoDuServer();
	          waitForPlayerAction();
	          envMouv();
	        }
	      }
	    }
	    catch (Exception ex) {
	    }
	  }
	      

	  private void waitForPlayerAction() throws InterruptedException {
	    while (attend) {
	      Thread.sleep(100);
	    }
	    attend = true;
	  }


	  private void envMouv() throws IOException {
	    pourServer.writeInt(ligneChoisie);
	    pourServer.writeInt(colonneChoisie);
	  }


	  private void recevoirInfoDuServer() throws IOException {
	    int status = deServer.readInt();
	 
	    if (status == JOUEUR1_GAGNE) {
	      continueDeJouer = false;
	      if (monJeton == 'X') {
	        jlblStatus.setText("I WON! (X)");
	      }
	      else if (monJeton == 'O') {
	        jlblStatus.setText("玩家1 (X) WON!");
	        recevoirMouv();
	      }
	    }

	    else if (status == JOUEUR2_GAGNE) {
	      continueDeJouer = false;
	      if (monJeton == 'O') {
	        jlblStatus.setText("I WON! (O)");
	      }
	      else if (monJeton == 'X') {
	        jlblStatus.setText("玩家2 (O) WON!");
	        recevoirMouv();
	      }
	    }
	    else if (status == EGALITE) {
	      continueDeJouer = false;
	      jlblStatus.setText("游戏结束，平局！");
	      if (monJeton == 'O') {
	    	  recevoirMouv();
	      }
	    }
	    else {
	      recevoirMouv();
	      jlblStatus.setText("轮到我了");
	      monTour = true;
	    }
	  }



	  private void recevoirMouv() throws IOException {
	    int ligne = deServer.readInt();
	    int colonne = deServer.readInt();
	    cell[ligne][colonne].setJeton(autreJeton);
	  }


	  private class Cell extends JPanel implements MouseListener {
	    private int ligne;
	    private int colonne;
	    private char jeton=' ';
	    
	    public Cell(int ligne, int colonne) {
	      this.ligne = ligne;
	      this.colonne = colonne;
	      setBorder(new LineBorder(Color.black, 1));
	      addMouseListener(this);
	    }
	    
	  
	    public void setJeton(char c) {
	      jeton = c;
	      repaint();
	    }
	  

	    protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      
	      if (jeton == 'X') {
	        g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
	        g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
	      }
	      else if (jeton == 'O') {
	        g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
	      }
	    }


	    public void mouseClicked(MouseEvent e) {
	      if ((jeton == ' ')&& monTour) {
	        setJeton(monJeton);
	        monTour = false;
	        ligneChoisie = ligne;
	        colonneChoisie = colonne;
	        jlblStatus.setText("等待其他玩家");
	        attend = false;
	      }
	    }
	    public void mousePressed(MouseEvent e) { }
	    public void mouseReleased(MouseEvent e) { }
	    public void mouseEntered(MouseEvent e) { }
	    public void mouseExited(MouseEvent e) { }
	  }
	}
