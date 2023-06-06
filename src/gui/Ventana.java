package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlador.Comandos;
import modelo.Operadora;
import modelo.Gramatica;
import modelo.Tabla;


@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{
	
	JPanel panel, tabla, extra;
	JLabel autor, res, info, etqLog;
	JTextArea gramatica, cadena;
	JButton bAnalizar, bVerificarC, log;
	JTable tablaR;
	
	//ATRIBUTOS PARA LOS COMANDOS
	Tabla tablaA;
	Operadora analizadora;
	
	String [][] mat;
	GridBagConstraints rest;
	
	
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
		rest= new GridBagConstraints();
		
		//PANEL CENTRAL
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));

		//CREAR ETIQUETAS
		autor = new JLabel("Realizado por Ulises Becerril Valdés");
		extra = new JPanel();
		
		rest.gridx = 1;
		rest.gridy = 4;
		rest.weightx =1.0;
		rest.gridwidth = 2;
		rest.gridheight = 1;
		
		panel.add(autor, rest);
		rest.weightx =0;
		
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
		
		etqLog = new JLabel("Sisale");
		
		//CREAR TEXTAREAS
		gramatica = new JTextArea();
		gramatica.setLineWrap(true);
		//PLACEHOLDER
		TextPrompt tFondo = new TextPrompt("<html><p style=\"color:rgb(128,139,150);\"><i>INGRESA EN ESTA ÁREA LA GRAMÁTICA A ANALIZAR<br> DE LA FORMA:<br> S->aS</i></p></html>", gramatica);
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
		tabla = new JPanel(new GridLayout(2,1));
		tabla.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Tabla LL1",TitledBorder.CENTER,TitledBorder.TOP));
		tablaR = new JTable(3,4);
		tabla.add(tablaR);
		
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
			Gramatica gram = new Gramatica(gramatica.getText());
				tabla.removeAll();
		
				extra.removeAll();
			
			this.tablaA = (Tabla)this.control.ejecutaComando(Comandos.CANALIZAG, gram, null);
			//AGREGAR ELEMENTOS NECESARIOS EN CLASE TABLA PARA PODER CREAR LA TABLA AQUI
			mat= tablaA.getMat();
			JPanel si = new JPanel(new GridLayout(mat.length, mat[0].length));
			si.setBorder(new EmptyBorder(5,5,5,5));
			for(int i =0;i<mat.length;i++) {
				for(int j =0; j<mat[i].length;j++) {
					if(mat[i][j]!=null) {
						JLabel o = new JLabel("   "+mat[i][j]);
						o.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						si.add(o);						
					}else {
						JLabel o = new JLabel("");
						o.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						si.add(o);
					}
				}
			}
			
			tabla.remove(tablaR);
			tabla.add(si, BorderLayout.CENTER);
			
			
			extra.setLayout(new GridBagLayout());
			rest.gridx = 0;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 2;
			rest.fill = GridBagConstraints.BOTH;
			JLabel o = new JLabel(tablaA.gramToString());
			o.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			extra.add(o,rest);
			
			rest.gridx = 1;
			rest.gridy = 0;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			JLabel o1 = new JLabel(tablaA.pToString());
			o1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			extra.add(o1,rest);
			
			rest.gridx = 1;
			rest.gridy = 1;
			rest.gridwidth = 1;
			rest.gridheight = 1;
			JLabel o2 = new JLabel(tablaA.sToString());
			o2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			extra.add(o2,rest);
			rest.fill = GridBagConstraints.NONE;
			
			tabla.add(extra);
			
			
			this.setVisible(false);
			this.setVisible(true);
			repaint();
		}//FIN IF
		
		break;	
	
	case Comandos.ANALIZAC:
		if(cadena.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(this, "Ingresa una cadena a analizar");
		}else {
			if(this.tablaA == null) {
				JOptionPane.showMessageDialog(this, "Analiza una gramática primero");
			}else{
				this.analizadora = (Operadora)this.control.ejecutaComando(getName(), tablaA, tablaA);
				//MOSTRAR SI LA CADENA ES VALIDA O NO
				//SETEAR LA ETIQUETA DEL LOG
			}//FIN IF	
		}//FIN IF
		break;
		
	case Comandos.LOG:
		if(res.getText().compareTo("La cadena es: ")==0) {
			JOptionPane.showMessageDialog(this, "Analiza primero una cadena");
		}else {
			JOptionPane.showMessageDialog(this, etqLog);
		}//FIN IF
	break;
		}//FIN SWITCH
	}//FIN ACTION
	
}//FIN CLASE VENTANA
