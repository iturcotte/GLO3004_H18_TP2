package ca.ulaval.glo3004.tp2;

public class ControlleurValve {
	private int max_concurrent;
	private int n;
	private int compteur_ouvre = 0;
	private int na = 0;
	private int nb = 0;
	private int wa = 0;
	private int wb = 0;
	private int index_courant_ouvre_valve_a = 1;
	private int index_courant_ouvre_valve_b = 1;
	private int index_courant_ferme_valve_a = 1;
	private int index_courant_ferme_valve_b = 1;
	private String t = "a";
    
	public ControlleurValve(int _max_concurrent, int _n) {
		n = _n;
		max_concurrent = _max_concurrent;
	}
	
	
	public synchronized boolean requeteValve(int index, String type) {	
		if (type == "a" && wa < n) {
			wa += 1;
			afficherAction(index, type, "requeteValve");
			return true;
		}
		if (type == "b" && wb < n) {
			afficherAction(index, type, "requeteValve");
			wb += 1;
			return true;
		}
	
		return false;
	}
	
	public synchronized boolean ouvreValve(int index, String type) {
		if (compteur_ouvre == max_concurrent) {
			return false;
		}
	    if (type == "a" && index == index_courant_ouvre_valve_a && nb == 0 && na < n && wa > 0 && (wb == 0 || t == "a")) {
			na += 1;
			wa -= 1;
			compteur_ouvre++;
			index_courant_ouvre_valve_a = (index_courant_ouvre_valve_a%n)+1;
			afficherAction(index, type, "ouvreValve");
		    return true;
		} else if (type == "b" && index == index_courant_ouvre_valve_b && na == 0 && nb < n && wb > 0 && (wa == 0 || t == "b")) {
	    	nb += 1;
	    	wb -= 1;
	    	compteur_ouvre++;
	    	index_courant_ouvre_valve_b = (index_courant_ouvre_valve_b%n)+1;
	    	afficherAction(index, type, "ouvreValve");
	    	return true;
	    } else {
		   return false;
	    }
	}
	
	public synchronized boolean fermeValve(int index, String type) {
		if (type == "a" && index == index_courant_ferme_valve_a && na > 0) {
     		na -= 1;
	    	t = "b";
	    	compteur_ouvre--;
	    	index_courant_ferme_valve_a = (index_courant_ferme_valve_a%n)+1;
	    	afficherAction(index, type, "fermeValve");
	    	return true;
		} else if (type == "b" && index == index_courant_ferme_valve_b && nb > 0) {
			nb -= 1;
			t = "a";
			compteur_ouvre--;
			index_courant_ferme_valve_b = (index_courant_ferme_valve_b%n)+1;
			afficherAction(index, type, "fermeValve");
		    return true;
	    } else {
		    return false;
		}
   }
	
	private void afficherAction(int index, String type, String action) {
		System.out.println(index + "." + type + "." + action);
	}
}

