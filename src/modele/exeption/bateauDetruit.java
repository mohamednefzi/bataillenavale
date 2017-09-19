package modele.exeption;

import java.util.ArrayList;

import modele.flotte.algoInterne.Pos;

@SuppressWarnings("serial")
public class bateauDetruit extends Exception{
	private ArrayList<Pos> allPos;
	
	public bateauDetruit(ArrayList<Pos> allPos) {
		this.allPos = allPos;
	}
	
	public ArrayList<Pos> getAllPos() {
		return allPos;
	}
}
