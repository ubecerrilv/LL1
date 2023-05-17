package controlador;

import modelo.Data;
import modelo.Gramatica;
import modelo.Tabla;

public class ControlVPrincipal extends ControlAbs {

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
			switch(c) {
			case Comandos.CANALIZAG:
				Gramatica gram = (Gramatica)d;//QUITAR RECURSIVIDAD EN CLASE GRAMATICA
				//GENERAR TABLA EN CLASE TABLA
				Tabla tabla = new Tabla(gram);
				return tabla;
			}
		return null;//REGRESAR UN MODELO
	}

}
