package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import agenda_appuntamenti.Appuntamento;
import agenda_appuntamenti.Agenda;
import agenda_appuntamenti.AgendaException;
import agenda_appuntamenti.AppuntamentiException;

class TestAppuntamento { 
	DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter formatterOrario = DateTimeFormatter.ofPattern("HH-mm");

	@Test
	void testCostruttore() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario Rossi","Ufficio sotto casa");
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
			new Appuntamento("12-5-2023","14-30","60","Mario","Torino");
		});
		assertEquals("Data dell’appuntamento, nel formato gg-mm-aaaa",ex1.getMessage());
		
		AppuntamentiException ex2 = assertThrows(AppuntamentiException.class, () -> {
			new Appuntamento("12-05-2023","14;30","60","Mario","Torino");
		});
		assertEquals("Orario dell’appuntamento, nel formato hh-mm",ex2.getMessage());
		 
		AppuntamentiException ex3 = assertThrows(AppuntamentiException.class, () -> {
			new Appuntamento("12-05-2023","14-30","1 ora","Mario","Torino");
		});
		assertEquals("Durata prevista espressa in minuti",ex3.getMessage());
		
		AppuntamentiException ex4 = assertThrows(AppuntamentiException.class, () -> {
			new Appuntamento("12-05-2023","14-30","60","Ma!i0","Torino");
		}); 
		assertEquals("Il nome deve essere composto solo da caratteri dell'alfabeto",ex4.getMessage());  
		
		AppuntamentiException ex5 = assertThrows(AppuntamentiException.class, () -> {
			new Appuntamento("12-05-2023","14-30","60","Mario","Torin0");
		});
		assertEquals("Il luogo deve essere composto solo da caratteri dell'alfabeto",ex5.getMessage()); 
	}
	
	@Test
	void testGetData() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Torino"); 
		
		assertEquals(app.getData(),LocalDate.parse("12-05-2023", formatterData));
	}
	
	@Test
	void testGetOrario() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Torino");
		
		assertEquals(app.getOrario(),LocalTime.parse("14-30", formatterOrario)); 
	}
	
	@Test
	void testGetDurata() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Torino");
		
		assertEquals(app.getDurata(),60);
	}
	
	@Test
	void testGetNome() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Torino"); 
		
		assertEquals(app.getNome(),"Mario");
	}
	
	@Test
	void testGetLuogo() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Torino");  
		
		assertEquals(app.getLuogo(),"Torino"); 
	}
	
	@Test
	void testSetData() throws AppuntamentiException { 
		Appuntamento app = new Appuntamento("12-06-2023","14-30","60","Mario","Torino");  
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> { 
			app.setData("12/05-2023");
		});
		assertEquals("Data dell’appuntamento, nel formato gg-mm-aaaa",ex1.getMessage());
		
		app.setData("12-05-2023");

		assertEquals(app.getData(),LocalDate.parse("12-05-2023", formatterData));
	}
	
	@Test
	void testSetOrario() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","18-30","60","Mario","Torino");  
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
			app.setOrario("14/30");
		});
		assertEquals("Orario dell’appuntamento, nel formato hh-mm",ex1.getMessage());
			
		app.setOrario("14-30");

		
		assertEquals(app.getOrario(),LocalTime.parse("14-30", formatterOrario)); 

	}
	
	@Test
	void testSetDurata() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","20","Mario","Torino");  
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
			app.setDurata("1h");
		});
		assertEquals("Durata prevista espressa in minuti",ex1.getMessage());
		
		app.setDurata("60");
		
		assertEquals(app.getDurata(),60);
 
	}
	
	@Test
	void testSetNome() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Piero","Torino");  
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
			app.setNome("Mar!o");
		}); 
		assertEquals("Il nome deve essere composto solo da caratteri dell'alfabeto",ex1.getMessage());
		
		app.setNome("Mario");

		
		assertEquals(app.getNome(),"Mario");
	}
	
	@Test
	void testSetLuogo() throws AppuntamentiException {
		Appuntamento app = new Appuntamento("12-05-2023","14-30","60","Mario","Vercelli");  
		
		AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
			app.setLuogo("Torin0");
		});
		assertEquals("Il luogo deve essere composto solo da caratteri dell'alfabeto",ex1.getMessage());
		
		app.setLuogo("Torino");
		
		assertEquals(app.getLuogo(),"Torino");
	}
	
	@Test
	void testToString() throws AppuntamentiException {
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Piero", "Ufficio");
		String expected = "\n"
				+ "				Appuntamento [data=01-01-2022, orario=10-00, durata=60, nome=Piero, luogo=Ufficio]";
		assertEquals(app1.toString(),expected);
	}
	
	@Test
	void testControllo() throws AppuntamentiException, AgendaException {
		Agenda agenda = new Agenda("Lavoro");
		Appuntamento app1 = new Appuntamento("01-01-2022", "10-00", "60", "Filippo", "Ufficio");
		Appuntamento app2 = new Appuntamento("01-01-2022", "10-00", "60", "Mario", "Ristorante");
		Appuntamento app3 = new Appuntamento("01-01-2022", "09-30", "90", "Piero", "Caffe");
		Appuntamento app4 = new Appuntamento("05-01-2022", "15-00", "60", "Francesco", "Palestra");
		Appuntamento app5 = new Appuntamento("01-04-2022", "17-00", "60", "Piero", "Ristorante");
		Appuntamento app6 = new Appuntamento("12-12-2022", "06-30", "90", "Bob", "Caffe");
		Appuntamento app7 = new Appuntamento("01-01-2022", "10-30", "90", "Piero", "Caffe");
		Appuntamento app8 = new Appuntamento("04-01-2022", "23-30", "1000", "Piero", "Caffe");
		
		app1.controllo(agenda);
		agenda.getAppuntamenti().add(app1);
		
	    assertEquals(1, agenda.getAppuntamenti().size());
	    
	    AppuntamentiException ex1 = assertThrows(AppuntamentiException.class, () -> {
	    	 app2.controllo(agenda);
		}); 
		assertEquals("Il nuovo appuntamento e' nello stesso giorno e allo stesso orario di un altro appuntamento",ex1.getMessage());
		assertEquals(1, agenda.getAppuntamenti().size());
		
	    AppuntamentiException ex2 = assertThrows(AppuntamentiException.class, () -> {
	    	app3.controllo(agenda);
		}); 
		assertEquals("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento",ex2.getMessage());
		assertEquals(1, agenda.getAppuntamenti().size());
		
		app4.controllo(agenda);
		agenda.getAppuntamenti().add(app4);
		app5.controllo(agenda);
		agenda.getAppuntamenti().add(app5);
		app6.controllo(agenda);
		agenda.getAppuntamenti().add(app6);
		assertEquals(4, agenda.getAppuntamenti().size());
		
	    AppuntamentiException ex3 = assertThrows(AppuntamentiException.class, () -> {
	    	app7.controllo(agenda);
		}); 
		assertEquals("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento",ex3.getMessage());
		
	    AppuntamentiException ex4 = assertThrows(AppuntamentiException.class, () -> {
	    	app8.controllo(agenda);
		}); 
		assertEquals("Il nuovo appuntamento si sovrappone, a livello di orario, ad un altro appuntamento",ex4.getMessage());
	}
	
	@Test
	void testCompareTo() throws AppuntamentiException {
		Appuntamento app1 = new Appuntamento("01-01-2020", "10-00", "60", "Paolo", "Torino");
        Appuntamento app2 = new Appuntamento("02-01-2020", "09-00", "60", "Pietro", "Milano");
        Appuntamento app3 = new Appuntamento("31-12-2019", "11-00", "60", "Piero", "Vercelli");
        
        assertEquals(-1, app1.compareTo(app2));
        assertEquals(1, app2.compareTo(app1));
        assertEquals(1, app1.compareTo(app3));
        assertEquals(0, app1.compareTo(app1));
        
        app2.setData("01-01-2020");
        assertEquals(1, app1.compareTo(app2));
        assertEquals(-1, app2.compareTo(app1));
	}
	
}
