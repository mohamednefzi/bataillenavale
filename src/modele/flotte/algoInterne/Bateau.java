package modele.flotte.algoInterne;
import java.util.ArrayList;
import modele.exeption.bateauDetruit;

public class Bateau {
	private boolean alive;
	private ArrayList<Pos> allPos;
	
	
	//Constructeur
	public Bateau(int posX, int posY , boolean direction, int taille){
		Pos posTete = new Pos(posX, posY);	
		this.alive = true;
		allPos = new ArrayList<Pos>(taille);
		allPos.add(posTete);
		
		if(direction == true) {
			for (int i = 0; i<taille; i++) {
				Pos oldPos = (Pos) allPos.get(i);
				Pos newPos = new Pos(oldPos.getPosX()+1,oldPos.getPosY());
				
				allPos.add(newPos);
			}
		}else {
			for (int i = 0; i<taille; i++) {
				Pos oldPos = (Pos) allPos.get(i);
				Pos newPos = new Pos(oldPos.getPosX(),oldPos.getPosY()+1);
				
				allPos.add(newPos);
			}	
		}
	}
	
	//accesseurs/methodes utiles
	public Pos getPosTete() {
		return this.allPos.get(0);
	}
	public int getTaille() {
		return this.allPos.size();
	} 
	public void checkAlive() throws bateauDetruit{
		int cptPdV = allPos.size();
		for(int i=0;i<allPos.size();i++) {
			if(allPos.get(i).isDestroyed()) {cptPdV -= 1;}
		}
		if(cptPdV == 0) {
			this.alive = false;
			throw new bateauDetruit(allPos);}		

	}
	public ArrayList<Pos> getAllPos(){
		return allPos;	
	};
	public boolean getAlive() {
		return this.alive;
	}
}
