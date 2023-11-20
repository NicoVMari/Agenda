package agenda_appuntamenti;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe che rappresenta un appuntamento, con informazioni come data, orario, durata, nome e luogo.
 * Implementa l'interfaccia Comparable per poter essere confrontata con altri appuntamenti.
 * 
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
*/
public class Appuntamento implements Comparable<Appuntamento>{
	private LocalDate data;
	private LocalTime orario;
	private int durata;
	private String nome;
	private String luogo;
	
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
	 * @throws AppuntamentiException Se il nome non e' valido, viene lanciata un'eccezione
	*/
	private void validaNome(String nome) throws AppuntamentiException {
		if (!nome.matches("[A-Za-z ]+")) throw new AppuntamentiException("Il nome deve essere composto solo da caratteri dell'alfabeto");
	}

	/**
	 * Metodo privato per la validazione della durata
	 * 
	 * @param durata Durata dell'appuntamento in minuti
	 * @throws AppuntamentiException Se la durata non e' valida, viene lanciata un'eccezione
	*/
	private void validaDurata(String durata) throws AppuntamentiException {
		if (!durata.matches("[0-9]+")) throw new AppuntamentiException("Durata prevista espressa in minuti");
	}

	/**
	 * Metodo privato per la validazione dell'orario
	 * 
	 * @param orario Orario dell'appuntamento in formato "HH-mm"
	 * @throws AppuntamentiException Se l'orario non e' valido, viene lanciata un'eccezione
	*/
	private void validaOrario(String orario) throws AppuntamentiException {
		if (!orario.matches("\\d{2}-\\d{2}")) throw new AppuntamentiException("Orario dell’appuntamento, nel formato hh-mm");
	}

	/**
	 * Metodo privato per la validazione della data
	 * 
	 * @param data Data dell'appuntamento in formato "dd-MM-yyyy"
	 * @throws AppuntamentiException Se la data non e' valida, viene lanciata un'eccezione
	*/
	private void validaData(String data) throws AppuntamentiException {
		if (!data.matches("\\d{2}-\\d{2}-\\d{4}")) throw new AppuntamentiException("Data dell’appuntamento, nel formato gg-mm-aaaa");	
	}
	
	/**
	 * Metodo privato per la validazione del luogo
	 * 
	 * @param luogo Luogo dell'appuntamento
	 * @throws AppuntamentiException Se il luogo non e' valido, viene lanciata un'eccezione
	 */
	private void validaLuogo(String luogo) throws AppuntamentiException {
		if (!luogo.matches("[A-Za-z ]+")) throw new AppuntamentiException("Il luogo deve essere composto solo da caratteri dell'alfabeto");
	} 
	 
	/**
	 * Costruttore per creare un nuovo appuntamento con informazioni specificate dall'utente
	 * 
	 * @param data Data dell'appuntamento in formato "dd-MM-yyyy"
	 * @param orario Orario dell'appuntamento in formato "HH-mm"
	 * @param durata Durata dell'appuntamento in minuti
	 * @param nome Nome dell'appuntamento
	 * @param luogo Luogo dell'appuntamento
	 * @throws AppuntamentiException Se le informazioni fornite non sono valide, viene lanciata un'eccezione
	 */
	public Appuntamento(String data, String orario, String durata, String nome, String luogo) throws AppuntamentiException { 

		validaData(data);
		validaOrario(orario);
		validaDurata(durata);
		validaNome(nome);
		validaLuogo(luogo);
		
		this.data = LocalDate.parse(data, formatterData);
		this.orario = LocalTime.parse(orario, formatterOrario);
		this.durata = Integer.parseInt(durata);
		this.nome = nome;   
		this.luogo = luogo; 
	} 
	
	
 
	/**
	 * Imposta la data dell'appuntamento
	 * 
	 * @param data Data dell'appuntamento in formato "dd-MM-yyyy"
	 * @throws AppuntamentiException Se la data non e' valida, viene lanciata un'eccezione
	*/
	public void setData(String data) throws AppuntamentiException {
		validaData(data);
		this.data = LocalDate.parse(data, formatterData);
	}

	/**
	 * Imposta l'orario dell'appuntamento
	 * 
	 * @param orario Orario dell'appuntamento in formato "HH-mm"
	 * @throws AppuntamentiException Se l'orario non e' valido, viene lanciata un'eccezione
	 */
	public void setOrario(String orario) throws AppuntamentiException {
		validaOrario(orario);
		this.orario =  LocalTime.parse(orario, formatterOrario);
	}

