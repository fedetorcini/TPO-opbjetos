package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Exceptions.CantidadDeFrutasInvalidaException;
import Exceptions.NoHaySaldoSuficienteException;
import Exceptions.NoSePuedePagarPremioException;
import Exceptions.YaExistePremioConEsaCombinacionException;
import javafx.scene.image.Image;
import src.Casino;
import src.Ticket;
import src.Tragamonedas;


public class Principal extends JFrame {

	private JGradientButton cajaButton;
	private FedeJTextField cajaTextField;
	
	private JGradientButton cargarMaquinaButton;
	private FedeJTextField cargarMaquinaTextField;
	
	private JGradientButton crearMaquinaButton;
	private FedeJTextField precioJugadaTextField;
	private FedeJTextField recaudacionIncialTextField;
	private FedeJTextField recaudacionMinimaTextField;
	private FedeJTextField catidadCasillasTextField;

	private JGradientButton crearPremioButton;
	private FedeJTextField valorPremioTextField;
	private JComboBox<String> frutasDisponibles;
	private JLabel frutasElegidas;
	private ArrayList<Integer> frutasList = new ArrayList<Integer>();

	
	private CircleButton jugarButton;

	private CircleButton retirarButton;

	private JLabel creditGUI;
	private JComboBox<Tragamonedas> machineList;
	private Casino controlador;
	private Container container;
		
	private int WINDOW_HEIGHT = 900;
	private int WINDOW_WIDTH = 900;
	
	private long deltaTime = 33;
	private Color mainColor = Color.GRAY.brighter();
	private Color secondary = Color.white;
	private Color mainBackgroundColor = new Color(245, 245, 245);
	
	
	
