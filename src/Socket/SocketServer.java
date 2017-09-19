package Socket;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {


	public static void main(String[] arg) {
		ServerSocket monServerSocket;
		Socket monSocket;
		final PrintWriter pw;
		final BufferedReader input;
		try {
			

			monServerSocket = new ServerSocket(777);

			System.out.println("Serveur : attente de connexion...");

			// attente de la connexion d'un client
			monSocket = monServerSocket.accept();

			// connexion établie
			System.out.println("Serveur : Connexion établie");

			// communication avec le client

			// envoie d'un int
			// os.write(200);

			// envoie d'une chaîne de caractères
			pw = new PrintWriter(monSocket.getOutputStream(), true);
			InputStreamReader in = new InputStreamReader(monSocket.getInputStream());
			input = new BufferedReader(in);
			Thread envoie = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("envoie de serveur: test");
					int[] r = { 1, 2, 3 };
					int i=0;
					while (true) {
						for (int a : r) {
							System.out.println("envoie Serveur"+a);
							pw.println(a+i);
							pw.flush();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						i++;
					}

				}
			});
			envoie.start();
			Thread recevoir = new Thread(new Runnable() {

				@Override
				public void run() {
					
					String C;
					System.out.println("reception de serveur: test");
					while (true) {
						try {
							while ((C = input.readLine()) != null) {
								System.out.println("reception de serveur: " + C);
								
							}
						} catch (IOException  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			recevoir.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
