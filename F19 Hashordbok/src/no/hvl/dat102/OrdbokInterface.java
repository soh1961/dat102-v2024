package no.hvl.dat102;

import java.util.Iterator;

/**
 * Kontrakt for en ordbok med ulike søkeneøkler. Verdier knytter til
 * nøklene er ulik null.
 *  
 */
public interface OrdbokInterface<K, V> {
	
	/**
	 * Legger et nytt element til ordboka. Om søkenøkkelen allerede finnes
	 * i ordboken, så vil tilhørende verdi bli erstattet med ny verdi.
	 * 
	 * @param nokkel Nøkkel for det nye elementet (kan være del av verdi).
	 * @param verdi Verdi knyttet til nøkkelen.
	 * @return Enten null om det nye elementet er lagt til ordboken eller
	 * gammel verdi om verdien erstattet verdi fra før.
	 * 
	 */
	V leggTil(K nokkel, V verdi);

	/**
	 * Fjerner et gitt element fra ordboken.
	 * 
	 * @param nokkel Nøkkel til element som skal fjernast.
	 * @return Enten verdien knytta til nøkklelen om den finnes eller null om den
	 * ikke finnes. 
	 */
	V fjern(K nokkel);

	/**
	 * Henter verdien knyttet til gitt søkenøkkel.
	 * 
	 * @param nokkel Nøkkel til elementet som man søker etter.
	 * @return Enten verdien knytta til nøkklelen om den finnes eller null om den
	 * ikke finnes.
	 */
	V getVerdi(K nokkel);

	/**
	 * Sjekker om element med gitt søkenøkkel finnes i ordboka.
	 * 
	 * @param nokkel Nøkkel til ønsket element.
	 * @return True om elementet med gitt nøkkel finnes i ordboka.
	 */
	boolean inneholder(K nokkel);

	/**
	 * Lager en iterator som går gjennom alle nøklene  i ordboka.
	 * 
	 * @return En iterator som gir sekvensiell tilgang til nøklene i ordboka.
	 */
	Iterator<K> getNokkelIterator();

	/**
	 * Lager en iterator som går gjennom alle verdiene i ordboka.
	 * 
	 * @return En iterator som gir sekvensiell tilgang til verdiene i ordboka.
	 */
	Iterator<V> getVerdiIterator();

	/**
	 * Sjekker om ordboka er tom.
	 * 
	 * @return True om ordboka er tom.
	 */
	boolean erTom();

	/**
	 * Finner antall elementer i ordboka.
	 * 
	 * @return Antall element (nøkkel-verdi par) i ordboka.
	 */
	int getAntall();

	/** Fjerner alle elementer fra ordboka. */
	void toem();
}
