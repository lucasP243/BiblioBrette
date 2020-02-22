package com.bibliobrette.server.app.documents.états;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.bibliobrette.server.app.Abonné;

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
	 * Durée normale d'un emprunt.
	 */
	private static final Period DURÉE_EMPRUNT = Period.ofWeeks(3);

	/**
	 * Retard toléré avant sanction.
	 */
	private static final Period RETARD_TOLÉRÉ = Period.ofWeeks(2);
	
	/**
	 * Durée de la santion en cas de retard.
	 */
	private static final Period SANCTION = Period.ofMonths(1);

	/**
	 * 
	 */
	private final Abonné emprunteur;

	/**
	 * Contruit un EmpruntMinuteur.
	 * @param ab l'abonné ayant emprunté le document
	 */
	public EmpruntMinuteur(Abonné ab) {
		this.emprunteur = ab;
		schedule(new EmpruntClassique(), 
				Date.from(LocalDate.now().plus(DURÉE_EMPRUNT)
						.atStartOfDay(ZoneId.systemDefault()).toInstant())
				);
	}

	/**
	 * S'écoule durant la période d'emprunt classique.
	 */
	private class EmpruntClassique extends TimerTask {

		@Override
		public void run() {
			schedule(new EmpruntRetard(), 
					Date.from(LocalDate.now().plus(RETARD_TOLÉRÉ)
							.atStartOfDay(ZoneId.systemDefault()).toInstant())
					);
		}
	}

	/**
	 * S'écoule durant la période de retard toléré.
	 */
	private class EmpruntRetard extends TimerTask {

		@Override
		public void run() {
			emprunteur.sanctionner(SANCTION);
		}
	}

}
