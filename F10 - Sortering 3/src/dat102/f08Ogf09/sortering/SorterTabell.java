package dat102.f08Ogf09.sortering;

public class SorterTabell {

	// Flettesortering
	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int n) {
		flettesortering(a, 0, n - 1);
	}

	// sorterer heile tabellen
	public static <T extends Comparable<? super T>> void flettesortering(T[] a) {
		flettesortering(a, 0, a.length - 1);
	}

	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int first, int last) {

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempArray = (T[]) new Comparable<?>[a.length]; // unchecked cast
		flettesortering(a, tempArray, first, last);
	}

	private static <T extends Comparable<? super T>> void flettesortering(T[] a, T[] tempTab, int forste, int siste) {

		if (forste >= siste) {
			/*
			 * basis: Gjer ingenting
			 * Dette er eit triks for å vise at vi har tenkt på basistilfelle.
			 * Kunne i staden utelate dette og teste om første < siste og så 
			 * gått rett på delen i else-blokka.
			 */
		} else {
			int midtpkt = (forste + siste) / 2;
			flettesortering(a, tempTab, forste, midtpkt);
			flettesortering(a, tempTab, midtpkt + 1, siste);
			flett(a, tempTab, forste, midtpkt, siste);
		}

	}

	private static <T extends Comparable<? super T>> void flett(T[] a, T[] tempTab, int forste, int midten, int siste) {

		// Flettar saman to deler som ligg ved sida av kvaranre
		// forste, ..., midten og midten + 1, ..., siste
		int fV = forste;
		int sluttV = midten;
		int fH = midten + 1;
		int sluttH = siste;

		// Så lenge det er element att i begge delane, flytt over den minste til tempTab
		int index = fV; // Next available location in tempArray
		for (; (fV <= sluttV) && (fH <= sluttH); index++) {
			if (a[fV].compareTo(a[fH]) < 0) {
				tempTab[index] = a[fV];
				fV++;
			} else {
				tempTab[index] = a[fH];
				fH++;
			}
		}

		// No vil ein av delane vere to. Kopierer over resten i den andre delen

		// Venstre del, kan vere tom
		for (; fV <= sluttV; fV++, index++) {
			tempTab[index] = a[fV];
		}

		// Høgre del, kan vere tom
		for (; fH <= sluttH; fH++, index++) {
			tempTab[index] = a[fH];
		}

		// Kopier den sorterte delen tilbake
		for (index = forste; index <= siste; index++) {
			a[index] = tempTab[index];
		}
	}

	public static <T extends Comparable<? super T>> void kvikksorter(T[] a) {
		kvikksorter(a, 0, a.length - 1);
		sorterVedInnsetting(a);
	}

	private static final int MIN_GRENSE = 3;

	private static <T extends Comparable<? super T>> void kvikksorter(T[] a, int forste, int siste) {

		if (siste - forste + 1 < MIN_GRENSE) {
			/*
			 * Basistilfelle: Gjer ingenting
			 * Boken kaller sortering ved innsetting på elementa som er att,
			 * men det er meir effektivt å kalle soertering ved innsetting på 
			 * heile tabellen til slutt. Dette skjer i public-metoden over.
			 */
		} else {
			int delepunkt = partition(a, forste, siste);
			kvikksorter(a, forste, delepunkt - 1);
			kvikksorter(a, delepunkt + 1, siste);	

			
		}
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int forste, int siste) {
		int midten = (forste + siste) / 2;

		// Ordnar første, midterse og siste element slik at dei står rett i forhold til
		// kvarandre
		sortFirstMiddleLast(a, forste, midten, siste);

		// Flyttar pivot til nest siste plass
		swap(a, midten, siste - 1);
		int pivotIndex = siste - 1;
		T pivotValue = a[pivotIndex];

		// Finn første i venstre del som er mindre større enn pivot
		// siste i høgre del som er mindre enn pivot

		int fraVenstre = forste + 1;
		int fraHogre = siste - 2;

		boolean ferdig = false;
		while (!ferdig) {

			while (a[fraVenstre].compareTo(pivotValue) < 0) {
				fraVenstre++;
			}

			while (a[fraHogre].compareTo(pivotValue) > 0) {
				fraHogre--;
			}

			if (fraVenstre < fraHogre) {
				swap(a, fraVenstre, fraHogre);
				fraVenstre++;
				fraHogre--;
			} else {
				ferdig = true;
			}
		}

		// Place pivotValue between the subarrays Smaller and Larger
		swap(a, pivotIndex, fraVenstre);
		pivotIndex = fraVenstre;

		return pivotIndex;
	}

	// Sorts the first, middle, and last entries of an array into ascending order.
	// Parameters:
	// a An array of Comparable objects.
	// first The integer index of the first array entry;
	// first >= 0 and < a.length.
	// mid The integer index of the middle array entry.
	// last The integer index of the last array entry;
	// last - first >= 2, last < a.length.
	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
		order(a, first, mid); // Make a[first] <= a[mid]
		order(a, mid, last); // Make a[mid] <= a[last]
		order(a, first, mid); // Make a[first] <= a[mid]
	} // end sortFirstMiddleLast

	// Orders two given array elements into ascending order so that a[i] <= a[j].
	private static <T extends Comparable<? super T>> void order(T[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0) {
			swap(a, i, j);
		}
	}

	/**
	 * Sorter dei første n elmenta i tabellen i ikkje stigande ordning (det betyr
	 * stigande ordning om der ikkje er like element).
	 * 
	 * @param <T> Datatype på element som skal sorterast. Må vere ein referansetype
	 * @param a   Tabell som skal sorterast
	 * @param n   Angir kor mange (frå starten) element som skal sorterast >
	 */
	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a, int n) {

		for (int i = 0; i < n; i++) {

			// finne indeks for minste element i usortert del
			T min = a[i];
			int minIndeks = i;

			for (int j = i + 1; j < n; j++) {
				if (a[j].compareTo(min) < 0) {
					min = a[j];
					minIndeks = j;
				}
			}

			swap(a, i, minIndeks);
		}
	}

	/**
	 * Oftast skal heile tabellen sorterast slik vi overlastar denne metoden slik at
	 * dei som brukar den slepp skrive antal (a.length)
	 * 
	 * @param <T>
	 * @param a
	 */
	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a) {
		utvalgssortering(a, a.length);
	}

	public static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a) {
		sorterVedInnsetting(a, 0, a.length - 1);
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a, int forste, int siste) {

		for (int i = forste + 1; i <= siste; i++) {

			T temp = a[i];
			int j = i - 1;

			// finn rett plass
			while (j >= forste && temp.compareTo(a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = temp;
		}

	}

}
