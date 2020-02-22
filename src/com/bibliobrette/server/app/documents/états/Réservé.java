package com.bibliobrette.server.app.documents.états;

import java.util.TimerTask;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.documents.DocumentNuméroté;
import com.bibliobrette.server.app.documents.ÉtatDocument;
import com.bibliobrette.server.exceptions.EmpruntException;

/**
 * Représente l'état réservé d'un document.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class Réservé implements ÉtatDocument {

	/**
	 * L'abonné ayant réservé le document.
	 */
	private final Abonné réservéPar;

	/**
	 * L'autoannulation associée à cette réservation.
	 */
	private final TimerTask autoAnnulation;

	/**
	 * Consrtuit un nouvel état Réservé.
	 * @param doc le document réservé
	 * @param ab l'abonné ayant réservé
	 */
	public Réservé(DocumentNuméroté doc, Abonné ab) {
		this.réservéPar = ab;
		this.autoAnnulation = new AutoAnnulation(this, doc);
	}

	@Override
	public ÉtatDocument emprunter(DocumentNuméroté doc, Abonné ab) 
			throws EmpruntException {
		if (ab.equals(réservéPar)) {
			autoAnnulation.cancel();
			return new Emprunté(ab);
		}
		else throw new EmpruntException("réservé");
	}

	@Override
	public ÉtatDocument retour(DocumentNuméroté doc) {
		return new Disponible(doc);
	}

	@Override
	public String toString() {
		return "réservé par " + réservéPar.nom();
	}

}
