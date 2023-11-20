package agenda_appuntamenti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La classe Agende rappresenta un insieme di oggetti Agenda. Offre metodi per creare nuove agende, eliminare agende esistenti e creare un'agenda a partire da un file di testo.
 * 
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
 */
public class Agende {
	private ArrayList<Agenda> agende;


	/**
	 * Costruttore che inizializza una nuova lista vuota di agende
	 */
	public Agende() {
		this.agende = new ArrayList<Agenda>(); 
	}

	/**
	 * Restituisce la lista di agende
	 * 
	 * @return lista delle agende
	 */
	public ArrayList<Agenda> getAgende() {
		return agende;
	}
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * @return una stringa che rappresenta l'oggetto nel formato "Agende [agende=valore]"
	*/

	@Override
	public String toString() {
		return "Agende "+agende+" ";
	}
	
	/**
	 * Crea una nuova agenda con il nome fornito e l'aggiunge alla lista delle agende.
	 * 
	 * @param nome nome dell'agenda
	 * @throws AgendeException se il nome dell'agenda e' gia' presente nella lista
	 * @throws AgendaException se il nome dell'agende non e' valido
	 * @return Agenda creata
	 */
	public Agenda creaAgenda(String nome) throws AgendeException, AgendaException {
		ValidaAggiungiAgenda(nome);
		Agenda agenda = new Agenda(nome); 
		agende.add(agenda);
		return agenda;
	}

	/**
	 * Verifica se il nome dell'agenda e' gia' presente nella lista delle agende.
	 * 
	 * @param nome nome dell'agenda
	 * @throws AgendeException se il nome e' gia' presente
	 */
	private void ValidaAggiungiAgenda(String nome) throws AgendeException{
		for(Agenda a : agende) { 
			if(a.getNome().equals(nome)) throw new AgendeException("Il nome dell'agenda e' gia' presente"); 
		}
	}
	
	/**
	 * Rimuove l'agenda fornita dalla lista delle agende.
	 * 
	 * @param agenda l'agenda da eliminare
	 */
	public void eliminaAgenda(Agenda agenda) { 
		agende.remove(agenda); 
	} 
	
	/**
	 * Crea una nuova agenda a partire dai dati in un file di testo.
	 * 
	 * @param nomeA nome dell'agenda
	 * @param filepath percorso del file di testo
	 * @throws AgendaException se il nome dell'agende non e' valido
	 * @throws IOException in caso di problemi durante la lettura del file
	 * @throws AppuntamentiException in caso di problemi nelal crezione dell'appuntamento
	 * @throws AgendeException se il nome dell'agenda e' gia' presente nella lista
	 * @return Agenda
	 */
	public Agenda creaAgendaDaFile(String nomeA, String filepath) throws AgendaException, IOException, AppuntamentiException, AgendeException{
		 ValidaAggiungiAgenda(nomeA);
		Agenda agenda = new Agenda(nomeA);
 
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) { 
            String line = br.readLine();
            while (line != null) {
                String[] datiAppuntamento = line.split(";");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
                LocalDate data = LocalDate.parse(datiAppuntamento[0], formatter);
                formatter = DateTimeFormatter.ofPattern("HH-mm");
                LocalTime orario = LocalTime.parse(datiAppuntamento[1], formatter);
                int durata = Integer.parseInt(datiAppuntamento[2]);
                String nome = datiAppuntamento[3];
                String luogo = datiAppuntamento[4]; 
                Appuntamento appuntamento = new Appuntamento(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), orario.format(DateTimeFormatter.ofPattern("HH-mm")), Integer.toString(durata), nome, luogo);
                agenda.inserisciAppuntamento(appuntamento);

                line = br.readLine(); 
            }
        }

      
        agende.add(agenda);
        return agenda; 
    }
}
