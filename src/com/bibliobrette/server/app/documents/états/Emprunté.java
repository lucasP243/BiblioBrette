package com.bibliobrette.server.app.documents.états;

import java.util.Timer;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.documents.DocumentNuméroté;
import com.bibliobrette.server.app.documents.ÉtatDocument;

/**
 * Représente l'état emprunté d'un document.
 * 
 * @version 1.1
 * 
 * @author Lucas Pinard
 *
 */
class Emprunté implements ÉtatDocument {
	
	/**
	 * Le minuteur mesurant la durée de l'emprunt.
	 * @since 1.1
	 */
	private final Timer empruntMinuteur;
	
	/**
	 * Construit un nouvel état emprunté.
	 * @param ab l'abonné ayant emprunté le document.
	 */
	public Emprunté(Abonné ab) {
		this.empruntMinuteur = new EmpruntMinuteur(ab);
	}

	@Override
	public ÉtatDocument retour(DocumentNuméroté doc) {
		empruntMinuteur.cancel();
		return new Disponible(doc);
	}
	
	@Override
	public String toString() {
		return "indisponible";
	}

}
