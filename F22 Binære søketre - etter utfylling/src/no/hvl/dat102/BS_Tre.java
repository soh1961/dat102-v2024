package no.hvl.dat102;

public class BS_Tre<T extends Comparable<? super T>> extends BinaerTre<T> implements SoektreADT<T> {

	BS_Tre() {
		super();
	}

	BS_Tre(T rotElement) {
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
		// sjå inneholder
		return false;
	}

	@Override
	public T finn(T element) {
		return finn(element, rot);
	}

	private T finn(T element, BinaerTreNode<T> p) {
		T svar = null;

		// basis: p == null -> tomt tre, element finst ikkje

		if (p != null) {
			int sml = element.compareTo(p.getElement());
			if (sml == 0) { // basis, har funne elementet
				svar = p.getElement();
			} else if (sml < 0) {
				svar = finn(element, p.getVenstre());
			} else {
				svar = finn(element, p.getHogre());
			}
		}

		return svar;
	}

	@Override
	public T leggTil(T nyttElement) {
		if (rot == null) {
			rot = new BinaerTreNode<T>(nyttElement);
			return null;
		} else {
			return leggTil(nyttElement, rot);
		}
	}

	private T leggTil(T nyttElement, BinaerTreNode<T> p) {
		// p != null

		T resultat = null;
		int sml = nyttElement.compareTo(p.getElement());

		if (sml == 0) {
			resultat = p.getElement(); // tek vare på gamal verdi
			p.setElement(nyttElement);
		} else if (sml < 0) {
			if (p.getVenstre() != null) {
				resultat = leggTil(nyttElement, p.getVenstre());
			} else {
				BinaerTreNode<T> ny = new BinaerTreNode<>(nyttElement);
				p.setVenstre(ny);
			}
		} else {
			if (p.getHogre() != null) {
				resultat = leggTil(nyttElement, p.getHogre());
			} else {
				// For å vise alternativ til to linjer
				p.setHogre(new BinaerTreNode<>(nyttElement));
			}
		}

		return resultat;
	}

	@Override
	public T fjern(T element) {
		/*
		 * Det er ikkje pensum å kunne skrive kode for denne.
		 */

		throw new UnsupportedOperationException();
	}
}
