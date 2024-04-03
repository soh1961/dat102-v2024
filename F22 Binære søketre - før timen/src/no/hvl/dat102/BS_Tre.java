package no.hvl.dat102;

public class BS_Tre <T extends Comparable<? super T>> extends BinaerTre<T> 
implements SoektreADT<T>{
	BS_Tre(){
		super();
	}
	
	BS_Tre(T rotElement){
		super(rotElement, null, null);
	}
	
	@Override
	public void setTre(T rotData) {
		// Vi ønskjer ikkje at denne metoden skal være tilgjengeleg
		// Ville i så fall være mulig å øydelegge det binære søketreet
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setTre(T rotData, BinaerTre<T> venstre, BinaerTre<T> hogre) {
		// Vi ønskjer ikkje at denne metoden skal være tilgjengeleg
		// Ville i så fall være mulig å øydelegge det binære søketreet
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean inneholder(T element) {
		// Blir fylt inn på forelesning
		return false;
	}

	@Override
	public T finn(T element) {
		// Blir fylt inn på forelesning
		return null;
	}

	@Override
	public T leggTil(T nyElement) {
		// Blir fylt inn på forelesning
		return null;
	}

	@Override
	public T fjern(T element) {
		/*
		 * Det er ikkje pensum å kunne skrive kode for denne.
		 */
		
		throw new UnsupportedOperationException();
	}
}
