package com.bibliobrette.server.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bibliobrette.server.app.documents.doctypes.DocumentFactory;
import com.bibliobrette.server.exceptions.EmpruntException;
import com.bibliobrette.server.exceptions.RetourException;

class TestBibliothèque {

	private static Bibliothèque bibliotest;

	private static Abonné a1;

	private static Abonné a2;

	private static Document l1;

	private static Document l2;

	private static Document d1;

	private static Document d2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// Données de test :
		bibliotest = new Bibliothèque();

		
		bibliotest.ajouter(a1 = new Abonné(
				"Jean Sérien", 
				"abc@example.net",
				LocalDate.parse("1970-01-01")
				));
		bibliotest.ajouter(a2 = new Abonné(
				"Tanguy Rlandé", 
				"xyz@example.net",
				LocalDate.parse("2007-05-30")
				));

		bibliotest.ajouter(l1 = DocumentFactory.créer(
				"Livre", 
				"Les Fleurs du mal", 
				"Charles Baudelaire", 
				"Larousse", 
				"1857"
				));
		bibliotest.ajouter(l2 = DocumentFactory.créer(
				"Livre", 
				"Léviatemps", 
				"Maxime Chattam", 
				"Pocket", 
				"2010"
				));

		bibliotest.ajouter(d1 = DocumentFactory.créer(
				"DVD", 
				"Le Château Ambulant", 
				"Hayao Miyazaki", 
				"2005", 
				"tout_public"
				));
		bibliotest.ajouter(d2 = DocumentFactory.créer(
				"DVD", 
				"La Maison de Cire", 
				"Jaume Collet-Serra", 
				"2005", 
				"-16"
				));

	}

	@Test
	void testUser() {
		assertEquals(a1, bibliotest.getAbonné(a1.numero()));
		assertEquals(a2, bibliotest.getAbonné(a2.numero()));

		assertEquals("1000: Jean Sérien <abc@example.net>" + System.lineSeparator()
		+ "1001: Tanguy Rlandé <xyz@example.net>" + System.lineSeparator(),
		bibliotest.getListAbonnés()
				);

		assertEquals(50, a1.age());
		assertEquals(12, a2.age());

		a1.sanctionner(Period.ofDays(1));
		assertTrue(a1.estSanctionné());
		assertFalse(a2.estSanctionné());
	}

	@Test
	void testDocument() {
		assertEquals(l1, bibliotest.getDocument(l1.numero()));
		assertEquals(l2, bibliotest.getDocument(l2.numero()));
		assertEquals(d1, bibliotest.getDocument(d1.numero()));
		assertEquals(d2, bibliotest.getDocument(d2.numero()));

		assertEquals("1000: Les Fleurs du mal, Charles Baudelaire - Larousse, 1857 (disponible)" + System.lineSeparator()
		+ "1001: Léviatemps, Maxime Chattam - Pocket, 2010 (disponible)" + System.lineSeparator()
		+ "1002: Le Château Ambulant, réalisé par Hayao Miyazaki, 2005 (disponible)" + System.lineSeparator()
		+ "1003: La Maison de Cire, réalisé par Jaume Collet-Serra, 2005 (-16) (disponible)" + System.lineSeparator(), 
		bibliotest.getListDocuments()
				);

	}

	@Test
	void testBibliothèque() {
		try {
			l1.reserver(a1);
			assertThrows(EmpruntException.class, () -> { l1.reserver(a2); });
			assertThrows(EmpruntException.class, () -> { l1.emprunter(a2); });
			l1.emprunter(a1);
			assertThrows(EmpruntException.class, () -> { l1.reserver(a2); });
			assertThrows(EmpruntException.class, () -> { l1.emprunter(a2); });
			l1.retour();
			assertThrows(RetourException.class, () -> { l1.retour(); });
		} catch (EmpruntException | RetourException e) {
			fail("Exception inattendue lancée dans " + e.getStackTrace()[0]);
		}
	}

}
