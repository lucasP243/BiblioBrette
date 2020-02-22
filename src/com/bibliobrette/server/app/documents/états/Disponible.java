package com.bibliobrette.server.app.documents.�tats;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.AlertesMail;
import com.bibliobrette.server.app.documents.DocumentNum�rot�;
import com.bibliobrette.server.app.documents.�tatDocument;

/**
 * Repr�sente l'�tat disponible d'un document
 * 
 * @version 1.3
 * 
 * @author Lucas Pinard
 *
 */
class Disponible implements �tatDocument {
	
	static {
		DocumentNum�rot�.setInitial(new Disponible());
	}
	
	/**
	 * Contructeur basique d'�tat Disponible
	 */
	private Disponible() {}

	/**
	 * Constructeur basique d'�tat Disponible. Lance une alerte � tous les
	 * abonn�s en ayant plac� une sur le document concern�.
	 * @param doc le document concern�.
	 * 
	 * @since 1.3
	 */
	public Disponible(DocumentNum�rot� doc) {
		this();
		AlertesMail.alerter(doc);
	}
	
	@Override
	public �tatDocument reserver(DocumentNum�rot� doc, Abonn� ab) {
		return new R�serv�(doc, ab);
	}

	@Override
	public �tatDocument emprunter(DocumentNum�rot� doc, Abonn� ab) {
		return new Emprunt�(ab);
	}

	@Override
	public String toString() {
		return "disponible";
	}

}
