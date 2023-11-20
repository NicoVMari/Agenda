package agenda_appuntamenti;

/**
 * La classe {@code AgendaException} rappresenta un'eccezione che viene lanciata quando si verifica un errore durante l'utilizzo di un'oggetto di tipo {@code Agenda}.
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
 * @see Agenda
*/
public class AgendaException extends Exception{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nuova istanza della classe AgendaException con il messaggio di errore specificato.
	 *
	 * @param string il messaggio di errore
	 */
	public AgendaException(String string) {
		super(string);  
	}  
}
