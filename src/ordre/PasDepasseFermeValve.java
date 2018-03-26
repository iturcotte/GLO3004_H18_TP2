package ordre;

public class PasDepasseFermeValve {
	int currentTurn;
	int n;
	
	public PasDepasseFermeValve(int _n) {
		this.n = _n;
		this.currentTurn = 1;
	}
	
	public synchronized boolean fermeValve(int num) {
		if (num == currentTurn) {
			currentTurn = (currentTurn%n)+1;
			return true;
		} else {
			return false;
		}
	}
}
