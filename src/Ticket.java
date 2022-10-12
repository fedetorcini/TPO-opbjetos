public class Ticket {

	private static int nextID = 0;
	
	private int ticketId;
	private boolean usado;
	private int valor;

	public Ticket(int value) {
		valor = value;
		usado = false;

		ticketId = nextID;
		nextID++;
    }

	public boolean soyEseTicket(int codigo) {
		return codigo == ticketId;
	}

	public int utilize() {
		usado = true;
		return valor;
	}

	public boolean esValido() {
		return usado;
	}

}