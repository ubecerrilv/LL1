package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Tabla implements Data {
/*****************************************************************************************
 * 										ATRIBUTOS
 *****************************************************************************************/
	String [][] mat;
	Gramatica gramar;
/*****************************************************************************************
* 										METODOS
*****************************************************************************************/

	public Tabla() {
	}
	
	public Tabla(Gramatica gram) {
		this.gramar=gram;
		ArrayList<Produccion> producciones = gram.getProducciones();
		ArrayList<Character> nter = gram.getNoTerminales();
		ArrayList<Character> ter = gram.getTerminales();
		Collections.sort(ter);
		
		mat = new String[nter.size()+1][ter.size()+2];
		
		mat[0][0]="   ";
		for(int i =0; i<ter.size();i++) {
			mat[0][i+1]=""+ter.get(i);
		}
		mat[0][mat[0].length-1]="$";
		
		for(int i =0; i<nter.size();i++) {
			mat[i+1][0]=""+nter.get(i);
		}
		
		//FALTA CONSTRUIR TABLA EN SI
		for(int i =0;i<mat.length;i++) {
			for(int j =0; j<mat[i].length;j++) {
				for(int x =0; x<producciones.size();x++) {
					if(producciones.get(x).getlI().compareTo(mat[i][0])==0
					   && gram.primeros(producciones.get(x).getlD().charAt(0)).contains(mat[0][j].charAt(0))) {
						if(mat[i][j]==null) {
							mat[i][j]=producciones.get(x).aString();							
						}else {
							mat[i][j]+="|"+producciones.get(x).getlD();
						}
					}else if(producciones.get(x).getlI().compareTo(mat[i][0])==0
					&& gram.primeros(producciones.get(x).getlD().charAt(0)).contains('e')
					&& gram.siguientes(producciones.get(x).getlI().charAt(0),'~').contains(mat[0][j].charAt(0))) {
						if(mat[i][j]==null) {
							mat[i][j]=producciones.get(x).aString();							
						}else {
							mat[i][j]+="|"+producciones.get(x).getlD();
						}
					}
				}//FIN FOR
			}//FIN FOR
			
		}//FIN FOR
		
		
		/*for(int i =0;i<mat.length;i++) {
			for(int j =0; j<mat[i].length;j++) {
				System.out.print(mat[i][j]+"\t\t");
			}
			System.out.println();
		}*/
		
		
		
	}//FIN CONSTRUCTOR TABLA

	public String[][] getMat() {
		return mat;
	}

	public void setMat(String[][] mat) {
		this.mat = mat;
	}
	
	public String gramToString() {
		String si="<html><body>GRAMATICA SIN RECURSIVIDAD:<br>";
		for(int i =0; i<this.gramar.getProducciones().size();i++) {
			si+=gramar.getProducciones().get(i).aString()+"<br>";
		}
		si+="</body></html>";
		return si;
	}
	public String pToString() {
		String si="<html><body>CONJUNTOS DE PRIMEROS:<br>";
		ArrayList<Character> nt = gramar.getNoTerminales();
		for(int i = 0; i<nt.size();i++) {
			ArrayList<Character> p = gramar.primeros(nt.get(i));
			si +="P("+nt.get(i)+")={";
			for(int y =0; y<p.size();y++) {
				si+=p.get(y)+",";
			}
			si = si.substring(0, si.length() - 1);
			si+="}<br>";
		}
		return si;
	}
	
	public String sToString() {
		String si="<html><body>CONJUNTOS DE SIGUIENTES:<br>";
		ArrayList<Character> nt = gramar.getNoTerminales();
		for(int i = 0; i<nt.size();i++) {
			ArrayList<Character> p = gramar.siguientes(nt.get(i), '~');
			si +="S("+nt.get(i)+")={";
			for(int y =0; y<p.size();y++) {
				si+=p.get(y)+",";
			}
			si = si.substring(0, si.length() - 1);
			si+="}<br>";
		}
		return si;
		
	}
}
