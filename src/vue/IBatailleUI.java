package vue;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controleur.IControleur;

public interface IBatailleUI {

	public ArrayList<JMenuItem> getMenu();

	public JPanel getPanneauOption();

	public void setPanneauOption(JPanel PanneauOption);

	public JPanel getPanneauEntete();

	public int getTailleX();

	public void setTailleX(int tailleX);

	public int getTailleY();

	public void setTailleY(int tailleY);

	public TerrainDeJeu getTerrainJoueur();

	public void setTerrainJoueur(TerrainDeJeu terrainJoueur);

	public TerrainDeJeu getTerrainAd();

	public void setTerrainAd(TerrainDeJeu terrainAd);

	public void setEnabledAll(Object object, boolean state);

	public void addComponent(Container obParent, Component obChild);

	public IControleur getMonControleur();

	public void setMonControleur(IControleur monControleur);

	public int[] getCaseJoue();

	public int getTypeJeu();

	public int getTypeJoueur();

	public int getdificulte();

}
