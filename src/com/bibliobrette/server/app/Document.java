package com.bibliobrette.server.app;

import com.bibliobrette.server.exceptions.*;

/**
 * L'interface {@code Document} d�finit les fonctionnalit�s de base 
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
	 * R�serve le document au nom d'un abonn�.
	 * @param ab l'abonn� r�servant le document
	 * @throws EmpruntException si le document est d�j� r�serv�/emprunt�
	 */
	void reserver(Abonn� ab) throws EmpruntException;
	
	/**
	 * Emprunte le document au nom d'un abonn�.
	 * @param ab l'abonn� empruntant le document
	 * @throws EmpruntException si le document est d�j� r�serv�/emprunt�
	 */
	void emprunter(Abonn� ab) throws EmpruntException;
	
	/**
	 * Retourne un document r�serv� ou emprunt�.
	 * @throws RetourException si le document n'est ni r�serv� ni emprunt�
	 */
	void retour() throws RetourException; 
}
