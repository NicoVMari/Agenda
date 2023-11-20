package agenda_appuntamenti;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * La classe Agenda e' responsabile di gestire gli appuntamenti.
 * Contiene informazioni sul nome dell'agenda e sugli appuntamenti che essa contiene.
 * Fornisce metodi per la gestione degli appuntamenti (inserimento, aggiornamento, rimozione).
 * Inoltre, permette di scrivere gli appuntamenti su un file.
 * 
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
*/
public class Agenda implements Iterable<Appuntamento>{
	private String nome; 
    private ArrayList<Appuntamento> appuntamenti;
    
	/**
	 * Formatter per il parsing della data in un oggetto LocalDate
	*/
	DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	/**
	 * Formatter per il parsing dell'orario in un oggetto LocalTime
	*/
	DateTimeFormatter formatterOrario = DateTimeFormatter.ofPattern("HH-mm");
    
    /**
	 * Metodo privato per la validazione del nome
	 * 
	 * @param nome Nome dell'appuntamento
	 * @throws AgendaException Se il nome non e' valido, viene lanciata un'eccezione
	*/
	private void validaNome(String nome) throws AgendaException {
		if (!nome.matches("[A-Za-z ]+")) throw new AgendaException("Il nome deve essere composto solo da caratteri dell'alfabeto");  
	}

	/**
	 * Costruttore con un parametro, utilizzato per creare un'agenda con un nome specifico
	 * 
	 * @param nome  il nome dell'agenda
	 * @throws AgendaException se il nome non e' valido
	 */
	public Agenda(String nome) throws AgendaException {
		validaNome(nome);
		
		this.nome = nome;
		this.appuntamenti = new ArrayList<Appuntamento>();    
	}
	
	/**
	 * Restituisce il nome dell'agenda
	 * 
	 * @return nome dell'agenda
	 */
	public String getNome() { 
		return nome;
	} 

	/**
     * Restituisce la lista degli appuntamenti nell'agenda.
     *
     * @return la lista degli appuntamenti nell'agenda
     */
	public ArrayList<Appuntamento> getAppuntamenti() {
		return appuntamenti; 
	}
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * @return una stringa che rappresenta l'oggetto nel formato "Agenda [nome=valore, appuntamenti=valore]"
	*/
	@Override
	public String toString() {
		return "\n\tAgenda [nome=" + nome + ", appuntamenti=" + appuntamenti + "]";
	}
	
