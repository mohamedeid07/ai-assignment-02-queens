package Model;


public class Queens {

	public static void main(String[] args) {
		ReadInput r = new ReadInput();
		char[][] input = r.read();
		System.out.println("CSP:");
		CSP csp = new CSP();
		csp.solve(input);
		System.out.println("Hill Climbing:");
		HillClimbing hc = new HillClimbing();
		hc.solve(input);
		System.out.println("K-Beam:");
		KBeam kb = new KBeam();
		kb.solve(input);
		System.out.println("Genetic:");
		Genetic genetic = new Genetic();
		genetic.solve(input);
		
	}

}
