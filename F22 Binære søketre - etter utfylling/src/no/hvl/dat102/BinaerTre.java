package no.hvl.dat102;


import java.util.Iterator;
import java.util.NoSuchElementException;

import no.hvl.dat102.lenketstabel.LenketStabel;
import no.hvl.dat102.lenketstabel.StabelADT;

public class BinaerTre<T> implements BinaerTreADT<T> {
	protected BinaerTreNode<T> rot;

	public BinaerTre() {
		rot = null;
	}

	public BinaerTre(T data) {
		rot = new BinaerTreNode<>(data);
	}

	public BinaerTre(T rotData, BinaerTre<T> venstre, BinaerTre<T> hogre) {
		privateSetTre(rotData, venstre, hogre);
	}
	
	@Override
	public int getAntall() {
		return getAntall(rot);
	}

	private int getAntall(BinaerTreNode<T> t) {
		if (t == null) {
			return 0;
		}

		int aV = getAntall(t.getVenstre());
		int aH = getAntall(t.getHogre());
		return 1 + aV + aH;
		
		// alt: return 1 + getAntall(t.getVenstre()) + getAntall(t.getHogre());
		
		// alt for heile: return t == null ? 0 : 1 + ...;
	}

	@Override
	public int getHogde() {
		return getHogde(rot);
	}

	private int getHogde(BinaerTreNode<T> t) {
		if (t == null) {
			return 0;
		}
		
		int hV = getHogde(t.getVenstre());
		int hH = getHogde(t.getHogre());
		return 1 + Math.max(hV, hH);
	}

	/* Dei tre vis-metodane nedanfor er tatt med for å vise rekursiv gjennnomgang av tre.
	 * Bruk av iteratorar er meir generelt for det er ikkje sikkert at du ønskjer å skrive
	 * ut elementa.
	 */
	public void visPreorden() {
		visPreorden(rot);
		System.out.println();
	}

	private void visPreorden(BinaerTreNode<T> t) {
		if (t == null) {
			// basis: gjer ingenting
		} else {
			System.out.print(t.getElement() + " ");
			visPreorden(t.getVenstre());
			visPreorden(t.getHogre());
		}
		
		/*
		 // basis gjer ingenting
		 if (t != null){
		    dei tre kodelinjene
		 }
		 */
	}

	public void visInorden() {
		System.out.println("Inorden gjennomgang");
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> t) {
		if (t == null) {
			// basis: gjer ingenting
		} else {
			visInorden(t.getVenstre());
			System.out.print(t.getElement() + " ");
			visInorden(t.getHogre());
		}
	}

	public void visPostorden() {
		visPostorden(rot);
		System.out.println();
	}

	private void visPostorden(BinaerTreNode<T> t) {
		if (t == null) {
			// basis: gjer ingenting
		} else {
			visPostorden(t.getVenstre());
			visPostorden(t.getHogre());
			System.out.print(t.getElement() + " ");
		}
	}

	@Override
	public Iterator<T> getInordenIterator() {
		return new InordenIterator();
	}

	private class InordenIterator implements Iterator<T> {
		private StabelADT<BinaerTreNode<T>> nodeStack;
		private BinaerTreNode<T> currentNode;

		public InordenIterator() {
			nodeStack = new LenketStabel<>();
			currentNode = rot;
		} // end default constructor

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		} // end hasNext

		public T next() {
			BinaerTreNode<T> nextNode = null;

			// Find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getVenstre();
			} // end while

			// Get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nextNode != null; // Since nodeStack was not empty
				// before the pop
				currentNode = nextNode.getHogre();
			} else
				throw new NoSuchElementException();

			return nextNode.getElement();
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public T getRotData() {
		if (rot == null) {
			return null;
		} else {
			return rot.getElement();
		}
	}

	@Override
	public boolean erTom() {
		return rot == null;
	}

	@Override
	public void toem() {
		rot = null;
	}

	@Override
	public Iterator<T> getPreordenIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getPostOrdenIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getNivaaIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTre(T rotData) {
		rot = new BinaerTreNode<T>(rotData);
	}

	@Override
	public void setTre(T rotData, BinaerTreADT<T> venstre, BinaerTreADT<T> hogre) {
		// Generelt må vi gå gjennom venstre og høgre for å lage nye nodar
		// Metoden nedanfor fungerer for vår implementasjon
	}

	public void setTre(T rotData, BinaerTre<T> venstre, BinaerTre<T> hogre) {
		privateSetTre(rotData, venstre, hogre);
	}
	
	private void privateSetTre(T rotData, BinaerTre<T> venstre, BinaerTre<T> hogre) {
		rot = new BinaerTreNode<T>(rotData);
		
		if (venstre != null) {
			rot.setVenstre(venstre.rot);
		}

		if (hogre != null) {
			rot.setHogre(hogre.rot);
		}
	}
}
