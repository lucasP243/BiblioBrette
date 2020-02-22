package com.bibliobrette.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe {@code LogStream} est une sur-impl�mentation de {@code PrintStream} 
 * ajoutant, � chaque appel de {@code println(String)}, la date et l'heure 
 * courant, afin de retracer chaque message �crit.
 * 
 * @version 1.1
 * 
 * @author Lucas Pinard
 *
 */
class LogStream extends PrintStream {

	/**
	 * Le format sous lequel s'affiche l'heure et la date par d�faut.
	 */
	private static final DateTimeFormatter FORMAT_DEFAUT = 
			DateTimeFormatter.ofPattern("<yyyy-MM-dd HH:mm:ss>");

	/**
	 * Le format sous lequel s'affiche l'heure et la date par d�faut.
	 * @since 1.1
	 */
	private DateTimeFormatter formatDateTime;

	/**
	 * Construit un {@code LogPrinter} avec le format <i>DateTime</i> par d�faut.
	 * @param stream Le {@code FileOutputStream} dans lequel �crire
	 */
	public LogStream(OutputStream stream) {
		this(stream, FORMAT_DEFAUT);
	}

	/**
	 * Construit un {@code LogPrinter}.
	 * @param stream Le {@code FileOutputStream} dans lequel �crire
	 * @param formatDateTime le format <i>DateTime</i> � utiliser
	 * @since 1.1
	 */
	public LogStream(OutputStream stream, DateTimeFormatter formatDateTime) {
		super(stream);
		this.formatDateTime = formatDateTime;
	}

	/**
	 * Modifieur basique pour le format <i>DateTime</i>.
	 * @param format le nouveau format <i>DateTime</i> � utiliser
	 */
	public void setFormat(DateTimeFormatter format) {
		this.formatDateTime = format;
	}

	@Override
	/**
	 * �crit un message pr�c�d� de la date et l'heure courante, puis un retour
	 * � la ligne.
	 * @param message le message � �crire
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
