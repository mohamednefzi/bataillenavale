package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial") 
class Case extends JButton{

	public Case() {
		super();
		
		setPreferredSize(new Dimension(30,30));
	}
 public int getPosX() {
	 return getLocation().x/getWidth();
 }
 public int getPosY() {
	 return getLocation().y/getHeight();
 }
		
}

@SuppressWarnings("serial")
class TerrainDejeu extends JPanel {
	int tailleX;
	int tailleY;
	
	public TerrainDejeu(int tailleX, int tailleY) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		setLayout(new GridLayout(tailleX,tailleY));
		
		for (int i = 0; i <tailleY ; i++) {
			for (int j = 0; j < tailleX; j++) {
				Case boutton = new Case();
				boutton.addActionListener(new ecouteur(this));
				this.add((JButton)boutton);
				
				
			}
		}
		
	}
}

//class ecouteur
class ecouteur implements ActionListener{
	JPanel monPanneau;
	
	public ecouteur(JPanel monPanneau) {
	
		this.monPanneau = monPanneau;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((Case)e.getSource()).getPosX()+"=X   Y="+((Case)e.getSource()).getPosY());		
		((Case)e.getSource()).setEnabled(false);
		((Case)e.getSource()).setBackground(Color.GREEN);
	}
	
}