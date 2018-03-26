package ca.ulaval.glo3004.tp2;

public class Bocal extends Thread {

	private String _type;
	private int _index;
	
	private ControlleurValve _cValve;
	private ControlleurEtiquetage _cEtiquetage;
	
	public Bocal(String type, int index, ControlleurValve cValve, ControlleurEtiquetage cEtiquetage) {
		_type = type;
		_index = index;
		_cValve = cValve;
		_cEtiquetage = cEtiquetage;
	}
	
	
	@Override
	public void run() {
		
	//	System.out.println("Execution : " + obtenirNomBocal());
		
		
		this.requeteValve();
		
	}
	
	
	public void requeteValve() {
		
		synchronized(_cValve.lock) {
		
			try {
				while(!_cValve.requeteValve(_index, _type)) _cValve.lock.wait();
				
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
				while(!_cValve.ouvreValve(_index, _type)) _cValve.lock.wait();
				_cValve.lock.notifyAll();
				afficherAction("ouvreValve");
				
	//			Thread.sleep(500);
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
		
		_cValve.fermeValve(_index, _type); 
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
		
		
		
		this.requeteEtiquetage();
		//this.requeteValve();
	}
	
	
	
	public void rupture() {
		
		afficherAction("rupture");
		
	} 

	
	public void finRupture() {
		
		afficherAction("finRupture");
		
	}
	
	
	public void requeteEtiquetage() {
		
		
		synchronized(_cEtiquetage.lock) {
			
			try {
				while(!_cEtiquetage.requeteEtiquetage(_type)) _cEtiquetage.lock.wait();
				
				afficherAction("requeteEtiquetage");
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.commenceEtiquetage();
		
	} 

	public void commenceEtiquetage() {
		
			synchronized(_cEtiquetage.lock) {
			try {
				while(!_cEtiquetage.CommenceEtiquetage(_type)) _cEtiquetage.lock.wait();
				
				afficherAction("CommenceEtiquetage");
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.etiquette();
		}
	} 

	public void etiquette() {
		
		afficherAction("etiquette");
		
		this.TermineEtiquetage();
	} 

	public void TermineEtiquetage() {
		
		_cEtiquetage.TermineEtiquetage(_type); 
		afficherAction("TermineEtiquetage");
		try {
			synchronized(_cEtiquetage.lock) {
			
				_cEtiquetage.lock.notifyAll();
			
			}
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private String obtenirNomBocal() {
		return _index + "." + _type;
	}
	
	private void afficherAction(String action) {
		System.out.println(obtenirNomBocal() + "." + action);
	}
}
