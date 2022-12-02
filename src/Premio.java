package src;

import java.util.*;

public class Premio {


    private int ganancia;
    private int cantidadCasillas;
    private ArrayList<Integer> combinacion;

    public Premio(ArrayList<Integer> combinacion, int cantidadCasillas, int ganancia) 
    {
    	this.combinacion = combinacion;
    	this.cantidadCasillas = cantidadCasillas;
    	this.ganancia = ganancia;
    }

    public boolean tengoEsaCombinacion(ArrayList<Integer> casillas) {
    	for (int i = 0; i < cantidadCasillas; i++)
    	{
    		if(this.combinacion.get(i) != casillas.get(i))
    			return false;
    	}
    	return true;
    }

	public int getGanancia() {
		return this.ganancia;
	}

	public ArrayList<Integer> getCombinacion() {
		return combinacion;
	}

}