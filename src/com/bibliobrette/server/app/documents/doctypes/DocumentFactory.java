package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Document;
import com.bibliobrette.server.exceptions.DocumentParsingException;
import com.bibliobrette.server.exceptions.RestrictionParseException;

/**
 * Fabrique de documents classique.
 * 
 * @version 1.2
 * 
 * @author Lucas Pinard
 *
 */
public abstract class DocumentFactory {

	/**
	 * Fabrique de documents.
	 * @param args les arguments de création
	 * @return le document créé
	 * @throws DocumentParsingException si les arguments sont invalides
	 */
	public static Document créer(String ...args) throws DocumentParsingException {
		try {
			if (args[0].equalsIgnoreCase("Livre")) {
				return new Livre(
						args[1], 
						args[2], 
						args[3], 
						Integer.parseInt(args[4])
						);
			}
			else if (args[0].equalsIgnoreCase("DVD")) {
				return new DVD(
						args[1], 
						args[2], 
						Integer.parseInt(args[3]), 
						RestrictionAge.parse(args[4])
						);
			}
			else return null;
		} catch (ArrayIndexOutOfBoundsException | RestrictionParseException e) {
			throw new DocumentParsingException();
		}
	}
}
