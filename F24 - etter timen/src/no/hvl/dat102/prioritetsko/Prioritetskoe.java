package no.hvl.dat102.prioritetsko;
import no.hvl.dat102.haug.*;

public class Prioritetskoe<T extends Comparable<? super T>> implements PrioritetskoeADT<T> {
	private MaksHaugADT<T> h;
	
	Prioritetskoe (int n){
		h = new MaksHaug<T>(n);
	}
	
	@Override
	public void leggTil(T nyttElement) {
		h.leggTil(nyttElement);
	}

	@Override
	public T fjern() {
		return h.fjernMaks();
	}

	@Override
	public T peek() {
		return h.finnMaks();
	}

	@Override
	public boolean erTom() {
		return h.erTom();
	}
	
	@Override
	public int antall() {
		return h.getAntall();
	}

	@Override
	public void toem() {
		h.toem();
	}
}

