package modelo;

public class Produccion implements Data {
/*****************************************************************************************
* 										ATRIBUTOS
*****************************************************************************************/
		String lI,
			   lD,
			   equivalente;
		
/*****************************************************************************************
 * 										METODOS
*****************************************************************************************/

	public Produccion(String  ladoI, String ladoD) {
		this.lI=ladoI;
		this.lD=ladoD;
	}

public String getlI() {
	return lI;
}

public void setlI(String lI) {
	this.lI = lI;
}

public String getlD() {
	return lD;
}

public void setlD(String lD) {
	this.lD = lD;
}

public String getEquivalente() {
	return equivalente;
}

public void setEquivalente(String equivalente) {
	this.equivalente = equivalente;
}



	
}
