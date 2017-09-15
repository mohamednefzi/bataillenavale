package model;

import java.util.ArrayList;

import flotte.Flotte;
import flotte.flotteDetruite;

public class IA {
	private int intel;
	private ArrayList<Position> historique;
	Flotte laFlotte;
	
	IA(int intel) {
		this.intel = intel;
		if(intel <= 2) {
			this.laFlotte = new Flotte(4);
		}else {this.laFlotte = new Flotte(5);}
	}
	
	
	public Position jouerCoup() {
		Position nouvCase = null;
		
		if(intel <= 1 ) {
			nouvCase = new Position((int) ((Math.random()*10)%10), (int) ((Math.random()*10)%10));
		} else {
			do {
				nouvCase = new Position((int) ((Math.random()*10)%10), (int) ((Math.random()*10)%10));
			}while(checkHistorique(nouvCase));
		}
		
		
	historique.add(nouvCase);	
	return nouvCase;
	}
	
	private boolean checkHistorique(Position nouvCase) {
		boolean result = true;
		
			for (int i = 0; i<historique.size(); i++) {
				if ((historique.get(i).getX()==nouvCase.getX())&&(historique.get(i).getY()==nouvCase.getY())) {
					result = true;
				}
			}
		
		return result;
	};
	
	public void subitCoup(Position attack) {
		Pos posAtt = new Pos(attack.getX(), attack.getY());
		
		try {
			laFlotte.collision(posAtt, true);
		} catch (flotteDetruite e) {
			isDie();
		}

		
	};
	
	private void isDie() {
		
		/////a Completer;
		
		
	}
	
	
}

class Position {
	private int x;
	private int y;
	
	Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
