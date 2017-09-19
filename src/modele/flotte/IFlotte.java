package modele.flotte;

import java.util.ArrayList;

import modele.exeption.bateauDetruit;
import modele.exeption.flotteDetruite;

public interface IFlotte {
	
	public boolean collision(int x, int y) throws flotteDetruite, bateauDetruit;
	public ArrayList<int []> getAllPos();
}
