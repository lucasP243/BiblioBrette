package com.bibliobrette.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * La classe {@code Service} est une abstraction repr�sentant un service
 * pouvant �tre rendu par le serveur. Elle encapsule le socket client et ses
 * canaux de communication afin d'isoler la gestion des canaux des 
 * impl�mentations de {@code Service}.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public abstract class Service implements Runnable {

	/**
	 * Le {@code Socket} permettant la communication avec le client.
	 */
	private Socket client;
	
	/**
	 * Le canal de communication du serveur vers le client.
	 */
	private PrintWriter serverToClient;

	/**
	 * Le canal de communication du client vers le serveur.
	 */
	private BufferedReader clientToServer;

	/**
	 * Initialise les canaux de communication afin d'�tre utilis�s par les
	 * imp�mentations de {@code Service}
	 * @param client
	 */
	public Service(Socket client) {
		this.client = client;
		
		try {
			serverToClient = new PrintWriter(client.getOutputStream(), false);
			clientToServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}
		catch (IOException e) {
			System.err.println(String.format(
					"�chec d'ouverture des canaux de communication avec %s",
					client.getInetAddress()
					));
		}
	}
	
	/**
	 * �crit un message vers le client.
	 * @param message Le message � �crire au client
	 * 
	 * @implNote La fonction {@code write(String)} n'envoie pas imm�diatement
	 * le message au client; afin d'�viter de deadlock la communication
	 * client-serveur, elle met le message en liste d'attente, et tous les
	 * messages en liste d'attentes sont envoy�s au client lors de l'appel de
	 * la fonction {@code read()}, qui peut ainsi les lire comme un unique
	 * message. Ce choix d'impl�mentation permet de simplifier la communication
	 * client-serveur, mais elle implique que le client doit respecter un
	 * cycle <i>lecture - �criture</i>.
	 * 
	 * @see #read()
	 * @see #flush()
	 */
	protected void write(String message) {
		serverToClient.println(message);
	}
	
	/**
	 * Attends l'envoi d'un message depuis le client
	 * @return Le message lu
	 * 
	 * @implNote Afin d'�viter de deadlock la communication client-serveur,
	 * la fonction {@code read()} appelle la fonction {@code flush()}.
	 * 
	 * @throws IOException si une erreur d'entr�e/sortie intervient
	 * 
	 * @see #write(String)
	 * @see #flush()
	 */
	protected String read() throws IOException {
		serverToClient.flush();
		return clientToServer.readLine();
	}
	
	/**
	 * Termine la connexion avec le client.
	 */
	public final void endConnection() {
		try {
			serverToClient.flush();
			client.close();
		} catch (IOException e) {
			System.err.println("Erreur de fermeture de communication avec le client");
		} finally {
		System.out.println(String.format(
				"Fin de communication avec %s",
				client.getInetAddress()
				));
		}
	}
	
	@Override
	@Deprecated
	protected final void finalize() throws Throwable {
		client.close();
	}
}
