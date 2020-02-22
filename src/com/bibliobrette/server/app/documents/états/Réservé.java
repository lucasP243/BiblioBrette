package com.bibliobrette.server.app.documents.�tats;

import java.util.TimerTask;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.documents.DocumentNum�rot�;
import com.bibliobrette.server.app.documents.�tatDocument;
import com.bibliobrette.server.exceptions.EmpruntException;

/**
 * Repr�sente l'�tat r�serv� d'un document.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class R�serv� implements �tatDocument {

	/**
	 * L'abonn� ayant r�serv� le document.
	 */
	private final Abonn� r�serv�Par;

	/**
	 * L'autoannulation associ�e � cette r�servation.
	 */
	private final TimerTask autoAnnulation;

	/**
	 * Consrtuit un nouvel �tat R�serv�.
	 * @param doc le document r�serv�
	 * @param ab l'abonn� ayant r�serv�
	 */
	public R�serv�(DocumentNum�rot� doc, Abonn� ab) {
		this.r�serv�Par = ab;
		this.autoAnnulation = new AutoAnnulation(this, doc);
	}

	@Override
	public �tatDocument emprunter(DocumentNum�rot� doc, Abonn� ab) 
			throws EmpruntException {
		if (ab.equals(r�serv�Par)) {
			autoAnnulation.cancel();
			return new Emprunt�(ab);
		}
		else throw new EmpruntException("r�serv�");
	}

	@Override
	public �tatDocument retour(DocumentNum�rot� doc) {
		return new Disponible(doc);
	}

	@Override
	public String toString() {
		return "r�serv� par " + r�serv�Par.nom();
	}

}
