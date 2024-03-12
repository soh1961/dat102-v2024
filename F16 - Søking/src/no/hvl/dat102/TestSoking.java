package no.hvl.dat102;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSoking {

	/*
	 * Har brukt static variabler siden setUp er en static-metode som ikke har
	 * tilgang til objektvariablene. Alterntiver kunne vært:
	 * 
	 * Ha objektvariabler som man gir verdi i setUp-metoden der man
	 * bruker @BeforeEach
	 * 
	 * Sette opp tabeller / kjerder i test-metodene.
	 */
	static Integer[] usortertTabell = null;
	static Integer[] sortertTabell = null;
	static Integer[] tomTabell = {};
	static Node<Integer> usortertLenke = null;
	static Node<Integer> sortertLenke = null;
	static Node<Integer> tomLenke = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		usortertTabell = new Integer[] { 1, 8, 0, 5, 7 };
		sortertTabell = new Integer[] { 1, 5, 7, 8, 9 };

		// Usortert lenke
		usortertLenke = new Node<Integer>(7);
		Node<Integer> ny = new Node<>(5);
		ny.neste = usortertLenke;
		usortertLenke = ny;

		ny = new Node<Integer>(0);
		ny.neste = usortertLenke;
		usortertLenke = ny;

		ny = new Node<Integer>(8);
		ny.neste = usortertLenke;
		usortertLenke = ny;

		ny = new Node<Integer>(1);
		ny.neste = usortertLenke;
		usortertLenke = ny;

		// Sortert lenke
		sortertLenke = new Node<Integer>(9);

		ny = new Node<Integer>(8);
		ny.neste = sortertLenke;
		sortertLenke = ny;

		ny = new Node<Integer>(7);
		ny.neste = sortertLenke;
		sortertLenke = ny;

		ny = new Node<Integer>(5);
		ny.neste = sortertLenke;
		sortertLenke = ny;

		ny = new Node<Integer>(1);
		ny.neste = sortertLenke;
		sortertLenke = ny;
	}

	@Test
	void testUsortertTabell() {
		assertTrue(Soking.usortertTabell(usortertTabell, 1));
		assertTrue(Soking.usortertTabell(usortertTabell, 0));
		assertTrue(Soking.usortertTabell(usortertTabell, 7));
		assertFalse(Soking.usortertTabell(usortertTabell, 3));
		assertFalse(Soking.usortertTabell(tomTabell, 3));
	}

	@Test
	void testSortertTabell() {
		assertTrue(Soking.linearSokSortertTabell(sortertTabell, 1));
		assertTrue(Soking.linearSokSortertTabell(sortertTabell, 7));
		assertTrue(Soking.linearSokSortertTabell(sortertTabell, 9));
		assertFalse(Soking.linearSokSortertTabell(sortertTabell, 0));
		assertFalse(Soking.linearSokSortertTabell(sortertTabell, 6));
		assertFalse(Soking.linearSokSortertTabell(sortertTabell, 11));
		assertFalse(Soking.linearSokSortertTabell(tomTabell, 3));
	}

	@Test
	void testUsortertLenke() {
		assertTrue(Soking.usortertLenke(usortertLenke, 1));
		assertTrue(Soking.usortertLenke(usortertLenke, 0));
		assertTrue(Soking.usortertLenke(usortertLenke, 7));
		assertFalse(Soking.usortertLenke(usortertLenke, 3));
		assertFalse(Soking.usortertLenke(tomLenke, 3));
	}

	@Test
	void testSortertLenke() {
		assertTrue(Soking.linearSokSortertLenke(sortertLenke, 1));
		assertTrue(Soking.linearSokSortertLenke(sortertLenke, 7));
		assertTrue(Soking.linearSokSortertLenke(sortertLenke, 9));
		assertFalse(Soking.linearSokSortertLenke(sortertLenke, 0));
		assertFalse(Soking.linearSokSortertLenke(sortertLenke, 6));
		assertFalse(Soking.linearSokSortertLenke(sortertLenke, 11));
		assertFalse(Soking.linearSokSortertLenke(sortertLenke, 3));
		assertFalse(Soking.linearSokSortertLenke(tomLenke, 3));
	}
}
