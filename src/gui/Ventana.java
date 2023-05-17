package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlador.Comandos;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	JPanel panel;
	JLabel autor, res, info;
	JTextArea gramatica, cadena;
	JButton bAnalizar, bVerificarC, log;
	JTable tabla;
	
	
	public Ventana() {
		super("Algoritmo LL1");
		
		//SETEAR LOOK
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		//CREAR E INSERTAR COMPONENTES
		//CREACION DEL OBJETO DE RESTRICCIONES
		GridBagConstraints rest = new GridBagConstraints();
		
		//PANEL CENTRAL
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));

		//CREAR ETIQUETAS
		autor = new JLabel("Realizado por Ulises Becerril Valdés");
		
		rest.gridx = 1;
		rest.gridy = 4;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		
		panel.add(autor, rest);
		
		info = new JLabel("Generación de tabla LL1 ->");
		
		rest.gridx = 1;
		rest.gridy = 1;
		rest.weighty = 1.0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(info, rest);
		rest.weighty = 0;
		
		
		res = new JLabel("La cadena es: ");
		rest.gridx = 2;
		rest.gridy = 2;
		rest.weightx =1.0;
		rest.gridwidth = 1;
		rest.gridheight = 2;
		
		panel.add(res, rest);
		rest.weightx =0;
		
		//CREAR TEXTAREAS
		gramatica = new JTextArea();
		gramatica.setLineWrap(true);
		//PLACEHOLDER
		TextPrompt tFondo = new TextPrompt("INGRESA EN ESTA ÁREA LA GRAMÁTICA A ANALIZAR", gramatica);
		tFondo.changeAlpha(0.75f);
		tFondo.changeStyle(Font.ITALIC);
		
		rest.gridx = 0;
		rest.gridy = 0;
		rest.weightx=1.0;
		rest.gridwidth = 1;
		rest.gridheight = 2;
		rest.fill = GridBagConstraints.BOTH;
		
		panel.add(gramatica, rest);
		rest.weightx=0;
		rest.fill = GridBagConstraints.NONE;
		
		cadena = new JTextArea();
		cadena.setLineWrap(true);
		//PLACEHOLDER
		TextPrompt tFondo2 = new TextPrompt("INGRESA LA CADENA A ANALIZAR", cadena);
		tFondo2.changeAlpha(0.75f);
		tFondo2.changeStyle(Font.ITALIC);
		
		rest.gridx = 0;
		rest.gridy = 2;
		rest.weightx=1.0;
		rest.gridwidth = 1;
		rest.gridheight = 2;
		rest.fill = GridBagConstraints.HORIZONTAL;
		
		panel.add(cadena, rest);
		rest.weightx=0;
		rest.fill = GridBagConstraints.NONE;
			
		//CREAR BOTONES
		bAnalizar = new JButton("Analizar gramática");
		bAnalizar.setActionCommand(controlador.Comandos.ANALIZAG);//CREAR Y CAMBIAR COMANDO
		bAnalizar.addActionListener(this);
		
		rest.gridx = 1;
		rest.gridy = 0;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bAnalizar, rest);
		
		
		bVerificarC =  new JButton("Analizar cadena");
		bVerificarC.setActionCommand(Comandos.ANALIZAC);
		bVerificarC.addActionListener(this);
		
		rest.gridx = 1;
		rest.gridy = 2;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(bVerificarC, rest);
		
		
		log = new JButton("Ver log");
		log.setActionCommand(Comandos.LOG);
		log.addActionListener(this);
		
		rest.gridx = 1;
		rest.gridy = 3;
		rest.gridwidth = 1;
		rest.gridheight = 1;
		
		panel.add(log, rest);
		
		//CREAR TABLA
		tabla = new JTable(3,5);
		
		rest.gridx = 2;
		rest.gridy = 0;
		rest.weightx =1.0;
		rest.gridwidth = 1;
		rest.gridheight = 2;
		rest.fill = GridBagConstraints.BOTH;
		
		panel.add(tabla, rest);
		rest.weightx =0;
		rest.fill = GridBagConstraints.NONE;
		
		//PANEL A LA VENTANA
		this.add(panel);
		
		//CONFIGURACIONES DE LA VENTANA
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//FIN CONSTRUCTOR DE LA VENTANA

@Override
public void actionPerformed(ActionEvent e) {
		
	switch (e.getActionCommand()) {//CASO DE LOS COMANDOS (BOTONES)
	case Comandos.ANALIZAG:
		if(gramatica.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(this, "Ingresa una gramatica a analizar");
		}else {
			
		}//FIN IF
		
		break;	
	
	case Comandos.ANALIZAC:
		if(cadena.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(this, "Ingresa una cadena a analizar");
		}else {
			
		}//FIN IF
		break;
		
	case Comandos.LOG:
		if(res.getText().compareTo("La cadena es: ")==0) {
			JOptionPane.showMessageDialog(this, "Analiza primero una cadena");
		}else {
			
		}//FIN IF
	break;
		}//FIN SWITCH
	}//FIN ACTION
	
}//FIN CLASE VENTANA
