package no.hvl.dat102;

public class Main {

	public static void main(String[] args) {
		BS_Tre<Integer> t = new BS_Tre<>();
		t.leggTil(8);
		t.leggTil(3);
		t.leggTil(0);
		t.leggTil(1);
		
		t.visInorden();
		

	}

}
