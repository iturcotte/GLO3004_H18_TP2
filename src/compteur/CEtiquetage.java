package compteur;

public class CEtiquetage {
	int n;
	int c;
	
	public CEtiquetage(int _n) {
		this.n = _n;
		this.c = 0;
	}
	
	public boolean commenceEtiquetage() {
		if (c < n) {
			c++;
			return true;
		} else {
			return false;
		}
	}
	
	public void termineEtiquetage() {
		c--;
	}
}
