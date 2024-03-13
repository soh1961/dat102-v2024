package dat102.f10.radixsortering;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRadix {

		private int[] a;
		private int[] b;
		private int[] c;
		private int[] fasitABC = {2, 18, 111, 210, 217, 415}; 
		
		@BeforeEach
		void setUp() throws Exception {
			a = new int[]{415, 210, 217, 111, 2, 18};
			b = new int[]{2, 415, 18, 111, 210, 217};
			c = new int[]{415, 217, 18, 111, 2, 210};
		}

		@Test
		void test() {
			SorterTabell.radixSortering(a);
			assertArrayEquals(fasitABC, a);
			SorterTabell.radixSortering(b);
			assertArrayEquals(fasitABC, b);
			SorterTabell.radixSortering(c);
			assertArrayEquals(fasitABC, c);
		}

		
		private boolean erSortert(Integer[] tab) {
			for (int i = 0; i < tab.length - 1; i++) {
				if (tab[i].compareTo(tab[i + 1]) > 0) {
					return false;
				}
			}
			
			return true;
		}
	}
