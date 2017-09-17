package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
			laBataille.getStatus().setText("");
			if (laBataille.getPanneauMsg().getComponentCount() == 0) {
				String nom = JOptionPane.showInputDialog(null, "entrez votre pseudo", "", JOptionPane.QUESTION_MESSAGE);

				JLabel message = new JLabel("Bienvenue au jeu Bataille Navale " + (nom != null ? nom : ""));

				laBataille.getPanneauMsg().add(message);

				laBataille.addComponent(laBataille.getPanneauEntete(), laBataille.getPanneauMsg());
				JPanel panneauStatus = new JPanel();
				panneauStatus.add(laBataille.getStatus());
				laBataille.addComponent(laBataille.getPanneauEntete(), panneauStatus);

			}

			
			laBataille.setTypeDeJeu(new JComboBox<>());
			laBataille.setTypeDeJoueur(new JComboBox<>());
			laBataille.setChoixDeDiffuclté(new JComboBox<>());
			
			laBataille.getTypeDeJeu().addItem("Reseau");
			laBataille.getTypeDeJeu().addItem("Ordinateur");
			laBataille.getTypeDeJeu().addItemListener(new SelectionEvents(laBataille));
			laBataille.getTypeDeJoueur().addItem("Serveur");
			laBataille.getTypeDeJoueur().addItem("Client");

			laBataille.getChoixDeDiffuclté().addItem("Facile");
			laBataille.getChoixDeDiffuclté().addItem("moyen");
			laBataille.getChoixDeDiffuclté().addItem("Difficile");

			JButton bouttonPosition = new JButton("Commencer à jouer");

			bouttonPosition.addActionListener(new ecouteurBouttonAleatoire(laBataille));
			laBataille.addComponent(laBataille.getPanneauOption(), laBataille.getTypeDeJeu());
			laBataille.addComponent(laBataille.getPanneauOption(), laBataille.getTypeDeJoueur());
			laBataille.addComponent(laBataille.getPanneauOption(), laBataille.getChoixDeDiffuclté());
			laBataille.addComponent(laBataille.getPanneauOption(), bouttonPosition);
			laBataille.add(laBataille.getPanneauOption(), BorderLayout.SOUTH);

			JPanel terrainJeu = new JPanel();
			laBataille.setTerrainJoueur(new TerrainDejeu(laBataille.getTailleX(), laBataille.getTailleY(), false,laBataille));
			laBataille.setTerrainAd(new TerrainDejeu(laBataille.getTailleX(), laBataille.getTailleY(), true,laBataille));

			terrainJeu.add(laBataille.getTerrainAd());
			terrainJeu.add(new JPanel());
			terrainJeu.add(laBataille.getTerrainJoueur());

			laBataille.add(terrainJeu, BorderLayout.CENTER);
			String[] Jactive = { "RJ", "P" };
			String[] Jdesactive = { "J", "R" };
			laBataille.setEnableChange(Jactive, true);
			laBataille.setEnableChange(Jdesactive, false);
			break;
		
		case "RJ":
			laBataille.getStatus().setText("Cliquer sur Jouer");
			laBataille.getContentPane().removeAll();
			laBataille.add(laBataille.getPanneauEntete(), BorderLayout.NORTH);
			laBataille.getPanneauOption().removeAll();
			
			String[] RJactive = { "J" };
			String[] RJdesactive = { "RJ", "R", "P" };
			laBataille.setEnableChange(RJactive, true);
			laBataille.setEnableChange(RJdesactive, false);

			break;
		case "R":
			laBataille.getStatus().setText("le Jeu a Repris");
			String[] Ractive = { "RJ", "P" };
			String[] Rdesactive = { "J", "R" };
			laBataille.setEnableChange(Ractive, true);
			laBataille.setEnableChange(Rdesactive, false);

			laBataille.getContentPane().setEnabled(false);
			break;
		case "P":
			System.out.println("pause");
			laBataille.getStatus().setText("Pause");
			laBataille.getContentPane().setEnabled(false);
			String[] Pactive = { "RJ", "R" };
			String[] Pdesactive = { "J", "P" };
			laBataille.setEnableChange(Pactive, true);
			laBataille.setEnableChange(Pdesactive, false);

			break;
		case "Q":
			System.exit(0);
			break;
		default:
			break;
		}
		SwingUtilities.updateComponentTreeUI(laBataille);
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
		System.out.println(laBataille.getTypeDeJoueur().getSelectedItem());
		if (laBataille.getTypeDeJoueur().getSelectedItem() == "Client"&&laBataille.getTypeDeJoueur().isEnabled()) {
			String IPServeur = JOptionPane.showInputDialog(null, "Entrez l'IP du Serveur", "",
					JOptionPane.QUESTION_MESSAGE);
			System.out.println(IPServeur);
		}

		for(int[] tab:laBataille.getMonControleur().creerBateau()) {
			System.out.println("X="+tab[0]+"  Y="+tab[1]+"  id="+tab[2]);
			laBataille.getTerrainJoueur().positionnerMorceauBateau(tab[0], tab[1], tab[2]);
			
		}
		
		laBataille.setEnabledAll(laBataille.getPanneauOption(), false);
	}
	
}

class SelectionEvents implements ItemListener  {

	BatailleUI laBataille;
	
	public SelectionEvents(BatailleUI laBataille) {
		super();
		this.laBataille = laBataille;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange() == ItemEvent.SELECTED) {
	          Object i = e.getItem();
	          String item=i.toString();
	          	if(item=="Ordinateur") {
	          		laBataille.getTypeDeJoueur().setEnabled(false);
	          	}
	          	else laBataille.getTypeDeJoueur().setEnabled(true);
	       }
	}
		
}
