package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class socketClient {
	


	public static void main(String[] arg) {
		Socket monSocket;
		PrintWriter pw;
		BufferedReader input;
		try {
			monSocket = new Socket("localhost", 777);

			System.out.println("Client: connexion etablie");
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
					System.out.println("envoie de Client: test");
					int[] r = { 10, 20, 30 };
					int i=0;
					while (true) {
						for (int a : r) {
							System.out.println("envoie Client"+a);
							pw.println(a+i);
							pw.flush();
						}
						i=i+10;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			});
			envoie.start();
			Thread recevoir = new Thread(new Runnable() {

				@Override
				public void run() {

					String C;
					System.out.println("reception de Client: test");
					while (true) {
						try {
							while ((C = input.readLine()) != null) {
								System.out.println("reception de Client: " + C);
							}
						} catch (IOException e) {
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