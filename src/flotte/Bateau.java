package flotte;
import java.util.ArrayList;

import model.Pos;

public class Bateau {
	private Pos posTete;
	private boolean direction; //true = horizontale, false = vertical
	private int taille;
	private boolean alive;

	private ArrayList<Pos> allPos;
	
	//Constructeur
	Bateau(int posX, int posY , boolean direction, int taille){
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
	public boolean checkAlive() {
		boolean result=true;
		int cptPdV = allPos.size();
		for(int i=0;i<allPos.size();i++) {
			if(allPos.get(i).isDestroyed()) {cptPdV -= 1;}
		}
		if(cptPdV == 0) {result = false;}	
			
		return result;
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
