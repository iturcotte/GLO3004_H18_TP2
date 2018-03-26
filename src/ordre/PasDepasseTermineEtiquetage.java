package ordre;

public class PasDepasseTermineEtiquetage {
	int currentTurn;
	int n;
	
	public PasDepasseTermineEtiquetage(int _n) {
		this.n = _n;
		this.currentTurn = 1;
	}
	
	public synchronized boolean termineEtiquetage(int num) {
		if (num == currentTurn) {
			currentTurn = (currentTurn%n)+1;
			return true;
		} else {
			return false;
		}
	}
}
