package com.bibliobrette.server.exceptions;

public class AppInitializationException extends RuntimeException {

	public AppInitializationException() {
		super("Échec d'initialisation de l'application.");
	}
}
