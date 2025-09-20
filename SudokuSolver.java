
public class SudokuSolver {
private static final int SIZE = 9; // size of the grid

public static void main(String[] args) {  
    int[][] board = {  
        {5, 3, 0, 0, 7, 0, 0, 0, 0},  
        {6, 0, 0, 1, 9, 5, 0, 0, 0},  
        {0, 9, 8, 0, 0, 0, 0, 6, 0},  
        {8, 0, 0, 0, 6, 0, 0, 0, 3},  
        {4, 0, 0, 8, 0, 3, 0, 0, 1},  
        {7, 0, 0, 0, 2, 0, 0, 0, 6},  
        {0, 6, 0, 0, 0, 0, 2, 8, 0},  
        {0, 0, 0, 4, 1, 9, 0, 0, 5},  
        {0, 0, 0, 0, 8, 0, 0, 7, 9}  
    };  

    if (solveSudoku(board)) {  
        printBoard(board);  
    } else {  
        System.out.println("No solution exists.");  
    }  
}  

// Solve the Sudoku using backtracking  
private static boolean solveSudoku(int[][] board) {  
    for (int row = 0; row < SIZE; row++) {  
        for (int col = 0; col < SIZE; col++) {  
            if (board[row][col] == 0) {  // empty cell  
                for (int num = 1; num <= SIZE; num++) {  
                    if (isSafe(board, row, col, num)) {  
                        board[row][col] = num;  

                        if (solveSudoku(board)) {  
                            return true;  
                        } else {  
                            board[row][col] = 0; // backtrack  
                        }  
                    }  
                }  
                return false; // no number fits here, backtrack  
            }  
        }  
    }  
    return true; // solved  
}  

// Check if placing num in board[row][col] is valid  
private static boolean isSafe(int[][] board, int row, int col, int num) {  
    // Check row and column  
    for (int i = 0; i < SIZE; i++) {  
        if (board[row][i] == num || board[i][col] == num) {  
            return false;  
        }  
    }  

    // Check 3x3 subgrid  
    int boxRowStart = row - row % 3;  
    int boxColStart = col - col % 3;  

    for (int r = boxRowStart; r < boxRowStart + 3; r++) {  
        for (int c = boxColStart; c < boxColStart + 3; c++) {  
            if (board[r][c] == num) {  
                return false;  
            }  
        }  
    }  
    return true;  
}  

// Print the Sudoku board  
private static void printBoard(int[][] board) {  
    for (int row = 0; row < SIZE; row++) {  
        if (row % 3 == 0 && row != 0) {  
            System.out.println("-----------");  
        }  
        for (int col = 0; col < SIZE; col++) {  
            if (col % 3 == 0 && col != 0) {  
                System.out.print("|");  
            }  
            System.out.print(board[row][col]);  
        }  
        System.out.println();  
    }  
}

}