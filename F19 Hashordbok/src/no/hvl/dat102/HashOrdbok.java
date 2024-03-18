package no.hvl.dat102;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashOrdbok<K, V> implements OrdbokInterface<K, V> {

	private int antallElement;

	// Bør vere primtall (og aldri et jevnt tall)
	private static final int STANDARD_KAPASITET = 17;

	// Hashtabellen
	private TabellElement<K, V>[] hashTabell;
	private int tabellStorrelse;
	private static final double MAK_FYLLINGSGRAD = 0.5;

	public HashOrdbok() {
		this(STANDARD_KAPASITET);
	}

	public HashOrdbok(int startKapasitet) {
		startKapasitet = sjekkKapasitet(startKapasitet);
		antallElement = 0;
		tabellStorrelse = finnNestePrimtall(startKapasitet);

		@SuppressWarnings("unchecked")
		TabellElement<K, V>[] temp = (TabellElement<K, V>[]) new TabellElement[tabellStorrelse];
		hashTabell = temp;
	}

	@Override
	public V leggTil(K nokkel, V verdi) {

		if ((nokkel == null) || (verdi == null)) {
			throw new IllegalArgumentException("Cannot add null to a dictionary.");
		} else {
			V gammelVerdi; // Verdi som skal returnerast

			int index = getHashIndex(nokkel);
			index = probe(index, nokkel);

			if ((hashTabell[index] == null) || hashTabell[index].erFjerna()) {
				hashTabell[index] = new TabellElement<>(nokkel, verdi);
				antallElement++;
				gammelVerdi = null;
			} else { // Nøkkel finst frå før
				gammelVerdi = hashTabell[index].getVerdi();
				hashTabell[index].setValue(verdi);
			}

			if (erForFull()) {
				utvidTabell();
			}

			return gammelVerdi;
		}
	}

	@Override
	public V fjern(K nokkel) {

		V fjernaVerdi = null;

		int index = getHashIndex(nokkel);
		index = locate(index, nokkel);

		if (index != -1) {
			// NB! Noden vil fremdeles være der, men markert som fjerna
			fjernaVerdi = hashTabell[index].getVerdi();
			hashTabell[index].setTilFjerna();
			antallElement--;
		}

		return fjernaVerdi;
	}

	@Override
	public V getVerdi(K key) {

		V result = null;

		int index = getHashIndex(key);
		index = locate(index, key);

		if (index != -1) {
			result = hashTabell[index].getVerdi(); // Key found; get value
		}
		// else not found; result is null

		return result;
	}

	@Override
	public boolean inneholder(K key) {
		return getVerdi(key) != null;
	}

	@Override
	public boolean erTom() {
		return antallElement == 0;
	}

	@Override
	public int getAntall() {
		return antallElement;
	}

	@Override
	public final void toem() {

		for (int index = 0; index < hashTabell.length; index++) {
			hashTabell[index] = null;
		}

		antallElement = 0;
	}

	@Override
	public Iterator<K> getNokkelIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getVerdiIterator() {
		return new ValueIterator();
	}

	private int getHashIndex(K key) {
		int hashIndex = key.hashCode() % hashTabell.length;

		if (hashIndex < 0) {
			hashIndex = hashIndex + hashTabell.length;
		}

		return hashIndex;
	}

	private int probe(int index, K key) {
		boolean found = false;
		int availableIndex = -1;

		while (!found && (hashTabell[index] != null)) {
			if (hashTabell[index].erMed()) {
				if (key.equals(hashTabell[index].getKey())) {
					found = true; // Key found
				} else { // Follow probe sequence
					index = (index + 1) % hashTabell.length; // Linear probing
				}
			} else { // Skip entries that were removed

				// Save index of first location in removed state
				if (availableIndex == -1) {
					availableIndex = index;
				}

				index = (index + 1) % hashTabell.length; // Linear probing

			}
		}

		if (found || (availableIndex == -1)) {
			return index; // Index of either key or null
		} else {
			return availableIndex; // Index of an available location
		}
	}

	private int locate(int index, K key) {
		boolean found = false;

		while (!found && (hashTabell[index] != null)) {
			if (hashTabell[index].erMed() && key.equals(hashTabell[index].getKey())) {
				found = true; // Key found
			} else { // Follow probe sequence
				index = (index + 1) % hashTabell.length; // Linear probing
			}
		}

		int result = -1;
		if (found) {
			result = index;
		}

		return result;
	} // end locate

	private void utvidTabell() {
		// utvider tabellen
		// Må også lage ny hashfunksjon og legge inn elmementene på nytt ved
		// hjelp av den nye funksjonen
	}

	private boolean erForFull() {
		return antallElement > MAK_FYLLINGSGRAD * hashTabell.length;
	}

	private boolean erPrimtall(int t) {
		if (t % 2 == 0) {
			return t == 2;
		}

		if (t < 2) {
			return false;
		}

		/*
		 * Nå er t et oddetall > 2. Det betyr at om det ikke er et primtall må det vær
		 * et produkt av minst to oddetall. Et av faktorene må være <= kvadratroten av
		 * tallet. To faktorer som er større enn kvadratroten av tallet blir større enn
		 * t.
		 * 
		 * Dermed er det tilstrekkelig å vise at t har ingen faktorer som er <= enn
		 * kvadratroten av tallet for å vise at det er et primtall.
		 */

		int kvadratrot = (int) Math.sqrt(t);
		boolean primtall = true;
		int faktor = 3;

		while (primtall && faktor <= kvadratrot) {
			primtall = t % faktor != 0;
			faktor += 2;
		}

		return primtall;
	}

	// Returns a prime integer that is >= the given t.
	private int finnNestePrimtall(int t) {
		int p = t;
		if (p % 2 == 0) { // p er et jevnt tall
			p++;
		}

		while (!erPrimtall(p)) {
			p += 2;
		}

		return p;
	}

	private int sjekkKapasitet(int kapasitet) {
		if (kapasitet < STANDARD_KAPASITET) {
			kapasitet = STANDARD_KAPASITET;
		}

		return kapasitet;
	}

	private class KeyIterator implements Iterator<K> {
		private int currentIndex; // Current position in hash table
		private int numberLeft; // Number of entries left in iteration

		private KeyIterator() {
			currentIndex = 0;
			numberLeft = antallElement;
		} // end default constructor

		public boolean hasNext() {
			return numberLeft > 0;
		} // end hasNext

		public K next() {
			K result = null;

			if (hasNext()) {
				// Skip table locations that do not contain a current entry
				while ((hashTabell[currentIndex] == null) || hashTabell[currentIndex].erFjerna()) {
					currentIndex++;
				} // end while

				result = hashTabell[currentIndex].getKey();
				numberLeft--;
				currentIndex++;
			} else {
				throw new NoSuchElementException();
			}

			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class ValueIterator implements Iterator<V> {
		private int currentIndex;
		private int numberLeft;

		private ValueIterator() {
			currentIndex = 0;
			numberLeft = antallElement;
		}

		public boolean hasNext() {
			return numberLeft > 0;
		}

		public V next() {
			V result = null;

			if (hasNext()) {
				// Skip table locations that do not contain a current entry
				while ((hashTabell[currentIndex] == null) || hashTabell[currentIndex].erFjerna()) {
					currentIndex++;
				} // end while

				result = hashTabell[currentIndex].getVerdi();
				numberLeft--;
				currentIndex++;
			} else {
				throw new NoSuchElementException();
			}

			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static class TabellElement<S, T> {
		private S nokkel;
		private T verdi;
		private Tilstander tilstand;

		private enum Tilstander {
			OPPTATT, FJERNA
		}

		private TabellElement(S sokenokkel, T dataverdi) {
			nokkel = sokenokkel;
			verdi = dataverdi;
			tilstand = Tilstander.OPPTATT;
		}

		private S getKey() {
			return nokkel;
		}

		private T getVerdi() {
			return verdi;
		}

		private void setValue(T newValue) {
			verdi = newValue;
		}

		// Returns true if this entry is currently in the hash table.
		private boolean erMed() {
			return tilstand == Tilstander.OPPTATT;
		}

		// Returns true if this entry has been removed from the hash table.
		private boolean erFjerna() {
			return tilstand == Tilstander.FJERNA;
		}

		// Sets the state of this entry to removed.
		private void setTilFjerna() {
			nokkel = null;
			verdi = null;
			tilstand = Tilstander.FJERNA; // Entry not in use, ie deleted from table
		}
	}
}
