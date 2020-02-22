package com.bibliobrette.server.app.documents;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.exceptions.EmpruntException;
import com.bibliobrette.server.exceptions.RetourException;

/**
 * La classe {@code �tatDocument} d�finit les actions pouvant �tre effectu�es
 * sur un document d�pendant de l'�tat courant de ce dernier
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public interface �tatDocument {
	
	/**
	 * R�serve un document au nom d'un abonn�.
	 * @param doc le document � r�server
	 * @param ab l'abonn� r�servant le document
	 * @return le nouvel �tat r�sultant de l'op�ration
	 * @throws EmpruntException si le document est d�j� r�serv�/emprunt�
	 */
	default �tatDocument reserver(DocumentNum�rot� doc, Abonn� ab) 
			throws EmpruntException {
		throw new EmpruntException("indisponible");
	}

	/**
	 * Emprunte un document au nom d'un abonn�.
	 * @param doc le document � emprunter
	 * @param ab l'abonn� empruntant le document
	 * @return le nouvel �tat r�sultant de l'op�ration
	 * @throws EmpruntException si le document est d�j� r�serv�/emprunt�
	 */
	default �tatDocument emprunter(DocumentNum�rot� doc, Abonn� ab) 
			throws EmpruntException {
		throw new EmpruntException("indisponible");
	}

	/**
	 * Retoune un document.
	 * @param doc le document � retourner
	 * @return le nouvel �tat r�sultant de l'op�ration
	 * @throws RetourException si le document n'est ni r�serv� ni emprunt�
	 */
	default �tatDocument retour(DocumentNum�rot� doc) throws RetourException {
		throw new RetourException("disponible");
	}
	
	@Override
	String toString();
}
