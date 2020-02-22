package com.bibliobrette.server.app.documents;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.exceptions.EmpruntException;
import com.bibliobrette.server.exceptions.RetourException;

/**
 * La classe {@code ÉtatDocument} définit les actions pouvant être effectuées
 * sur un document dépendant de l'état courant de ce dernier
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public interface ÉtatDocument {
	
	/**
	 * Réserve un document au nom d'un abonné.
	 * @param doc le document à réserver
	 * @param ab l'abonné réservant le document
	 * @return le nouvel état résultant de l'opération
	 * @throws EmpruntException si le document est déjà réservé/emprunté
	 */
	default ÉtatDocument reserver(DocumentNuméroté doc, Abonné ab) 
			throws EmpruntException {
		throw new EmpruntException("indisponible");
	}

	/**
	 * Emprunte un document au nom d'un abonné.
	 * @param doc le document à emprunter
	 * @param ab l'abonné empruntant le document
	 * @return le nouvel état résultant de l'opération
	 * @throws EmpruntException si le document est déjà réservé/emprunté
	 */
	default ÉtatDocument emprunter(DocumentNuméroté doc, Abonné ab) 
			throws EmpruntException {
		throw new EmpruntException("indisponible");
	}

	/**
	 * Retoune un document.
	 * @param doc le document à retourner
	 * @return le nouvel état résultant de l'opération
	 * @throws RetourException si le document n'est ni réservé ni emprunté
	 */
	default ÉtatDocument retour(DocumentNuméroté doc) throws RetourException {
		throw new RetourException("disponible");
	}
	
	@Override
	String toString();
}
