package Model;

public class Utils {

	public boolean checkConstraint(int arow, int acol, int brow, int bcol) {
		if (arow == brow)
			return false;
		else if (Math.abs(arow - brow) == Math.abs(acol - bcol))
			return false;
		return true;
	}

	public int attackingQueens(int[] state) {
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
	
	public void printResult(int[] state, int totalno, long totalTime, int steps) {
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
	
	public int[] inputToState(char[][] input) {
		int[] state = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] == 'Q')
					state[j] = i;
			}
		}
		return state;
	}

	public char[][] stateToInput(int[] state) {
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
}
