
import java.util.*;

public class Caja {

	private Collection <Ticket> tickets;
	
    public Caja() {
    	tickets = new ArrayList<Ticket> ();
    }

    public Ticket comprarTicket(int value) {
    	
    	Ticket ticket =  new Ticket (value);
    	tickets.add(ticket);
    	
    	return ticket;
    }
}