package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.documents.DocumentNuméroté;

/**
 * Modélise un document de type DVD
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class DVD extends DocumentNuméroté {

	/**
	 * Le nom du DVD.
	 */
	private String nom;

	/**
	 * Le réalisateur du DVD.
	 */
	private String réalisateur;

	/**
	 * L'année de sortie du DVD.
	 */
	private int année;

	/**
	 * La restriction d'âge du DVD.
	 */
	private RestrictionAge restriction;

	/**
	 * Construit un DVD.
	 * @param nom le nom
	 * @param réalisateur le réalisateur
	 * @param année l'année de sortie
	 * @param r la restriction d'âge
	 */
	public DVD(String nom, String réalisateur, int année, RestrictionAge r) {
		this.nom = nom;
		this.réalisateur = réalisateur;
		this.année = année;
		this.restriction = r;
	}

	@Override
	public boolean autoriser(Abonné ab) {
		return super.autoriser(ab) && restriction.autoriser(ab);
	}

	@Override
	public String toString() {
		return String.format(
				super.toString(),
				String.format(
						"%s, réalisé par %s, %d",
						nom, réalisateur, année)
				+ (restriction == RestrictionAge.NO_RESTRICTION ?
						"" : " (-" + restriction.age() + ")"
						)
				);
	}
}
