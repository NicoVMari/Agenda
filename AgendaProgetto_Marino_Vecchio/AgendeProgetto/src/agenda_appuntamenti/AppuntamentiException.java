package agenda_appuntamenti;

/**
 * La classe {@code AppuntamentiException} rappresenta un'eccezione che viene lanciata quando si verifica un errore durante l'utilizzo di un'oggetto di tipo {@code Appuntamenti}.
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
 * @see Appuntamento
*/
public class AppuntamentiException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nuova istanza della classe AppuntamentiException con il messaggio di errore specificato.
	 *
	 * @param string il messaggio di errore
	 */
	public AppuntamentiException(String string) {
		super(string); 
	} 
}
