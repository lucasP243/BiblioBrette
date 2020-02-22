package com.bibliobrette.server.services;

import java.io.IOException;
import java.net.Socket;

import com.bibliobrette.server.Appli;
import com.bibliobrette.server.Service;
import com.bibliobrette.server.exceptions.EmpruntException;

public class ServiceRéservation extends Service {

	public ServiceRéservation(Socket client) {
		super(client);
	}

	@Override
	public void run() {
		
		int docId = 0, aboId = 0;
		
		try {
			String[] cmd = read().split(" ");

			if (cmd[0].equalsIgnoreCase("exit")) {
				endConnection();
				return;
			}

			else {
				docId = Integer.parseInt(cmd[0]);
				aboId = Integer.parseInt(cmd[1]);
				Appli.bibliobrette.getDocument(docId).reserver(
						Appli.bibliobrette.getAbonné(aboId)
						);
			}
			
			write("success");
			endConnection();
		}
		catch (EmpruntException e) {
			write(e.getMessage());
			proposerAlerte(docId, aboId);
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			write("failure");
			endConnection();
		}
		catch (IOException e) {
			endConnection();
		}
	}

	private void proposerAlerte(int docId, int aboId) {
		try {
			String cmd = read();
			if (cmd.equalsIgnoreCase("o"))
				Appli.bibliobrette.placerAlerte(
						Appli.bibliobrette.getDocument(docId),
						Appli.bibliobrette.getAbonné(aboId)
						);
			write("success");
			endConnection();
		} catch (IOException e) {
			endConnection();
		}
	}
}
