package flotte;

class Pos{
	private int posX;
	private int posY;
	
	Pos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}

 public class Bateau {
	private Pos posTete;
	private boolean direction; //true = horizontale, false = vertical
	private int taille;
	private boolean alive;

	
	//Constructeur
	Bateau(int posX, int posY , boolean direction, int taille){
		this.posTete = new Pos(posX, posY);
		this.direction = direction;
		this.taille =  taille;	
		this.alive = true;
	}
	
	
	//accesseurs
	public Pos getPosTete() {
		return this.posTete;
	}
	public boolean getDirection() {
		return this.direction;
	}
	public int getTaille() {
		return this.taille;
	} 
	public boolean getAlive() {
		return this.alive;
	}
	
}
