package com.bibliobrette.server.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * La classe {@code Bibliothèque} modélise une bibliothèque classique avec des
 * documents et des abonnés.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Bibliothèque {
	
	/**
	 * La liste des abonnés, associés à leur ID.
	 */
	private Map<Integer, Abonné> abonnés;
	
	/**
	 * La liste des documents, associés à leur ID.
	 */
	private Map<Integer, Document> documents;
	
	/**
	 * Construit une nouvelle {@code Bibliothèque} vide.
	 */
	public Bibliothèque() {
		abonnés = new ConcurrentHashMap<>();
		documents = new ConcurrentHashMap<>();
	}
	
	/**
	 * Ajoute un abonné à la bibliothèque.
	 * @param ab L'abonné à ajouter
	 */
	public void ajouter(Abonné ab) {
		abonnés.put(ab.numero(), ab);
	}
	
	/**
	 * Ajoute un document à la bibliothèque.
	 * @param doc Le document à ajouter
	 */
	public void ajouter(Document doc) {
		documents.put(doc.numero(), doc);
	}

	/**
	 * Récupère un abonné par son identifiant.
	 * @param id L'identifiant de l'abonné recherché
	 * @return l'abonné avec l'identifiant demandé
	 */
	public Abonné getAbonné(int id) {
		return abonnés.get(id);
	}

	/**
	 * Récupère un document par son identifiant.
	 * @param id L'identifiant du document recherché
	 * @return le document avec l'identifiant demandé
	 */
	public Document getDocument(int id) {
		return documents.get(id);
	}
	
	/**
	 * Récupère la liste des abonnés.
	 * @return un paragraphe listant les abonnés existants
	 */
	public String getListAbonnés() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, Abonné> entrée: abonnés.entrySet()) {
			sb.append(entrée.getValue() + System.lineSeparator());
		}
		return sb.toString();
	}
	
	/**
	 * Récupère la liste des documents.
	 * @return un paragraphe listant les documents existants
	 */
	public String getListDocuments() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, Document> entrée: documents.entrySet()) {
			sb.append(entrée.getValue() + System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * Place une alerte mail pour un abonné sur un document.
	 * @param cible Le document concerné
	 * @param alerté L'abonné souhaitant être alerté
	 */
	public void placerAlerte(Document cible, Abonné alerté) {
		AlertesMail.placer(cible, alerté);
	}
	
}
