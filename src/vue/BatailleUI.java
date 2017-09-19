package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controleur.IControleur;

@SuppressWarnings("serial")
public class BatailleUI extends JFrame implements IBatailleUI {
	// attributs

	private JPanel panneauEntete;
	private int tailleX;
	private int TailleY;
	private TerrainDeJeu terrainJoueur;
	private TerrainDeJeu terrainAd;
	private ArrayList<JMenuItem> menu;
	private JPanel PanneauOption;
	private JPanel panneauMsg;
	private JLabel status;
	private JComboBox<String> typeDeJeu;
	private JComboBox<String> typeDeJoueur;
	private JComboBox<String> choixDeDiffuclté;
	private IControleur monControleur;
	private int[] casejouer = new int[2];
	// lemain batilleUI

	static public void main(String[] arg0) {
		new BatailleUI(10, 10);
	}
	// contructeur
	public BatailleUI(int tailleX, int tailleY) {
		super("Bataille navale");
		menu = new ArrayList<>();
		this.tailleX = tailleX;
		this.TailleY = tailleY;
		PanneauOption = new JPanel();
		panneauMsg = new JPanel();
		status = new JLabel("");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		ImageIcon icone = new ImageIcon("images/icone.jpg");
		setIconImage(icone.getImage());
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		String[] tabMenuJeu = { "Jouer", "Recommencer le Jeu", "Reprendre", "Pause", "Quitter" };
		String[] tabAcComMenuJeu = { "J", "RJ", "R", "P", "Q" };
		Boolean[] tabIsEnableJeu = { true, false, false, false, true };
		String[] tabMenuOp = { "Option Graphique", "Option Jeu" };
		String[] tabAcComMenuOp = { "OG", "OJ" };
		Boolean[] tabIsEnableOp = { true, true };

		panneauEntete = new JPanel();
		panneauEntete.setLayout(new GridLayout(0, 1));

		JMenu menuJeu = new JMenu("Jeu");
		// creation menu Jeu
		createMenu(menuJeu, tabMenuJeu, tabAcComMenuJeu, tabIsEnableJeu);

		JMenu menuOption = new JMenu("Option");
		// creation menu option
		createMenu(menuOption, tabMenuOp, tabAcComMenuOp, tabIsEnableOp);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuJeu);
		// menuBar.add(menuOption);
		panneauEntete.add(menuBar);
		add(panneauEntete, BorderLayout.NORTH);
		

		setVisible(true);

	}

	// mutateur et accesseurs
	@Override
	public ArrayList<JMenuItem> getMenu() {
		return menu;
	}

	@Override
	public JPanel getPanneauOption() {
		return PanneauOption;
	}

	@Override
	public void setPanneauOption(JPanel PanneauOption) {
		this.PanneauOption = PanneauOption;
	}

	@Override
	public JPanel getPanneauEntete() {
		return panneauEntete;
	}

	@Override
	public int getTailleX() {
		return tailleX;
	}

	@Override
	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}

	@Override
	public int getTailleY() {
		return TailleY;
	}

	@Override
	public void setTailleY(int tailleY) {
		TailleY = tailleY;
	}

	@Override
	public TerrainDeJeu getTerrainJoueur() {
		return terrainJoueur;
	}

	@Override
	public void setTerrainJoueur(TerrainDeJeu terrainJoueur) {
		this.terrainJoueur = terrainJoueur;
	}

	@Override
	public TerrainDeJeu getTerrainAd() {
		return terrainAd;
	}

	@Override
	public void setTerrainAd(TerrainDeJeu terrainAd) {
		this.terrainAd = terrainAd;
	}

	public JPanel getPanneauMsg() {
		return panneauMsg;
	}

	public void setPanneauMsg(JPanel panneauMsg) {
		this.panneauMsg = panneauMsg;
	}

	public JLabel getStatus() {
		return status;
	}

	public JComboBox<String> getTypeDeJeu() {
		return typeDeJeu;
	}

	public void setTypeDeJeu(JComboBox<String> typeDeJeu) {
		this.typeDeJeu = typeDeJeu;
	}

	public JComboBox<String> getTypeDeJoueur() {
		return typeDeJoueur;
	}

	public void setTypeDeJoueur(JComboBox<String> typeDeJoueur) {
		this.typeDeJoueur = typeDeJoueur;
	}

	public JComboBox<String> getChoixDeDiffuclté() {
		return choixDeDiffuclté;
	}

	public void setChoixDeDiffuclté(JComboBox<String> choixDeDiffuclté) {
		this.choixDeDiffuclté = choixDeDiffuclté;
	}

	@Override
	public IControleur getMonControleur() {
		return monControleur;
	}

	@Override
	public void setMonControleur(IControleur monControleur) {
		this.monControleur = monControleur;
	}

	@Override
	public int[] getCaseJoue() {
		return casejouer;
	}

	// methodes
	// methode pour desactiver tout les composant d'un conteneur de type object
	@Override
	public void setEnabledAll(Object object, boolean state) {
		if (object instanceof Container) {
			Container c = (Container) object;
			Component[] components = c.getComponents();
			for (Component component : components) {
				setEnabledAll(component, state);
				component.setEnabled(state);
				component.setBackground(new Color(196, 196, 245));
			}
		} else {
			if (object instanceof Component) {
				Component component = (Component) object;
				component.setEnabled(state);
			}
		}
	}
	

	// methode de creation de menu
	private void createMenu(JMenu nomMenu, String[] tabMenu, String[] tabAcComMenu, Boolean[] tabisEnable) {
		for (int i = 0; i < tabMenu.length; i++) {
			JMenuItem sousMenu = new JMenuItem(tabMenu[i]);
			sousMenu.setActionCommand(tabAcComMenu[i]);
			sousMenu.addActionListener(new MenuEvent(this));
			sousMenu.setEnabled(tabisEnable[i]);
			menu.add(sousMenu);
			nomMenu.add(sousMenu);

		}
	}

	@Override
	public void addComponent(Container obParent, Component obChild) {
		obParent.add(obChild);
	}

	public void setEnableChange(String[] lesSousMenu, boolean state) {
		for (JMenuItem sousmenu : menu) {
			for (String chaineAcComm : lesSousMenu)
				if (sousmenu.getActionCommand().equals(chaineAcComm))
					sousmenu.setEnabled(state);
		}
	}

	public void setCaseJoue(int posX, int posY) {
		casejouer[0] = posX;
		casejouer[1] = posY;
	}

	@Override
	public int getTypeJeu() {
		System.out.println((typeDeJeu.getSelectedItem()));
		return ((typeDeJeu.getSelectedItem()) == "Reseau" ? 1 : 2);
	}

	@Override
	public int getTypeJoueur() {
		System.out.println((typeDeJoueur.getSelectedItem()));
		return ((typeDeJoueur.getSelectedItem()) == "Serveur" ? 1 : 2);
	}

	@Override
	public int getdificulte() {
		int retour = -1;
		String choix = choixDeDiffuclté.getSelectedItem().toString();
		System.out.println(choix);
		switch (choix) {
		case "Facile":
			retour = 1;
			break;
		case "Moyen":
			retour = 2;
			break;
		case "Difficile":
			retour = 3;
			break;
		}
		return retour;
	}

	
}