package Exceptions;

public class NoSePuedePagarPremioException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSePuedePagarPremioException() {
		super("No es posible pagar el premio de esta maquina");
	}
}
