
import java.util.*;

public class Tragamonedas {


	private int RECAUDACION_MINIMA;
	private int CANTIDAD_CASILLAS;

	private int precioJugada;
    private int credito;
    private int recaudacion;
    private int mayorPremio;
	
    private ArrayList<Integer> casillas;
    private Collection<Premio> premios;
    
    /*private static final int BANANA = 0;
    private static final int FRUTILLA = 1;
    private static final int GUINDA = 2;
    private static final int MANZANA = 3;
    private static final int SANDIA = 4;
    private static final int UVA = 5;    */
    
    public Tragamonedas( int precioJugada, int recaudacionInicial, int RECAUDACION_MINIMA, int CANTIDAD_CASILLAS) {

    	this.precioJugada = precioJugada;
    	this.recaudacion = recaudacionInicial;
    	this.RECAUDACION_MINIMA = RECAUDACION_MINIMA;
    	this.CANTIDAD_CASILLAS = CANTIDAD_CASILLAS;

    	this.mayorPremio = 0;
    	this.credito = 0;
    	this.casillas = new ArrayList<Integer>();
    }

    public void AgregarCredito(int cantidad) {
    	credito += cantidad;
    }

    public void Jugar() {
    	
    	if (recaudacion - credito - mayorPremio > RECAUDACION_MINIMA) {
    		
    		recaudacion += precioJugada;
    		credito -= precioJugada;
	    	
    		realizarTiro();
    	}
    	//else
    		//notificar();
    }
    
    public void realizarTiro() {
    	Random ran = new Random();
    	for (int i = 0 ; i < CANTIDAD_CASILLAS; i++)
    	{
    		casillas.add(ran.nextInt() % 6);
    	}
    	
    	for(Premio premio: premios)
    	{
    		if (premio.tengoEsaCombinacion(casillas)) {
    			int valorPremio = premio.getGanancia();
    			credito += valorPremio;
    			recaudacion -= valorPremio;
    		}
    		
    	}
    }

    public void AgregarPremio(int ganancia, int fruit1, int fruit2, int fruit3) {
    	
    	if (ganancia > mayorPremio)
    		mayorPremio = ganancia;
    	
    	ArrayList<Integer> combinacion = new ArrayList<Integer>();
    	combinacion.add(fruit1);
    	combinacion.add(fruit2);
    	combinacion.add(fruit3);
    	
    	premios.add(new Premio(combinacion, CANTIDAD_CASILLAS, ganancia));
    }

    public Ticket EmitirTicket() {
    	
    	Ticket ticket = new Ticket(credito);
    	recaudacion -= credito;
    	credito = 0;
    	
        return ticket;
    }

}