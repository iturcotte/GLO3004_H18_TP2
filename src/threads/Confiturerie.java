package threads;

import ordre.PasDepasseOuvreValve;
import ordre.PasDepasseFermeValve;
import ordre.PasDepasseCommenceEtiquetage;
import ordre.PasDepasseTermineEtiquetage;
import controle.ControleValve;
import controle.ControleEtiquetage;
import compteur.CValve;
import compteur.CEtiquetage;

public class Confiturerie {
	
	static final int n = 2;

	public static void main(String[] args) {
		CValve cValve = new CValve(4);
		CEtiquetage cEtiquetage = new CEtiquetage(4);
		ControleValve valveControle = new ControleValve(2);
		ControleEtiquetage etiqControle = new ControleEtiquetage(2);
		PasDepasseOuvreValve pasDepasseOuvreValveA = new PasDepasseOuvreValve(n);
		PasDepasseOuvreValve pasDepasseOuvreValveB = new PasDepasseOuvreValve(n);
		PasDepasseFermeValve pasDepasseFermeValveA = new PasDepasseFermeValve(n);
		PasDepasseFermeValve pasDepasseFermeValveB = new PasDepasseFermeValve(n);
		PasDepasseCommenceEtiquetage pasDepasseCommenceEtiquetageA = new PasDepasseCommenceEtiquetage(n);
		PasDepasseCommenceEtiquetage pasDepasseCommenceEtiquetageB = new PasDepasseCommenceEtiquetage(n);
		PasDepasseTermineEtiquetage pasDepasseTermineEtiquetageA = new PasDepasseTermineEtiquetage(n);
		PasDepasseTermineEtiquetage pasDepasseTermineEtiquetageB = new PasDepasseTermineEtiquetage(n);
		
		Bocal bocal1A = new Bocal(1, "A", pasDepasseOuvreValveA, pasDepasseFermeValveA, 
								          pasDepasseCommenceEtiquetageA, pasDepasseTermineEtiquetageA, valveControle, etiqControle,
								          cValve, cEtiquetage);
		Bocal bocal2A = new Bocal(2, "A", pasDepasseOuvreValveA, pasDepasseFermeValveA, 
										  pasDepasseCommenceEtiquetageA, pasDepasseTermineEtiquetageA, valveControle, etiqControle,
										  cValve, cEtiquetage);
		Bocal bocal1B = new Bocal(1, "B", pasDepasseOuvreValveB, pasDepasseFermeValveB, 
										  pasDepasseCommenceEtiquetageB, pasDepasseTermineEtiquetageB, valveControle, etiqControle,
										  cValve, cEtiquetage);
		Bocal bocal2B = new Bocal(2, "B", pasDepasseOuvreValveB, pasDepasseFermeValveB, 
										  pasDepasseCommenceEtiquetageB, pasDepasseTermineEtiquetageB, valveControle, etiqControle,
										  cValve, cEtiquetage);
		
		bocal2A.start();
		bocal2B.start();
		bocal1A.start();
		bocal1B.start();
	}

}
