package compteur;

public class CValve {
	int n;
	int c;
	
	public CValve(int _n) {
		this.n = _n;
		this.c = 0;
	}
	
	public boolean ouvreValve() {
		if (c < n) {
			c++;
			return true;
		} else {
			return false;
		}
	}
	
	public void fermeValve() {
		c--;
	}
}
