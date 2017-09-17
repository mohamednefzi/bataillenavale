package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class Case extends JButton{
	
	public Case() {
		super();
		
		setPreferredSize(new Dimension(30, 30));
	}

	public int getPosX() {
		return getLocation().x / getWidth();
	}

	public int getPosY() {
		return getLocation().y / getHeight();
	}

	void CaseJouer(int posX, int posY, int type) {

		if (getPosX() == posX && getPosY() == posY) {
			switch (type) {
			case 1:
				setBackground(Color.ORANGE);
				break;
			case 2:
				setBackground(Color.RED);
				break;
			default:
				setBackground(Color.GREEN);
				break;

			}

			setEnabled(false);

		}

	}

}

@SuppressWarnings("serial")
class TerrainDejeu extends JPanel {
	ArrayList<Case> lesCases;
	int tailleX;
	int tailleY;
	boolean estclicable;
	BatailleUI mabataille;
	//Constructeur
	public TerrainDejeu(int tailleX, int tailleY, boolean estclicable,BatailleUI mabataille) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.estclicable = estclicable;
		this.mabataille=mabataille;
		lesCases = new ArrayList<>();
		setLayout(new GridLayout(tailleX, tailleY));

		for (int i = 0; i < tailleY; i++) {
			for (int j = 0; j < tailleX; j++) {
				Case boutton = new Case();
				boutton.setEnabled(this.estclicable);
				boutton.addActionListener(new ecouteur(this,mabataille));
				lesCases.add(boutton);
				this.add((JButton) boutton);

			}
		}
		
		if (this.estclicable == true) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
		}else {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		}
	}

	public ArrayList<Case> getLesCases() {
		return lesCases;
	}

	public void setLesCases(ArrayList<Case> lesCases) {
		this.lesCases = lesCases;
	}

	void positionnerMorceauBateau(int posX, int posY, int id) {
		for (Case uneCase : lesCases) {
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

// class ecouteur
class ecouteur implements ActionListener {
	JPanel monPanneau;
 BatailleUI maBataille;
	public ecouteur(JPanel monPanneau,BatailleUI maBataille) {

		this.monPanneau = monPanneau;
		this.maBataille=maBataille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int posX = ((Case) e.getSource()).getPosX();
		int posY = ((Case) e.getSource()).getPosY();
		int type = 2;
		maBataille.setCaseJoue(posX, posY);
		System.out.println(maBataille.getCaseJoue()[0] + "=X   Y=" + maBataille.getCaseJoue()[1]);
		System.out.println(((Case) e.getSource()).getPosX() + "=X   Y=" + ((Case) e.getSource()).getPosY());
		((Case) e.getSource()).CaseJouer(posX, posY, type);
		for(Case C:maBataille.getTerrainJoueur().getLesCases())
		{
			if(C.getPosX()==posX&&C.getPosY()==posY) {
				C.CaseJouer(posX, posY, type);
			}
		}

	}

}