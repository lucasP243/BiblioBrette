package com.bibliobrette.server.exceptions;

public class AppInitializationException extends RuntimeException {

	public AppInitializationException() {
		super("�chec d'initialisation de l'application.");
	}
}
