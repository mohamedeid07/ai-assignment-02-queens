package Model;


public class Queens {

	public static void main(String[] args) {
		ReadInput r = new ReadInput();
		char[][] input = r.read();
		
		System.out.println("CSP:");
		CSP csp = new CSP();
		csp.solve(input);
//		
//		System.out.println("Hill Climbing:");
//		HillClimbing hc = new HillClimbing();
//		hc.solve(input);
		
//		for (int i = 1; i < 100; i= i * 2) {
//			KBeam kb = new KBeam();
//			System.out.println("K-Beam for k= " + i +" :");
//			kb.solve(i);
//		}
		
//		System.out.println("Genetic:");
//		Genetic genetic = new Genetic();
//		genetic.solve(input);
		
	}

}
