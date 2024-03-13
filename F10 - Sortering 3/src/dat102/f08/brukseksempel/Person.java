package dat102.f08.brukseksempel;

public class Person implements Comparable<Person>{
	private String fornavn;
	private String etternavn;
	
	public Person() {
		this("", "");
	}
	
	// har ikkje laga get- og set-metoder
	
	public Person(String fornavn, String etternavn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
	}
	
	@Override
	public String toString() {
		return etternavn + ", " + fornavn;
	}

	@Override
	public int compareTo(Person p) {
		
		int resultat = etternavn.compareTo(p.etternavn);
		if (resultat == 0) {
			resultat = fornavn.compareTo(p.fornavn);
		}
		
		return resultat;
	}
}
