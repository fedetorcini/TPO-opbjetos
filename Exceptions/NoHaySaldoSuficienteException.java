package Exceptions;

public class NoHaySaldoSuficienteException extends RuntimeException {
	public NoHaySaldoSuficienteException()
	{
		super("No hay saldo suficiente");
	}
}
