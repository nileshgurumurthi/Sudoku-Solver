package Model;

public class Sudoku {

    public static final int GRID_SIZE = 9;

    private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < GRID_SIZE; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for (int i = 0; i < GRID_SIZE; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        int startingRow = row - (row % 3);
        int startingColumn = column - (column % 3);
        for (int i = startingRow; i < startingRow + 3; i++){
            for (int j = startingColumn; j < startingColumn + 3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !(isNumberInRow(board,number,row)) && !(isNumberInColumn(board,number,column)) &&
                !(isNumberInBox(board,number,row,column));
    }

}
