package threads;

import controle.ControleValve;
import controle.ControleEtiquetage;
import ordre.PasDepasseOuvreValve;
import ordre.PasDepasseFermeValve;
import ordre.PasDepasseCommenceEtiquetage;
import ordre.PasDepasseTermineEtiquetage;
import compteur.CValve;
import compteur.CEtiquetage;

public class Bocal extends Thread {
	int num;
	String type;
	ControleValve valveControle;
	ControleEtiquetage etiqControle;
	PasDepasseOuvreValve pasDepasseOuvreValve;
	PasDepasseFermeValve pasDepasseFermeValve;
	PasDepasseCommenceEtiquetage pasDepasseCommenceEtiquetage;
	PasDepasseTermineEtiquetage pasDepasseTermineEtiquetage;
	CValve cValve;
	CEtiquetage cEtiquetage;
	
	public Bocal(int _num, String _type, 
				 PasDepasseOuvreValve _pasDepasseOuvreValve,
				 PasDepasseFermeValve _pasDepasseFermeValve,
				 PasDepasseCommenceEtiquetage _pasDepasseCommenceEtiquetage,
				 PasDepasseTermineEtiquetage _pasDepasseTermineEtiquetage,
				 ControleValve _valveControle,
				 ControleEtiquetage _etiqControle,
				 CValve _cValve,
				 CEtiquetage _cEtiquetage) {
		this.num = _num;
		this.type = _type;
		this.pasDepasseOuvreValve = _pasDepasseOuvreValve;
		this.pasDepasseFermeValve = _pasDepasseFermeValve;
		this.pasDepasseCommenceEtiquetage = _pasDepasseCommenceEtiquetage;
		this.pasDepasseTermineEtiquetage = _pasDepasseTermineEtiquetage;
		this.valveControle = _valveControle;
		this.etiqControle = _etiqControle;
		this.cValve = _cValve;
		this.cEtiquetage = _cEtiquetage;
	}

	@Override
	public void run() {
		requeteValve();
		ouvreValve();
		remplit();
		fermeValve();
	
		requeteEtiquetage();
		commenceEtiquetage();
		etiquette();
		termineEtiquetage();
		System.out.println("[ Bocal "+num+"."+type+" ] : DONE");
	}

	public void requeteValve() {
		while (!valveControle.requeteValve(type)) {
			try {
				System.out.println("[ Bocal "+num+"."+type+" ] : requeteValve - Waiting...");
				synchronized (valveControle) {
					valveControle.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[ Bocal "+num+"."+type+" ] : requeteValve");
	}

	public void ouvreValve() {
		while (!pasDepasseOuvreValve.ouvreValve(num)) {
			System.out.println("[ Bocal "+num+"."+type+" ] : ouvreValve - Waiting pasDepasseOuvreValve...");
		}
	
		while (!valveControle.ouvreValve(type)) {
			try {
				System.out.println("[ Bocal "+num+"."+type+" ] : ouvreValve - Waiting valveControle...");
				synchronized (valveControle) {
					valveControle.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while (!cValve.ouvreValve()) {
			System.out.println("[ Bocal "+num+"."+type+" ] : ouvreValve - Waiting cValve...");
		}
		
		System.out.println("[ Bocal "+num+"."+type+" ] : ouvreValve");
	}

	public void remplit() {
		System.out.println("[ Bocal "+num+"."+type+" ] : remplit");
	}
	
	public void fermeValve() {
		while (!pasDepasseFermeValve.fermeValve(num)) {
			System.out.println("[ Bocal "+num+"."+type+" ] : fermeValve - Waiting pasDepasseFermeValve...");
		}
		cValve.fermeValve();
		valveControle.fermeValve(type);
		System.out.println("[ Bocal "+num+"."+type+" ] : fermeValve");
	}
	
	public void requeteEtiquetage() {
		while (!etiqControle.requeteEtiquetage(type)) {
			try {
				System.out.println("[ Bocal "+num+"."+type+" ] : requeteEtiquetage - Waiting...");
				synchronized (etiqControle) {
					etiqControle.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[ Bocal "+num+"."+type+" ] : requeteEtiquetage");
	}
	
	public void commenceEtiquetage() {
		while (!pasDepasseCommenceEtiquetage.commenceEtiquetage(num)) {
			System.out.println("[ Bocal "+num+"."+type+" ] : commenceEtiquetage - Waiting pasDepasseCommenceEtiquetage...");
		}
		
		while (!etiqControle.commenceEtiquetage(type)) {
			try {
				System.out.println("[ Bocal "+num+"."+type+" ] : commenceEtiquetage - Waiting etiqControle...");
				synchronized (etiqControle) {
					etiqControle.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while (!cEtiquetage.commenceEtiquetage()) {
			System.out.println("[ Bocal "+num+"."+type+" ] : commenceEtiquetage - Waiting cEtiquetage...");
		}
		
		System.out.println("[ Bocal "+num+"."+type+" ] : commenceEtiquetage");
	}
	
	public void etiquette() {
		System.out.println("[ Bocal "+num+"."+type+" ] : etiquette");
	}
	
	public void termineEtiquetage() {
		while (!pasDepasseTermineEtiquetage.termineEtiquetage(num)) {
			System.out.println("[ Bocal "+num+"."+type+" ] : termineEtiquetage - Waiting pasDepasseTermineEtiquetage...");
		}
		cEtiquetage.termineEtiquetage();
		etiqControle.termineEtiquetage(type);
		System.out.println("[ Bocal "+num+"."+type+" ] : termineEtiquetage");
	}
}
