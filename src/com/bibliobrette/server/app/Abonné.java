package com.bibliobrette.server.app;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.time.Period;

/**
 * La classe {@code Abonn�} mod�lise les informations concernant un abonn�
 * inscrit au services de la biblioth�que.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Abonn� {

	/**
	 * Compteur attribuant automatiquement les identifiants d'abonn�s.
	 */
	private static int compteur = 1000;

	/**
	 * L'identifiant unique de l'abonn�.
	 */
	private final int ID;

	/**
	 * Le nom d'utilisateur de l'abonn�.
	 */
	private String nom;

	/**
	 * L'adresse e-mail de l'abonn�.
	 */
	private String email;

	/**
	 * La date de naissance de l'abonn�.
	 */
	private LocalDate dateNaissance;

	/**
	 * La date d'expiration de la derni�re sanction de l'abonn�.
	 */
	private LocalDate dateR�demption;

	/**
	 * Inscrit un abonn� avec les informations donn�es.
	 * @param nom le nom d'utilisateur
	 * @param email l'adresse e-mail
	 * @param dateNaissance la date de naissance
	 */
	public Abonn�(String nom, String email, LocalDate dateNaissance) {
		synchronized(Abonn�.class) {
			ID = compteur++;
		}

		this.nom = nom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.dateR�demption = null;
	}

	/**
	 * Accesseur simple.
	 * @return l'id de l'abonn�
	 */
	public int numero() { return ID; }

	/**
	 * Accesseur simple.
	 * @return le nom d'utilisateur de l'abonn�
	 */
	public String nom() { return nom; }

	public String email() { return email; }

	/**
	 * Calcule l'�ge de l'abonn� en fonction de sa date de naissance et de la
	 * date actuelle.
	 * @return l'�ge de l'abonn�
	 */
	public int age() {
		return (int) YEARS.between(dateNaissance, LocalDate.now());
	}

	/**
	 * V�rifie si l'abonn� est actuellement sanctionn�.
	 * @return {@code true} si l'abonn� est actuellement sanctionn�, 
	 * {@code false} sinon
	 */
	public boolean estSanctionn�() {
		return dateR�demption != null && 
				dateR�demption.isAfter(LocalDate.now());
	}

	/**
	 * Sanctionne l'abonn� pour une p�riode donn�e.
	 * @param dur�e La dur�e de la sanction � infliger
	 */
	public void sanctionner(Period dur�e) {
		this.dateR�demption = LocalDate.now().plus(dur�e);
	}

	@Override
	public String toString() {
		return String.format(
				"%d: %s <%s>",
				ID, nom, email
				);
	}

}
