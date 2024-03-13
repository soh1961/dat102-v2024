package no.hvl.dat102.ordnapar;

public class OrdnaPar<S, T> {

	private S forste;
	private T andre;
	
	public OrdnaPar(S forste, T andre) {
		this.forste = forste;
		this.andre = andre;
	}

	// get-, set-metoder og toString
	
	public S getForste() {
		return forste;
	}

	public void setForste(S forste) {
		this.forste = forste;
	}

	public T getAndre() {
		return andre;
	}

	public void setAndre(T andre) {
		this.andre = andre;
	}
	
	@Override
	public String toString() {
		return "(" + forste + ", " + andre + ")";
	}
}
