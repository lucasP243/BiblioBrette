package com.bibliobrette.server.app.documents.�tats;

import java.util.Timer;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.documents.DocumentNum�rot�;
import com.bibliobrette.server.app.documents.�tatDocument;

/**
 * Repr�sente l'�tat emprunt� d'un document.
 * 
 * @version 1.1
 * 
 * @author Lucas Pinard
 *
 */
class Emprunt� implements �tatDocument {
	
	/**
	 * Le minuteur mesurant la dur�e de l'emprunt.
	 * @since 1.1
	 */
	private final Timer empruntMinuteur;
	
	/**
	 * Construit un nouvel �tat emprunt�.
	 * @param ab l'abonn� ayant emprunt� le document.
	 */
	public Emprunt�(Abonn� ab) {
		this.empruntMinuteur = new EmpruntMinuteur(ab);
	}

	@Override
	public �tatDocument retour(DocumentNum�rot� doc) {
		empruntMinuteur.cancel();
		return new Disponible(doc);
	}
	
	@Override
	public String toString() {
		return "indisponible";
	}

}
