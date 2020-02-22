package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.documents.DocumentNum�rot�;

/**
 * Mod�lise un document de type Livre.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Livre extends DocumentNum�rot� {

	/**
	 * Le titre du livre.
	 */
	private String titre;

	/**
	 * L'auteur du livre.
	 */
	private String auteur;

	/**
	 * L'�diteur du livre.
	 */
	private String �diteur;

	/**
	 * L'ann�e de parution du livre.
	 */
	private int ann�e;

	/**
	 * Construit un livre.
	 * @param titre le titre
	 * @param auteur l'auteur
	 * @param �diteur l'�diteur
	 * @param ann�e l'ann�e de parution
	 */
	public Livre(String titre, String auteur, String �diteur, int ann�e) {
		this.titre = titre;
		this.auteur = auteur;
		this.�diteur = �diteur;
		this.ann�e = ann�e;
	}
	
	@Override
	public boolean autoriser(Abonn� ab) {
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				super.toString(), 
				String.format(
						"%s, %s - %s, %d",
						titre, auteur, �diteur, ann�e)
				);
	}
}
