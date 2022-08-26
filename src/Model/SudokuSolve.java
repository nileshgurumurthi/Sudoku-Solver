package Model;

public class SudokuSolve {

    private static boolean isNumberValid(int number){
        if (number >= 0 && number <= 9){
            return true;
        }
        return false;
    }

    public static boolean isSudokuValid(int[][] board){
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                int currentNumber = board[row][column];
                if (!isNumberValid(currentNumber)){
                    return false;
                }
                if (currentNumber != 0){
                    board[row][column] = 0;
                    if (!Sudoku.isValidPlacement(board,currentNumber,row,column)){
                        return false;
                    }
                    board[row][column] = currentNumber;
                }
            }
        }
        return true;
    }

    public static boolean solveBoard(int[][] board){
        if (isSudokuValid(board)){
            return fillBoard(board);
        }
        return false;
    }

    private static boolean fillBoard(int[][] board){
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                if (board[row][column] == 0){
                    for (int numberToTry = 0; numberToTry <= Sudoku.GRID_SIZE; numberToTry++){
                        if (Sudoku.isValidPlacement(board,numberToTry,row,column)){
                            board[row][column] = numberToTry;
                            if (fillBoard(board)){
                                return true;
                            }
                            board[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}
