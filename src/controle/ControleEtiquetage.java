package controle;

public class ControleEtiquetage {

	String currentType;
	int waitingA;
	int nbsA;
	int waitingB;
	int nbsB;
	int n;
	
	public ControleEtiquetage(int _n) {
		this.n = _n;
		this.waitingA = 0;
		this.nbsA = 0;
		this.waitingB = 0;
		this.nbsB = 0;
		this.currentType = "A";
	}
	
	public synchronized boolean requeteEtiquetage(String type) {
		if (type.equals("A") && waitingA < n) {
			waitingA++;
			return true;
		} else if (type.equals("B") && waitingB < n) {
			waitingB++;
			return true;
		}
		return false;
	}

	public synchronized boolean commenceEtiquetage(String type) {
		if (type.equals("A") && nbsB == 0 && nbsA < n && waitingA > 0 && (waitingB == 0 || currentType.equals("A"))) {
			nbsA++;
			waitingA--;
			synchronized (this) {
				notifyAll();
			}
			return true;
		} else if (type.equals("B") && nbsA == 0 && nbsB < n && waitingB > 0 && (waitingA == 0 || currentType.equals("B"))) {
			nbsB++;
			waitingB--;
			synchronized (this) {
				notifyAll();
			}
			return true;
		}
		return false;
	}
	
	public synchronized void termineEtiquetage(String type) {
		if (type.equals("A") && nbsA > 0) {
			nbsA--;
			currentType = "B";
			synchronized (this) {
				notifyAll();
			}
		} else if (type.equals("B") && nbsB > 0) {
			nbsB--;
			currentType = "A";
			synchronized (this) {
				notifyAll();
			}
		}
	}
	
}
