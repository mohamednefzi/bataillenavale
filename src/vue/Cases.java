package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cases extends JButton {

	public Cases() {
		super();
		setPreferredSize(new Dimension(30, 30));
	}

	public int getPosX() {
		return getLocation().x / getWidth();
	}

	public int getPosY() {
		return getLocation().y / getHeight();
	}

	public void CaseJouer(int posX, int posY, int type) {
		if (getPosX() == posX && getPosY() == posY) {
			switch (type) {
			case 1:
				setBackground(Color.GREEN);
				break;
			case 2:
				setBackground(Color.ORANGE);
				break;
			default:
				setBackground(Color.RED);
				break;

			}

			setEnabled(false);
		}
	}
}

// class ecouteur
class ecouteur implements ActionListener {
	BatailleUI maBataille;

	public ecouteur(BatailleUI maBataille) {

		this.maBataille = maBataille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int posX = ((Cases) e.getSource()).getPosX();
		int posY = ((Cases) e.getSource()).getPosY();
		int type;
		maBataille.setCaseJoue(posX, posY);
		
		maBataille.getMonControleur().resultatCasesjoué(posX, posY);
		if(maBataille.getMonControleur().resultatCasesjoué(posX, posY).size()==0) {
			type=1;
		}else if(maBataille.getMonControleur().resultatCasesjoué(posX, posY).size()==1) {
			type=2;
		}else type=3;
		
		for (int[] uneCase : maBataille.getMonControleur().resultatCasesjoué(posX, posY)) {
			for (Cases C : maBataille.getTerrainJoueur().getLesCases()) {
				if (C.getPosX() == uneCase[0] && C.getPosY() == uneCase[1]) {
					C.CaseJouer(uneCase[0], uneCase[1],type);
				}
			}
			/*
			for (Cases C : maBataille.getTerrainAd().getLesCases()) {
				if (C.getPosX() == uneCase[0] && C.getPosY() == uneCase[1]) {
					C.CaseJouer(uneCase[0], uneCase[1],type);
				}
				
			}
	*/
		}
	}

}