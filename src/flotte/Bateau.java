package flotte;
import java.util.ArrayList;

import model.Pos;

public class Bateau {
	private Pos posTete;
	private boolean direction; //true = horizontale, false = vertical
	private int taille;
	private boolean alive;

	
	//Constructeur
	Bateau(int posX, int posY , boolean direction, int taille){
		this.posTete = new Pos(posX, posY);
		this.direction = direction;
		this.taille =  taille;	
		this.alive = true;
	}
	
	
	//accesseurs
	public Pos getPosTete() {
		return this.posTete;
	}
	public boolean getDirection() {
		return this.direction;
	}
	public int getTaille() {
		return this.taille;
	} 
	public boolean getAlive() {
		return this.alive;
	}
	public ArrayList<Pos> getAllPos(){
		ArrayList<Pos> allPos = new ArrayList<Pos>(taille);
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
		
		return allPos;
	}
	
	
}
