package ca.ulaval.glo3004.tp2;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Confiturerie {

	private static String[] S = {"a", "b"};
	public static int N = 2;
	
	private static ArrayList<Bocal> _bocals = new ArrayList<Bocal>();
	
	public static void main(String[] args) {
		
		
		ControlleurValve cValve = new ControlleurValve(N);
		ControlleurEtiquetage cEtiquetage = new ControlleurEtiquetage(N);
		
		CyclicBarrier b = new CyclicBarrier(4);
	
		for (String s : S) {
		for (int n = 1; n <= N; n++) {
			
			   
				Bocal bocal = new Bocal(s, n, cValve, cEtiquetage, b);
				_bocals.add(bocal);
				
				// Augmenter la priorite des Bocaux "a"
				//bocal.setPriority(s == "a" ? 2 : 1);
			
					
				bocal.start();


			} 
		}
		
		

		
	}
	
	
	
	
}
