package model;

public class Pos {
	
	private int posX;
	private int posY;
	
	public Pos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	
	public boolean compareTo(Pos aPos) {
		boolean result;
		int posX2;
		int posY2;
		
		posX2 = aPos.getPosX();
		posY2 = aPos.getPosY();
		
		if ((posX2 == this.posX)&&(posY2==this.posY)) {
			result =true;
		}else {result = false;}
		
		return result;
	}
	
	
}
