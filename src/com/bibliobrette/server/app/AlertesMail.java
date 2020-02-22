package com.bibliobrette.server.app;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * La classe {@code AlertesMail} gère les envois d'alertes mail.
 * Elle n'est pas instanciable ni surchargeable.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public final class AlertesMail {

	// Privatisation du constructeur pour interdire l'instanciation
	private AlertesMail() {}

	/**
	 * La Session mail du serveur.
	 */
	private static final Session SESSION;

	static {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", 25);
		SESSION = Session.getInstance(props);
	}

	/**
	 * Adresse e-mail du serveur.
	 */
	private static final String SERVER_ADDRESS = "bibliobrette@no-reply.com";

	/**
	 * Le sujet par défaut du mail d'alerte.
	 */
	private static final String MAIL_SUBJECT = "Retour d'un document";

	/**
	 * Le texte template du mail d'alerte.
	 */
	private static final String MAIL_TEXT =
			"Cher %s, le document \"%s\" est de nouveau disponible. Vous pouvez "
					+ "dès à présent l'emprunter.<br/><br/>"
					+ "[Ce message a été envoyé automatiquement, merci de ne pas "
					+ "y répondre]";

	/**
	 * Les abonnés ayant placé une alerte sur un document.
	 */
	private static Map<Document, List<Abonné>> alertes = 
			new ConcurrentHashMap<>();

	/**
	 * Placer une alerte sur un document pour un abonné.
	 * @param cible le document concerné
	 * @param alerté l'abonné souhaitant être alerté.
	 */
	static void placer(Document cible, Abonné alerté) {
		if (alertes.get(cible) == null) {
			alertes.put(cible, new LinkedList<>());
		}

		alertes.get(cible).add(alerté);
		System.out.println("Abonné "+alerté.nom()+" placé en alerte "
				+ "pour le document "+cible.numero()
				);
	}

	/**
	 * Envoyer une alerte à chaque abonné ayant placé une alerte sur 
	 * un document.
	 * @param cible le document ayant déclenché l'alerte
	 */
	public static void alerter(Document cible) {
		if (alertes.get(cible) == null) return;
		for (Abonné a : alertes.get(cible)) {
			sendMail(cible, a);
			System.out.println("Alert sent to " + a.email());
		}

		// On purge la liste des alertes pour ce document
		alertes.put(cible, new LinkedList<>());
	}

	/**
	 * Envoie un mail à un abonné pour un document.
	 * @param cible le document concerné
	 * @param alerté l'abonné destinataire
	 */
	public static void sendMail(Document cible, Abonné alerté) {

		Message mail = new MimeMessage(SESSION);

		try {
			mail.setFrom(new InternetAddress(SERVER_ADDRESS));
			mail.setRecipients(
					Message.RecipientType.TO, 
					InternetAddress.parse(alerté.email(), false));
			mail.setSubject(MAIL_SUBJECT);
			mail.setText(String.format(MAIL_TEXT, alerté.nom(), cible));
			mail.setSentDate(new Date());
			Transport.send(mail);
		} catch (MessagingException e) {
			System.err.println("Échec de l'envoi d'un message à " + alerté.email()
					);
		}
	}

}
