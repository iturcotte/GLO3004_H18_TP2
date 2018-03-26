package ca.ulaval.glo3004.tp2;

import java.util.ArrayList;

public class Confiturerie {

	private static String[] S = {"a", "b"};
	public static int N = 2;
	
	private static ArrayList<Bocal> _bocals = new ArrayList<Bocal>();
	
	public static void main(String[] args) {
		
		
		ControlleurValve cValve = new ControlleurValve(N);
		ControlleurEtiquetage cEtiquetage = new ControlleurEtiquetage(N);
		
		for (int n = 1; n <= N; n++) {
			for (String s : S) {
			   
				Bocal bocal = new Bocal(s, n, cValve, cEtiquetage);
				_bocals.add(bocal);
				
				// Augmenter la priorite des Bocaux "a"
				bocal.setPriority(s == "a" ? 2 : 1);
			
					
				bocal.start();


			} 
		}
		
		

		
	}
	
	
	
	
}
