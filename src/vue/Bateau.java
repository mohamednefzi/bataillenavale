package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Bateau extends JPanel {
	private int nbCase;
	private int direction;
	private ArrayList<MorceauBateau> morceauBateau;

	public Bateau(int nbCase) {
		super();
		this.nbCase = nbCase;
		morceauBateau= new ArrayList<>();
		direction = (int) (Math.random() * 2);
		if (direction == 0) // direction horizontale
			setLayout(new GridLayout(this.nbCase, 1));
		else
			setLayout(new GridLayout(1, this.nbCase));
		for (int i = 0; i < this.nbCase; i++) {

			JButton partition = new JButton();
			partition.setBorderPainted(false);
			partition.setPreferredSize(new Dimension(30, 30));
			partition.setBackground(new Color(129, 129, 247));
			add(partition);
		}
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
	}

	public int getNbCase() {
		return nbCase;
	}

	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}


	public ArrayList<MorceauBateau> getMorceauBateau() {
		return this.morceauBateau;
	}

	public void setMorceauBateau(ArrayList<MorceauBateau> morceauBateau) {
		this.morceauBateau = morceauBateau;
	}

	
}



//classe morceau bateau encapsulé dans bateau
class MorceauBateau {
	private int posX = -1;
	private int posY = -1;
	private Boolean estTouche;

	public MorceauBateau(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		estTouche = false;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Boolean getEstTouche() {
		return estTouche;
	}

	public void setEstTouche(Boolean estTouche) {
		this.estTouche = estTouche;
	}
}