	public Principal(){
		controlador = new Casino();
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		configurar();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void configurar() 
	{
		
		initializeContainer();
		
		setComprarTicketForm();
		setCargarTicketForm();
		setMachineList();
		setCreditBanner();
		setCreateMachineForm();
		setCrearPremioForm();
		setJugarButton();
		setRetirarButton();
	}
	

	private void initializeContainer() 
	{
		container = this.getContentPane();
		container.setLayout(null);
		container.setBackground(mainBackgroundColor);		
	}


	private void setCreditBanner() 
	{
		creditGUI = new JLabel("temp name", SwingConstants.CENTER);
		creditGUI.setBounds( WINDOW_WIDTH - 250, WINDOW_HEIGHT - 100, 200, 50);
		creditGUI.setOpaque(true);
		creditGUI.setBackground(mainColor);
		creditGUI.setText("Credito: $ " +  controlador.getCredito());
		creditGUI.setFont(new Font("Serif", Font.BOLD, 24));
		container.add(creditGUI);
		
		Thread t1 = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	while (true)
		    	{
		    		creditGUI.setText("Credito: $ " +  controlador.getCredito());
					try {
						Thread.sleep(deltaTime);
					} catch (InterruptedException e) {
					}
		    	}
		    }
		}); 
		t1.start();
		
	}

	private void setMachineList()
	{
		machineList = new JComboBox<Tragamonedas>();
		machineList.setBounds( 10, 210, 400, 30);
		machineList.setOpaque(true);
		machineList.setBackground(mainColor);
		machineList.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {	
		    	int idMaquina = ((Tragamonedas) machineList.getSelectedItem()).getId();
		    	controlador.seleccionarMaquinaActiva(idMaquina);
		    }
		});
		container.add(machineList);
	}
	
	private void setCreateMachineForm() 
	{
		crearMaquinaButton = new JGradientButton(mainColor, secondary);
		crearMaquinaButton.setBounds( 10, 10, 200, 50);
		crearMaquinaButton.setText("Crear Maquina");
		crearMaquinaButton.setFont(new Font("Serif", Font.BOLD, 20));
		container.add(crearMaquinaButton);
		
		crearMaquinaButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		int precioJugada = Integer.parseInt(precioJugadaTextField.getText());
		    		int recaudacionIncial = Integer.parseInt(recaudacionIncialTextField.getText());
		    		int recaudacionMinima = Integer.parseInt(recaudacionMinimaTextField.getText());
		    		int catidadCasillas = Integer.parseInt(catidadCasillasTextField.getText());
		    		Tragamonedas temp = controlador.getMaquinaConId(controlador.crearMaquina(precioJugada, recaudacionIncial, recaudacionMinima, catidadCasillas));
		    		if ( temp != null )
		    		{
		    			machineList.addItem(temp);
		    		}
		    		else 
		    		{
			    		JOptionPane.showMessageDialog(null, "<h3><center>A OCURRIDO UN ERROR AL CREAR LA MAQUINA <h3>", "Tragamonedas Factory", JOptionPane.ERROR_MESSAGE);
		    		}
		    		
		    	} catch (NumberFormatException error){
		    		JOptionPane.showMessageDialog(null, "<html><h3><center>Ingrese unicamente numeros <h3>", "Tragamonedas Factory", JOptionPane.WARNING_MESSAGE);
		    	}
		    	precioJugadaTextField.reset();
		    	recaudacionIncialTextField.reset();
		    	recaudacionMinimaTextField.reset();
		    	catidadCasillasTextField.reset();
		    }
		});
		
		precioJugadaTextField = new FedeJTextField( 10, 60, 100, 50, "Precio Jugada");
		container.add(precioJugadaTextField);
		recaudacionIncialTextField = new FedeJTextField( 110, 60, 100, 50, "Rec. Inicial");
		container.add(recaudacionIncialTextField);
		recaudacionMinimaTextField = new FedeJTextField( 10, 110, 100, 50, "Rec. Minima");
		container.add(recaudacionMinimaTextField);
		catidadCasillasTextField = new FedeJTextField( 110, 110, 100, 50, "# Casillas");
		container.add(catidadCasillasTextField);
		
	}
	
	private static int frutaAEntero(String fruta)
	{
		switch (fruta)
		{
		case "Banana":
			return 1;
		case "Frutilla":
			return 2;
		case "Guinda":
			return 3;
		case "Manzana":
			return 4;
		case "Sandia":
			return 5;
		case "Uva":
			return 6;
		default:
			return -1;
		}
	}
	
	private void setCrearPremioForm() 
	{
		crearPremioButton = new JGradientButton(mainColor, secondary);
		crearPremioButton.setBounds( 220, 10, 100, 50);
		crearPremioButton.setText("<html><center>Crear<p>Premio</html>");
		crearPremioButton.setFont(new Font("Serif", Font.BOLD, 15));
		container.add(crearPremioButton);
		
		valorPremioTextField = new FedeJTextField(321, 10, 100, 50, "Valor");
		container.add(valorPremioTextField);		
		
		frutasElegidas = new JLabel("", SwingConstants.CENTER);
		frutasElegidas.setOpaque(true);
		frutasElegidas.setBounds( 321, 60, 100, 120);
		frutasElegidas.setBackground(mainColor);
		frutasElegidas.setFont(new Font("Times", Font.PLAIN, 15));
		container.add(frutasElegidas);
		
		frutasDisponibles = new JComboBox<String>();
		frutasDisponibles.setBounds( 220, 60, 100, 30);
		frutasDisponibles.setBackground(mainColor);
		frutasDisponibles.addItem("Banana");
		frutasDisponibles.addItem("Frutilla");
		frutasDisponibles.addItem("Guinda");
		frutasDisponibles.addItem("Manzana");
		frutasDisponibles.addItem("Sandia");
		frutasDisponibles.addItem("Uva");
		frutasDisponibles.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {	
		    	String fruta = frutasDisponibles.getSelectedItem().toString();
		    	frutasList.add(frutaAEntero(fruta));
		    	frutasElegidas.setText("<html><center>" + frutasElegidas.getText() + "<p>" + fruta);
		    }
		});
		container.add(frutasDisponibles);

		crearPremioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	try {
		    		int[] frutasArr = new int[frutasList.size()];
		    		for (int  i = 0; i < frutasList.size(); i ++)
		    		{
		    			frutasArr[i] = frutasList.get(i);
		    		}		    		
		    		controlador.agregarPremio(Integer.parseInt(valorPremioTextField.getText()),frutasArr);
		    	} catch (NumberFormatException ex1){
		    		JOptionPane.showMessageDialog(null, "<html><h3><center>Solo se aceptan valores numericos<h3>", "Tragamonedas", JOptionPane.ERROR_MESSAGE);
		    	} catch (YaExistePremioConEsaCombinacionException ex2){
		    		JOptionPane.showMessageDialog(null, "<html><h3><center>No puede ingresar premios con combinacion repetida<h3>", "Tragamonedas", JOptionPane.ERROR_MESSAGE);
		    	} catch (CantidadDeFrutasInvalidaException ex3){
		    		JOptionPane.showMessageDialog(null, "<html><h3><center>Verifique la cantidad de frutas<h3>", "Tragamonedas", JOptionPane.ERROR_MESSAGE);
		    	}
		    	
		    	frutasList = new ArrayList<Integer>();
		    	valorPremioTextField.reset();
		    	frutasElegidas.setText("");
		    }
		});
	}
	
	private void setComprarTicketForm()
	{
		
		cajaButton = new JGradientButton(mainColor, secondary);
		cajaButton.setBounds( WINDOW_WIDTH - 250, 10, 200, 50);
		cajaButton.setText("Comprar Credito ");
		cajaButton.setFont(new Font("Serif", Font.BOLD, 20));
		container.add(cajaButton);
		
		cajaButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		int value = Integer.parseInt(cajaTextField.getText());
		    		int idTicket = controlador.comprarTicket(value);
		    		JOptionPane.showMessageDialog(null,
		    						"<html><h1><center>Id Ticket: " 
		    						+ idTicket + 
		    						"<h3><center>Recuerda Anotar el Id del Ticket."
		    						+ "<h3<center>NO Hay reembolzo en caso de perdida.",
		    						"Venta de ticket",
		    						JOptionPane.WARNING_MESSAGE
		    				);
		    	} catch (NumberFormatException error){
		    		JOptionPane.showMessageDialog(null, "Ingresar un numero valido");
		    	}
		    	cajaTextField.setText("Ingresar valor");
		    }
		});
		
		cajaTextField = new FedeJTextField(WINDOW_WIDTH - 200, 60, 100, 50, "Ingresar Valor");
		container.add(cajaTextField);		
	}
	
	private void setCargarTicketForm()
	{
		cargarMaquinaButton = new JGradientButton(mainColor, secondary);
		cargarMaquinaButton.setBounds( WINDOW_WIDTH - 250, 160, 200, 50);
		cargarMaquinaButton.setText("Cargar Maquina");
		cargarMaquinaButton.setFont(new Font("Serif", Font.BOLD, 20));
		container.add(cargarMaquinaButton);
		
		cargarMaquinaButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		int value = Integer.parseInt(cargarMaquinaTextField.getText());
		    		if (controlador.cargarCredito(value))
		    		{
		    			JOptionPane.showMessageDialog(null,
		    					"<html><h2><center>Id Ticket: " 
		    							+ value + 
		    							"<h3><center> Carga realizada correcatmente.",
		    							"Carga de Maquina",
		    							JOptionPane.INFORMATION_MESSAGE
		    					);
		    		}
		    		else 
		    		{
		    			JOptionPane.showMessageDialog(null,
		    					"<html><h2><center>Id Ticket: " 
		    							+ value + 
		    							"<h3><center> Id invalido o ticket ya utilizado.",
		    							"Carga de Maquina",
		    							JOptionPane.WARNING_MESSAGE
		    					);
		    		}
		    		
		    	} catch (NumberFormatException error){
		    		JOptionPane.showMessageDialog(null, "Ingresar un numero valido");
		    	}
		    	cargarMaquinaTextField.setText("Ticket ID");
		    }
		});
		
		cargarMaquinaTextField = new FedeJTextField(WINDOW_WIDTH - 200, 210, 100, 50, "Ticket ID");
		container.add(cargarMaquinaTextField);		
	}

	private void setJugarButton()
	{
		jugarButton = new CircleButton("Jugar", mainColor, secondary, Color.RED);
		jugarButton.setBounds( 60, WINDOW_HEIGHT - 200, 100, 100);
		jugarButton.setFont(new Font("Arial", Font.BOLD, 30));
		container.add(jugarButton);
		
		jugarButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	try {
					int result = controlador.jugarConMaquinaArreglada();
					if (result == Tragamonedas.VICTORY)
					{
						JOptionPane.showMessageDialog(null,
								"<html><center> HAS GANADO!",
								"Staff",
								JOptionPane.INFORMATION_MESSAGE
						);
					}
				} catch (NoHaySaldoSuficienteException ex2) {
					JOptionPane.showMessageDialog(null,
							"<html><center> La maquina que desea utilizar no tiene suficiente credito cargado",
							"Staff",
							JOptionPane.INFORMATION_MESSAGE
					);
				} catch (NoSePuedePagarPremioException ex1) {
					int response = JOptionPane.showConfirmDialog(null, "<html><center>No hay saldo para pagar los premios.<p> Desea continuar?", "Tragamonedas",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) 
				    {
				    	controlador.jugarSinBeneficios();
				    } 
				} 
		    }
		});
	}
	void setRetirarButton ()
	{
		retirarButton = new CircleButton("Retirar", mainColor, secondary, Color.CYAN);
		retirarButton.setBounds( 200, WINDOW_HEIGHT - 187, 75, 75);
		retirarButton.setFont(new Font("Arial", Font.BOLD, 20));
		container.add(retirarButton);
		
		retirarButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				if (controlador.getCredito() > 0) {
		    		Ticket ticket = controlador.retirarCreditoDeMaquina();
		    		if (ticket != null)
		    		{
	    				JOptionPane.showMessageDialog(null,
	    						"<html><h1><center>Id Ticket: " 
	    						+ ticket.getId() + "<p>valor : " + ticket.getValor() + 
	    						"<h3><center>Recuerda Anotar el Id del Ticket."
	    						+ "<h3<center>NO Hay reembolzo en caso de perdida.",
	    						"Venta de ticket",
	    						JOptionPane.INFORMATION_MESSAGE
	    				);
		    		}
				}

		    }
		});
	}
}


/*
 * Fruits to number and number to fruit
 * exceptions
 * popups
 */
