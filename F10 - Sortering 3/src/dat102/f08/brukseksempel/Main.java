package dat102.f08.brukseksempel;

import java.util.ArrayList;
import java.util.List;

import dat102.f08Ogf09.sortering.SorterTabell;

public class Main {
	public static void main(String[] args) {
		List<Person> personer = new ArrayList<>();
		personer.add(new Person("Sven-Olai", "Høyland"));
		
		// Legger til flere personer
		
		Person[] tab = personer.toArray(new Person[0]);
		
		SorterTabell.utvalgssortering(tab);
		
		for (Person p : tab) {
			System.out.println(p);
		}
		
		Integer[] a = {7, 0, 2, 9, 11, 6};
		SorterTabell.sorterVedInnsetting(a);
		
		for (Integer e : a) {
			System.out.print(e + " ");
		}
		
		System.out.println();
	}

}
