package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import agenda_appuntamenti.Agenda;
import agenda_appuntamenti.AgendaException;
import agenda_appuntamenti.Agende;
import agenda_appuntamenti.AgendeException;
import agenda_appuntamenti.AppuntamentiException;
import agenda_appuntamenti.Appuntamento;

class TestAgende {
	DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter formatterOrario = DateTimeFormatter.ofPattern("HH-mm"); 
	
	@Test
	void testCostruttore0Par() throws AgendaException{
		Agende agende = new Agende();
		assertEquals(agende.getAgende().size(),0); 
	}
	
	@Test
	void testGetAgende() throws AgendaException, AgendeException{
		Agende agende = new Agende();
		agende.creaAgenda("Lavoro");
		agende.creaAgenda("Hobby");
		agende.creaAgenda("Casa");
		
		assertEquals(agende.getAgende().size(),3);
		assertEquals(agende.getAgende().get(0).getNome(),"Lavoro");
		assertEquals(agende.getAgende().get(1).getNome(),"Hobby");
		assertEquals(agende.getAgende().get(2).getNome(),"Casa");
	}
	
	@Test
	void testCreaAgenda() throws AgendaException, AgendeException {
		Agende agende = new Agende();
		agende.creaAgenda("Lavoro");
		agende.creaAgenda("Hobby");
		agende.creaAgenda("Casa");
		
		assertEquals(agende.getAgende().size(),3);
		assertEquals(agende.getAgende().get(0).getNome(),"Lavoro");
		assertEquals(agende.getAgende().get(1).getNome(),"Hobby");
		assertEquals(agende.getAgende().get(2).getNome(),"Casa");
		
		Agenda a4 = agende.creaAgenda("Palestra");
		
		assertEquals(a4.getNome(),"Palestra");
		assertEquals(agende.getAgende().size(),4);
		assertEquals(agende.getAgende().get(3).getNome(),"Palestra");
		
		AgendeException ex1 = assertThrows(AgendeException.class, () -> { 
			agende.creaAgenda("Palestra");
		});
		assertEquals("Il nome dell'agenda e' gia' presente",ex1.getMessage());
	}
	
	@Test
	void testEliminaAgenda() throws AgendaException, AgendeException {
		Agende agende = new Agende();
		agende.creaAgenda("Lavoro");
		agende.creaAgenda("Hobby");
		agende.creaAgenda("Casa");
		
		assertEquals(agende.getAgende().size(),3);
		assertEquals(agende.getAgende().get(0).getNome(),"Lavoro");
		assertEquals(agende.getAgende().get(1).getNome(),"Hobby");
		assertEquals(agende.getAgende().get(2).getNome(),"Casa");
		
		agende.eliminaAgenda(agende.getAgende().get(2));
		assertEquals(agende.getAgende().size(),2);
		assertEquals(agende.getAgende().get(1).getNome(),"Hobby");
		
		agende.eliminaAgenda(agende.getAgende().get(1));
		agende.eliminaAgenda(agende.getAgende().get(0));
		assertEquals(agende.getAgende().size(),0); 
	}
	
	@Test
	void testCreaDaFile() throws AgendaException, IOException, AppuntamentiException, AgendeException {
		Agende agende = new Agende();
		Agenda a1 = agende.creaAgendaDaFile("Lavoro","fileLeggi.txt");

		assertNotNull(a1);
        assertEquals("Lavoro", a1.getNome());
        assertEquals(7, a1.getAppuntamenti().size());
        
        Appuntamento app1 = a1.getAppuntamenti().get(0);
        assertEquals(LocalDate.parse("12-03-2023", formatterData), app1.getData());
        assertEquals(LocalTime.parse("12-30", formatterOrario), app1.getOrario());
        assertEquals(60, app1.getDurata()); 
        assertEquals("Mario", app1.getNome());
        assertEquals("Torino", app1.getLuogo());
          
        Appuntamento app2 = a1.getAppuntamenti().get(1);
        assertEquals(LocalDate.parse("22-03-2023", formatterData), app2.getData());
        assertEquals(LocalTime.parse("18-00", formatterOrario), app2.getOrario());
        assertEquals(120, app2.getDurata());
        assertEquals("Paolo", app2.getNome());
        assertEquals("Milano", app2.getLuogo());
	}
	
	@Test
	void testToString() throws AgendaException, IOException, AppuntamentiException, AgendeException {
		Agende agende = new Agende(); 
		agende.creaAgenda("Lavoro");
		agende.creaAgenda("Hobby");
		agende.creaAgenda("Casa");
		agende.creaAgendaDaFile("Ufficio","fileLeggi.txt"); 
		
		String expected = "Agende [\n"
				+ "	Agenda [nome=Lavoro, appuntamenti=[]], \n"
				+ "	Agenda [nome=Hobby, appuntamenti=[]], \n"
				+ "	Agenda [nome=Casa, appuntamenti=[]], \n"
				+ "	Agenda [nome=Ufficio, appuntamenti=[\n"
				+ "				Appuntamento [data=12-03-2023, orario=12-30, durata=60, nome=Mario, luogo=Torino], \n"
				+ "				Appuntamento [data=22-03-2023, orario=18-00, durata=120, nome=Paolo, luogo=Milano], \n"
				+ "				Appuntamento [data=28-04-2023, orario=18-30, durata=35, nome=Piero, luogo=Torino], \n"
				+ "				Appuntamento [data=28-05-2023, orario=12-30, durata=60, nome=Bob, luogo=Torino], \n"
				+ "				Appuntamento [data=28-06-2023, orario=15-30, durata=90, nome=Francesco, luogo=Milano], \n"
				+ "				Appuntamento [data=28-07-2023, orario=10-30, durata=60, nome=Gianluca, luogo=Vercelli], \n"
				+ "				Appuntamento [data=01-08-2023, orario=16-00, durata=45, nome=Paolo, luogo=Vercelli]]]] ";
		assertEquals(agende.toString(),expected);
	}
}
