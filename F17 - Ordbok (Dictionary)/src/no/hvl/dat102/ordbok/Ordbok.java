package no.hvl.dat102.ordbok;

import java.util.HashMap;
import java.util.Iterator;

public class Ordbok<K, V> implements OrdbokInterface<K, V> {
	
	private HashMap<K, V> ordbok;
	
	public Ordbok() {
		ordbok = new HashMap<K, V>();
	}

	// Blir fylt inn på forelesning
	// Vil bruke den forhåndsdefinerte klassen HashMap i Java
	
	@Override
	public V leggTil(K nokkel, V verdi) {
		V gammel = ordbok.get(nokkel);
		ordbok.put(nokkel, verdi);
		return gammel;
	}

	@Override
	public V fjern(K nokkel) {
		return ordbok.remove(nokkel);
	}

	@Override
	public V getVerdi(K nokkel) {
		return ordbok.get(nokkel);
	}

	@Override
	public boolean inneholder(K nokkel) {
		return ordbok.containsKey(nokkel);
	}

	@Override
	public Iterator<K> getNokkelIterator() {
		return ordbok.keySet().iterator();
	}

	@Override
	public Iterator<V> getVerdiIterator() {
		return ordbok.values().iterator();
	}

	@Override
	public boolean erTom() {
		return ordbok.isEmpty();
	}

	@Override
	public int getAntall() {
		return ordbok.size();
	}

	@Override
	public void toem() {
		ordbok.clear();
		
	}
	
}
