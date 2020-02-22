package com.bibliobrette.server.app.documents.états;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.AlertesMail;
import com.bibliobrette.server.app.documents.DocumentNuméroté;
import com.bibliobrette.server.app.documents.ÉtatDocument;

/**
 * Représente l'état disponible d'un document
 * 
 * @version 1.3
 * 
 * @author Lucas Pinard
 *
 */
class Disponible implements ÉtatDocument {
	
	static {
		DocumentNuméroté.setInitial(new Disponible());
	}
	
	/**
	 * Contructeur basique d'état Disponible
	 */
	private Disponible() {}

	/**
	 * Constructeur basique d'état Disponible. Lance une alerte à tous les
	 * abonnés en ayant placé une sur le document concerné.
	 * @param doc le document concerné.
	 * 
	 * @since 1.3
	 */
	public Disponible(DocumentNuméroté doc) {
		this();
		AlertesMail.alerter(doc);
	}
	
	@Override
	public ÉtatDocument reserver(DocumentNuméroté doc, Abonné ab) {
		return new Réservé(doc, ab);
	}

	@Override
	public ÉtatDocument emprunter(DocumentNuméroté doc, Abonné ab) {
		return new Emprunté(ab);
	}

	@Override
	public String toString() {
		return "disponible";
	}

}
