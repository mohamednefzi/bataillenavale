package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class MenuEvent implements ActionListener {
	BatailleUI laBataille;

	MenuEvent(BatailleUI laBataille) {

		this.laBataille = laBataille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuSelect = ((JMenuItem) e.getSource()).getActionCommand();
		switch (menuSelect) {
		case "J":
			String nom = JOptionPane.showInputDialog(null, "entrez votre pseudo", "", JOptionPane.QUESTION_MESSAGE);

			JLabel message = new JLabel("Bienvenue au jeu Bataille Navale " + (nom != null ? nom : ""));

			JPanel panneauMsg = new JPanel();
			panneauMsg.add(message);
			laBataille.addComponent(laBataille.getPanneauEntete(), panneauMsg);
			JPanel panneauBateau = new JPanel();
			// panneauBateau.setLayout(new GridLayout(1, 0));
			for (int i = 1; i < 5; i++) {
				int nbMoBa = (i == 1 ? 2 : i);
				Bateau bateauJoueur = new Bateau(nbMoBa);

				laBataille.addBateau(bateauJoueur);
				panneauBateau.add(bateauJoueur);

			}
			JButton bouttonPosition = new JButton("aléatoire");

			bouttonPosition.addActionListener(new ecouteurBouttonAleatoire(laBataille));
			panneauBateau.add(bouttonPosition);
			laBataille.add(panneauBateau, BorderLayout.SOUTH);

			JPanel terrainJeu = new JPanel();
			TerrainDejeu terrainJoueur = new TerrainDejeu(laBataille.getTailleX(), laBataille.getTailleY());
			TerrainDejeu terrainAd = new TerrainDejeu(laBataille.getTailleX(), laBataille.getTailleY());
			terrainJeu.add(terrainJoueur);
			terrainJeu.add(new JPanel());
			terrainJeu.add(terrainAd);
			laBataille.add(terrainJeu, BorderLayout.CENTER);
			setEnableChange(e, "RJ");

			SwingUtilities.updateComponentTreeUI(laBataille);
			break;
		case "RJ":
			// rafraichir la fenetre
			break;
		case "R":
			// Reprende le jeu
			break;
		case "P":
			// appliquer une pausea la fenetre
			break;
		case "Q":
			System.exit(0);
			break;
		default:
			break;
		}
	}

	// permet d'activer desactiver des sous menu au besoin
	private void setEnableChange(ActionEvent e, String chaineAcComm) {
		for (JMenuItem sousmenu : laBataille.getMenu()) {
			if (sousmenu.getActionCommand().equals(e.getActionCommand()))
				sousmenu.setEnabled(false);
			if (sousmenu.getActionCommand().equals(chaineAcComm))
				sousmenu.setEnabled(true);
		}
	}
}

class ecouteurBouttonAleatoire implements ActionListener {
	BatailleUI laBataille;

	public ecouteurBouttonAleatoire(BatailleUI uneBataille) {
		super();
		this.laBataille = uneBataille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (Bateau b : laBataille.getBateauJoueur()) {

			placerBateauAléatoire(b);
			laBataille.setEnabledAll(((Object) b), false);

		}
	
	}

	void placerBateauAléatoire(Bateau unBateau) {
		int posX;
		int posY;
		boolean bateauPlace = false;
		do {

			posX = (int) (Math.random() * 10);
			posY = (int) (Math.random() * 10);
			if (validerPositionBateau(posX, posY, unBateau) && IsInside(posX, posY, unBateau)) {
				bateauPlace = true;
			}

		} while (!bateauPlace);
		insererBateau(posX, posY, unBateau);
	}

	boolean validerPositionBateau(int posX, int posY, Bateau unBateau) {
		boolean placeValide = true;
		int j, i;
		int dir = unBateau.getDirection();
		int nbCase = unBateau.getNbCase();
		Bateau unBateauEnPlace;
		if (laBataille.getBateauJoueurEnPlace().size() > 0) {
			if (dir == 0) {
				i = posX;
				while (i < posX + nbCase && placeValide) {
					j = 0;
					while (j < laBataille.getBateauJoueurEnPlace().size() && placeValide) {
						unBateauEnPlace = laBataille.getBateauJoueurEnPlace().get(j);
						if (!testerMorceauBateau(i, posY, dir, unBateauEnPlace.getMorceauBateau())) {

							placeValide = false;
						} else {
							j++;
						}

					}
					i++;
				}
			} else {
				i = posX;
				while (i < posY + nbCase && placeValide) {
					j = 0;
					while (j < laBataille.getBateauJoueurEnPlace().size() && placeValide) {
						unBateauEnPlace = laBataille.getBateauJoueurEnPlace().get(j);
						if (!testerMorceauBateau(posX, i, dir, unBateauEnPlace.getMorceauBateau())) {

							placeValide = false;
						} else {
							j++;
						}

					}
					i++;
				}
			}
		}
		return placeValide;
	}

	boolean testerMorceauBateau(int posX, int posY, int dir, ArrayList<MorceauBateau> MoBa) {
		boolean morceauLibre = true;
		int i = 0;
		while (i < MoBa.size() && morceauLibre) {
			if (MoBa.get(i).getPosX() == posX && MoBa.get(i).getPosY() == posY) {
				morceauLibre = false;
			} else {
				i++;
				
			}

		}
		
		return morceauLibre;
	}

	boolean IsInside(int posX, int posY, Bateau unBateau) {
		boolean isInside;
		int tailleX = laBataille.getTailleX();
		int tailleY = laBataille.getTailleY();
		int dir = unBateau.getDirection();
		int nbCase = unBateau.getNbCase();
		if ((dir == 0 && posX + nbCase < tailleX) || (dir == 1 && posY + nbCase < tailleY)) {

			isInside = true;
		} else
			isInside = false;
		return isInside;
	}

	void insererBateau(int posX, int posY, Bateau unBateau) {
		int dir = unBateau.getDirection();
		int nbCase = unBateau.getNbCase();
		ArrayList<MorceauBateau> MoBa = new ArrayList<>();
		if (dir == 0) {
			for (int i = 0; i < nbCase; i++) {
				MoBa.add(new MorceauBateau(posX + i, posY));

			}

		} else {
			for (int i = 0; i < nbCase; i++) {
				MoBa.add(new MorceauBateau(posX, posY + i));

			}

		}
		unBateau.setMorceauBateau(MoBa);
		laBataille.addBateauEnplace(unBateau);
	}

}
