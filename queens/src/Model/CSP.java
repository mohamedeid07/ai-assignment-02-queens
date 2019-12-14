package Model;

public class CSP {
	
	Utils utils = new Utils();
	private int nodes =0, steps=0;

	private int[] step(int[] state, int index) {
		int min = Integer.MAX_VALUE;
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
		while (utils.attackingQueens(state) != 0) {
			steps++;
			nodes++;
			state = step(state, (int) (Math.random() * 8));
			if (steps >= 50) {
				state = initState.clone();
				steps = 0;
			}
		}

		return state;
	}

	
	
	
	public char[][] solve(char[][] input) {
		input = utils.flatenInput(input);
		int[] state = utils.inputToState(input);
		int[] initState = state.clone();
		long startTime = System.nanoTime();
		state = iterativeImprovement(state, initState);
		long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        utils.printResult(state, nodes, totalTime, steps);

		return null;
	}
}
