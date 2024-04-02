package no.hvl.dat102;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		/*
   		Konstruerer eit binært tre som ser slik ut
  
           5
          / \
         1   7 
  
	 	*/
		BinaerTre<Integer> t1 = new BinaerTre<>(1);
		BinaerTre<Integer> t2 = new BinaerTre<>(7);
		BinaerTre<Integer> t3 = new BinaerTre<>(5, t1, t2);

		Iterator<Integer> it = t3.getInordenIterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// Lage eit ny tre med t3 som venstre undertre, 17 i rota og eit undertre
		// med berre ein node som inneheld 20
		
		/*
   		Konstruerer eit binært tre som ser slik ut
   		     17
            /  \
           5   20 
          / \
         1   7 
  
	 	*/
		
		System.out.println("Antall nodar: " + t3.getAntall());
		System.out.println("Høgda: " + t3.getHogde());
		t3.visPreorden();
		t3.visInorden();
		t3.visPostorden();
	}
}
