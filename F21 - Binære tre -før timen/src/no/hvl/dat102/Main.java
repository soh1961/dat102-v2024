package no.hvl.dat102;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		BinaerTre<Integer> t1 = new BinaerTre<>(1);
		BinaerTre<Integer> t2 = new BinaerTre<>(7);
		BinaerTre<Integer> t3 = new BinaerTre<>(5, t1, t2);

		Iterator<Integer> it = t3.getInordenIterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}
}
