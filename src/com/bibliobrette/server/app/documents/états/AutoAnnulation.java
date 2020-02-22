package com.bibliobrette.server.app.documents.�tats;

import java.util.Timer;
import java.util.TimerTask;

import com.bibliobrette.server.app.documents.DocumentNum�rot�;

/**
 * Impl�mente l'auto-annulation d'une r�servation.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class AutoAnnulation extends TimerTask {
	
	/**
	 * La dur�e standard d'une r�servation.
	 */
	private static final long DUR�E_R�SERVATION = 2*60*60*1000; // 2 heures
	
	/**
	 * La r�servation associ�e.
	 */
	private final R�serv� r�servation;
	
	/**
	 * Le document concern� par la r�servation.
	 */
	private final DocumentNum�rot� r�serv�;

	/**
	 * D�marre la t�che d'auto-annulation de la r�servation.
	 * @param r�servation la r�servation associ�e
	 * @param doc le document concern� par la r�servation
	 */
	public AutoAnnulation(R�serv� r�servation, DocumentNum�rot� doc) {
		this.r�servation = r�servation;
		this.r�serv� = doc;
		new Timer().schedule(this, DUR�E_R�SERVATION);
	}

	@Override
	public void run() {
		r�servation.retour(r�serv�);
	}

}
