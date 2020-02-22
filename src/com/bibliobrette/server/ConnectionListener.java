package com.bibliobrette.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.bibliobrette.server.services.ServiceInstancier;

/**
 * La classe ConnectionListener encapsule un {@code ServerSocket} et s'exécute
 * à répétition dans un Thread à part afin de recevoir et accepter les 
 * connexions entrantes.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class ConnectionListener implements Runnable {
	
	/**
	 * Le {@code ServerSocket} recevant et acceptant les connexions entrantes.
	 */
	private ServerSocket s;

	/**
	 * Construit un {@code ConnectionListener} sur le port indiqué.
	 * @param port Le port d'écoute
	 * @throws IOException si une 
	 */
	public ConnectionListener(int port) throws IOException {
		this.s = new ServerSocket(port);
	}

	@Override
	public void run() {
		do try {
			Socket client = s.accept();
			System.out.println(String.format(
					"Connexion reçue sur le port %d depuis %s",
					s.getLocalPort(),
					client.getInetAddress()
					));
			ServiceInstancier.initService(client, s.getLocalPort());
		} catch (IOException e) {
			System.err.println(String.format(
					"Tentative de connexion échouée sur le port %d",
					s.getLocalPort()
					));
		} while (true);
	}

}
