package ordre;

public class PasDepasseCommenceEtiquetage {
	int currentTurn;
	int n;
	
	public PasDepasseCommenceEtiquetage(int _n) {
		this.n = _n;
		this.currentTurn = 1;
	}
	
	public synchronized boolean commenceEtiquetage(int num) {
		if (num == currentTurn) {
			currentTurn = (currentTurn%n)+1;
			return true;
		} else {
			return false;
		}
	}
}
