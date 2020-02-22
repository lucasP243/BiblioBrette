package com.bibliobrette.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe {@code LogStream} est une sur-implémentation de {@code PrintStream} 
 * ajoutant, à chaque appel de {@code println(String)}, la date et l'heure 
 * courant, afin de retracer chaque message écrit.
 * 
 * @version 1.1
 * 
 * @author Lucas Pinard
 *
 */
class LogStream extends PrintStream {

	/**
	 * Le format sous lequel s'affiche l'heure et la date par défaut.
	 */
	private static final DateTimeFormatter FORMAT_DEFAUT = 
			DateTimeFormatter.ofPattern("<yyyy-MM-dd HH:mm:ss>");

	/**
	 * Le format sous lequel s'affiche l'heure et la date par défaut.
	 * @since 1.1
	 */
	private DateTimeFormatter formatDateTime;

	/**
	 * Construit un {@code LogPrinter} avec le format <i>DateTime</i> par défaut.
	 * @param stream Le {@code FileOutputStream} dans lequel écrire
	 */
	public LogStream(OutputStream stream) {
		this(stream, FORMAT_DEFAUT);
	}

	/**
	 * Construit un {@code LogPrinter}.
	 * @param stream Le {@code FileOutputStream} dans lequel écrire
	 * @param formatDateTime le format <i>DateTime</i> à utiliser
	 * @since 1.1
	 */
	public LogStream(OutputStream stream, DateTimeFormatter formatDateTime) {
		super(stream);
		this.formatDateTime = formatDateTime;
	}

	/**
	 * Modifieur basique pour le format <i>DateTime</i>.
	 * @param format le nouveau format <i>DateTime</i> à utiliser
	 */
	public void setFormat(DateTimeFormatter format) {
		this.formatDateTime = format;
	}

	@Override
	/**
	 * Écrit un message précédé de la date et l'heure courante, puis un retour
	 * à la ligne.
	 * @param message le message à écrire
	 */
	public void println(String message) {
		super.println(
				String.format("%s %s",
						LocalDateTime.now().format(formatDateTime),
						message
						)
				);
	}

}
