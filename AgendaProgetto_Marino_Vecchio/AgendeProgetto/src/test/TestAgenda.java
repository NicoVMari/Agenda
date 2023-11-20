package test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import agenda_appuntamenti.Agenda;
import agenda_appuntamenti.Appuntamento;
import agenda_appuntamenti.AgendaException;
import agenda_appuntamenti.AppuntamentiException;

class TestAgenda { 
	DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter formatterOrario = DateTimeFormatter.ofPattern("HH-mm");
	
	private static final PrintStream originalOut = System.out;
	
	@Test
	void testCostruttore1Par() throws AgendaException { 
		Agenda a1 = new Agenda("Lavoro"); 
		
		assertEquals(a1.getNome(),"Lavoro");
		assertEquals(a1.getAppuntamenti(),new ArrayList<Appuntamento>());
		
		AgendaException ex1 = assertThrows(AgendaException.class, () -> {
			new Agenda("Lavoro£!"); 
		}); 
		assertEquals("Il nome deve essere composto solo da caratteri dell'alfabeto",ex1.getMessage());
	}
	
	@Test
	void testGetNome() throws AppuntamentiException, AgendaException{
		Appuntamento app1 = new Appuntamento("12-05-2023","14-30","60","Mario","Torino");
		Agenda a1 = new Agenda("Lavoro");
		
		assertEquals(a1.getNome(),"Lavoro");
	}
	
	@Test
	void testGetAppuntamenti() throws AppuntamentiException, AgendaException{
		Appuntamento app1 = new Appuntamento("12-05-2023","14-30","60","Mario","Torino");
		Agenda a1 = new Agenda("Lavoro");
		a1.inserisciAppuntamento(app1);
		
		assertTrue(a1.getAppuntamenti().get(0).equals(app1)); 
	}
	
	@Test
	void testScriviSuFile() throws AgendaException, IOException, AppuntamentiException {
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		Appuntamento app2 = new Appuntamento("02-01-2022", "09-00", "120","Paolo", "Medico");
		Appuntamento app3 = new Appuntamento("02-01-2022", "18-00", "90","Mario", "Palestra");
		Appuntamento app4 = new Appuntamento("03-01-2022", "14-00", "30", "Piero", "Riunione"); 
		Appuntamento app5 = new Appuntamento("10-01-2022", "09-00", "120","Piero", "Caffe");
		Agenda agenda = new Agenda("Prova"); 
		agenda.inserisciAppuntamento(app1);
		agenda.inserisciAppuntamento(app2);
		agenda.inserisciAppuntamento(app3);
		agenda.inserisciAppuntamento(app4);
		agenda.inserisciAppuntamento(app5);
		
        agenda.scriviSuFile("fileScrivi.txt");

        try (BufferedReader br = new BufferedReader(new FileReader("fileScrivi.txt"))) {
            assertEquals("01-01-2022;10-00;60;Piero;Ufficio", br.readLine());
            assertEquals("02-01-2022;09-00;120;Paolo;Medico", br.readLine());
            assertEquals("02-01-2022;18-00;90;Mario;Palestra", br.readLine());
            assertEquals("03-01-2022;14-00;30;Piero;Riunione", br.readLine());
            assertEquals("10-01-2022;09-00;120;Piero;Caffe", br.readLine()); 
        } 
	}   
	
	@Test
	void testIterable() throws AppuntamentiException, AgendaException {
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		Appuntamento app2 = new Appuntamento("02-01-2022", "09-00", "120","Paolo", "Medico");
		Appuntamento app3 = new Appuntamento("02-01-2022", "18-00", "90","Mario", "Palestra");
		Appuntamento app4 = new Appuntamento("03-01-2022", "14-00", "30", "Piero", "Riunione"); 
		Appuntamento app5 = new Appuntamento("10-01-2022", "09-00", "120","Piero", "Caffe");
		Agenda agenda = new Agenda("Prova"); 
		agenda.inserisciAppuntamento(app1);
		agenda.inserisciAppuntamento(app2);
		agenda.inserisciAppuntamento(app3);
		agenda.inserisciAppuntamento(app4);
		agenda.inserisciAppuntamento(app5);
		
		Iterator<Appuntamento> it_app = agenda.iterator();
		
		assertTrue(it_app.hasNext());
		assertEquals(it_app.next().getNome(),"Piero");
		assertTrue(it_app.hasNext());
		assertEquals(it_app.next().getDurata(),120);
		assertTrue(it_app.hasNext());
		assertEquals(it_app.next().getLuogo(),"Palestra");
		assertTrue(it_app.hasNext());
		assertEquals(it_app.next().getData(), LocalDate.parse("03-01-2022", formatterData));
		assertTrue(it_app.hasNext());
		assertEquals(it_app.next().getOrario(),LocalTime.parse("09-00", formatterOrario));
		assertFalse(it_app.hasNext());

		
		int i = 0;
        for (Appuntamento a : agenda) { 
        	assertEquals(agenda.getAppuntamenti().get(i++), a);
        }
        assertEquals(i,5);
      
	}
	
