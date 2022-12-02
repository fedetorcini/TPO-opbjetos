package Exceptions;

public class YaExistePremioConEsaCombinacionException extends RuntimeException {
	public YaExistePremioConEsaCombinacionException()
	{
		super("Ya existe un premio con esa combinacion en maquina activa");
	}
}
