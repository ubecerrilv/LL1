package modelo;

import java.util.ArrayList;

public class Operadora implements Data {
/*****************************************************************************************
 * 										ATRIBUTOS
 *****************************************************************************************/
	String [] varAux = {"X","Z","W","Y","L","P","O"};
	int contador = 0;
	String nTAct, nTAnt;
	int s =0;
	
/*****************************************************************************************
* 										METODOS
*****************************************************************************************/

	public Operadora() {
	}
	
	public Gramatica quitaRI (Gramatica gr) {//REEMPLAZAR X'S POR LETRAS DINAMICAS(CONTADOR Y AUMENTAR)
		if(esRecursivaDirecta(gr)) {
			ArrayList<String> nr = new ArrayList<String>();//AUXILIAR PARA LO NO TERMINALES RECURSIVOS
			for(int i =0; i<gr.getProducciones().size();i++) {
				if(gr.getProducciones().get(i).getlI().compareTo(gr.getProducciones().get(i).getlD().substring(0, 1))==0) {
					if(!nr.contains(gr.getProducciones().get(i).getlI())) {nr.add(gr.getProducciones().get(i).getlI());}
				}//FIN IF
			}//FIN PRIMER FOR
			
			ArrayList<Produccion> producciones = gr.getProducciones();
			String aux[] = new String[producciones.size()];
			for(int k =0; k<aux.length;k++) {aux[k]=producciones.get(k).getlI();}
			
			for(int i = 0; i<producciones.size();i++) {
				if(i>0) {nTAnt=aux[i-1];}else {nTAnt=aux[i];}
				
				if(nr.contains(producciones.get(i).getlI())) {//LA PRODUCCION EMPIEZA CON LETRA QUE TIENE RECURSIVIDAD
					
					nTAct = gr.getProducciones().get(i).getlI();
					
					if(nTAct.compareToIgnoreCase(nTAnt)==0 || s==0) {producciones.get(i).setEquivalente(varAux[contador]);s=1;}else {contador++;
					producciones.get(i).setEquivalente(varAux[contador]);}
						
						
					if(producciones.get(i).getlI().compareTo(producciones.get(i).getlD().substring(0, 1))==0){
						producciones.get(i).setlI(producciones.get(i).getEquivalente());
						producciones.get(i).setlD(producciones.get(i).getlD().substring(1)+producciones.get(i).getEquivalente());
					}else {
						producciones.get(i).setlD(producciones.get(i).getlD()+producciones.get(i).getEquivalente());
					}
				}//FIN IF
			}//FIN SEGUNDO FOR
			
			for(int x = 0; x<nr.size();x++) {
				producciones.add(new Produccion(this.varAux[x], "e"));
			}//FIN TERCER FOR
			
			String gramres ="";
			//System.out.println("\nGRAMATICA SIN RECURSIVIDAD DIRECTA");
			for(int y = 0; y<producciones.size();y++) {
				gramres+=producciones.get(y).getlI();
				//System.out.print(producciones.get(y).getlI());
				gramres+="->";
				gramres+=producciones.get(y).getlD();
				//System.out.println(producciones.get(y).getlD());
				gramres+="\n";
			}
			return new Gramatica(gramres);
			
		}else {
			return null;
		}//FIN IF SI ES RECURSIVA
	}
	
	public boolean esRecursivaDirecta(Gramatica gram) {
		ArrayList<Produccion> producciones = gram.getProducciones();
		boolean si = false;
		for(int i =0; i<producciones.size();i++){
			//System.out.println(producciones.get(i).getlD().substring(0, 1));
			if(producciones.get(i).getlI().compareTo(producciones.get(i).getlD().substring(0, 1))==0) {
				si = true;
			}
		}
		return si;
	}//FIN ESRECURSIVA
	
}//FIN CLASE
