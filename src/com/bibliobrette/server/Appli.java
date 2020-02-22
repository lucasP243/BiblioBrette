package com.bibliobrette.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.Biblioth�que;
import com.bibliobrette.server.app.documents.doctypes.DocumentFactory;
import com.bibliobrette.server.exceptions.AppInitializationException;
import com.bibliobrette.server.exceptions.DocumentParsingException;

public abstract class Appli {

	/**
	 * Le chemin du ficher de log de l'application serveur.
	 */
	private static final String LOG_PATH = "./log.txt";

	/**
	 * Le port sur lequel recevoir les connexions pour le service r�servation.
	 */
	public static final int PORT_RESERVATION = 2500;

	/**
	 * Le port sur lequel recevoir les connexions pour le service emprunt.
	 */
	public static final int PORT_EMPRUNT = 2600;

	/**
	 * Le port sur lequel recevoir les connexions pour le service retour.
	 */
	public static final int PORT_RETOUR = 2700;

	/**
	 * La biblioth�que (unique) g�r�e par l'application.
	 */
	public static final Biblioth�que bibliobrette = new Biblioth�que();

	public static void main(String[] args) {

		// Initialiser le log sur le System.out et le System.err
		PrintStream logStream = null;
		try {
			logStream = new LogStream(new FileOutputStream(new File(LOG_PATH), true));
		} catch (FileNotFoundException e) {
			throw new AppInitializationException();
		} finally {
			if (logStream != null) {
				System.setOut(logStream);
				System.setErr(logStream);
			}
		}

		// D�marrer les sockets d'�coute sur chaque port
		try {
			new Thread(
					new ConnectionListener(
							PORT_RESERVATION
							), "ReservationSocketListener"
					).start();

			new Thread(
					new ConnectionListener(
							PORT_EMPRUNT
							), "EmpruntSocketListener"
					).start();

			new Thread(
					new ConnectionListener(
							PORT_RETOUR
							), "RetourSocketListener"
					).start();
			
		} catch (IOException e) {
			throw new AppInitializationException();
		}

		// Documents test
		bibliobrette.ajouter(new Abonn�("Jean S�rien", "abc@example.net", LocalDate.parse("1970-01-01")));
		bibliobrette.ajouter(new Abonn�("Tanguy Rland�", "xyz@example.net", LocalDate.parse("2001-09-11")));

		try {

			bibliobrette.ajouter(DocumentFactory.cr�er("Livre", "Les Fleurs du mal", "Charles Baudelaire", "Larousse", "1857"));
			bibliobrette.ajouter(DocumentFactory.cr�er("Livre", "L�viatemps", "Maxime Chattam", "Pocket", "2010"));

			bibliobrette.ajouter(DocumentFactory.cr�er("DVD", "Le Ch�teau Ambulant", "Hayao Miyazaki", "2005", "tout_public"));
			bibliobrette.ajouter(DocumentFactory.cr�er("DVD", "La Maison de Cire", "Jaume Collet-Serra", "2005", "-16"));

		} catch (DocumentParsingException e) { throw new AppInitializationException(); };

	}

}
