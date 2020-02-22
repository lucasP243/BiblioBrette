package com.bibliobrette.server.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * La classe {@code Biblioth�que} mod�lise une biblioth�que classique avec des
 * documents et des abonn�s.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Biblioth�que {
	
	/**
	 * La liste des abonn�s, associ�s � leur ID.
	 */
	private Map<Integer, Abonn�> abonn�s;
	
	/**
	 * La liste des documents, associ�s � leur ID.
	 */
	private Map<Integer, Document> documents;
	
	/**
	 * Construit une nouvelle {@code Biblioth�que} vide.
	 */
	public Biblioth�que() {
		abonn�s = new ConcurrentHashMap<>();
		documents = new ConcurrentHashMap<>();
	}
	
	/**
	 * Ajoute un abonn� � la biblioth�que.
	 * @param ab L'abonn� � ajouter
	 */
	public void ajouter(Abonn� ab) {
		abonn�s.put(ab.numero(), ab);
	}
	
	/**
	 * Ajoute un document � la biblioth�que.
	 * @param doc Le document � ajouter
	 */
	public void ajouter(Document doc) {
		documents.put(doc.numero(), doc);
	}

	/**
	 * R�cup�re un abonn� par son identifiant.
	 * @param id L'identifiant de l'abonn� recherch�
	 * @return l'abonn� avec l'identifiant demand�
	 */
	public Abonn� getAbonn�(int id) {
		return abonn�s.get(id);
	}

	/**
	 * R�cup�re un document par son identifiant.
	 * @param id L'identifiant du document recherch�
	 * @return le document avec l'identifiant demand�
	 */
	public Document getDocument(int id) {
		return documents.get(id);
	}
	
	/**
	 * R�cup�re la liste des abonn�s.
	 * @return un paragraphe listant les abonn�s existants
	 */
	public String getListAbonn�s() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, Abonn�> entr�e: abonn�s.entrySet()) {
			sb.append(entr�e.getValue() + System.lineSeparator());
		}
		return sb.toString();
	}
	
	/**
	 * R�cup�re la liste des documents.
	 * @return un paragraphe listant les documents existants
	 */
	public String getListDocuments() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, Document> entr�e: documents.entrySet()) {
			sb.append(entr�e.getValue() + System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * Place une alerte mail pour un abonn� sur un document.
	 * @param cible Le document concern�
	 * @param alert� L'abonn� souhaitant �tre alert�
	 */
	public void placerAlerte(Document cible, Abonn� alert�) {
		AlertesMail.placer(cible, alert�);
	}
	
}
