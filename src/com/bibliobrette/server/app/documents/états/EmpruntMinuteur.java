package com.bibliobrette.server.app.documents.�tats;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.bibliobrette.server.app.Abonn�;

/**
 * Attribue les sanctions en cas de retour tardif d'un document.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
class EmpruntMinuteur extends Timer {

	/**
	 * Dur�e normale d'un emprunt.
	 */
	private static final Period DUR�E_EMPRUNT = Period.ofWeeks(3);

	/**
	 * Retard tol�r� avant sanction.
	 */
	private static final Period RETARD_TOL�R� = Period.ofWeeks(2);
	
	/**
	 * Dur�e de la santion en cas de retard.
	 */
	private static final Period SANCTION = Period.ofMonths(1);

	/**
	 * 
	 */
	private final Abonn� emprunteur;

	/**
	 * Contruit un EmpruntMinuteur.
	 * @param ab l'abonn� ayant emprunt� le document
	 */
	public EmpruntMinuteur(Abonn� ab) {
		this.emprunteur = ab;
		schedule(new EmpruntClassique(), 
				Date.from(LocalDate.now().plus(DUR�E_EMPRUNT)
						.atStartOfDay(ZoneId.systemDefault()).toInstant())
				);
	}

	/**
	 * S'�coule durant la p�riode d'emprunt classique.
	 */
	private class EmpruntClassique extends TimerTask {

		@Override
		public void run() {
			schedule(new EmpruntRetard(), 
					Date.from(LocalDate.now().plus(RETARD_TOL�R�)
							.atStartOfDay(ZoneId.systemDefault()).toInstant())
					);
		}
	}

	/**
	 * S'�coule durant la p�riode de retard tol�r�.
	 */
	private class EmpruntRetard extends TimerTask {

		@Override
		public void run() {
			emprunteur.sanctionner(SANCTION);
		}
	}

}
