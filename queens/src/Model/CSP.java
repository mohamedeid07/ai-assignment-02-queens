package Model;

public class CSP {

	private int[] inputToState(char[][] input) {
		int[] state = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] == 'Q')
					state[j] = i;
			}
		}
		return state;
	}

	private char[][] stateToInput(int[] state) {
		char[][] res = new char[8][8];
		for (int i = 0; i < state.length; i++) {
			res[state[i]][i] = 'Q';
		}
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				if (res[i][j] != 'Q')
					res[i][j] = '#';
			}
		}
		return res;
	}

	private boolean checkConstraint(int arow, int acol, int brow, int bcol) {
		if (arow == brow)
			return false;
		else if (Math.abs(arow - brow) == Math.abs(acol - bcol))
			return false;
		return true;
	}

	private int attackingQueens(int[] state) {
		int res = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				if (i != j && !checkConstraint(state[i], i, state[j], j)) {
					res++;
				}
			}
		}
		return res;
	}

	private int[] step(int[] state, int index) {
		int min = 64;
		int pos = 0;
		for (int i = 0; i < 8; i++) {
			state[index] = i;
			int aq = attackingQueens(state);
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
		while (attackingQueens(state) != 0) {
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

	private void printResult(int[] state, int totalno, long totalTime, int steps) {
		char[][] result = stateToInput(state);
		System.out.println("Total time in msec = " + totalTime);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("number of steps = " + steps);
		System.out.println("number of nodes = " + totalno);
	}
	
	public char[][] solve(char[][] input) {
		
		int[] state = inputToState(input);
		int[] initState = state.clone();
		long startTime = System.nanoTime();
		int [] no = iterativeImprovement(state, initState);
		long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
		printResult(state, no[0], totalTime, no[1]);

		return null;
	}
}
