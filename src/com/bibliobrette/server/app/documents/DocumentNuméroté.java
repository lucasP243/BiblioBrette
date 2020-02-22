package com.bibliobrette.server.app.documents;

import com.bibliobrette.server.app.Abonn�;
import com.bibliobrette.server.app.Document;
import com.bibliobrette.server.exceptions.AppInitializationException;
import com.bibliobrette.server.exceptions.EmpruntException;
import com.bibliobrette.server.exceptions.RetourException;

/**
 * La classe {@code DocumentNum�rot�} est une impl�mentation basique de 
 * {@code Document}. Elle impl�mente une attribuation automatique des 
 * identifiant par auto-incr�mentation, et le fonctionnement de base des 
 * fonctionnalit�s de r�servatoin, d'emprunt et de retour.
 * 
 * @version 1.2
 * 
 * @author Lucas Pinard
 *
 */
public abstract class DocumentNum�rot� implements Document {

	/**
	 * �tat � utiliser � l'instanciation.
	 */
	private static �tatDocument �tatInitial;

	/**
	 * Modifieur pour l'�tat initial.
	 * @param initial l'�tat initial � d�finir
	 */
	public static void setInitial(�tatDocument initial) {
		try {
			DocumentNum�rot�.�tatInitial = initial;
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
	 * L'�tat courant du document.
	 */
	private �tatDocument �tatCourant;

	/**
	 * Construit un document.
	 */
	public DocumentNum�rot�() {
		synchronized (DocumentNum�rot�.class) {
			this.ID = compteur++;
		}
		this.�tatCourant = �tatInitial;
	}

	@Override
	public final int numero() { return ID; }

	/**
	 * V�rifie si un abonn� est autoris� � consulter le document.
	 * @param ab l'abonn�
	 * @return {@code true} si l'abonn� est autoris�, {@code false} sinon
	 */
	public boolean autoriser(Abonn� ab) {
		return !ab.estSanctionn�();
	}

	@Override
	public final void reserver(Abonn� ab) throws EmpruntException {
		if (autoriser(ab)) synchronized (this) {
			�tatCourant = �tatCourant.reserver(this, ab);
		}
		else throw new EmpruntException("non autoris�");
	}

	@Override
	public final void emprunter(Abonn� ab) throws EmpruntException {
		if (autoriser(ab)) synchronized (this) {
			�tatCourant = �tatCourant.emprunter(this, ab);
		}
		else throw new EmpruntException("non autoris�");
	}

	@Override
	public final void retour() throws RetourException {
		synchronized (this) {
			�tatCourant = �tatCourant.retour(this);
		}
	}

	@Override
	public String toString() {
		return ID + ": %s (" + �tatCourant + ")";
	}

}
