package src;

import java.util.*;

import Exceptions.NoSePuedePagarPremioException;
import src.view.PremioView;
import src.view.TicketView;
import src.view.TragamonedasView;

public class Casino {

	private Tragamonedas maquinaActiva;
	private Caja caja;
	private Collection<Ticket> ticketsEmitidos;
	private Collection<Tragamonedas> maquinas;
	
	public final static int ERROR = -1;

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
    	if (temp != null)
    	{
    		return temp.getId();    		
    	}
    	return ERROR;
    }

    
    public boolean cargarCredito(int codigo) 
    {
    	Ticket ticket = buscarTicket(codigo);
    	
    	if (ticket != null && ticket.esValido() && maquinaActiva != null)
    	{
    		maquinaActiva.agregarCredito(ticket.utilize());	
    		return true;
    	}
    	
    	return false;
    }

    
	private Ticket buscarTicket(int codigo) 
	{
		for (Ticket ticket: ticketsEmitidos)
			if (ticket.soyEseTicket(codigo))
				return ticket;				
		return null;		
	}

	
	public int jugarConMaquina(){
		if (maquinaActiva != null)
		{
			return maquinaActiva.jugar();		
		}
		return Tragamonedas.DEFEAT;
	}
	
	/*
	 * Puramente para testing, genera un resultado simpre igual.
	 * No depende del azar
	 */
	public int jugarConMaquinaArreglada() 
	{
		if (maquinaActiva != null)
		{
			return maquinaActiva.jugarArreglada();		
		}
		return Tragamonedas.DEFEAT; 
	}

	
    public TicketView retirarCreditoDeMaquina(){
    	if (maquinaActiva != null)
    	{
    		Ticket temp = maquinaActiva.emitirTicket();
    		ticketsEmitidos.add(temp);
    		return temp.getView();
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

	public TragamonedasView getMaquinaConId(int idBuscado) {
		for(Tragamonedas maquina : maquinas)
		{
			if (maquina.soyEseTragamonedas(idBuscado))
			{
				return maquina.getView();
			}
		}
		return null;
	}

	public ArrayList<PremioView> getPremios() {
		if (maquinaActiva != null)
		{
			return maquinaActiva.getPremios();
		}
		return new ArrayList<PremioView>();
	}

	public int[] getUltimaCombinacion() {
		if (maquinaActiva != null)
		{			
			return maquinaActiva.getUltimaCombinacion();
		}
		return new int[0];
	}
	

}