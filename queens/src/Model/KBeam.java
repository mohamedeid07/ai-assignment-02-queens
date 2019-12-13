package Model;

import java.util.Arrays;
import java.util.Comparator;

class Sortbyheuristic implements Comparator<int[]> {
	Utils utils = new Utils();
	// Used for sorting states in ascending order of
	public int compare(int[] a, int[] b) {
		return utils.attackingQueens(a) - utils.attackingQueens(b);
	}
}

public class KBeam {

	Utils utils = new Utils();
	private int maxIterations = 3000;
	private int steps=0,nodes=0;

	private int[][] generateRandomStates(int k) {
		int[][] states = new int[k][8];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 8; j++) {
				states[i][j] = (int) (Math.random() * 8);
			}
		}
		return states;
	}

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

	private int[][] generateSuccessors(int[][] states) {
		int[][] succStates = new int[states.length * 8][8];
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < 8; j++) {
				nodes++;
				succStates[i * 8 + j] = step(states[i], j);
			}
		}
		return succStates;
	}

	private int[] getsol(int k) {
		int[][] states = generateRandomStates(k);
		int[][] newStates;
		for (int x = 0; x < maxIterations; x++) {
			steps++;
			newStates = generateSuccessors(states);
			for (int i = 0; i < newStates.length; i++) {
				if (utils.attackingQueens(newStates[i]) == 0)
					return newStates[i];
			}
			Arrays.sort(newStates, new Sortbyheuristic());
			states = Arrays.copyOfRange(newStates, 0, k);
		}
		return null;
	}

	public char[][] solve(int k) {
		
		long startTime = System.nanoTime();
		int[] state = getsol(k);
		while(state == null) {
			state = getsol(k);
		}
		long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        utils.printResult(state, nodes, totalTime, steps);
		return null;
	}
}
