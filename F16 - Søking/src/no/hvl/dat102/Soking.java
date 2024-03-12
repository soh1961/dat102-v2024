package no.hvl.dat102;

public class Soking<T> {

	// Søker i usortert tabell
	public static <T> boolean usortertTabell(T[] a, T element) {
		boolean funnet = false;
		int i = 0;

		while (!funnet && i < a.length) {
			if (element.equals(a[i])) {
				funnet = true;
			}
			i++;
		}

		return funnet;
	}
	
	// Søker i usortert kjede
	public static <T> boolean usortertLenke(Node<T> start, T element) {
		
		Node<T> p = start;
		
		while (p != null) {
			if (element.equals(p.data)) {
				return true;
			}
			
			p = p.neste;
		}
	
		return false;
	}

	// Lineær søk i sortert tabell
	public static <T extends Comparable<T>> boolean linearSokSortertTabell(T[] a, T element) {
		int i = 0;

		while (i < a.length && element.compareTo(a[i]) > 0) {
			i++;
		}

		if (i == a.length) {
			return false;
		} else {
			return element.equals(a[i]);
		}
	}

	// Lineær søk i sortert lenket/kjedet struktur
	public static <T extends Comparable<T>> boolean linearSokSortertLenke(Node<T> start, T element) {
		Node<T> p = start;
		while (p != null && element.compareTo(p.data) > 0) {
			p = p.neste;
		}

		if (p == null) {
			return false;
		} else {
			return element.equals(p.data);
		}
	}

	// Rekursiv
	public static <T extends Comparable<T>> boolean binaerSok_R(T[] a, T element) {
		return binaerSok_R(a, element, 0, a.length - 1);
	}

	public static <T extends Comparable<T>> boolean binaerSok_R(T[] a, T element, int min, int maks) {
		if (min > maks) { // basis, tom tabelldel
			return false;
		}
		
		int midtpunkt = (min + maks) / 2;
		int resultat = element.compareTo(a[midtpunkt]);
		
		if (resultat == 0) { // basis, funne elementet
			return true;
		}
		
		if (resultat < 0) {  // søker i venstre del
			return binaerSok_R(a, element, min, midtpunkt - 1);
		} else { // søker i høgre del
			return binaerSok_R(a, element, midtpunkt + 1, maks);
		}
		
	}
	
	// Iterativ
	public static <T extends Comparable<T>> int binaerSok_I(T[] a, T element) {
		return binaerSok_I(a, element, 0, a.length - 1);
	}
	
	public static <T extends Comparable<T>> int binaerSok_I(T[] a, T element, int min, int maks) {
		int forste = min;
		int siste = maks;
		int indeks = -1;
		boolean funnet = false;
		int resultat = 0;
		
		while (!funnet && forste <=siste) {
			int midtpunkt = (forste + siste)/2;
			resultat = element.compareTo(a[midtpunkt]);
			
			if (resultat == 0) {
				funnet = true;
				indeks = midtpunkt;
			} else if (resultat < 0) {
				siste = midtpunkt - 1;
			} else {
				forste = midtpunkt + 1;
			}
		}
		
		return indeks;
	}
}
