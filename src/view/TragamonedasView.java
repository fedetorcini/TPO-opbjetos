package src.view;

import java.util.ArrayList;

import src.Tragamonedas;

public class TragamonedasView {

	public final static int VICTORY = Tragamonedas.VICTORY;
	public final static int	DEFEAT = Tragamonedas.DEFEAT;
	
	private int id;
	private int credito;
	private int precioJugada;

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public int getPrecioJugada() {
		return precioJugada;
	}

	public void setPrecioJugada(int precioJugada) {
		this.precioJugada = precioJugada;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString()
	{
		return "Maquina : " + String.valueOf(id) + " - credito disponible : " + String.valueOf(credito) + " - precio jugada : " + String.valueOf(precioJugada);
		
	}


}
