package com.bibliobrette.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.Bibliothèque;
import com.bibliobrette.server.app.documents.doctypes.DocumentFactory;
import com.bibliobrette.server.exceptions.AppInitializationException;
import com.bibliobrette.server.exceptions.DocumentParsingException;

public abstract class Appli {

	/**
	 * Le chemin du ficher de log de l'application serveur.
	 */
	private static final String LOG_PATH = "./log.txt";

	/**
	 * Le port sur lequel recevoir les connexions pour le service réservation.
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
	 * La bibliothèque (unique) gérée par l'application.
	 */
	public static final Bibliothèque bibliobrette = new Bibliothèque();

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

		// Démarrer les sockets d'écoute sur chaque port
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
		bibliobrette.ajouter(new Abonné("Jean Sérien", "abc@example.net", LocalDate.parse("1970-01-01")));
		bibliobrette.ajouter(new Abonné("Tanguy Rlandé", "xyz@example.net", LocalDate.parse("2001-09-11")));

		try {

			bibliobrette.ajouter(DocumentFactory.créer("Livre", "Les Fleurs du mal", "Charles Baudelaire", "Larousse", "1857"));
			bibliobrette.ajouter(DocumentFactory.créer("Livre", "Léviatemps", "Maxime Chattam", "Pocket", "2010"));

			bibliobrette.ajouter(DocumentFactory.créer("DVD", "Le Château Ambulant", "Hayao Miyazaki", "2005", "tout_public"));
			bibliobrette.ajouter(DocumentFactory.créer("DVD", "La Maison de Cire", "Jaume Collet-Serra", "2005", "-16"));

		} catch (DocumentParsingException e) { throw new AppInitializationException(); };

	}

}