	/**
	 * Imposta la durata dell'appuntamento
	 * 
	 * @param durata Durata dell'appuntamento in minuti
	 * @throws AppuntamentiException Se la durata non e' valida, viene lanciata un'eccezione
	 */
	public void setDurata(String durata) throws AppuntamentiException {
		validaDurata(durata);
		this.durata = Integer.parseInt(durata);
	}

	/**
	 * Imposta il nome dell'appuntamento
	 * 
	 * @param nome Nome dell'appuntamento
	 * @throws AppuntamentiException Se il nome non e' valido, viene lanciata un'eccezione
	 */
	public void setNome(String nome) throws AppuntamentiException {
		validaNome(nome);
		this.nome = nome;
	}

	/**
	 * Imposta il luogo dell'appuntamento
	 * 
	 * @param luogo Luogo dell'appuntamento
	 * @throws AppuntamentiException Se il luogo non e' valido, viene lanciata un'eccezione
	 */
	public void setLuogo(String luogo) throws AppuntamentiException {
		validaLuogo(luogo);
		this.luogo = luogo;
	} 
	
	/**
	 * Metodo toString per rappresentare in formato stringa le informazioni dell'appuntamento
	 * 
	 * @return Informazioni dell'appuntamento in formato stringa
	 */
	@Override 
	public String toString() {
		return "\n\t\t\t\tAppuntamento [data=" + data.format(formatterData) + ", orario=" + orario.format(formatterOrario) + ", durata=" + durata + ", nome=" + nome + ", luogo=" + luogo + "]";
	}

	/**
	 * Restituisce la data dell'appuntamento
	 * 
	 * @return Data dell'appuntamento
	 */
	public LocalDate getData() {
		return data; 
	}

	/**
	 * Restituisce l'orario dell'appuntamento
	 * 
	 * @return Orario dell'appuntamento
	 */
	public LocalTime getOrario() {
		return orario;
	}

	/**
	 * Restituisce la durata dell'appuntamento
	 * 
	 * @return Durata dell'appuntamento in minuti
	*/
	public int getDurata() {
		return durata;
	}

	/**
	 * Restituisce il nome dell'appuntamento
	 * 
	 * @return Nome dell'appuntamento
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce il luogo dell'appuntamento
	 * 
	 * @return Luogo dell'appuntamento
	 */
	public String getLuogo() { 
		return luogo;
	}
	
	/**
	 * Verifica se un nuovo appuntamento si sovrappone con quelli già presenti nell'agenda
	 * 
	 * @param agenda Agenda in cui verificare la sovrapposizione del nuovo appuntamento
	 * @throws AppuntamentiException Se l'appuntamento si sovrappone con un altro appuntamento già presente nell'agenda
	 */
	public void controllo(Agenda agenda) throws AppuntamentiException {
		for (Appuntamento app : agenda) {  
			if(this.getData().equals(app.getData()) && this.getOrario().equals(app.getOrario())) 
				throw new AppuntamentiException("Il nuovo appuntamento e' nello stesso giorno e allo stesso orario di un altro appuntamento");
			else if(this.sovrapponeOrario(app)) 
				throw new AppuntamentiException("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento");	
		}
	}

	/**
	 * Verifica se un nuovo appuntamento si sovrappone con un altro appuntamento in termini di orario
	 * 
	 * @param app Appuntamento con cui verificare la sovrapposizione
	 * @return true se si sovrappone, false altrimenti
	 */
	private boolean sovrapponeOrario(Appuntamento app) { 
		LocalDateTime orarioInizioNewApp = LocalDateTime.of(this.getData(), this.getOrario());
		LocalDateTime orarioFineNewApp = orarioInizioNewApp.plusMinutes(this.getDurata());
		
		LocalDateTime orarioInizioApp = LocalDateTime.of(app.getData(),app.getOrario() );
		LocalDateTime orarioFineApp = orarioInizioApp.plusMinutes(app.getDurata());
		  
		return orarioInizioNewApp.isBefore(orarioFineApp) && orarioFineNewApp.isAfter(orarioInizioApp);
	}
	
	/**
	 * Confronta questo appuntamento con un altro in base alla data e all'orario
	 * 
	 * @return Risultato del confronto: 0 se sono uguali, 1 se questo appuntamento e' successivo, -1 se e' precedente
	 */
	@Override
	public int compareTo(Appuntamento other) {
	    if (this.data.equals(other.getData())) {
	        return this.orario.compareTo(other.getOrario()); 
	    }
	    return this.data.compareTo(other.getData());
	}
}
 