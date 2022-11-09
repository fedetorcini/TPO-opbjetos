package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.Casino;
import src.Ticket;


public class CasinoTests 
{
	@Test
	public void Test01CreoUnCasino() 
	{
		Casino myCasino = new Casino();
    }
	
	@Test
	public void Test02CreoUnCasinoYCreoUnaMaquina() 
	{
		Casino myCasino = new Casino();
		myCasino.crearMaquina(50, 500, 100, 4);
	}
	
	@Test
	public void Test03CreoUnaMaquinaCargoPremioEIntetoJugarSinSaldoNoJuega() 
	{
		Casino myCasino = new Casino();
		int idMaquina = myCasino.crearMaquina(10, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		myCasino.agregarPremio(200,1,1,1,1);
		myCasino.jugarConMaquina();
	}
	
	@Test
	public void Test04CreoUnaMaquinaConPremioCargoTicketYJuego() 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int idMaquina = myCasino.crearMaquina(10, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		myCasino.agregarPremio(200,1,1,1,1);
		
		//CargoTicket
		int idTicket = myCasino.comprarTicket(15);
		myCasino.cargarCredito(idTicket);
		
		//Juego
		myCasino.jugarConMaquina();
	}
	
	@Test
	public void Test05CreoUnaMaquinaIntentoUsarTicketDosVecesSoloFuncionaUna() 
	{
		//Creo maquina
		Casino myCasino = new Casino();
		int idMaquina = myCasino.crearMaquina(10, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		
		//CargoTicket
		int valorTicket = 15;
		int idTicket = myCasino.comprarTicket(valorTicket);
		myCasino.cargarCredito(idTicket);
		myCasino.cargarCredito(idTicket);

		//Juego
		assertEquals(myCasino.getCredito(), valorTicket);
	}
	
	@Test
	public void Test06CreoUnaMaquinaConSaldoYJuegoTresVecesHastaQuedarmeSinSaldo() 
	{
		//Creo maquina 
		Casino myCasino = new Casino();
		int valorJugada = 10;
		int idMaquina = myCasino.crearMaquina(valorJugada, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		
		//CargoTicket
		int valorTicket = 20;
		int idTicket = myCasino.comprarTicket(valorTicket);
		myCasino.cargarCredito(idTicket);
		
		//Juego 3 veces hasta quedarme sin saldo
		int saldoInicial = valorTicket;
		myCasino.jugarConMaquina(); //credito deberia bajar 1 valor jugada
		assertEquals(myCasino.getCredito(), saldoInicial - valorJugada);
		myCasino.jugarConMaquina(); //credito deberia bajar 1 valor jugada otra vez
		assertEquals(myCasino.getCredito(), saldoInicial - 2 * valorJugada);
		myCasino.jugarConMaquina(); //credito no alcanza por ende no juego y credito se mantiene igual
		assertEquals(myCasino.getCredito(), saldoInicial - 2 * valorJugada);
	}

	@Test	
	public void Test07CreoUnaMaquinaConSaldoYPremioJugadorJuegaYGana() 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int valorJugada = 10;
		int recompensa = 100;
		int idMaquina = myCasino.crearMaquina(valorJugada, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		myCasino.agregarPremio(recompensa,1,1,1,1);
		
		//CargoTicket
		int valorTicket = 20;
		int idTicket = myCasino.comprarTicket(valorTicket);
		myCasino.cargarCredito(idTicket);
		
		//Juego y gano
		myCasino.jugarConMaquinaArreglada(); //credito deberia subir 
		assertEquals(myCasino.getCredito(), valorTicket - valorJugada + recompensa);
	}
	
	@Test	
	public void Test08CreoUnaMaquinaConSaldoYPremioJugadorJuegaGanaYRetiraGanancia() 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int valorJugada = 10;
		int recompensa = 100;
		int idMaquina = myCasino.crearMaquina(valorJugada, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		myCasino.agregarPremio(recompensa,1,1,1,1);
		
		//CargoTicket
		int valorTicket = 20;
		int idTicket = myCasino.comprarTicket(valorTicket);
		myCasino.cargarCredito(idTicket);
		
		//Juego y gano
		myCasino.jugarConMaquinaArreglada(); //credito deberia subir 
		int creditoEsperado = valorTicket - valorJugada + recompensa;
		assertEquals(myCasino.getCredito(), creditoEsperado);
		
		//Retiro dinero
		Ticket ticketEmitido = myCasino.retirarCreditoDeMaquina();
		assertEquals(ticketEmitido.getValor(), creditoEsperado);
		assertEquals(myCasino.getCredito(), 0);
		
	}
	
	@Test	
	public void Test09NoPuedoAgregarPremioConMayorCantidaDeCasillasQueLaMaquina() 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int valorJugada = 10;
		int recompensa = 100;
		int idMaquina = myCasino.crearMaquina(valorJugada, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		
		//Agrego premio invalido
		myCasino.agregarPremio(recompensa,1,1,1,1,1);
		
		//No habra premios
		int cantidadEsperada = 0;
		assertEquals(myCasino.getCantidadPremiosDeMaquina(), cantidadEsperada);
	}
	
	@Test	
	public void Test10NoPuedoAgregarMismoPremioDosVeces() 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int valorJugada = 10;
		int recompensa = 100;
		int idMaquina = myCasino.crearMaquina(valorJugada, 8000, 100, 4);
		myCasino.seleccionarMaquinaActiva(idMaquina);
		
		//Agrego mismo premio 2 veces
		myCasino.agregarPremio(recompensa,1,1,1,1);
		myCasino.agregarPremio(recompensa,1,1,1,1);
		
		//Solo habra 1 premio
		int cantidadEsperada = 1;
		assertEquals(myCasino.getCantidadPremiosDeMaquina(), cantidadEsperada);
	}
	
	@Test	
	public void Test11CuatroMaquinasDiferentesConDosPremiosDiferentesGanoEnUnaRetiroDineroYPierdoEnOtra () 
	{
		//Creo maquina con premio
		Casino myCasino = new Casino();
		int costoJugadaA = 10;
		int costoJugadaD = 17;
		int idMaquinaA = myCasino.crearMaquina(costoJugadaA  , 1000 , 100 , 3);
		int idMaquinaB = myCasino.crearMaquina(12   	     , 1000 , 100 , 4);
		int idMaquinaC = myCasino.crearMaquina(1000			 , 25000, 2000, 5);
		int idMaquinaD = myCasino.crearMaquina(costoJugadaD  , 1000 , 100 , 3);
		
		//Creo 4 maquinas de diferentes premios
		
		int segundoPremioA = 150;
		myCasino.seleccionarMaquinaActiva(idMaquinaA);
		myCasino.agregarPremio(200			  ,2,2,1);
		myCasino.agregarPremio(segundoPremioA ,1,1,1);
		
		myCasino.seleccionarMaquinaActiva(idMaquinaB);
		myCasino.agregarPremio(180,2,2,5,1);
		myCasino.agregarPremio(170,5,1,3,5);
		
		myCasino.seleccionarMaquinaActiva(idMaquinaC);
		myCasino.agregarPremio(2000 ,4,2,1,2,2);
		myCasino.agregarPremio(10000,5,5,5,5,2);
		
		
		myCasino.seleccionarMaquinaActiva(idMaquinaD);
		myCasino.agregarPremio(200,2,2,1);
		myCasino.agregarPremio(170,5,1,3);
		
		//Compro ticket
		int valorInicialTicket = 200;
		int idTicket = myCasino.comprarTicket(valorInicialTicket);

		//Gano en maquina A el segundo premio
		myCasino.seleccionarMaquinaActiva(idMaquinaA);
		myCasino.cargarCredito(idTicket);
		myCasino.jugarConMaquinaArreglada();
		assertEquals(valorInicialTicket - costoJugadaA + segundoPremioA, myCasino.getCredito());
		
		//Retiro dinero
		Ticket ticketEmitido = myCasino.retirarCreditoDeMaquina();
		assertEquals(valorInicialTicket - costoJugadaA + segundoPremioA, ticketEmitido.getValor());

		//Pierdo en maquina D
		myCasino.seleccionarMaquinaActiva(idMaquinaD);
		myCasino.cargarCredito(ticketEmitido.getId());
		assertEquals(valorInicialTicket - costoJugadaA + segundoPremioA, myCasino.getCredito());
		myCasino.jugarConMaquinaArreglada();
		
		
		//Solo habra 1 premio
		int creditoEsperado = valorInicialTicket - costoJugadaA + segundoPremioA - costoJugadaD;
		assertEquals(creditoEsperado, myCasino.getCredito());
	}
	
}
