package src;

import java.util.*;

public class Casino {

	private Tragamonedas maquinaActiva;
	private Caja caja;
	private Collection<Ticket> ticketsEmitidos;
	private Collection<Tragamonedas> maquinas;

	public Casino() {
		caja = new Caja();
		maquinas = new ArrayList<Tragamonedas>();
		ticketsEmitidos = new ArrayList<Ticket>();
    }

    public int comprarTicket(int value) {
    	Ticket temp = caja.comprarTicket(value);
    	ticketsEmitidos.add(temp);
    	return temp.getId();
    }

    public int crearMaquina(int precioJugada, int recaudacionInicial, int recaudacionMinima, int catidadCasillas) {
    	Tragamonedas temp = new Tragamonedas( precioJugada, recaudacionInicial, recaudacionMinima, catidadCasillas);
    	maquinas.add(temp);
    	return temp.getId();
    }

    
    public void cargarCredito(int codigo) 
    {
    	Ticket ticket = buscarTicket(codigo);
    	
    	if (ticket != null && ticket.esValido() && maquinaActiva != null)
    		maquinaActiva.agregarCredito(ticket.utilize());	
    }

    
	private Ticket buscarTicket(int codigo) 
	{
		for (Ticket ticket: ticketsEmitidos)
			if (ticket.soyEseTicket(codigo))
				return ticket;				
		return null;		
	}

	
	public void jugarConMaquina() {
		if (maquinaActiva != null)
		{
			maquinaActiva.jugar();		
		}
	}
	
	/*
	 * Puramente para testing, genera un resultado simpre igual.
	 * No depende del azar
	 */
	public void jugarConMaquinaArreglada() 
	{
		if (maquinaActiva != null)
		{
			maquinaActiva.jugarArreglada();		
		}
	}

	
    public Ticket retirarCreditoDeMaquina(){
    	if (maquinaActiva != null)
    	{
    		Ticket temp = maquinaActiva.emitirTicket();
    		ticketsEmitidos.add(temp);
    		return temp;
    	}
    	else return null;
    }

    public void agregarPremio(int ganancia, int... fruits)
    {
    	if (maquinaActiva != null)
    	{
    		maquinaActiva.agregarPremio(ganancia, fruits);
    	}
    }
    
	public void seleccionarMaquinaActiva(int idBuscado) {
		
		for (Tragamonedas maquina : maquinas)
		{
			if (maquina.soyEseTragamonedas(idBuscado))
			{
				this.maquinaActiva = maquina;
			}
		}
	}

	public int getCredito() {
		if (maquinaActiva != null)
		{
			return maquinaActiva.getCredito();
		}
		else return 0;
	}

	public int getCantidadPremiosDeMaquina() {
		return maquinaActiva.getCantidadPremios();
	}

	public void jugarSinBeneficios() {
		if (maquinaActiva != null)
		{
			maquinaActiva.jugarSinBeneficio();		
		}
	}

	public void jugarSinBeneficiosArreglada() {
		if (maquinaActiva != null)
		{
			maquinaActiva.jugarSinBeneficioArreglado();		
		}		
	}

	
	

}