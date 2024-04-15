package no.hvl.dat102.prioritetsko;

import no.hvl.dat102.haug.MaksHaugADT;

public class Prioritetskoe<T extends Comparable<? super T>> implements PrioritetskoeADT<T> {
	private MaksHaugADT<T> h;
	
	@Override
	public void leggTil(T nyttElement) {
		// fyll inn
	}

	@Override
	public T fjern() {
		// fyll inn
		return null;
	}

	@Override
	public T peek() {
		// fyll inn
		return null;
	}

	@Override
	public boolean erTom() {
		// fyll inn
		return false;
	}
	
	@Override
	public int antall() {
		// fyll inn
		return 0;
	}

	@Override
	public void toem() {
		// fyll inn
	}
}


