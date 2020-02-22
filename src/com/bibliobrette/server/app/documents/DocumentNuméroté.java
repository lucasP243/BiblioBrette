package com.bibliobrette.server.app.documents;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.app.Document;
import com.bibliobrette.server.exceptions.AppInitializationException;
import com.bibliobrette.server.exceptions.EmpruntException;
import com.bibliobrette.server.exceptions.RetourException;

/**
 * La classe {@code DocumentNuméroté} est une implémentation basique de 
 * {@code Document}. Elle implémente une attribuation automatique des 
 * identifiant par auto-incrémentation, et le fonctionnement de base des 
 * fonctionnalités de réservatoin, d'emprunt et de retour.
 * 
 * @version 1.2
 * 
 * @author Lucas Pinard
 *
 */
public abstract class DocumentNuméroté implements Document {

	/**
	 * État à utiliser à l'instanciation.
	 */
	private static ÉtatDocument étatInitial;

	/**
	 * Modifieur pour l'état initial.
	 * @param initial l'état initial à définir
	 */
	public static void setInitial(ÉtatDocument initial) {
		try {
			DocumentNuméroté.étatInitial = initial;
		} catch (Exception e) {
			throw new AppInitializationException();
		}
	}

	/**
	 * Compteur attribuant automatiquement les identifiants de document.
	 */
	private static int compteur = 1000;

	/**
	 * L'identifiant du document.
	 */
	private final int ID;

	/**
	 * L'état courant du document.
	 */
	private ÉtatDocument étatCourant;

	/**
	 * Construit un document.
	 */
	public DocumentNuméroté() {
		synchronized (DocumentNuméroté.class) {
			this.ID = compteur++;
		}
		this.étatCourant = étatInitial;
	}

	@Override
	public final int numero() { return ID; }

	/**
	 * Vérifie si un abonné est autorisé à consulter le document.
	 * @param ab l'abonné
	 * @return {@code true} si l'abonné est autorisé, {@code false} sinon
	 */
	public boolean autoriser(Abonné ab) {
		return !ab.estSanctionné();
	}

	@Override
	public final void reserver(Abonné ab) throws EmpruntException {
		if (autoriser(ab)) synchronized (this) {
			étatCourant = étatCourant.reserver(this, ab);
		}
		else throw new EmpruntException("non autorisé");
	}

	@Override
	public final void emprunter(Abonné ab) throws EmpruntException {
		if (autoriser(ab)) synchronized (this) {
			étatCourant = étatCourant.emprunter(this, ab);
		}
		else throw new EmpruntException("non autorisé");
	}

	@Override
	public final void retour() throws RetourException {
		synchronized (this) {
			étatCourant = étatCourant.retour(this);
		}
	}

	@Override
	public String toString() {
		return ID + ": %s (" + étatCourant + ")";
	}

}
