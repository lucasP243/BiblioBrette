package com.bibliobrette.server.app;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.time.Period;

/**
 * La classe {@code Abonné} modélise les informations concernant un abonné
 * inscrit au services de la bibliothèque.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public class Abonné {

	/**
	 * Compteur attribuant automatiquement les identifiants d'abonnés.
	 */
	private static int compteur = 1000;

	/**
	 * L'identifiant unique de l'abonné.
	 */
	private final int ID;

	/**
	 * Le nom d'utilisateur de l'abonné.
	 */
	private String nom;

	/**
	 * L'adresse e-mail de l'abonné.
	 */
	private String email;

	/**
	 * La date de naissance de l'abonné.
	 */
	private LocalDate dateNaissance;

	/**
	 * La date d'expiration de la dernière sanction de l'abonné.
	 */
	private LocalDate dateRédemption;

	/**
	 * Inscrit un abonné avec les informations données.
	 * @param nom le nom d'utilisateur
	 * @param email l'adresse e-mail
	 * @param dateNaissance la date de naissance
	 */
	public Abonné(String nom, String email, LocalDate dateNaissance) {
		synchronized(Abonné.class) {
			ID = compteur++;
		}

		this.nom = nom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.dateRédemption = null;
	}

	/**
	 * Accesseur simple.
	 * @return l'id de l'abonné
	 */
	public int numero() { return ID; }

	/**
	 * Accesseur simple.
	 * @return le nom d'utilisateur de l'abonné
	 */
	public String nom() { return nom; }

	public String email() { return email; }

	/**
	 * Calcule l'âge de l'abonné en fonction de sa date de naissance et de la
	 * date actuelle.
	 * @return l'âge de l'abonné
	 */
	public int age() {
		return (int) YEARS.between(dateNaissance, LocalDate.now());
	}

	/**
	 * Vérifie si l'abonné est actuellement sanctionné.
	 * @return {@code true} si l'abonné est actuellement sanctionné, 
	 * {@code false} sinon
	 */
	public boolean estSanctionné() {
		return dateRédemption != null && 
				dateRédemption.isAfter(LocalDate.now());
	}

	/**
	 * Sanctionne l'abonné pour une période donnée.
	 * @param durée La durée de la sanction à infliger
	 */
	public void sanctionner(Period durée) {
		this.dateRédemption = LocalDate.now().plus(durée);
	}

	@Override
	public String toString() {
		return String.format(
				"%d: %s <%s>",
				ID, nom, email
				);
	}

}
