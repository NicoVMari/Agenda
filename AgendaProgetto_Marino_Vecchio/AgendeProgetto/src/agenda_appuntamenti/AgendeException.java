package agenda_appuntamenti;

/**
 * La classe {@code AgendeException} rappresenta un'eccezione che viene lanciata quando si verifica un errore durante l'utilizzo di un'oggetto di tipo {@code Agende}.
 * 
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
 * @see Agende
 */
public class AgendeException extends Exception {
	private static final long serialVersionUID = 1L;


    /**
     * Costruisce una nuova eccezione AgendeException con il messaggio di dettaglio specificato.
     * 
     * @param string Il messaggio di errore.
     */
	public AgendeException(String string) {
		super(string); 
	} 
}
