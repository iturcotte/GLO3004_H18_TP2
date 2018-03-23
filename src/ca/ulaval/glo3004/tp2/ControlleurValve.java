package ca.ulaval.glo3004.tp2;

import java.util.concurrent.*;

public class ControlleurValve {

	private int _n;
	
	public Semaphore _semMax;
	public Semaphore _semB;
	
	private String _typeCourant = "";
	
	
	
	public ControlleurValve(int n) {
		_n = n;
		_semMax = new Semaphore(_n);
		_semB = new Semaphore(0);
		
		
	}
	
	
	public void requeteValve(String type) {
		
		try {
			
			if (_typeCourant == "" || _typeCourant == type) {
				_typeCourant = type;
				_semMax.acquire();
				
			} else {
				
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
