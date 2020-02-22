package com.bibliobrette.server.services;

import java.io.IOException;
import java.net.Socket;
import java.time.Period;

import com.bibliobrette.server.Appli;
import com.bibliobrette.server.Service;
import com.bibliobrette.server.exceptions.RetourException;

public class ServiceRetour extends Service {

	/**
	 * Durée de la sanction en cas de dégradation.
	 */
	private static final Period SANCTION_DEGRADATION = Period.ofMonths(1);

	public ServiceRetour(Socket client) {
		super(client);
	}

	@Override
	public void run() {

		int docId = 0, aboId = 0;

		try {
			String[] cmd = read().split(" ");

			if (cmd[0].equalsIgnoreCase("exit")) {
				endConnection();
			}

			else {
				docId = Integer.parseInt(cmd[0]);
				aboId = Integer.parseInt(cmd[1]);
				Appli.bibliobrette.getDocument(docId).retour();
			}

			write("degradation");

			String reponse = read();
			if (reponse.equalsIgnoreCase("o")) {
				Appli.bibliobrette.getAbonné(aboId)
				.sanctionner(SANCTION_DEGRADATION);
			}

			write("success");
			endConnection();

		} catch (ArrayIndexOutOfBoundsException | NumberFormatException | 
				RetourException e) {
			write("failure");
			endConnection();
		}
		catch (IOException e) {
			endConnection();
		}
	}
}
