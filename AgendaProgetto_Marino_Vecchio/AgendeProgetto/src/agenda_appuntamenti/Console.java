package agenda_appuntamenti;

import java.io.IOException;

import jbook.util.Input;

/**
 * Classe che rappresenta un l'interfaccia utente in grado di utilizzare tutte le funzioni delle classi Agende,Agenda e Appuntamento
 * 
 * @author Nicolo' Vittorio Marino 20044867@studenti.uniupo.it, Marco Vecchio 20044258@studenti.uniupo.it
*/
public class Console {
	
	/**
	 * Il metodo principale che esegue l'applicazione.
	 * @param args argomenti della riga di comando passati al programma
	 * @throws IOException se c'è un errore durante la lettura o scrittura dello stream di input/output
	 * @throws AppuntamentiException se c'è un errore nella gestione degli appuntamenti
	*/
	public static void main(String[] args) throws IOException, AppuntamentiException {
		Agende agende = new Agende();
		
		int r1 = 0;
		while(r1 != 6) {
			try {
				while(true) {
					r1 = Input.readInt("=========================\n"
							+ "Quali operazioni vuoi compiere?\n"
							+ "1)Visualizza le agende presenti nel sistema\n"
							+ "2)Crea Agenda vuota\n"
							+ "3)Crea Agenda da file\n"
							+ "4)Elimina Agenda\n"
							+ "5)Modifica Agenda\n"
							+ "6)Esci\n");
					if(r1 == 1) {
						if(agende.getAgende().size() > 0) { 
							System.out.println(agende.toString());
						}else
							System.out.println("Non ci sono ancora agende all'interno del sistema");
					}
					if(r1 == 2) {
						try {
							String nome = Input.readString("Inserisci il nome della nuova Agenda: ");
							Agenda agenda = agende.creaAgenda(nome);
							System.out.println("\nCreazione dell'agenda andata a buon termine");
							modAgenda(agenda);
						}catch(AgendeException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}catch(AgendaException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}
					}
					if(r1 == 3) {
						try {
							String nome = Input.readString("Inserisci il nome della nuova Agenda: ");
							String filepath = Input.readString("Inserisci il percorso file da dove si vogliono prendere i dati: ");
							Agenda agenda = agende.creaAgendaDaFile(nome,filepath);
							System.out.println("\nCreazine dell'agenda da file andata a buon termine");
							modAgenda(agenda);
						}catch(AgendeException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------"); 
						}catch(AgendaException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}catch(IOException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}catch(AppuntamentiException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}
					}
					if(r1 == 4) {
						if(agende.getAgende().size() > 0) {
							System.out.println("=========================\nQuale agenda vuoi eliminare?");
							int i = 0, del = 0;
							for(Agenda a : agende.getAgende()) {
								System.out.println(i++ +": "+a.getNome());
							}
							del = Input.readInt();
							agende.eliminaAgenda(agende.getAgende().get(del));
							System.out.println("\nEliminazione dell'agenda andata a buon termine");
						}else
							System.out.println("Non ci sono ancora agende da eliminare nel sistema");
					}
					if(r1 == 5) {
						if(agende.getAgende().size() > 0) {
							System.out.println("=========================\nQuale agenda vuoi aggiornare?");
						    int i = 0, mod = 0;
						    for(Agenda a : agende.getAgende()) {
						    	System.out.println(i++ +": "+a.toString()); 
						    	}
						    mod = Input.readInt();
						    modAgenda(agende.getAgende().get(mod));
						    System.out.println("\nAggiornamento dell'agenda andata a buon termine");
						    }else
						    	System.out.println("\nNon ci sono agende da modificare nel sistema");
					}
					if(r1 == 6) {
						System.out.println("\nFine Programma!!\n---------------\n");
						break;
					}
				}
			}catch(NumberFormatException e) {
				System.out.println("\nDevi inserisci un valore intero!!\n---------------");
			}
		}
	}

