package src;

import java.util.*;

import Exceptions.CantidadDeFrutasInvalidaException;
import Exceptions.NoHaySaldoSuficienteException;
import Exceptions.NoSePuedePagarPremioException;
import Exceptions.YaExistePremioConEsaCombinacionException;

public class Tragamonedas {


	public static int VICTORY = 1;
	public static int DEFEAT = -1;
	
	private static int IDs = 1000;
	private final int myId;
	private int RECAUDACION_MINIMA;
	private int CANTIDAD_CASILLAS;

	private int precioJugada;
    private int credito;
    private int recaudacion;
    private int mayorPremio;
	
    private ArrayList<Integer> casillas;
    private Collection<Premio> premios;
    
    public Tragamonedas( int precioJugada, int recaudacionInicial, int RECAUDACION_MINIMA, int CANTIDAD_CASILLAS) {
    	
    	this.myId = IDs;
    	IDs++;
    	
    	this.premios = new ArrayList<Premio>();
    	
    	this.precioJugada = precioJugada;
    	this.recaudacion = recaudacionInicial;
    	this.RECAUDACION_MINIMA = RECAUDACION_MINIMA;
    	this.CANTIDAD_CASILLAS = CANTIDAD_CASILLAS;

    	this.mayorPremio = 0;
    	this.credito = 0;
    }

    public void agregarCredito(int cantidad) {
    	credito += cantidad;
    }

    public Premio realizarTiro() {
    	Random ran = new Random();
    	for (int i = 0 ; i < CANTIDAD_CASILLAS; i++)
    	{
    		casillas.add(ran.nextInt() % 6);
    	}
    	
    	for(Premio premio: premios)
    	{
    		if (premio.tengoEsaCombinacion(casillas)) {
    			return premio;
    		}
    		
    	}
    	return null;
    }

    public int jugar(){
    	
    	int result = Tragamonedas.DEFEAT;
    	this.casillas = new ArrayList<Integer>();
    	if (credito - precioJugada < 0)
    	{
    	
    		throw new NoHaySaldoSuficienteException();
    	}
    	
    	else if (recaudacion + precioJugada - mayorPremio > RECAUDACION_MINIMA) 
    	{
    		
    		recaudacion += precioJugada;
    		credito -= precioJugada;
    		System.out.println("Realizando tiro....");
    		Premio resultado = realizarTiro();
    		if(resultado != null)
    		{
    			int valorPremio = resultado.getGanancia();
    			credito += valorPremio;
    			recaudacion -= valorPremio;
    			System.out.println("Felicidades GANASTE");
    			result = Tragamonedas.VICTORY;
    		}
    	}
    	else 
    	{
    		throw new NoSePuedePagarPremioException();
    	}
    	return result;
    }
    
    /*
	 * Puramente para testing, genera un resultado simpre igual.
	 * No depende del azar
	 */
    public Premio realizarTiroArreglado() {
    	for (int i = 0 ; i < CANTIDAD_CASILLAS; i++)
    	{
    		casillas.add(1);
    	}
    	
    	for(Premio premio: premios)
    	{
    		if (premio.tengoEsaCombinacion(casillas)) {
    			return premio;
    		}
    		
    	}
    	return null;
    }
    
    /*
	 * Puramente para testing, genera un resultado simpre igual.
	 * No depende del azar
	 */
    public int jugarArreglada()
    {
    	this.casillas = new ArrayList<Integer>();
    	if (credito - precioJugada < 0)
    	{
    		throw new NoHaySaldoSuficienteException();
    	}
    	
    	else if (recaudacion + precioJugada - mayorPremio > RECAUDACION_MINIMA) 
    	{
    		
    		recaudacion += precioJugada;
    		credito -= precioJugada;
    		System.out.println("Realizando tiro....");
    		Premio resultado = realizarTiroArreglado();
    		
    		if(resultado != null)
    		{
    			int valorPremio = resultado.getGanancia();
    			credito += valorPremio;
    			recaudacion -= valorPremio;
    			System.out.println("Felicidades GANASTE");
    		}
    	}
    	else throw new NoSePuedePagarPremioException();
		return Tragamonedas.VICTORY;
    }
    

    /*
	 * Puramente para testing, genera un resultado simpre igual.
	 * No depende del azar
	 */
    public int jugarSinBeneficioArreglado() {
    	this.casillas = new ArrayList<Integer>();
    	if (credito - precioJugada < 0)
    	{
    		throw new NoHaySaldoSuficienteException();
    	}
    	
		recaudacion += precioJugada;
		credito -= precioJugada;
		System.out.println("Realizando tiro....");
		Premio resultado = realizarTiroArreglado();
		if(resultado != null)
		{
			System.out.println("Felicidades GANASTE");
		}
		return Tragamonedas.VICTORY;
    }
    
    public int jugarSinBeneficio() {
    	int result = Tragamonedas.DEFEAT;
    	this.casillas = new ArrayList<Integer>();
    	if (credito - precioJugada < 0)
    	{
    		throw new NoHaySaldoSuficienteException();
    	}
    	
		recaudacion += precioJugada;
		credito -= precioJugada;
		System.out.println("Realizando tiro....");
		Premio resultado = realizarTiro();
		if(resultado != null)
		{
			System.out.println("Felicidades GANASTE");
			result = Tragamonedas.VICTORY;
		}
		return result;
    }
    
    public void agregarPremio(int ganancia, int... fruits) {
    	
    	//Verifico que largos coincidan
    	if (fruits.length == CANTIDAD_CASILLAS)
    	{    		
    		boolean premioYaExiste = false;
    		
    		//Actualizo valor de mayor premio+
			if (ganancia > mayorPremio)
				mayorPremio = ganancia;
			
			//Convierto array en Collection
			ArrayList<Integer> combinacion = new ArrayList<Integer>();
			for (int fruit: fruits)
			{
				combinacion.add(fruit);    		
			}
			
			//Checkeo preexistencia del premio
			for(Premio premio: premios)
        	{
        		if (premio.tengoEsaCombinacion(combinacion)) 
        		{
        			premioYaExiste = true;
        		}	
        	}
			
			//Si el premio no existe lo agrego
			if (!premioYaExiste) 
			{
				premios.add(new Premio(combinacion, CANTIDAD_CASILLAS, ganancia));
    		}
			else {
				throw new YaExistePremioConEsaCombinacionException();
			}
    	}
    	else {
			throw new CantidadDeFrutasInvalidaException();
    	}
    }

    public Ticket emitirTicket() {
    	
    	Ticket ticket = new Ticket(credito);
    	credito = 0;
    	
        return ticket;
    }

	public boolean soyEseTragamonedas(int idBuscado) {
		return idBuscado == this.myId;
	}

	public int getId() {
		return this.myId;
	}

	public int getCredito() {
		return credito;
	}

	public int getCantidadPremios() {
		return premios.size();
	}
	
	@Override
	public String toString()
	{
		return "Maquina : " + String.valueOf(myId) + " - credito disponible : " + String.valueOf(credito) + " - precio jugada : " + String.valueOf(precioJugada);
	}


}