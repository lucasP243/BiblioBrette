package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.documents.DocumentNuméroté;

/**
 * Modélise un document de type Livre.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Livre extends DocumentNuméroté {

	/**
	 * Le titre du livre.
	 */
	private String titre;

	/**
	 * L'auteur du livre.
	 */
	private String auteur;

	/**
	 * L'éditeur du livre.
	 */
	private String éditeur;

	/**
	 * L'année de parution du livre.
	 */
	private int année;

	/**
	 * Construit un livre.
	 * @param titre le titre
	 * @param auteur l'auteur
	 * @param éditeur l'éditeur
	 * @param année l'année de parution
	 */
	public Livre(String titre, String auteur, String éditeur, int année) {
		this.titre = titre;
		this.auteur = auteur;
		this.éditeur = éditeur;
		this.année = année;
	}
	
	@Override
	public boolean autoriser(Abonné ab) {
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				super.toString(), 
				String.format(
						"%s, %s - %s, %d",
						titre, auteur, éditeur, année)
				);
	}
}
