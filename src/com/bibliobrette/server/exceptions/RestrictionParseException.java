package com.bibliobrette.server.exceptions;

public class RestrictionParseException extends Exception {

	public RestrictionParseException(String parsed) {
		super(parsed + " n'est pas une resticion d'âge valide.");
	}
}
