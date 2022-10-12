
import java.util.*;

public class Casino {

	private Tragamonedas maquinaActiva;
	private Caja caja;
	private Collection<Ticket> ticketsEmitidos;
	private Collection<Tragamonedas> maquinas;

	public Casino() {
		
    }

    public void comprarTicket(int value) {
    	ticketsEmitidos.add(caja.comprarTicket(value));
    }

    public void crearMaquina(int precioJugada, int recaudacionInicial, int recaudacionMinima, int catidadCasillas) {
    	maquinas.add(new Tragamonedas( precioJugada, recaudacionInicial, recaudacionMinima, catidadCasillas));
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