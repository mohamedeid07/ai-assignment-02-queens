package Model;

public class CSP {
	
	Utils utils = new Utils();

	private int[] step(int[] state, int index) {
		int min = 64;
		int pos = 0;
		for (int i = 0; i < 8; i++) {
			state[index] = i;
			int aq = utils.attackingQueens(state);
			if (min > aq) {
				min = aq;
				pos = i;
			}
		}
		state[index] = pos;
		return state;
	}

	private int[] iterativeImprovement(int[] state, int[] initState) {
		int totalno = 0, cycle = 0;
		while (utils.attackingQueens(state) != 0) {
			cycle++;
			totalno++;
			step(state, (int) (Math.random() * 8));
			if (cycle >= 50) {
				state = initState.clone();
				cycle = 0;
			}
		}

		return new int[] {totalno, cycle};
	}

	
	
	public char[][] solve(char[][] input) {
		
		int[] state = utils.inputToState(input);
		int[] initState = state.clone();
		long startTime = System.nanoTime();
		int [] no = iterativeImprovement(state, initState);
		long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        utils.printResult(state, no[0], totalTime, no[1]);

		return null;
	}
}