	/**
     * Scrive l'agenda su un file di testo.
     *
     * @param filepath il percorso del file di testo su cui scrivere l'agenda
     * @throws IOException se si verifica un errore durante la scrittura del file
     */
    public void scriviSuFile(String filepath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) { 
            for (Appuntamento appuntamento : appuntamenti) {
                bw.write(appuntamento.getData().format(formatterData) + ";" + appuntamento.getOrario().format(formatterOrario) + ";" + appuntamento.getDurata() + ";" + appuntamento.getNome() + ";" + appuntamento.getLuogo());
                bw.newLine();
            }
        }
    }  

    /**
     * Restituisce un iteratore per gli appuntamenti dell'agenda.
     *
     * @return un iteratore per gli appuntamenti dell'agenda
     */
	@Override
	public Iterator<Appuntamento> iterator() {
		return new AppuntamentiIterator(appuntamenti);
	}
	
	/**
	 * Classe interna che implementa l'iteratore per gli appuntamenti dell'agenda.
	 */
	 private class AppuntamentiIterator implements Iterator<Appuntamento> {
	        private List<Appuntamento> appointments;
	        private int currentPosition;
	        
	    	/**
	    	 * Crea un nuovo iteratore per l'agenda.
	    	 *
	    	 * @param appuntamenti la lista degli appuntamenti dell'agenda
	    	 */
	        public AppuntamentiIterator(ArrayList<Appuntamento> appuntamenti) {
	            this.appointments = appuntamenti; 
	        }
	        
	    	/**
	    	 * Verifica se ci sono altri appuntamenti nell'agenda.
	    	 *
	    	 * @return true se ci sono altri appuntamenti nell'agenda, false altrimenti
	    	 */
	        public boolean hasNext() {
	            return currentPosition < appointments.size();  
	        }

	    	/**
	    	 * Restituisce il prossimo appuntamento dell'agenda.
	    	 *
	    	 * @return il prossimo appuntamento dell'agenda
	    	 */
	        public Appuntamento next() {
	            return appointments.get(currentPosition++);
	        }
	 }
	 
		/**
		*Inserisce un nuovo appuntamento nell'agenda, controllando che non ci siano conflitti di orari con gli altri appuntamenti presenti.
		*
		*@param newApp il nuovo appuntamento da inserire
		*@throws AppuntamentiException se il nuovo appuntamento e' nello stesso giorno e allo stesso orario di un altro appuntamento o se si sovrappone, a livello di orario, ad un altro appuntamento gia' presente
		*/
	 public void inserisciAppuntamento(Appuntamento newApp) throws AppuntamentiException{
			newApp.controllo(this);
			this.getAppuntamenti().add(newApp);  
	}
	
	/**
	 * Aggiorna le informazioni di un appuntamento esistente (app) con nuove informazioni specificate come argomento.
	 * 
	 * @param app l'appuntamento da modificare
	 * @param data la nuova data dell'appuntamento
	 * @param orario il nuovo orario dell'appuntamento
	 * @param durata la nuova durata dell'appuntamento
	 * @param nome il nuovo nome dell'appuntamento
	 * @param luogo il nuovo luogo dell'appuntamento
	 * @throws AppuntamentiException se il nuovo appuntamento (costruito con le nuove informazioni specificate) va in conflitto con altri appuntamenti gia' presenti nell'agenda
	 * @throws AgendaException se ci sono problemi con l'oggetto Agenda
	*/
	public void aggiornaAppuntamento(Appuntamento app,String data,String orario,String durata,String nome,String luogo) throws AppuntamentiException, AgendaException {
		Appuntamento app_temp = new Appuntamento(data,orario,durata,nome,luogo);
		this.getAppuntamenti().remove(app);
		
		app_temp.controllo(this);
		app.setData(data); 
		app.setOrario(orario);  
		app.setDurata(durata); 
		app.setNome(nome);
		app.setLuogo(luogo);
		 
		this.inserisciAppuntamento(app);	 
	}
	
	/**
	 * Cerca gli appuntamenti presenti in un'agenda in base a una stringa di ricerca specificata.
	 * Se la stringa di ricerca e' una data nel formato "dd-MM-yyyy", vengono cercati gli appuntamenti con quella data.
	 * Altrimenti, vengono cercati gli appuntamenti con il nome specificato nella stringa di ricerca.
	 * 
	 * @param str_cerca la stringa di ricerca (puo' essere una data o il nome di un appuntamento)
	 */
	public void cercaAppuntamenti(String str_cerca) { 
		int found = 0;
		if(str_cerca.matches("\\d{2}-\\d{2}-\\d{4}")) {
			for(Appuntamento app: this) {
				if(app.getData().equals(LocalDate.parse(str_cerca, DateTimeFormatter.ofPattern("dd-MM-yyyy")))) {
					System.out.print(app.toString());
					found = 1;
				}
			}
		}else {
			for(Appuntamento app: this) {
				if(app.getNome().equals(str_cerca)) { 
					System.out.print(app.toString()); 
					found = 1;
				}
			}
		} 
		if(found == 0)System.out.println("Appuntamento non trovato");
	}  

	/**
	 * Ordina gli appuntamenti presenti in un'agenda in base alla data e all'orario
	 * 
	 */
	public void ordinaAppuntamenti() {
		Collections.sort(this.getAppuntamenti()); 
	} 
}
