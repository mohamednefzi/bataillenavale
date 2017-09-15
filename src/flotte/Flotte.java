package flotte;

import java.util.ArrayList;
import model.Pos;

public class Flotte {

	private ArrayList<Bateau> flotte;


	////////-----------CONSTRUCTEURS DE FLOTTE IA------------------//////////////
	
	public Flotte(int qty) {	
		boolean[] newDirection= setRandDir(qty);
		
		for(int i=0; i<qty; i++) {
			int newPosX;
			int newPosY; 
			
			int j=i+1;
			if(j == 1) {j=3;}
			
			boolean noColision;
			do {
				noColision = true;
				newPosX = setRandPos(newDirection[i], j, true);
				newPosY = setRandPos(newDirection[i], j, false);
				
				ArrayList<Pos> poss = new Bateau(newPosX, newPosY, newDirection[i], j ).getAllPos();
				
				for(int k=0; k<j; k++) {	
					try {
						if(collision(poss.get(k), false)) {
							noColision = false;
						}
					} catch (flotteDetruite e) {}
				}
				
			}while (!noColision);
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
			newPos = (int) ((Math.random()*10)-(size-1)); 
		}
		else if (dir ^ type) {
			newPos= (int) (Math.random()*10); 
		}
		else {
			newPos = (int) ((Math.random()*10)-(size-1)); 
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
	
	public boolean collision(Pos aPos, boolean toDestroy) throws flotteDetruite {
		boolean result = false;
		
		for(int i = 0; i<4; i++) {
			ArrayList<Pos> poss = flotte.get(i).getAllPos();
			
			for (int j =0; j<flotte.get(i).getTaille();i++) {
				
				if(aPos.compareTo(poss.get(j))) {
					result = true;
					if (toDestroy) {
						aPos.destroy();
						if(!flotte.get(i).checkAlive()) {
							if(checkFlotteLife()) {throw new flotteDetruite();}
						}
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
	
	
}
