package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.documents.DocumentNum�rot�;

/**
 * Mod�lise un document de type DVD
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class DVD extends DocumentNum�rot� {

	/**
	 * Le nom du DVD.
	 */
	private String nom;

	/**
	 * Le r�alisateur du DVD.
	 */
	private String r�alisateur;

	/**
	 * L'ann�e de sortie du DVD.
	 */
	private int ann�e;

	/**
	 * La restriction d'�ge du DVD.
	 */
	private RestrictionAge restriction;

	/**
	 * Construit un DVD.
	 * @param nom le nom
	 * @param r�alisateur le r�alisateur
	 * @param ann�e l'ann�e de sortie
	 * @param r la restriction d'�ge
	 */
	public DVD(String nom, String r�alisateur, int ann�e, RestrictionAge r) {
		this.nom = nom;
		this.r�alisateur = r�alisateur;
		this.ann�e = ann�e;
		this.restriction = r;
	}

	@Override
	public boolean autoriser(Abonn� ab) {
		return super.autoriser(ab) && restriction.autoriser(ab);
	}

	@Override
	public String toString() {
		return String.format(
				super.toString(),
				String.format(
						"%s, r�alis� par %s, %d",
						nom, r�alisateur, ann�e)
				+ (restriction == RestrictionAge.NO_RESTRICTION ?
						"" : " (-" + restriction.age() + ")"
						)
				);
	}
}
