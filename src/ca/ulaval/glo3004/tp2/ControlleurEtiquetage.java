package ca.ulaval.glo3004.tp2;


public class ControlleurEtiquetage {
	private int max_concurrent;
	private int n;
	private int compteur_commence = 0;
	private int na = 0;
	private int nb = 0;
	private int wa = 0;
	private int wb = 0;
	private int index_courant_commence_etiquetage_a = 1;
	private int index_courant_commence_etiquetage_b = 1;
	private int index_courant_termine_etiquetage_a = 1;
	private int index_courant_termine_etiquetage_b = 1;
	private String t = "a";
	
	public ControlleurEtiquetage(int _max_concurrent, int _n) {
		n = _n;
		max_concurrent = _max_concurrent;
	}
	
	public synchronized boolean requeteEtiquetage(int index, String type) {	
		if (type == "a" && wa < n) {
			wa += 1;
			afficherAction(index, type, "requeteEtiquetage");
			return true;
		}
		if (type == "b" && wb < n) {
			wb += 1;
			afficherAction(index, type, "requeteEtiquetage");
			return true;
		}
		
		return false;
	}

	public synchronized boolean commenceEtiquetage(int index, String type) {
		if (compteur_commence == max_concurrent) {
			return false;
		}
	    if (type == "a" && index == index_courant_commence_etiquetage_a && nb == 0 && na < n && wa > 0 && (wb == 0 || t == "a")) {
			na += 1;
			wa -= 1;
			compteur_commence++;
			index_courant_commence_etiquetage_a = (index_courant_commence_etiquetage_a%n)+1;
			afficherAction(index, type, "commenceEtiquetage");
		    return true;
		}
	    else if (type == "b" && index == index_courant_commence_etiquetage_b && na == 0 && nb < n && wb > 0 && (wa == 0 || t == "b")) {
	    	nb += 1;
	    	wb -= 1;
	    	compteur_commence++;
	    	index_courant_commence_etiquetage_b = (index_courant_commence_etiquetage_b%n)+1;
	    	afficherAction(index, type, "commenceEtiquetage");
	    	return true;
	    }
	    else {
		   return false;
	    }
	}
	
	public synchronized boolean termineEtiquetage(int index, String type) {
		if (type == "a" && index == index_courant_termine_etiquetage_a && na > 0) {
     		na -= 1;
	    	t = "b";
	    	compteur_commence--;
	    	index_courant_termine_etiquetage_a = (index_courant_termine_etiquetage_a%n)+1;
	    	afficherAction(index, type, "TermineEtiquetage");
	    	return true;
		}
		else if (type == "b" && index == index_courant_termine_etiquetage_b && nb > 0) {
			nb -= 1;
			t = "a";
			compteur_commence--;
			index_courant_termine_etiquetage_b = (index_courant_termine_etiquetage_b%n)+1;
			afficherAction(index, type, "TermineEtiquetage");
		    return true;
	    }
		else {
		    return false;
		}
   }
	
   private void afficherAction(int index, String type, String action) {
		System.out.println(index + "." + type + "." + action);
	}
	
}


