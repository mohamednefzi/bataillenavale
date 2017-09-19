package modele.flotte;
import java.util.ArrayList;
import java.util.List;

import modele.exeption.bateauDetruit;
import modele.exeption.flotteDetruite;
import modele.flotte.algoInterne.Bateau;
import modele.flotte.algoInterne.Pos;

public class Flotte implements IFlotte{

	private ArrayList<Bateau> flotte = new ArrayList<Bateau>();


	////////-----------CONSTRUCTEURS DE FLOTTE ALEATOIRE------------------//////////////
	
	public Flotte(int qty) {	
		boolean[] newDirection= setRandDir(qty);
		
		for(int i=0; i<qty; i++) {
			int newPosX;
			int newPosY; 
			
			boolean collision = false;
			
			int j=i+1;
			if(j == 1) {j=3;}
			
			do {
				
				newPosX = setRandPos(newDirection[i], j, true);
				newPosY = setRandPos(newDirection[i], j, false);
				
				ArrayList<Pos> poss = new Bateau(newPosX, newPosY, newDirection[i], j).getAllPos();
				
				for(int k=0; k<j; k++) {	
					try {
						try {
							
							collision = collision(poss.get(k), false); 
							
							System.out.println("Collision dans constructeur flotte : " + collision);
							
						} catch (bateauDetruit e) {}
					} catch (flotteDetruite e) {}
				}
			}while (collision);
			flotte.add(new Bateau(newPosX, newPosY, newDirection[i], j ));
		}
	}
	private boolean[] setRandDir(int nbs) {
		boolean[] dirArray = new boolean[nbs];
		for(int i = 0; i<nbs; i++) {
			dirArray[i] = Math.random() < 0.5;	
		}
		return dirArray;
	}
	
	private int setRandPos( boolean dir, int size, boolean type) {
		int newPos;
		
		if (dir && type) {
			newPos =  ( (int)(Math.random()*10)%(10 - (size-1))); 
		}
		else if (dir ^ type) {
			newPos= (int) (Math.random()*10); 
		}
		else {
			newPos = ((int)(Math.random()*10) % (10 -(size-1))); 
		}
		return newPos;	
	};
	
	/////////--------------Constructeur de flotte Manuel-------------/////////////
	public Flotte() {
	}
	public void setNewShip(int posX, int posY , boolean direction, int taille) {
		flotte.add(new Bateau(posX, posY, direction, taille));
	}
	
	
	///////---------------------METHODES-----------------///////////////////
	
	public boolean collision(Pos aPos, boolean toDestroy) throws flotteDetruite, bateauDetruit {
		boolean result = false;
		
		if(flotte.size()!= 0) {
		for(int i = 0; i<flotte.size(); i++) {
			ArrayList<Pos> poss = flotte.get(i).getAllPos();
			
			for (int j =0; j<flotte.get(i).getTaille();j++) {
				
				if(aPos.compareTo(poss.get(j))) {
					result = true;
					if (toDestroy) {
						poss.get(j).destroy();
						try {
							flotte.get(i).checkAlive();
						} catch (bateauDetruit e) {
							if(checkFlotteLife()) {throw new flotteDetruite();}
							throw new bateauDetruit(e.getAllPos());
						}
					}
				}
			}
		}
		}
		return result;
	}
	@Override
	public boolean collision(int x, int y) throws flotteDetruite, bateauDetruit {
		boolean result = false;
		for(int i = 0; i<4; i++) {
			ArrayList<Pos> poss = flotte.get(i).getAllPos();
			Pos aPos = new Pos(x,y);
			
			for (int j =0; j<flotte.get(i).getTaille();i++) {
				
				if(aPos.compareTo(poss.get(j))) {
					result = true;
					poss.get(j).destroy();
					try {
						flotte.get(i).checkAlive();
					} catch (bateauDetruit e) {
						if(checkFlotteLife()) {throw new flotteDetruite();}
						throw new bateauDetruit(e.getAllPos());
					}
					
				}
			}
		}
		return result;
	}
	
	
	public boolean checkFlotteLife() {
		boolean result = true;
		int bateaux = flotte.size();
		for(int i = 0; i<flotte.size(); i++) {
			if (!flotte.get(i).getAlive()) {
				bateaux--;
			}				
		}
		if (bateaux == 0) {result = false;}			
		return result;
	}
	
	public ArrayList<int []> getAllPos(){
		ArrayList<int []> allPos = new ArrayList<>();

		for (int i=0; i<flotte.size();i++) {
			
			for(int j=0; j<flotte.get(i).getAllPos().size(); j++) {
				
				int Case[] = {flotte.get(i).getAllPos().get(j).getPosX(),flotte.get(i).getAllPos().get(j).getPosY(),i+1};
				allPos.add(Case);
			}
			
		}
		
		return allPos;
	}
	



public static void main(String[] args) {
	
	Flotte uneFlotte = new Flotte(4);
	
	ArrayList<int[]> allPos = uneFlotte.getAllPos();
	
	for(int[] p : uneFlotte.getAllPos()) {
		System.out.print("Premiere case x : "+ p[0]+" ; ");
		System.out.print("Premiere case y : "+ p[1]+" ; ");
		System.out.println("Premiere case id : "+ p[2]);
		
	}
	
	
}
}
