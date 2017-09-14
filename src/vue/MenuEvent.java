package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			//panneauBateau.setLayout(new GridLayout(1, 0));
			for (int i = 1; i < 5; i++) {
				Bateau bateauJoueur = new Bateau(i);

				laBataille.addBateau(bateauJoueur);
				panneauBateau.add(bateauJoueur);

			}
			JButton bouttonPosition = new JButton("aléatoire");

			bouttonPosition.addActionListener(new ecouteurBouttonAleatoire(laBataille));
			panneauBateau.add(bouttonPosition);
			laBataille.add(panneauBateau,BorderLayout.SOUTH);
			
				
			JPanel terrainJeu=new JPanel();
			TerrainDejeu terrainJoueur =new TerrainDejeu(10,10);
			TerrainDejeu terrainAd=new TerrainDejeu(10,10);
			terrainJeu.add(terrainJoueur);
			terrainJeu.add(new JPanel());
			terrainJeu.add(terrainAd);
			laBataille.add(terrainJeu,BorderLayout.CENTER);
			setEnableChange(e,"RJ");
			
			SwingUtilities.updateComponentTreeUI(laBataille);
			break;
		case "RJ":
			laBataille.repaint();
			
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
		for(JMenuItem sousmenu:laBataille.getMenu()) {
			if(sousmenu.getActionCommand().equals(e.getActionCommand()))
				sousmenu.setEnabled(false);
			if(sousmenu.getActionCommand().equals(chaineAcComm))
				sousmenu.setEnabled(true);
			}
	}
}
class ecouteurBouttonAleatoire implements ActionListener{
	BatailleUI laBataille;
	
	public ecouteurBouttonAleatoire(BatailleUI uneBataille) {
		super();
		this.laBataille = uneBataille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (Bateau b : laBataille.getBateauJoueur()) {
			
			laBataille.setEnabledAll(((Object) b), false);

		}
	}
	
}

