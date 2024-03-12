package no.hvl.dat102;

public class Node<T> {
	
	 // Brukar ikkje private for å kunne skrive kode slik de er vane med

	 T data;
	 Node<T> neste;

	public Node(T data) {
		this.data = data;
		this.neste = null;
	}
}
