import java.net.InetAddress;
import java.net.UnknownHostException;

import controleur.Controleur;
import controleur.IControleur;
import vue.BatailleUI;
import vue.IBatailleUI;

public class Principale {


	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		IControleur monControleur;
		IBatailleUI maBataille;
		InetAddress inetadr;
		try {
			inetadr = InetAddress.getLocalHost();
			String nomHote = (String) inetadr.getHostName();
	        System.out.println("Nom de la machine = "+nomHote );
	        //adresse ip sur le réseau
	       String adresseIPLocale = (String) inetadr.getHostAddress();
	        System.out.println("Adresse IP locale = "+adresseIPLocale );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		maBataille=new BatailleUI(10,10);
		monControleur=new Controleur(maBataille);
		maBataille.setMonControleur(monControleur);

	}

}
