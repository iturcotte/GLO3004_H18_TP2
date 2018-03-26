package ordre;

public class PasDepasseOuvreValve {
	int currentTurn;
	int n;
	
	public PasDepasseOuvreValve(int _n) {
		this.n = _n;
		this.currentTurn = 1;
	}
	
	public int currentTurn() {
		return currentTurn;
	}
	
	public synchronized boolean ouvreValve(int num) {
		if (num == currentTurn) {
			currentTurn = (currentTurn%n)+1;
			return true;
		} else {
			return false;
		}
	}
}
