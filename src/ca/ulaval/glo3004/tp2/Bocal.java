package ca.ulaval.glo3004.tp2;

public class Bocal extends Thread {

	private String _type;
	private int _index;
	
	public Bocal(String type, int index) {
		_type = type;
		_index = index;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("Execution : " + obtenirNomBocal());
		
	
		
	}
	
	
	public void requeteValve() {
		
		afficherAction("requeteValve");
		
		ouvreValve();
	}
	
	public void ouvreValve() {
		
		afficherAction("ouvreValve");
		
		remplit();
	}
	
	
	public void remplit() {
		
		afficherAction("remplit");
		
		fermeValve();
		
	} 

	
	public void fermeValve() {
		
		afficherAction("fermeValve");
		
		requeteEtiquetage();
		
	}
	
	
	
	public void rupture() {
		
		afficherAction("rupture");
		
	} 

	
	public void finRupture() {
		
		afficherAction("finRupture");
		
	}
	
	
	public void requeteEtiquetage() {
		
		afficherAction("requeteEtiquetage");
		
		commenceEtiquetage();
		
	} 

	public void commenceEtiquetage() {
		
		afficherAction("commenceEtiquetage");
		
		etiquette();
	} 

	public void etiquette() {
		
		afficherAction("etiquette");
		
		termineEtiquetage();
	} 

	public void termineEtiquetage() {
		
		afficherAction("termineEtiquetage");
		
	}
	
	
	
	private String obtenirNomBocal() {
		return _index + "." + _type;
	}
	
	private void afficherAction(String action) {
		System.out.println(obtenirNomBocal() + "." + action);
	}
}
