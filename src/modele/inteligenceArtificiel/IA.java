package modele.inteligenceArtificiel;

import java.util.ArrayList;

import controleur.IControleur;
import modele.exeption.bateauDetruit;
import modele.exeption.flotteDetruite;
import modele.flotte.Flotte;
import modele.flotte.algoInterne.Pos;

public class IA implements IIA {
	private int intel;
	private ArrayList<Position> historique;
	Flotte laFlotte;
	IControleur unControler;
	
	public IA(int intel) {
		this.intel = intel;
		if(intel <= 2) {
			this.laFlotte = new Flotte(4);
		}else {this.laFlotte = new Flotte(5);}
	}
	
	
	public int[] jouerCoup() {
		Position nouvCase = null;
		
		if(intel <= 1 ) {
			nouvCase = new Position((int) ((Math.random()*10)%10), (int) ((Math.random()*10)%10));
		} else {
			do {
				nouvCase = new Position((int) ((Math.random()*10)%10), (int) ((Math.random()*10)%10));
			}while(checkHistorique(nouvCase));
		}
		
	historique.add(nouvCase);	
	
	int[] retour = {nouvCase.getX(), nouvCase.getY()};
	
	return retour;
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
	
	public void subitCoup(int x, int y) {
		Pos posAtt = new Pos(x , y);
		
		try {
			try {
				if(laFlotte.collision(posAtt, true)) {
					int [][] laCase = {{x,y}};
					unControler.destructionBateau(laCase);
				};
			} catch (bateauDetruit e) {
				detruireBateau(e.getAllPos());
				
			}
		} catch (flotteDetruite e) {
			unControler.gameOver();
		}

		
	};
	
	
	private void detruireBateau(ArrayList<Pos> lesPos) {
		int [][] touteLesPos= new int[lesPos.size()][2];
		
		for (int i=0; i< lesPos.size();i++) {
			
			touteLesPos[i][0]= lesPos.get(i).getPosX();
			touteLesPos[i][1]= lesPos.get(i).getPosY();
			
		}
			
		
		
		unControler.destructionBateau(touteLesPos);
	};
	


	@Override
	public void setControler(IControleur unController) {
		// TODO Auto-generated method stub
		
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
