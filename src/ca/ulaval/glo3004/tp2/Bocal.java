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
		
	}
	
	public void ouvreValve() {
		
	}
	
	
	public void remplit() {
		
	} 

	
	public void fermeValve() {
		
	}
	
	
	
	public void rupture() {
		
	} 

	
	public void finRupture() {
		
	}
	
	
	public void requeteEtiquetage() {
		
	} 

	public void commenceEtiquetage() {
		
	} 

	public void etiquette() {
		
	} 

	public void termineEtiquetage() {
		
	}
	
	
	
	private String obtenirNomBocal() {
		return _index + "." + _type;
	}
	
	
}