	@Test
    void testAggiungiAppuntamento() throws AppuntamentiException, AgendaException {
		Agenda agenda = new Agenda("Lavoro");
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Filippo", "Ufficio");
		Appuntamento app2 = new Appuntamento("01-01-2022", "10-00", "60", "Mario", "Ristorante");
		Appuntamento app3 = new Appuntamento("01-01-2022", "09-30", "90", "Piero", "Caffe");
		Appuntamento app4 = new Appuntamento("05-01-2022", "15-00", "60", "Francesco", "Palestra");
		Appuntamento app5 = new Appuntamento("01-04-2022", "17-00", "60", "Piero", "Ristorante");
		Appuntamento app6 = new Appuntamento("12-12-2022", "06-30", "90", "Bob", "Caffe");
		Appuntamento app7 = new Appuntamento("01-01-2022", "10-30", "90", "Piero", "Caffe");
		
		agenda.inserisciAppuntamento(app1); 
		
	    assertEquals(1, agenda.getAppuntamenti().size());
	     
	    AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
	    	 agenda.inserisciAppuntamento(app2);
		}); 
		assertEquals("Il nuovo appuntamento e' nello stesso giorno e allo stesso orario di un altro appuntamento",ex1.getMessage());
		assertEquals(1, agenda.getAppuntamenti().size());
		
	    AppuntamentiException ex2 = assertThrows(AppuntamentiException.class, () -> {
	    	agenda.inserisciAppuntamento(app3);
		}); 
		assertEquals("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento",ex2.getMessage());
		assertEquals(1, agenda.getAppuntamenti().size());
		
		agenda.inserisciAppuntamento(app4); 
		agenda.inserisciAppuntamento(app5);
		agenda.inserisciAppuntamento(app6);
		assertEquals(4, agenda.getAppuntamenti().size());
		
	    AppuntamentiException ex3 = assertThrows(AppuntamentiException.class, () -> {
	    	agenda.inserisciAppuntamento(app7);
		}); 
		assertEquals("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento",ex3.getMessage());
	}
	
	@Test
	void testAggiornaAppuntamento() throws AppuntamentiException, AgendaException {
		Agenda agenda = new Agenda("Lavoro");
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Pietro", "Ufficio");
		Appuntamento app2 = new Appuntamento("01-01-2022", "10-00", "60", "Paolo", "Ristorante");
		Appuntamento app3 = new Appuntamento("02-04-2022", "18-00", "90","Mario", "Palestra");
		
		 agenda.inserisciAppuntamento(app1);
		 assertEquals(1, agenda.getAppuntamenti().size());
		    
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> { 
			agenda.inserisciAppuntamento(app2); 
		}); 
		assertEquals("Il nuovo appuntamento e' nello stesso giorno e allo stesso orario di un altro appuntamento",ex1.getMessage());
		assertEquals(1, agenda.getAppuntamenti().size());
		
		agenda.aggiornaAppuntamento(app1, "02-01-2022","10-00" ,"60", "Pietro", "Ufficio");
		agenda.inserisciAppuntamento(app2);
		assertEquals(2, agenda.getAppuntamenti().size());
		
		agenda.inserisciAppuntamento(app3);
		assertEquals(3, agenda.getAppuntamenti().size());
		agenda.aggiornaAppuntamento(app3, "02-04-2022", "18-00", "120", "Mario", "Palestra");
		assertEquals(agenda.getAppuntamenti().get(2).getDurata(),120);
		assertEquals(3, agenda.getAppuntamenti().size());
	}
	
	@Test
	void testCercaAppuntamenti() throws AppuntamentiException, AgendaException {
		  Agenda agenda = new Agenda("Lavoro");
		  Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		  Appuntamento app2 = new Appuntamento("02-01-2022", "09-00", "120","Paolo", "Medico");
		  Appuntamento app3 = new Appuntamento("02-01-2022", "18-00", "90","Mario", "Palestra");
		  Appuntamento app4 = new Appuntamento("03-01-2022", "14-00", "30", "Piero", "Riunione");
		  Appuntamento app5 = new Appuntamento("10-01-2022", "09-00", "120","Piero", "Caffe");
		  
		  agenda.inserisciAppuntamento(app1);
		  agenda.inserisciAppuntamento(app2);
		  agenda.inserisciAppuntamento(app3);
		  agenda.inserisciAppuntamento(app4);
		  agenda.inserisciAppuntamento(app5);
		  assertEquals(5, agenda.getAppuntamenti().size());
		  
		  ByteArrayOutputStream out1 = new ByteArrayOutputStream();
		  System.setOut(new PrintStream(out1)); 
		  agenda.cercaAppuntamenti("02-01-2022");
		  String output1 = out1.toString();
		  assertEquals("\n"
		  		+ "				Appuntamento [data=02-01-2022, orario=09-00, durata=120, nome=Paolo, luogo=Medico]\n"
		  		+ "				Appuntamento [data=02-01-2022, orario=18-00, durata=90, nome=Mario, luogo=Palestra]", output1);
		  System.setOut(originalOut);
		  
		  ByteArrayOutputStream out2 = new ByteArrayOutputStream();
		  System.setOut(new PrintStream(out2));
		  agenda.cercaAppuntamenti("Piero"); 
		  String output2 = out2.toString();
		  assertEquals("\n"
		  		+ "				Appuntamento [data=01-01-2022, orario=10-00, durata=60, nome=Piero, luogo=Ufficio]\n"
		  		+ "				Appuntamento [data=03-01-2022, orario=14-00, durata=30, nome=Piero, luogo=Riunione]\n"
		  		+ "				Appuntamento [data=10-01-2022, orario=09-00, durata=120, nome=Piero, luogo=Caffe]", output2);
		  
		  System.setOut(originalOut); 
		  
		  ByteArrayOutputStream out3 = new ByteArrayOutputStream();
		  System.setOut(new PrintStream(out3));
		  agenda.cercaAppuntamenti("Francesco"); 
		  String output3 = out3.toString();
		  assertEquals("Appuntamento non trovato\r\n", output3);
		  
		  System.setOut(originalOut); 
	}
	
	@Test
	void testOrdinaAppuntamenti() throws AppuntamentiException, AgendaException {
		  Agenda agenda = new Agenda("Lavoro");
		  Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		  Appuntamento app2 = new Appuntamento("06-01-2022", "09-00", "120","Paolo", "Medico");
		  Appuntamento app3 = new Appuntamento("02-01-2022", "18-00", "90","Mario", "Palestra"); 
		  Appuntamento app4 = new Appuntamento("03-01-2022", "14-00", "30", "Piero", "Riunione");
		  Appuntamento app5 = new Appuntamento("23-01-2022", "18-00", "120","Piero", "Caffe");
		  Appuntamento app6 = new Appuntamento("23-01-2022", "09-00", "30","Francesco", "Negozio");
		  
		  agenda.inserisciAppuntamento(app1);
		  agenda.inserisciAppuntamento(app2);
		  agenda.inserisciAppuntamento(app3);
		  agenda.inserisciAppuntamento(app4);
		  agenda.inserisciAppuntamento(app5);
		  agenda.inserisciAppuntamento(app6);
		  assertEquals(6, agenda.getAppuntamenti().size());  
		  
		  agenda.ordinaAppuntamenti();
		  
		  assertEquals(app1, agenda.getAppuntamenti().get(0));
	      assertEquals(app3, agenda.getAppuntamenti().get(1));
	      assertEquals(app4, agenda.getAppuntamenti().get(2));
	      assertEquals(app2, agenda.getAppuntamenti().get(3));
	      assertEquals(app6, agenda.getAppuntamenti().get(4));
	      assertEquals(app5, agenda.getAppuntamenti().get(5));
	}
	
	@Test
	void testToString() throws AppuntamentiException, AgendaException {
		 Agenda agenda = new Agenda("Lavoro");
		  Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		  Appuntamento app2 = new Appuntamento("06-01-2022", "09-00", "120","Paolo", "Medico");
		  Appuntamento app3 = new Appuntamento("02-01-2022", "18-00", "90","Mario", "Palestra"); 

		  agenda.inserisciAppuntamento(app1);
		  agenda.inserisciAppuntamento(app2);
		  agenda.inserisciAppuntamento(app3); 
		  
		  String expected = "\n"
		  		+ "	Agenda [nome=Lavoro, appuntamenti=[\n"
		  		+ "				Appuntamento [data=01-01-2022, orario=10-00, durata=60, nome=Piero, luogo=Ufficio], \n"
		  		+ "				Appuntamento [data=06-01-2022, orario=09-00, durata=120, nome=Paolo, luogo=Medico], \n"
		  		+ "				Appuntamento [data=02-01-2022, orario=18-00, durata=90, nome=Mario, luogo=Palestra]]]";
		  assertEquals(expected,agenda.toString()); 
	}
}
