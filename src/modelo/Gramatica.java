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
				if(!terminales.contains(aux4[x]) && !esM(aux4[x]) && aux4[x]!='e') {//SI NO ESTÁ Y NO ES MAYUSCULA
					terminales.add(aux4[x]);
				}
			}
			
		}//FIN FOR
		
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
	
	public ArrayList<Character> primeros(char c){
		ArrayList <Character>res = new ArrayList<Character>();
		if(this.terminales.contains(c)) {//CARACTER ES UN TERMINAL
			res.add(c);
			return res;
		}else if(c=='e') {
			res.add('e');
			return res;
		}else {
			for(int i = 0; i<this.producciones.size();i++) {//PARA TODAS LAS PRODUCCIONES xxx AGREGAR LOS PRIMEROS "DIRECTOS"
				if (producciones.get(i).getlI().charAt(0)==c &&!esM(producciones.get(i).getlD().charAt(0))) {//PRODUCCIONES CON ESE NO TERMINAL
					if(!res.contains(producciones.get(i).getlD().charAt(0))) {res.add(producciones.get(i).getlD().charAt(0));}//SI NO LO CONTIENE, LO AGREGA	
				}else if(producciones.get(i).getlI().charAt(0)==c &&esM(producciones.get(i).getlD().charAt(0))){//EMPIEZA POR UN NO TERMINAL
					ArrayList <Character>aux = new ArrayList<Character>();
					aux = primeros(producciones.get(i).getlD().charAt(0));
					if(aux.contains('e') && producciones.get(i).getlD().length()>=2) {
						aux = primeros(producciones.get(i).getlD().charAt(1));
						for(int j = 0; j<aux.size();j++) {
							if(!res.contains(aux.get(j))) {res.add(aux.get(j));}
						}
					}else {
						aux = primeros(producciones.get(i).getlD().charAt(0));
						for(int j = 0; j<aux.size();j++) {
							if(!res.contains(aux.get(j))) {res.add(aux.get(j));}
						}
					}
				}
			}//FIN FOR
			return res;			
		}//FIN IF EXTERNO
	}
	
	public ArrayList<Character> siguientes(char c, char ant){
		ArrayList <Character>res = new ArrayList<Character>();
		if(producciones.get(0).getlI().charAt(0)==c) {
			res.add('$');
		}
		for(int i =0;i<producciones.size();i++) {
			int ind = producciones.get(i).getlD().indexOf(c);
			if(ind!=-1) {
				String[] aux  = producciones.get(i).getlD().split(""+c);
				for(String si:aux) {
					if(si.compareTo("")!=0 && ind!=producciones.get(i).getlD().length()-1) {
						ArrayList <Character>aux2 = new ArrayList<Character>();
						aux2 = primeros(si.charAt(0));
						for(int j = 0; j<aux2.size();j++) {
							if(!res.contains(aux2.get(j)) && aux2.get(j)!='e') {res.add(aux2.get(j));}
						}//FIN FOR PARA AGREGAR
					}//FIN IF VERIFICAR LADO DERECHO EXISTENTE	
				}//FIN IF DEL SPLIT
				
				if(ind == producciones.get(i).getlD().length()-1 && c!= producciones.get(i).getlI().charAt(0) && producciones.get(i).getlI().charAt(0)!=ant) {//ULTIMO ELEMENTO
					ArrayList <Character>aux2 = new ArrayList<Character>();
					aux2 = siguientes(producciones.get(i).getlI().charAt(0), c);
					for(int j = 0; j<aux2.size();j++) {
						if(!res.contains(aux2.get(j)) && aux2.get(j)!='e') {res.add(aux2.get(j));}
					}//FIN FOR PARA AGREGAR
				}//FIN IF 2
				
				if(aux.length==2) {
					if(aux[1].compareTo("")!=0 && producciones.get(i).getlI().charAt(0)!=ant) {
						ArrayList <Character>aux2 = new ArrayList<Character>();
						aux2 = primeros(aux[1].charAt(0));
						if(aux2.contains('e')) {
							ArrayList <Character>aux3 = new ArrayList<Character>();
							aux3 = siguientes(producciones.get(i).getlI().charAt(0), c);
							for(int j = 0; j<aux3.size();j++) {
								if(!res.contains(aux3.get(j)) && aux3.get(j)!='e') {res.add(aux3.get(j));}
							}//FIN FOR PARA AGREGAR
						}
					}//FIN IF VERIFICAR LADO DERECHO EXISTENTE	
				}//FIN IF 3
				
				
			}//FIN IF DE EXISTENCIA DEL SIMBOLO DEL LADO DERECHO
		}//FIN FOR
		
		return res;
	}
}//FIN CLASE
