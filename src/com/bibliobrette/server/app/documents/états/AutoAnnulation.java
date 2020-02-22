package com.bibliobrette.server.app.documents.états;

import java.util.Timer;
import java.util.TimerTask;

import com.bibliobrette.server.app.documents.DocumentNuméroté;

/**
 * Implémente l'auto-annulation d'une réservation.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class AutoAnnulation extends TimerTask {
	
	/**
	 * La durée standard d'une réservation.
	 */
	private static final long DURÉE_RÉSERVATION = 2*60*60*1000; // 2 heures
	
	/**
	 * La réservation associée.
	 */
	private final Réservé réservation;
	
	/**
	 * Le document concerné par la réservation.
	 */
	private final DocumentNuméroté réservé;

	/**
	 * Démarre la tâche d'auto-annulation de la réservation.
	 * @param réservation la réservation associée
	 * @param doc le document concerné par la réservation
	 */
	public AutoAnnulation(Réservé réservation, DocumentNuméroté doc) {
		this.réservation = réservation;
		this.réservé = doc;
		new Timer().schedule(this, DURÉE_RÉSERVATION);
	}

	@Override
	public void run() {
		réservation.retour(réservé);
	}

}
