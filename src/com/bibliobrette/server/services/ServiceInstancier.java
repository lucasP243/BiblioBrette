package com.bibliobrette.server.services;

import java.net.Socket;

import com.bibliobrette.server.Appli;
import com.bibliobrette.server.Service;

public class ServiceInstancier {

	/**
	 * Initialise un service pour le client donné.
	 * @param client Le client à qui fournir le service
	 * @param port Le port qui a reçu la connexion (identifie le service)
	 */
	public static void initService(Socket client, int port) {
		Service s = null;
		switch (port) {
		case Appli.PORT_RESERVATION:
			s = new ServiceRéservation(client);
			break;

		case Appli.PORT_EMPRUNT:
			s = new ServiceEmprunt(client);
			break;

		case Appli.PORT_RETOUR:
			s = new ServiceRetour(client);
			break;

		default: return;
		}

		new Thread(s, client.getInetAddress().toString()).start();
	}

}
