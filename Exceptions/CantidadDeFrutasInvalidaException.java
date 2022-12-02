package Exceptions;

public class CantidadDeFrutasInvalidaException extends RuntimeException 
{
	public CantidadDeFrutasInvalidaException() {
		super("La cantidad de frutas en el premio no coincide con la cantidad de casillas en la maquina");
	}
}
