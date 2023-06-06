package controlador;


import modelo.Data;
import modelo.Gramatica;
import modelo.Operadora;
import modelo.Tabla;

public class ControlVPrincipal extends ControlAbs {
	Operadora op;
	Gramatica res;

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operadora();
			switch(c) {
			case Comandos.CANALIZAG:
				Gramatica gram = (Gramatica)d;
				res = op.quitaRI(gram);
				//GENERAR TABLA EN CLASE TABLA
				Tabla tabla = new Tabla(res);
				return tabla;
			}
		return null;//REGRESAR UN MODELO
	}

}
