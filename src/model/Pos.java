package model;

public class Pos {
	
	private int posX;
	private int posY;
	private boolean destroyed;
	
	public Pos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		this.destroyed = false;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public boolean isDestroyed() {
		return this.destroyed;
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
	
	public void destroy() {
		this.destroyed = true;
	}
	
	
}
