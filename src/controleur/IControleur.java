package controleur;

import java.util.ArrayList;





public interface IControleur {
	
	public boolean gameOver();
	public void destructionBateau(int[][] bateau);
	
	// appel methode couler si necessaire
	
	
	public ArrayList<int[]> creerBateau();
	public ArrayList<int[]> resultatCasesjoué(int posx,int posy);
	public void activeSocket();
	public void setAdresseIP(String adresseIP);
	public void startThread();

}
