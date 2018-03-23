package ca.ulaval.glo3004.tp2;


public class ControlleurEtiquetage {
	
public Object lock = new Object();
	
	private int _n;
	
	
	private int _na = 0;
	private int _nb = 0;
	private int _wa = 0;
	private int _wb = 0;
	
	private int _t = 1;
	
	
	
	public ControlleurEtiquetage(int n) {
		_n = n;
		
	}
	
	
	public boolean requeteEtiquetage(String type) {
		
		synchronized(lock) {
			
			if (type == "a" && _wa < _n) {
				_wa += 1;
				return true;
			}
			if (type == "b" && _wb < _n) {
				_wb += 1;
				return true;
			}
			
			return false;
		}
		
	}
	
	public boolean CommenceEtiquetage(String type) {
		
		synchronized(lock) {
			
			if (type == "a" && _nb == 0 && _na < _n && _wa > 0 && (_wb == 0 || _t == 1)) {
				_na += 1;
				_wa -= 1;
				return true;
			}
			if (type == "b" && _na == 0 && _nb < _n && _wb > 0 && (_wa == 0 || _t == 2)) {
				_nb += 1;
				_wb -= 1;
				return true;
			}
			
			return false;
		}
		
	}
	
	public boolean TermineEtiquetage(String type) {
		
		synchronized(lock) {
			
			if (type == "a" && _na > 0) {
				_na -= 1;
				_t = 2;
			
				return true;
			}
			if (type == "b" && _nb > 0) {
				_nb -= 1;
				_t = 1;
				
				return true;
			}
			
			return false;
		}
		
	}
	
}


