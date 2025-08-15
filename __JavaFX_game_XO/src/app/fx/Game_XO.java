package app.fx;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game_XO {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		int players = 0;
		boolean playersNumber = false;
		
		// creating a play area
		String [] playArea = {
				"*", "*", "*", 
				"*", "*", "*", 
				"*", "*", "*"
		};
		
		System.out.println("Let's play tic-tac-toe! \n");
		while (!playersNumber) {
			 try {
				 System.out.println("Enter the number of players (1 or 2)?");
				 players = scanner.nextInt();
				 
				 
				 if (players == 1 || players == 2) {
					 playersNumber = true;
				 } else {
					 System.out.println("\nInvalid value. Try again!");
				 }
			 } catch (InputMismatchException e) {
			        System.out.println("\nInvalid value. This is not a number. Try again!");
			        scanner.next(); //
			 }
		}
			 

		
	    if (players == 1) {
	    	printArray (playArea); 
	    	OnePlayer(playArea);
	    } else if (players == 2) {
			printArray (playArea);   	
			TwoPlayers (playArea);
	    }
	    
	      
	} // main
		
		
	
	public static void TwoPlayers(String[] playArea) {
		boolean gameOver = false;
		boolean Move;
		boolean validMove = false;
		Scanner scanner = new Scanner(System.in);


		do {   
			// Player 1
		    do {
		    	validMove = false;
		    	int moveX = 0;
		    	while (!validMove) {
					 try {
						 System.out.println("\nPlayer 1 (X), select a field: 1-9?");
						 moveX = scanner.nextInt();
						 
						 
						 if (moveX >= 1 && moveX <=9) {
							 validMove = true;
						 } else {
							 System.out.println("\nInvalid value. Try again!");
						 }
					 } catch (InputMismatchException e) {
					        System.out.println("\nInvalid value. This is not a number. Try again!");
					        scanner.next(); //
					 }
				}
		        Move = newStepX(playArea, moveX);
		    } while (!Move);
			    
		    
		    // Player 1 won?
		    if (horizontal(playArea) || vertical(playArea) || diagonal(playArea)) {
		        System.out.println("\nPlayer 1 (X) win!");
		        gameOver = true;
		        break;
		    }
			    
		    // Is it Draw?
		    if (!PossibleMoves(playArea)) {
		        System.out.println("\nIt's a draw!");
		        gameOver = true;
		        break;
		    }
			    			    
			 
		    // Player 2
		    do {
		    	int moveO = 0;
		    	validMove = false;
		    	while (!validMove) {
					 try {
						 System.out.println("\nPlayer 2 (O), select a field: 1-9?");
						 moveO = scanner.nextInt();
						 
						 
						 if (moveO >= 1 && moveO <=9) {
							 validMove = true;
						 } else {
							 System.out.println("\nInvalid value. Try again!");
						 }
					 } catch (InputMismatchException e) {
					        System.out.println("\nInvalid value. This is not a number. Try again!");
					        scanner.next(); //
					 }
		    	}
		        Move = newStepO(playArea, moveO);
		    } while (!Move);

		    
		    // Player 2 won?
		    if (horizontal(playArea) || vertical(playArea) || diagonal(playArea)) {
		        System.out.println("\nPlayer 2 (O) win!");
		        gameOver = true;
		        break;
		    }
			    
			    // Is it Draw?
		    if (!PossibleMoves(playArea)) {
		        System.out.println("\nIt's a draw!");
		        gameOver = true;
		        break;
		    }
		} while (!gameOver);
	}	// TwoPlayers


	public static void OnePlayer(String[] playArea) {
		boolean gameOver = false;
		boolean validMove = false;
		boolean Move;
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		
		
		do {    
		    // Player 1
			do {
				validMove = false;
		    	int moveX = 0;
		    	while (!validMove) {
					 try {
						 System.out.println("\nPlayer 1 (X), select a field: 1-9?");
						 moveX = scanner.nextInt();
						 
						 
						 if (moveX >= 1 && moveX <=9) {
							 validMove = true;
						 } else {
							 System.out.println("\nInvalid value. Try again!");
						 }
					 } catch (InputMismatchException e) {
					        System.out.println("\nInvalid value. This is not a number. Try again!");
					        scanner.next(); //
					 }
				}
		        Move = newStepX(playArea, moveX);
		    } while (!Move);
			
			
		    // Player 1 won?
		    if (horizontal(playArea) || vertical(playArea) || diagonal(playArea)) {
		        System.out.println("\nPlayer 1 (X) win!");
		        gameOver = true;
		        break;
		    }
		    
			    
		    // Is it Draw?
		    if (!PossibleMoves(playArea)) {
		        System.out.println("\nIt's a draw!");
		        gameOver = true;
		        break;
		    }
			    			    
			 
		    // Player 2
		    System.out.println("\nComputer's move:");
		    do {
		        int moveO = random.nextInt(9) + 1;
		        validMove = compStepO(playArea, moveO);
		    } while (!validMove);
		    
		    // Player 2 won?
		    if (horizontal(playArea) || vertical(playArea) || diagonal(playArea)) {
		        System.out.println("\nPlayer 2 (O) win!");
		        gameOver = true;
		        break;
		    }
			    
			    // Is it Draw?
		    if (!PossibleMoves(playArea)) {
		        System.out.println("\nIt's a draw!");
		        gameOver = true;
		        break;
		    }
		} while (!gameOver);
	}	// OnePlayer
	

	public static void printArray(String[] playArea) {
	    System.out.println(" ----------- ");
	    for (int i = 0; i < 9; i += 3) {
	        System.out.print("| ");
	        System.out.print(playArea[i] + " | ");
	        System.out.print(playArea[i+1] + " | ");
	        System.out.print(playArea[i+2] + " |");
	        System.out.println("\n ----------- ");
	    }
	}
	
	
	public static boolean newStepX(String[] newArray, int field) {				
		if (!newArray[field-1].equals("*")) {
			System.out.println("\nThis field is already taken! Player 1, select another field: 1-9?");
			return false;
		}
		
		newArray[field-1] = "X";	
		printArray(newArray);
		return true;
	} // newStepX
	
	
	public static boolean newStepO(String[] newArray, int field) {					
		if (!newArray[field-1].equals("*")) {
			System.out.println("\nThis field is already taken! Player 2, select another field: 1-9?");
			return false;
		}
		
		newArray[field-1] = "O";	
		printArray(newArray);
		return true;
	} // newStep0
	
	
	public static boolean compStepO(String[] newArray, int field) {		
		if ((field<1)||(field>9)) {
	        return false;
	    }		
		
		if (!newArray[field-1].equals("*")) {
			return false;
		}
		
		newArray[field-1] = "O";	
		printArray(newArray);
		return true;
	} // compStep0
	
	
	public static boolean PossibleMoves (String[] newArray) {		
		for (int i = 0; i < newArray.length; i++) { 
	        if  (newArray[i].equals("*")) {    
	        	return true;
	        } // if    		
		} // for   
		
		return false;	
	} // isFreeMove
	
	
	public static boolean horizontal (String[] newArray) {
		boolean win = false;
		
		for (int i = 0; i < newArray.length; i++) { 
	        if  (
	        	((newArray[0].equals("X")) && (newArray[1].equals("X")) && newArray[2].equals("X")) 
	        	||
	        	((newArray[3].equals("X")) && (newArray[4].equals("X")) && newArray[5].equals("X"))
	        	||
	        	((newArray[6].equals("X")) && (newArray[7].equals("X")) && newArray[8].equals("X"))
	        	||
	        	((newArray[0].equals("O")) && (newArray[1].equals("O")) && newArray[2].equals("O")) 
	        	||
	        	((newArray[3].equals("O")) && (newArray[4].equals("O")) && newArray[5].equals("O"))
	        	||
	        	((newArray[6].equals("O")) && (newArray[7].equals("O")) && newArray[8].equals("O"))
	        	){
	    			win = true;
	        } // if
		} // for   
		
		return win;
	}	// horizontal
	
	
	public static boolean vertical (String[] newArray) {
		boolean win = false;
		
		for (int i = 0; i < newArray.length; i++) {
	        if  (
	        	((newArray[0].equals("X")) && (newArray[3].equals("X")) && newArray[6].equals("X")) 
	        	||
	       		((newArray[1].equals("X")) && (newArray[4].equals("X")) && newArray[7].equals("X"))
	       		||
	   			((newArray[2].equals("X")) && (newArray[5].equals("X")) && newArray[8].equals("X"))
	   			||
        		((newArray[0].equals("O")) && (newArray[3].equals("O")) && newArray[6].equals("O"))         			
        		||
	        	((newArray[1].equals("O")) && (newArray[4].equals("O")) && newArray[7].equals("O"))
	        	||
	        	((newArray[2].equals("O")) && (newArray[5].equals("O")) && newArray[8].equals("O"))
	        	){
	    			win = true;
	    		} // if
		} // for   		
		return win;
	}	// vertical
	
	
	public static boolean diagonal (String[] newArray) {
		boolean win = false;
		
		for (int i = 0; i < newArray.length; i++) {
	        if  (
	        	((newArray[0].equals("X")) && (newArray[4].equals("X")) && newArray[8].equals("X")) 
	        	||
	        	((newArray[2].equals("X")) && (newArray[4].equals("X")) && newArray[6].equals("X"))	        		
	        	||
	        	((newArray[0].equals("O")) && (newArray[4].equals("O")) && newArray[8].equals("O")) 
	        	||
	        	((newArray[2].equals("O")) && (newArray[4].equals("O")) && newArray[6].equals("O"))
	        	){
	    			win = true;
	    		} // if
	        } // for   
		
		return win;
	}	// diagonal
	
	
	
} // Game_XO
