package flotte;

import java.util.ArrayList;

public class Flotte {

	private ArrayList<Bateau> flotte;
	
	
	////////-----------CONSTRUCTEURS DE FLOTTE------------------//////////////
	
	public Flotte() {	
		boolean[] newDirection= setRandDir(4);
		
		for(int i=0; i<4; i++) {
			int j=i+1;
			if(j == 1) {j=3;}
			int newPosX = setRandPos(newDirection[i], j, true);
			int newPosY = setRandPos(newDirection[i], j, false);
			flotte.add(new Bateau(newPosX, newPosY, newDirection[i], 4 ));		
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
			newPos = (int) ((Math.random()*10)%(10-size)); 
		}
		else if (dir ^ type) {
			newPos= (int) ((Math.random()*10)%10); 
		}
		else {
			newPos = (int) ((Math.random()*10)%(10-size)); 
		}
		return newPos;
		
		
		
		
	};
	
	
	///////---------------------METHODES-----------------///////////////////
	

	
	
	
	
}
