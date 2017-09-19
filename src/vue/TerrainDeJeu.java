package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TerrainDeJeu extends JPanel {
	ArrayList<Cases> lesCases;
	int tailleX;
	int tailleY;
	boolean estclicable;
	BatailleUI mabataille;

	// Constructeur
	public TerrainDeJeu(int tailleX, int tailleY, boolean estclicable, BatailleUI mabataille) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.estclicable = estclicable;
		this.mabataille = mabataille;
		lesCases = new ArrayList<>();
		setLayout(new GridLayout(tailleX, tailleY));

		for (int i = 0; i < tailleY; i++) {
			for (int j = 0; j < tailleX; j++) {
				Cases boutton = new Cases();
				boutton.setEnabled(this.estclicable);
				boutton.addActionListener(new ecouteur(mabataille));
				lesCases.add(boutton);
				this.add((JButton) boutton);

			}
		}

		if (this.estclicable == true) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
		} else {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		}
	}

	public ArrayList<Cases> getLesCases() {
		return lesCases;
	}

	void positionnerMorceauBateau(int posX, int posY, int id) {
		for (Cases uneCase : lesCases) {
			if (uneCase.getPosX() == posX && uneCase.getPosY() == posY) {
				switch (id) {
				case 1:
					uneCase.setBackground(new Color(131, 132, 47));
					break;
				case 2:
					uneCase.setBackground(new Color(100, 111, 111));
					break;
				case 3:
					uneCase.setBackground(new Color(218, 7, 218));
					break;
				case 4:
					uneCase.setBackground(new Color(129, 129, 247));
					break;
				}

				uneCase.setEnabled(false);

			}

		}

	}

}
