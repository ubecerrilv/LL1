package modelo;

import java.util.ArrayList;

import controlador.Comandos;

public class Gramatica implements Data {
/*****************************************************************************************
 * 										ATRIBUTOS
 *****************************************************************************************/
	ArrayList<Character> terminales,
	                  noTerminales;
	ArrayList<Produccion> producciones;
	
/*****************************************************************************************
* 										METODOS
*****************************************************************************************/

	public Gramatica() {
	}
	
	public Gramatica(String gram) {
		//INICIAR VARIABLES
		terminales = new ArrayList<Character>();
		noTerminales = new ArrayList<Character>();
		producciones = new ArrayList<Produccion>();
		//"LIMPIAR" GRAMATICA	
		gram = gram.replaceAll(" ", "");
		gram = gram.replaceAll(">", "");
		gram = gram.trim();
		
		String aux [] = gram.split("\n");//POR PRODUCCIONES
		for(int i = 0; i<aux.length;i++) {//POR CADA PRODUCCIÓN
			
			String aux2 [] = aux[i].split("-");
			
			if(aux2[1].toCharArray()[0]=='e' && aux2[1].length()>1) {
				String si=aux2[1].substring(1);
				System.out.println(si);
				producciones.add(new Produccion(aux2[0], si));//AÑADIR LA PRODUCCION
			}else {
				producciones.add(new Produccion(aux2[0], aux2[1]));//AÑADIR LA PRODUCCION
				}
			
			char[] aux3 = aux2[0].toCharArray();
			if(!noTerminales.contains(aux3[0])) {
				noTerminales.add(aux3[0]);//SI NO ESTA EN TERMINALES LO AÑADE
			}//FIN IF
			
			char aux4[] = aux2[1].toCharArray();
			for(int x= 0;x<aux4.length;x++) {
				if(!terminales.contains(aux4[x]) && !esM(aux4[x])) {//SI NO ESTÁ Y NO ES MAYUSCULA
					terminales.add(aux4[x]);
				}
			}
			
		}//FIN FOR
		System.out.println("NO TERMINALES");
		for(int t =0; t<noTerminales.size();t++) {
			System.out.println(noTerminales.get(t));
		}
		System.out.println("TERMINALES");
		for(int t =0; t<terminales.size();t++) {
			System.out.println(terminales.get(t));
		}
		System.out.println("GRAMATICA");
		for(int i = 0; i<this.producciones.size();i++) {
			System.out.println(producciones.get(i).getlI()+"->"+this.producciones.get(i).getlD());
		}
		
	}//FIN CONSTRUCTOR
	
	
	public ArrayList<Character> getTerminales() {
		return terminales;
	}

	public void setTerminales(ArrayList<Character> terminales) {
		this.terminales = terminales;
	}

	public ArrayList<Character> getNoTerminales() {
		return noTerminales;
	}

	public void setNoTerminales(ArrayList<Character> noTerminales) {
		this.noTerminales = noTerminales;
	}

	public ArrayList<Produccion> getProducciones() {
		return producciones;
	}

	public void setProducciones(ArrayList<Produccion> producciones) {
		this.producciones = producciones;
	}

	public boolean esM(char ar) {
		for(int y =0; y<Comandos.MAYUS.length;y++) {
			if(ar == Comandos.MAYUS[y]) {
				return true;
			}
		}
		return false;
	}
}//FIN CLASE
