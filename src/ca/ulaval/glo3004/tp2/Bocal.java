package ca.ulaval.glo3004.tp2;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;


public class Bocal extends Thread {

	private String _type;
	private int _index;

	private ControlleurValve _cValve;
	private ControlleurEtiquetage _cEtiquetage;

	private CyclicBarrier _b;

	public Bocal(String type, int index, ControlleurValve cValve, ControlleurEtiquetage cEtiquetage, CyclicBarrier b) {
		_type = type;
		_index = index;
		_cValve = cValve;
		_cEtiquetage = cEtiquetage;
		_b = b;
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
				_cValve.lock.notifyAll();
				afficherAction("requeteValve");

				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			_b.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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


		this.remplit();
	}


	public void remplit() {

		afficherAction("remplit");

		this.fermeValve();

	} 


	public void fermeValve() {



		try {
			synchronized(_cValve.lock) {

				while(!_cValve.fermeValve(_index, _type)) _cValve.lock.wait();
				_cValve.lock.notifyAll();
				afficherAction("fermeValve");
			}
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			_b.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.requeteEtiquetage();

	}



	public void rupture() {

		afficherAction("rupture");

	} 


	public void finRupture() {

		afficherAction("finRupture");

	}


	public void requeteEtiquetage() 
	{


		synchronized(_cEtiquetage.lock2) 
		{

			try 
			{

				while(!_cEtiquetage.requeteEtiquetage(_index, _type)) _cEtiquetage.lock2.wait();
				_cEtiquetage.lock2.notifyAll();
				afficherAction("requeteEtiquetage");

			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			_b.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.commenceEtiquetage();

	} 

	public void commenceEtiquetage() 
	{

		synchronized(_cEtiquetage.lock2) 
		{
			try 
			{


				while(!_cEtiquetage.CommenceEtiquetage(_index, _type)) _cEtiquetage.lock2.wait();
				_cEtiquetage.lock2.notifyAll();
				afficherAction("CommenceEtiquetage");
			} 

			catch (InterruptedException e) 
			{
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

	public void TermineEtiquetage() 
	{
		synchronized(_cEtiquetage.lock2) 
		{
			try 
			{
				while(!_cEtiquetage.TermineEtiquetage(_index, _type)) _cEtiquetage.lock2.wait();
				_cEtiquetage.lock2.notifyAll();
				afficherAction("TermineEtiquetage");
			}

			catch (InterruptedException e) 

			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String obtenirNomBocal() {
		return _index + "." + _type;
	}

	private void afficherAction(String action) {
		System.out.println(obtenirNomBocal() + "." + action);
	}
}
