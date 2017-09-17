package vue;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controleur.IControleur;

public interface IBatailleUI {
	
	public ArrayList<JMenuItem> getMenu();
	public JPanel getPanneauOption() ;
	public void setPanneauOption(JPanel PanneauOption);
	public JPanel getPanneauEntete();
	public int getTailleX();
	public void setTailleX(int tailleX);
	public int getTailleY();
	public void setTailleY(int tailleY);
	public TerrainDejeu getTerrainJoueur() ;
	public void setTerrainJoueur(TerrainDejeu terrainJoueur);
	public TerrainDejeu getTerrainAd();
	public void setTerrainAd(TerrainDejeu terrainAd);
	public void setEnabledAll(Object object, boolean state);
	public void addComponent(Container obParent,Component obChild);
	public IControleur getMonControleur();
	public void setMonControleur(IControleur monControleur);
	public int[] getCaseJoue();
	
}
