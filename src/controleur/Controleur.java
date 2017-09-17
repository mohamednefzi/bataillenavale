package controleur;


import java.util.ArrayList;

import vue.IBatailleUI;




public class Controleur<Bateau> implements IControleur{
	IBatailleUI laBataille;
	ArrayList<int []> tableauVue;
	
	
	public static void main(String[] args) {
		
	}
	//Constructeur
	public Controleur(IBatailleUI laBataille) {
		super();
		this.laBataille = laBataille;
		this.tableauVue=new ArrayList<int []>();
	}

// methode
	@Override
	public ArrayList<int[]> creerBateau(){
		int[] tab1= {1,2,1};
		tableauVue.add(tab1);
		int[] tab2= {2,2,1};
		tableauVue.add(tab2);
		int[] tab3= {3,2,1};
		tableauVue.add(tab3);
		
		int[] tab4= {1,4,2};
		tableauVue.add(tab4);
		int[] tab5= {2,4,2};
		tableauVue.add(tab5);
		int[] tab6= {3,4,2};
		tableauVue.add(tab6);
		
		int[] tab7= {5,4,3};
		tableauVue.add(tab7);
		int[] tab8= {6,4,3};
		tableauVue.add(tab8);
		int[] tab9= {7,4,3};
		tableauVue.add(tab9);
		int[] tab10= {8,4,3};
		tableauVue.add(tab10);
		
		int[] tab11= {8,6,4};
		tableauVue.add(tab11);
		int[] tab12= {8,7,4};
		tableauVue.add(tab12);
		
		return tableauVue;
	}
	

}