	private static void modAgenda(Agenda agenda) throws IOException, AppuntamentiException {
		int r2 = 0;
		while(r2 != 7) {
			try {
				while(true) {
					r2 = Input.readInt("\n=========================\n"
							+ "Quali operazioni sull'agenda vuoi compiere?\n"
							+ "1)Visualizza l'agenda\n"
							+ "2)Scrivi su file l'agenda\n"
							+ "3)Inserisci un nuovo appuntamento\n"
							+ "4)Aggiorna appuntamento\n"
							+ "5)Cerca appuntamento\n"
							+ "6)Ordina appuntamenti\n"
							+ "7)Esci\n");
					
					if(r2 == 1) {
						System.out.println(agenda.toString());
					}
					if(r2 == 2) {
						try {
							String filepath = Input.readString("Inserisci il percorso file su cui si vuole scrivere i dati: ");
							agenda.scriviSuFile(filepath);
							
							System.out.println("\nScrittura dell'agenda su file andata a buon termine");
						}catch(IOException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}
					}
					if(r2 == 3) {
						try {
							Appuntamento appuntamento = modAppuntamento();
							agenda.inserisciAppuntamento(appuntamento);
							
							System.out.println("\nInserimento del nuovo appuntamento nell'agenda andata a buon termine");
						}catch(AppuntamentiException e) {
							System.out.println("\n"+e.getMessage()+"!!\n---------------");
						}
					}
					if(r2 == 4) {
						if(agenda.getAppuntamenti().size() > 0) {
							System.out.println("=========================\nQuale appuntamento vuoi aggiornare?");
							int i = 0, agg = 0;
							for(Appuntamento app : agenda) {
								System.out.println(i++ +": "+app.toString()); 
							}
							agg = Input.readInt();
							String data = Input.readString("Inserisci la data dell'appuntamento aggiornato: ");
							String orario = Input.readString("Inserisci l'orario dell'appuntamento aggiornato: ");
							String durata = Input.readString("Inserisci la durata dell'appuntamento aggiornato: ");
							String nome = Input.readString("Inserisci il nome dell'appuntamento aggiornato: ");
							String luogo = Input.readString("Inserisci il luogo dell'appuntamento aggiornato: ");

							Appuntamento app = agenda.getAppuntamenti().get(agg);
							
							try {
								agenda.aggiornaAppuntamento(agenda.getAppuntamenti().get(agg), data, orario, durata, nome, luogo);
								
								System.out.println("\nAggiornamento dell'appuntamento nell'agenda andata a buon termine");
							} catch (AppuntamentiException e) {
								System.out.println("\n"+e.getMessage()+"!!\n---------------"); 
								agenda.inserisciAppuntamento(app);
							} catch (AgendaException e) {
								System.out.println("\n"+e.getMessage()+"!!\n---------------"); 
							}
						}else {
							System.out.println("\nNon ci sono appuntamenti da aggiornare nell'agenda");
						}
					}
					if(r2 == 5) {
						String src = Input.readString("Inserisci la data dell'appuntamento da ricercare oppure il nome dell'appuntamento: ");
						
						agenda.cercaAppuntamenti(src);
					}
					if(r2 == 6) {
						if(agenda.getAppuntamenti().size() > 0) { 
							agenda.ordinaAppuntamenti();
							System.out.println("\nAppuntamenti ordinati nell'agenda"); 
						}else {
							System.out.println("\nNon ci sono appuntamenti da ordinare nell'agenda");
						}
					}
					if(r2 == 7)
						break;
				}
			}catch(NumberFormatException e) {
				System.out.println("\nDevi inserisci un valore intero!!\n---------------"); 
			}
		}
	}

	private static Appuntamento modAppuntamento() throws AppuntamentiException {	
		String data = Input.readString("Inserisci la data dell'appuntamento : ");
		String orario = Input.readString("Inserisci l'orario dell'appuntamento : ");
		String durata = Input.readString("Inserisci la durata dell'appuntamento : ");
		String nome = Input.readString("Inserisci il nome dell'appuntamento : ");
		String luogo = Input.readString("Inserisci il luogo dell'appuntamento : ");
		
		return new Appuntamento(data,orario,durata,nome,luogo);
	}

}
