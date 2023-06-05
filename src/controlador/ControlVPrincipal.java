package controlador;

import java.util.ArrayList;

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
				
				for(int i = 0; i<res.getProducciones().size();i++) {
					System.out.println(res.getProducciones().get(i).aString());
				}
				
				System.out.println("PRIMEROS:");
				ArrayList<Character> nt = res.getNoTerminales();
				for(int i =0; i<nt.size();i++) {
					System.out.print(nt.get(i)+"={");
					ArrayList <Character>resp = new ArrayList<Character>();
					resp = res.primeros(nt.get(i));
					for(int j = 0; j<resp.size();j++) {
						System.out.print(resp.get(j)+",");
					}
					System.out.println("}");
				}
				
				
				
				//GENERAR TABLA EN CLASE TABLA
				Tabla tabla = new Tabla(res);
				return tabla;
			}
		return null;//REGRESAR UN MODELO
	}

}
