package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.TicketHibernate;
import com.revature.exceptions.TicketNotFoundException;
import com.revature.models.Ticket;
import com.revature.services.TicketService;



@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	
	@Mock 
	private static TicketHibernate tht;
	
	@InjectMocks
	private static TicketService tut;
	
	@BeforeAll
	public static void setup() {
		tut = new TicketService();
	}
	
	
	@Test
	public void getTicketByIDExists() throws TicketNotFoundException {
		Ticket expected = new Ticket();
		expected.setId(1);
		expected.setEmployee_id(1);
		expected.setManager_id(1);
		expected.setTicket_desc("Drove 100 Miles");
		expected.setType("TRAVEL");
		expected.setStatus("APPROVED");
		expected.setTicket_amount(500);
		
		Mockito.when(tht.getTicketByID(1)).thenReturn(expected);
		
		Ticket actual = tut.getTicketByID(1);
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void getTicketByIDNotExists() throws TicketNotFoundException {
		
		Mockito.when(tht.getTicketByID(5)).thenReturn(null);
		
		assertThrows(TicketNotFoundException.class, ()->tut.getTicketByID(5));
	}
	
	@Test
	public void updateTicketNotExists() throws TicketNotFoundException {
		tut.updateTicket("APPROVED", 6);
		assertThrows(TicketNotFoundException.class, ()->tut.updateTicket("APPROVED", 6));
	}
	
	@Test
	public void updateTicketMNotExists() throws TicketNotFoundException {
		tut.updateMTicket(1, 6);
		assertThrows(TicketNotFoundException.class, ()->tut.updateMTicket(1, 6));
	}
	
	
}
