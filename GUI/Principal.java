package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import src.Casino;


public class Principal extends JFrame {

	private JPanel background;
	private JButton cajaButton;
	private JLabel creditGUI;
	private Timer timer;
	private JComboBox<Integer> machineList;
	private Casino controlador;
	
	private int WINDOW_HEIGHT = 1080;
	private int WINDOW_WIDTH = 1920;
	
	public Principal(){
		controlador = new Casino();
		configurar();
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void configurar() {
		Container conteiner = this.getContentPane();
		conteiner.setLayout(null);
		conteiner.setBackground(Color.BLACK);
		
		
		creditGUI = new JLabel();
		creditGUI.setBounds( 0, WINDOW_HEIGHT - 335, 200, 50);
		creditGUI.setOpaque(true);
		creditGUI.setBackground(Color.GRAY);
		creditGUI.setText("Credito: $ " +  controlador.getCredito());
		creditGUI.setFont(new Font("Serif", Font.BOLD, 24));
		conteiner.add(creditGUI);
		
		
		cajaButton = new JButton();
		cajaButton.setBounds( WINDOW_WIDTH - 590, 0, 200, 50);
		cajaButton.setText("Comprar Credito ");
		cajaButton.setFont(new Font("Serif", Font.PLAIN, 20));
		conteiner.add(cajaButton);
		
		
		machineList = new JComboBox<Integer>();
		machineList.setBounds( 0, 0, 200, 200);
		machineList.setOpaque(true);
		machineList.setBackground(Color.GRAY);
		conteiner.add(machineList);
		
		
	}

	public void start() {
		
	}

}
