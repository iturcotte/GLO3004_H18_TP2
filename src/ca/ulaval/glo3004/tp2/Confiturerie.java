package ca.ulaval.glo3004.tp2;

import java.util.ArrayList;

public class Confiturerie {

	private static String[] S = {"a", "b"};
	public static int N = 2;
	public static int MAX_CONCURRENT = 1;
	
	private static ArrayList<Bocal> _bocals = new ArrayList<Bocal>();
	
	public static void main(String[] args) {
		ControlleurValve cValve = new ControlleurValve(MAX_CONCURRENT, N);
		ControlleurEtiquetage cEtiquetage = new ControlleurEtiquetage(MAX_CONCURRENT, N);
		
		for (int n = 1; n <= N; n++) {
			for (String s : S) {   
				Bocal bocal = new Bocal(s, n, cValve, cEtiquetage, false);
				_bocals.add(bocal);
				bocal.setPriority(s == "a" ? 2 : 1);
				bocal.start();
			} 
		}
	}
}
