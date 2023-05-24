package controlador;

import modelo.Data;
import modelo.Gramatica;
import modelo.Operadora;
import modelo.Tabla;

public class ControlVPrincipal extends ControlAbs {
	Operadora op;

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operadora();
			switch(c) {
			case Comandos.CANALIZAG:
				Gramatica gram = (Gramatica)d;
				op.quitaRI(gram);
				//GENERAR TABLA EN CLASE TABLA
				Tabla tabla = new Tabla(gram);
				return tabla;
			}
		return null;//REGRESAR UN MODELO
	}

}
