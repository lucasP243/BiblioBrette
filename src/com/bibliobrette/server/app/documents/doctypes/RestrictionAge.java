package com.bibliobrette.server.app.documents.doctypes;

import com.bibliobrette.server.app.Abonné;
import com.bibliobrette.server.exceptions.RestrictionParseException;

/**
 * Modélise une restriction d'age.
 * 
 * @version 1.0
 * 
 * @author Lucas Pinard
 *
 */
public enum RestrictionAge {

	NO_RESTRICTION(-1),

	TWELVE(12),

	SIXTEEN(16);

	private int age;

	private RestrictionAge(int age) {
		this.age = age;
	}

	public int age() { return age; }

	public boolean autoriser(Abonné ab) {
		return (age == -1) ? true : ab.age() >= age;
	}

	public static RestrictionAge parse(String s) throws RestrictionParseException {
		switch (s) {
		case "tout_public" : return NO_RESTRICTION;
		case "-12" : return TWELVE;
		case "-16" : return SIXTEEN;
		default : throw new RestrictionParseException(s);
		}
	}
}
