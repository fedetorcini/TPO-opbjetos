package src;

import src.view.TicketView;

public class Ticket {

	private static int nextID = 100000;
	
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
		return !usado;
	}

	public int getId() {
		return ticketId;
	}

	public int getValor() {
		return valor;
	}

	public TicketView getView() {
		TicketView view = new TicketView();
		view.setId(ticketId);
		view.setValor(valor);
		return view;
	}

}