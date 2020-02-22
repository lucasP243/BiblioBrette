package com.bibliobrette.client.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EmpruntApp {

	public static void main(String[] args) {

		Socket server = null;
		BufferedReader serverIn, clientIn;
		PrintWriter serverOut;
		String answer;

		try {

			server = new Socket("localhost", 2600);

			serverIn = new BufferedReader(
					new InputStreamReader(server.getInputStream())
					);
			
			clientIn = new BufferedReader(
					new InputStreamReader(System.in)
					);

			serverOut = new PrintWriter(
					server.getOutputStream(), true
					);
			
			do {
				serverOut.println(clientIn.readLine());
				System.out.println(answer = serverIn.readLine());
			} while (!(answer.equalsIgnoreCase("success")
					|| answer.equalsIgnoreCase("failure")));

		} catch (IOException e) {
			System.err.println("Connexion interrompue avec le serveur.");
		} finally {

			if (server != null) try {
				server.close();
			} catch (IOException e) {}
		}

	}

}
