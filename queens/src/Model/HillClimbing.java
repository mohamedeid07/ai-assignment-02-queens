package Model;

import java.util.Random;

public class HillClimbing {
	
    private static int n = 8;
    private static int stepsClimbedAfterLastRestart = 0;
    private static int steps =0;
    private static int heuristic = 0;
    private static int randomRestarts = 0;
    private static int nodesExpanded = 0;

    //Creates Random board
    public static Queen[] generateBoard() {
        Queen[] startBoard = new Queen[n];
        Random rndm = new Random();
        for(int i=0; i<n; i++){
            startBoard[i] = new Queen(rndm.nextInt(n), i);
        }
        return startBoard;
    }
    
    public  Queen[] convert(char[][] chArr){
    	Queen[] startBoard = new Queen[n];
    	int z=0;
    	for(int i=0; i<8; i++){
    		for(int j=0; j<8; j++){
    			if(chArr[i][j] =='Q'){
    				startBoard[z++] = new Queen(i,j);
    			}    			
    		}
    	}
    	return startBoard;
    }
 

    //Prints state
    private  void printState (Queen[] state) {
        //Creating temporary board from the present board
        int[][] tempBoard = new int[n][n];
        for (int i=0; i<n; i++) {
            //Get the positions of Queen from the Present board and set those positions as 1 in temp board
            tempBoard[state[i].getRow()][state[i].getColumn()]=1;
        }
        System.out.println();
        for (int i=0; i<n; i++) {
            for (int j= 0; j < n; j++) {
            	if(tempBoard[i][j]==1){
                System.out.print("Q" + " ");
            	}else{
            	System.out.print("#" + " ");
            	}
            }
            System.out.println();
        }
    }

    //Finds heuristic of a state
    public  int findHeuristic (Queen[] state) {
        int h = 0;
        for (int i = 0; i< state.length; i++) {
            for (int j=i+1; j<state.length; j++ ) {
                if (state[i].ifConflict(state[j])) {
                    h++;
                }
            }
        }
        return h;
    }

    // Find next state with the lowest h
    public Queen[] nextState (Queen[] current) {
        Queen[] next = new Queen[n];
        Queen[] tmpBoard = new Queen[n];
        int presentHeuristic = findHeuristic(current);
        int bestHeuristic = presentHeuristic;
        int tempH;

        for (int i=0; i<n; i++) {
            //  Copy present board as best board and temp board
            next[i] = new Queen(current[i].getRow(), current[i].getColumn());
            tmpBoard[i] = next[i];
        }
        //  Iterate each column
        for (int i=0; i<n; i++) {
            if (i>0)
                tmpBoard[i-1] = new Queen (current[i-1].getRow(), current[i-1].getColumn());
            tmpBoard[i] = new Queen (0, tmpBoard[i].getColumn());
            //  Iterate each row
            for (int j=0; j<n; j++) {
                //Get the heuristic
                tempH = findHeuristic(tmpBoard);
                nodesExpanded++;
                //Check if temp board better than best board
                if (tempH < bestHeuristic) {
                    bestHeuristic = tempH;
                    //  Copy the temp board as best board
                    for (int k=0; k<n; k++) {
                        next[k] = new Queen(tmpBoard[k].getRow(), tmpBoard[k].getColumn());
                    }
                }
                //Move the queen
                if (tmpBoard[i].getRow()!=n-1)
                    tmpBoard[i].move();
            }
        }
        //Check whether the present bord and the best board found have same heuristic
        //Then randomly generate new board and assign it to best board
        if (bestHeuristic == presentHeuristic) {
            randomRestarts++;
            stepsClimbedAfterLastRestart = 0;
            System.out.println("\nStuck at local maxima -> Random Restart :");
            next = generateBoard();
            heuristic = findHeuristic(next);
        } else
            heuristic = bestHeuristic;
        steps++;
        stepsClimbedAfterLastRestart++;
        return next;
    }

    public void solve(char[][] input) {
        int presentHeuristic;   
        System.out.println("Solution to "+n+" queens using hill climbing with random restart:");
        //Creating the initial Board    
        Queen[] presentBoard = convert(input);
        long startTime = System.nanoTime();
        presentHeuristic = findHeuristic(presentBoard);
        // test if the present board is the solution board
        while (presentHeuristic != 0) {
            //  Get the next board
            printState(presentBoard);
            presentBoard = nextState(presentBoard);
            presentHeuristic  = heuristic;
        }
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        //Printing the solution
        printState(presentBoard);
        System.out.println("\nTotal time taken in msec : " + totalTime);
        System.out.println("Number of steps : " + steps);
        System.out.println("Number of nodes Expanded : " + nodesExpanded);
        System.out.println("Number of random restarts: " + randomRestarts);
        System.out.println("Number of steps last restart: " + stepsClimbedAfterLastRestart); 	
        
    }
}

