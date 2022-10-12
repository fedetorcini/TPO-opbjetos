
import java.util.*;

public class Casino {

	private Tragamonedas maquinaActiva;
	private Caja caja;
	private Collection<Ticket> ticketsEmitidos;
	private Collection<Tragamonedas> maquinas;

	public Casino() {
		
    }

    public void comprarTicket() {
    	ticketsEmitidos.add(caja.comprarTicket(500));
    }

    public void crearMaquina() {
    	maquinas.add(new Tragamonedas( 50, 200, 50, 4));
    }

    
    public void cargarCredito(int codigo) 
    {
    	Ticket ticket = buscarTicket(codigo);
    	
    	if (ticket != null && ticket.esValido())
    		maquinaActiva.AgregarCredito(ticket.utilize());	
    }

    
	private Ticket buscarTicket(int codigo) 
	{
		for (Ticket ticket: ticketsEmitidos)
		{
			if (ticket.soyEseTicket(codigo))
			{
				return ticket;				
			}
		}
		
		return null;		
	}

	
	public void jugarConMaquina() {
		maquinaActiva.Jugar();
	}

	
    public Ticket retirarCreditoDeMaquina(){
		return maquinaActiva.EmitirTicket();
    }

}