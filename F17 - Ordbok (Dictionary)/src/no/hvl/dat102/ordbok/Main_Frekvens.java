package no.hvl.dat102.ordbok;

import java.util.Iterator;

public class Main_Frekvens {
	public static void main(String[] args) {
		String[] ord = {"er", "det", "finn", "er", "finn", "er"};
		
		// Lag ordbok
		Ordbok<String, Integer> frekvenser = new Ordbok<>();
		
		
		// Tell opp
		for (String e : ord) {
			Integer verdi = frekvenser.getVerdi(e);
			if (verdi == null) {
				frekvenser.leggTil(e, 1);
			} else {
				frekvenser.leggTil(e, verdi + 1);
			}
		}
		
		
		// Skriv ut frekvensoversikt
		Iterator<String> nI = frekvenser.getNokkelIterator();
		Iterator<Integer> vI = frekvenser.getVerdiIterator();
		
		while(nI.hasNext()) {
			System.out.println(nI.next() + ": " + vI.next());
		}
		
	}

}

