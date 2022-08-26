package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SudokuSet {

    private static int[][] orignalBoard = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
    private static int[][] gameBoard;
    private static Random random = new Random();
    private static HashMap<Integer,Level> levels = new HashMap<>();
    private static int currentLevel;

    static{
        levels.put(1,new Level("Easy",60,70));
        levels.put(2,new Level("Intermediate",40,50));
        levels.put(3,new Level("Hard",20,30));
        currentLevel = 1;
    }

    public static void setLevel(int level){
        currentLevel = level;
    }

    private static int findMinimumOptions(int[][] frequency){
        int minimum = Integer.MAX_VALUE;
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                if (orignalBoard[row][column] == 0){
                    int temp = 0;
                    for (int numberToTry = 1; numberToTry <= Sudoku.GRID_SIZE; numberToTry++){
                        if (Sudoku.isValidPlacement(orignalBoard,numberToTry,row,column)){
                            temp++;
                        }
                    }
                    frequency[row][column] = temp;
                    if (temp < minimum){
                        minimum = temp;
                    }
                }
            }
        }
        return minimum;
    }

    private static ArrayList<Position> getMinimumOptions(int minimum, int[][] frequency){
        ArrayList<Position> boardPositions = new ArrayList<>();
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                if (frequency[row][column] == minimum){
                    boardPositions.add(new Position(row,column));
                }
            }
        }
        return boardPositions;
    }

    private static void boardReset(){
        orignalBoard = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
    }

    public static void setBoard(){
        boardReset();
        findBoard();
        gameBoard = getGameBoard();
    }

    public static int[][] getBoard(){
        return gameBoard;
    }

    public static int[][] getOriginalBoard(){
        return orignalBoard;
    }

    private static int[][] getGameBoard(){
        int [][] gameBoard = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
        Level level = levels.get(currentLevel);
        int range = ((level.getUpperBound()) - level.getLowerBound()) + 1;
        int nrClues = random.nextInt(range) + level.getLowerBound();
        ArrayList<Position> boardPositions = getAllPositions();
        for (int i = 0; i < nrClues; i++){
            int index = random.nextInt(boardPositions.size());
            int row = boardPositions.get(index).getRow();
            int column = boardPositions.get(index).getColumn();
            gameBoard[row][column] = orignalBoard[row][column];
            boardPositions.remove(index);
        }
        return gameBoard;
    }

    private static ArrayList getAllPositions(){
        ArrayList<Position> boardPositions = new ArrayList<>();
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                boardPositions.add(new Position(row,column));
            }
        }
        return boardPositions;
    }

    private static boolean findBoard(){
        int [][] frequency = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
        int minimum = findMinimumOptions(frequency);
        if (minimum != Integer.MAX_VALUE){
            if (minimum == 0){
                return false;
            }
            ArrayList<Position> boardPositions = getMinimumOptions(minimum,frequency);
            int index = random.nextInt(boardPositions.size());
            int row = boardPositions.get(index).getRow();
            int column = boardPositions.get(index).getColumn();
            for (int numberToTry = 1; numberToTry <= Sudoku.GRID_SIZE; numberToTry++){
                if (Sudoku.isValidPlacement(orignalBoard,numberToTry,row,column)){
                    orignalBoard[row][column] = numberToTry;
                    if (findBoard()){
                        return true;
                    }
                    orignalBoard[row][column] = 0;

                }
            }
            return false;
        }
        return true;
    }



}
