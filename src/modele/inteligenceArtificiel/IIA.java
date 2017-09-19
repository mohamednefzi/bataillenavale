package modele.inteligenceArtificiel;

import controleur.IControleur;

public interface IIA {

	public int[] jouerCoup();
	public void subitCoup(int x, int y);

	public void setControler(IControleur unController);
}
