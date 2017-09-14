package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BatailleUI extends JFrame {
	// attributs
	private ArrayList<Bateau> bateauJoueur;
	private JPanel panneauEntete;
	private ArrayList <JMenuItem> menu= new ArrayList<>();
	public static void main(String[] args) {
		new BatailleUI();
	}

	// contructeur
	public BatailleUI() {
		super("Bataille navale");
		bateauJoueur = new ArrayList<Bateau>();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		ImageIcon icone = new ImageIcon("images/icone.jpg");
		setIconImage(icone.getImage());

		Toolkit outil = getToolkit();
		this.setSize(outil.getScreenSize());
		
		String[] tabMenuJeu = { "Jouer", "Recommencer le Jeu", "Reprendre", "Pause", "Quitter" };
		String[] tabAcComMenuJeu = { "J", "RJ", "P", "R", "Q" };
		Boolean[] tabIsEnableJeu = { true, false, false, true, true };
		String[] tabMenuOp = { "Option Graphique", "Option Jeu"};
		String[] tabAcComMenuOp = {"OG","OJ"};
		Boolean[] tabIsEnableOp = { true,true};
		
		panneauEntete= new JPanel();
		panneauEntete.setLayout(new GridLayout(0,1));
		
		JMenu menuJeu = new JMenu("Jeu");
		// creation menu Jeu
		createMenu(menuJeu,tabMenuJeu,tabAcComMenuJeu,tabIsEnableJeu);
		
		JMenu menuOption = new JMenu("Option");
		//creation menu option
		createMenu(menuOption,tabMenuOp,tabAcComMenuOp,tabIsEnableOp);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuJeu);
		//menuBar.add(menuOption);
		panneauEntete.add(menuBar);
		add(panneauEntete,BorderLayout.NORTH);
		

		setVisible(true);

	}

	// mutateur et accesseurs
	public ArrayList<Bateau> getBateauJoueur() {
		return bateauJoueur;
	}

	public void setBateauJoueur(ArrayList<Bateau> bateauJoueur) {
		this.bateauJoueur = bateauJoueur;
	}

	public ArrayList<JMenuItem> getMenu() {
		return menu;
	}

	// methodes
	public void addBateau(Bateau bateau) {
		bateauJoueur.add(bateau);
	}

	// methode pour desactiver tout les composant d'un conteneur de type object
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
	private void createMenu(JMenu nomMenu,String[]tabMenu,String[] tabAcComMenu,Boolean[] tabisEnable) {
		for(int i=0;i<tabMenu.length;i++) {
			JMenuItem sousMenu = new JMenuItem(tabMenu[i]);
			sousMenu.setActionCommand(tabAcComMenu[i]);
			sousMenu.addActionListener(new MenuEvent(this));
			sousMenu.setEnabled(tabisEnable[i]);
			menu.add(sousMenu);
			nomMenu.add(sousMenu);
			
		}
	}
	
	void addComponent(Container obParent,Component obChild) {
		 obParent.add(obChild);
	}

	public JPanel getPanneauEntete() {
		return panneauEntete;
	}

	
}