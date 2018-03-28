package ca.ulaval.glo3004.tp2;

public class Bocal extends Thread {

	private String type;
	private int index;
	
	private ControlleurValve cValve;
	private ControlleurEtiquetage cEtiquetage;
	
	boolean rupture;
	
	public Bocal(String _type, int _index, ControlleurValve _cValve, ControlleurEtiquetage _cEtiquetage, boolean _rupture) {
		type = _type;
		index = _index;
		cValve = _cValve;
		cEtiquetage = _cEtiquetage;
		rupture = _rupture;
	}
	
	
	@Override
	public void run() {	
		this.requeteValve();
	}
	
	
	public void requeteValve() {	
		while(!cValve.requeteValve(index, type)) {}	
		this.ouvreValve();	
	}
	
	public void ouvreValve() {
		while(!cValve.ouvreValve(index, type)) {}
		this.remplit();	
	}
	
	
	public void remplit() {	
		afficherAction("remplit");	
		this.fermeValve();
	} 

	
	public void fermeValve() {
		while(!cValve.fermeValve(index, type)) {}
		if (rupture) {
			this.rupture();
		} else {
			this.requeteEtiquetage();
		}
	}
	
	
	public void rupture() {
		afficherAction("rupture");
	} 

	
	public void finRupture() {
		afficherAction("finRupture");
	}

	
	public void requeteEtiquetage() 
	{
	    while(!cEtiquetage.requeteEtiquetage(index, type)) {}
		this.commenceEtiquetage();
	} 

	public void commenceEtiquetage() 
	{
		while(!cEtiquetage.commenceEtiquetage(index, type)) {}
		this.etiquette();
	} 

	public void etiquette() {
		afficherAction("etiquette");
		this.TermineEtiquetage();
	} 

	public void TermineEtiquetage() 
	{
		while(!cEtiquetage.termineEtiquetage(index, type)) {}
	}
	
	private String obtenirNomBocal() {
		return index + "." + type;
	}
	
	private void afficherAction(String action) {
		System.out.println(obtenirNomBocal() + "." + action);
	}
}
