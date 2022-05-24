/**
 * Connect4
 * A functional game of two player Connect 4
 * Nikhail Mann
 */
import java.util.Scanner;

public class ConnectFour {
    static int countmoves = 0;
    
    /**
     * createBoard
     * creates the board and updates it (2D array)
     * parameter (N/A)
     * return board
     */
    public static String[][] createBoard() {
        String[][] board = new String[7][15];
        
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                if (j%2 == 0) {
                    board[i][j] = "|";
                } else { 
                    board[i][j] = " "; 
               }
            
            if (i==6) { 
                board[i][j] = "-";
            }
        }
      }
      return board;
    }

    /**
     * printBoard
     * prints the board into the prompt 
     * parameter (String[][] board)
     * return void (doesn't return anything, just prints it)
     */
    public static void printBoard(String[][] board){
        
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        
    }
    
    /**
     * player1Turn
     * Allows player 1 to pick what column to drop in, drops the "X" token in (updates board)
     * parameter (String[][] board)
     * return void 
     */
    public static void player1Turn(String[][] board) {
        System.out.println("|0|1|2|3|4|5|6|");
        System.out.println("Player 1- Pick a column to drop in. (0-6): ");
        Scanner scan = new Scanner(System.in);
        
        int c = 2*scan.nextInt()+1;
        for (int i = 5; i>=0;i--){
            if (board[i][c] == " ") {
                System.out.println("\f");
                board[i][c] = "X";
                break;
            }
            if (i == 0 && board[i][c] != " ") {
                System.out.println("\f");
                System.out.println("That Column is full!");
                countmoves--;
                break;
            }
               System.out.println("\f");
        }
        
    }
    
    /**
     * player2Turn
     * Allows player 2 to pick what column to drop in, drops the "O" token in (updates board)
     * parameter (String[][] board)
     * return void 
     */
    public static void player2Turn(String[][] board) {
        System.out.println("|0|1|2|3|4|5|6|");
        System.out.println("Player 2- Pick a column to drop in. (0-6): ");
        Scanner scan = new Scanner(System.in);
        
        int c = 2*scan.nextInt()+1;
        for (int i = 5; i>=0;i--){
            if (board[i][c] == " ") {
                System.out.println("\f");
                board[i][c] = "O";
                break;
            }
            if (i == 0 && board[i][c] != " ") {
                System.out.println("\f");
                System.out.println("That Column is full!");
                countmoves--;
                break;
            }
            System.out.println("\f");
        }
        
    }
    
    /**
     * WinChecker
     * Analyzes the board after every turn to see if there is a winner (horizontal, vertical, diagonal)
     * parameter (String[][] board)
     * return null if no winner, board[i][j] (the token of the winner) if there is a winner 
     */
    public static String WinChecker(String[][] board) {
        //horizontal
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j+=2) {
                if (board[i][j+1] != " " && board[i][j+1] == board[i][j+3] 
                && board[i][j+3] == board[i][j+5] && board[i][j+5] == board[i][j+7]) {
                    return board[i][j+1];
                }
            }
        }
        //vertical
        // i switched J and I here for debugging and realized it wasnt the problem (too lazy to change it back)
        for (int i = 1; i < 15; i+=2){
            for (int j=0; j<3;j++){
                if (board[j][i]!=" " && board[j+1][i]!=" " && board[j+2][i]!=" " &&
                board[j+3][i]!=" " && board[j][i] == board[j+1][i] 
                && board[j+1][i] == board[j+2][i] && board[j+2][i] == board[j+3][i]){
                    return board[j][i];
                }
            }
        }
        //diagonal top left to bottom right
        // i got a weird error in this loop when i originally had "j < 13" I tr
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 9; j+=2) {
                if (board [i][j] != " " && board[i+1][j+2] == board[i][j] &&
                board [i+2][j+4] == board[i][j] && board[i+3][j+6] == board[i][j]) {
                    return board[i][j];
            }
        }
        }
        //diagonal bottom left to top right
        for (int i = 0; i < 3; i++) {
            for (int j = 7; j < 15; j+=2) {
                if (board [i][j] != " " && board[i+1][j-2] == board[i][j] &&
                board [i+2][j-4] == board[i][j] && board[i+3][j-6] == board[i][j]) {
                    return board[i][j];
            }
        }
        }
        return null;
        
    }
    
    /**
     * main
     * Incorporates all methods together in a loop/ introductory statements
     * parameter (String[] args)
     * return void 
     */
    public static void main (String[] args) {
        System.out.println("Welcome to Connect 4! Get Ready to Play!");
        String [][] board = createBoard();
        boolean gameLoop = true;
        printBoard(board);
        while(gameLoop) {
            if (countmoves % 2 == 0) {
                player1Turn(board);
            } else {
                player2Turn(board);
            }
            if (WinChecker(board) != null) {
                if (WinChecker(board) == "X") {
                    System.out.println("Player One wins!!!");
                    gameLoop = false;
                } else if (WinChecker(board) == "O") {
                    System.out.println("Player two wins!!!");
                    gameLoop = false;
                }
                
            }
            countmoves++;
            printBoard(board);
           
            if (countmoves == 42) {
                System.out.println("All spots have been played, the game is a draw!");
                gameLoop = false;
            }
            
        }
    }
}
