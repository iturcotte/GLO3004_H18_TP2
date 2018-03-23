package ca.ulaval.glo3004.tp2;

public class Bocal extends Thread {

	private String _type;
	private int _index;
	
	private ControlleurValve _cValve;
	
	public Bocal(String type, int index, ControlleurValve cValve) {
		_type = type;
		_index = index;
		_cValve = cValve;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("Execution : " + obtenirNomBocal());
		
		
		this.requeteValve();
		
	}
	
	
	public void requeteValve() {
		
		synchronized(_cValve.lock) {
		
			try {
				while(!_cValve.requeteValve(_type)) _cValve.lock.wait();
				
				afficherAction("requeteValve");
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
			
		this.ouvreValve();
		
		
	}
	
	public void ouvreValve() {
		
		synchronized(_cValve.lock) {
			try {
				while(!_cValve.ouvreValve(_type)) _cValve.lock.wait();
				
				afficherAction("ouvreValve");
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
			
		remplit();
	}
	
	
	public void remplit() {
		
		afficherAction("remplit");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fermeValve();
		
	} 

	
	public void fermeValve() {
		
		_cValve.fermeValve(_type); 
		afficherAction("fermeValve");
		try {
			synchronized(_cValve.lock) {
			
				_cValve.lock.notifyAll();
			
			}
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//requeteEtiquetage();
		this.requeteValve();
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
