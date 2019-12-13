package Model;


public class Queens {

	public static void main(String[] args) {
		ReadInput r = new ReadInput();
		char[][] input = r.read();
		
//		System.out.println("CSP:");
//		CSP csp = new CSP();
//		csp.solve(input);
//		
//		System.out.println("Hill Climbing:");
//		HillClimbing hc = new HillClimbing();
//		hc.solve(input);
		
		KBeam kb = new KBeam();
		System.out.println("K-Beam for k=1 :");
		kb.solve(1);
		
		System.out.println("K-Beam for k=50 :");
		kb.solve(50);
		
//		System.out.println("Genetic:");
//		Genetic genetic = new Genetic();
//		genetic.solve(input);
		
	}

}
