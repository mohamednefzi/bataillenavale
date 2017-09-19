package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import modele.flotte.Flotte;
import modele.flotte.IFlotte;
import modele.inteligenceArtificiel.IIA;
import vue.Cases;
import vue.IBatailleUI;

public class Controleur<Bateau> extends Thread implements IControleur {
	IBatailleUI laBataille;
	ArrayList<int[]> tableauVue;
	IFlotte maFlotte;
	IIA IA;
	PrintWriter pw;
	BufferedReader input;
	String adresseIP = "localhost";
	boolean aMoiDeJouer;

	public static void main(String[] args) {

	}

	// Constructeur
	public Controleur(IBatailleUI laBataille) {
		super();
		this.laBataille = laBataille;
		this.tableauVue = new ArrayList<int[]>();
		maFlotte = new Flotte(4);

	}

	// Getteur et setteur

	public IIA getIA() {
		return IA;
	}

	public void setIA(IIA iA) {
		IA = iA;
	}

	public String getAdresseIP() {
		return adresseIP;
	}

	public void setAdresseIP(String adresseIP) {
		this.adresseIP = adresseIP;
	}

	// methode
	@Override
	public ArrayList<int[]> creerBateau() {
		return maFlotte.getAllPos();
	}

	@Override
	public ArrayList<int[]> resultatCasesjoué(int posx, int posy) {
		return tableauVue;
	}

	/// METHODE CONTROLE MODELS

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void destructionBateau(int[][] bateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activeSocket() {
		aMoiDeJouer = (laBataille.getTypeJoueur() == 1);
		if (laBataille.getTypeJoueur() == 1) {
			ServerSocket monServerSocket;
			try {
				monServerSocket = new ServerSocket(777);
				System.out.println("Serveur : attente de connexion...");

				// attente de la connexion d'un client
				Socket monSocket = monServerSocket.accept();

				// connexion établie
				System.out.println("Serveur : Connexion établie");

				// envoie d'une chaîne de caractères
				pw = new PrintWriter(monSocket.getOutputStream(), true);
				InputStreamReader in = new InputStreamReader(monSocket.getInputStream());
				input = new BufferedReader(in);
				if (!gameOver())
					monSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				Socket monSocket = new Socket(adresseIP, 777);

				System.out.println("Client: connexion etablie");
				// connexion établie
				System.out.println("Serveur : Connexion établie");

				// envoie d'une chaîne de caractères
				pw = new PrintWriter(monSocket.getOutputStream(), true);
				InputStreamReader in = new InputStreamReader(monSocket.getInputStream());
				input = new BufferedReader(in);
				if (!gameOver())
					monSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run() {
		ArrayList<int[]> resultat = new ArrayList<>();
		String c;
		int type;
		if (aMoiDeJouer) {
			while (!gameOver()) {
				for (int a : laBataille.getCaseJoue()) {
					System.out.println("envoie Client" + a);
					pw.println(a);
				}
				try {
					while ((c = input.readLine()) != null && c != "pasTouché" && c != "gameover") {
						String C1 = input.readLine();
						int[] Case = new int[2];
						Case[0] = Integer.parseInt(c);
						Case[1] = Integer.parseInt(C1);
						resultat.add(Case);
					}
					if (c.equals("pasTouché")) {
						int[] Case = new int[2];
						Case[0] = laBataille.getCaseJoue()[0];
						Case[1] = laBataille.getCaseJoue()[1];
						type = 1;
						resultat.add(Case);
					} else if (resultat.size() == 1) {
						type = 2;
					} else {
						type = 3;
					}
					if (c != "pasTouché" && c != "gameover") {
						for (int[] uneCase : resultat) {
							for (Cases C : laBataille.getTerrainAd().getLesCases()) {
								if (C.getPosX() == uneCase[0] && C.getPosY() == uneCase[1]) {
									C.CaseJouer(uneCase[0], uneCase[1], type);
								}
							}
						}
						aMoiDeJouer=false;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			int posX=-1, posY=-1;
			try {
				while ((c = input.readLine()) != null && c != "pasTouché" && c != "gameover") {
					String C1 = input.readLine();
					posX = Integer.parseInt(c);
					posY = Integer.parseInt(C1);
					
				}
				
				for (int[] a : resultatCasesjoué(posX, posY)) {
					System.out.println("envoie Client" + a);
					pw.println(a[0]);
					pw.println(a[1]);
				}
				aMoiDeJouer=true;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	public void startThread() {
		this.start();
	}
}
