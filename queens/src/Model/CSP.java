package Model;

public class CSP {

	
	private int[] inputToState(char[][] input) {
		int[] state = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if(input[i][j] == 'Q')
					state[j] = i;
			}
		}
		return state;
	}
	
	private boolean checkConstraint(int arow, int acol, int brow, int bcol) {
		if(arow == brow)
			return false;
		else if(Math.abs(arow - brow) == Math.abs(acol - bcol))
			return false;
		return true;
	}
	
	private int attackingQueens(int[] state) {
		int res = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				if (i!=j && !checkConstraint(state[i], i, state[j], j)) {
					res++;
				} 
			}
		}
		return res;
	}
	
	public char[][] solve(char[][] input){
		int[] state = inputToState(input);
		
		System.out.println(attackingQueens(state));
		return null;
	}
}
