package dat102.f10.radixsortering;

import java.util.ArrayList;
import java.util.List;

public class SorterTabell {
	public static int finnSiffer(int tall, int vekt) {
		return (tall / vekt) % 10;
	}

	public static int finnStorste(int[] data) {
		int maks = data[0];
		for (int i = 1; i < data.length; i++) {
			if (data[i] > maks) {
				maks = data[i];
			}
		}

		return maks;
	}

	public static int lengde(int tall) {
		return ("" + tall).length();
	}

	public static void radixSortering(int[] data) {
		radixSortering(data, 10);
	}

	public static void radixSortering(int[] data, int radix) {

		int n = data.length;
		int maks = finnStorste(data);
		int maksAntallSiffer = lengde(maks);

		List<Integer>[] sifferkoer = new List[radix];

		for (int i = 0; i < radix; i++) {
			sifferkoer[i] = new ArrayList<>();
		}

		int vekt = 1; // starter på enerposisjon
		for (int i = 0; i < maksAntallSiffer; i++) {
			// fordeler i køer
			for (int j = 0; j < n; j++) {
				int siffer = finnSiffer(data[j], vekt);
				sifferkoer[siffer].addLast(data[j]);
			}
			// slår sammen køene
			int p = 0;
			for (int k = 0; k < radix; k++) {
				while (!sifferkoer[k].isEmpty()) {
					data[p] = sifferkoer[k].removeFirst();
					p++;
				}
			}
			vekt *= 10; // flytter til neste siffer
		}
	}

}
