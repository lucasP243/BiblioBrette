package com.bibliobrette.server.app;

import com.bibliobrette.server.exceptions.*;

/**
 * L'interface {@code Document} définit les fonctionnalités de base 
 * d'un document.
 * 
 * @version 1.0
 * 
 * @author J.F. Brette
 *
 */
public interface Document {
	
	/**
	 * Accesseur simple.
	 * @return l'identifiant du document
	 */
	int numero();
	
	/**
	 * Réserve le document au nom d'un abonné.
	 * @param ab l'abonné réservant le document
	 * @throws EmpruntException si le document est déjà réservé/emprunté
	 */
	void reserver(Abonné ab) throws EmpruntException;
	
	/**
	 * Emprunte le document au nom d'un abonné.
	 * @param ab l'abonné empruntant le document
	 * @throws EmpruntException si le document est déjà réservé/emprunté
	 */
	void emprunter(Abonné ab) throws EmpruntException;
	
	/**
	 * Retourne un document réservé ou emprunté.
	 * @throws RetourException si le document n'est ni réservé ni emprunté
	 */
	void retour() throws RetourException; 
}
