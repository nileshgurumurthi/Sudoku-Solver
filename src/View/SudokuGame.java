package View;

import Model.Sudoku;
import Model.SudokuSet;
import Model.SudokuSolve;

import java.util.Scanner;

public class SudokuGame {

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        System.out.println("Welcome to the Sudoku Solver. Please choose an option below");
        System.out.println("(a) Play Sudoku");
        System.out.println("(b) Solve Sudoku");
        System.out.print("Selected Option: ");
        Scanner sc = new Scanner(System.in);
        String chosenOption = sc.next();
        if (chosenOption.charAt(0) == 'a' && chosenOption.length() == 1){
            chooseLevel();
        }
        else if (chosenOption.charAt(0) == 'b' && chosenOption.length() == 1){
            int [][] board = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
            solveSudokuBoard(board);
        }
        else{
            System.out.println("Invalid Input. Please try again");
            System.out.println("");
            menu();
        }

    }

    public static void solveSudokuBoard(int[][] board){
        if (takeBoardInput(board) && SudokuSolve.solveBoard(board)){
            System.out.println("\nThe solved board is shown below");
            printSudokuBoard(board);
        }
        else{
            System.out.println("Invalid Sudoku Board");
            board = new int[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
            solveSudokuBoard(board);
        }
    }

    public static boolean takeBoardInput(int[][] board){
        System.out.println("\nPlease enter Sudoku Board to solve. Use '-' for empty spaces");
        boolean input = true;
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            Scanner scRow = new Scanner(System.in);
            String currentRow = scRow.nextLine();
            Scanner scCol = new Scanner(currentRow);
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                try{
                    String elem = scCol.next();
                    if (elem.charAt(0) == '-' && elem.length() == 1){
                        board[row][column] = 0;
                    }
                    else{
                        int newElem = Integer.parseInt(elem);
                        board[row][column] = newElem;
                    }

                }
                catch(Exception e){
                    input = false;
                }
            }
        }
        return input;
    }

    public static void chooseLevel(){
        System.out.println("\nPlease select a level");
        System.out.println("(a) Easy");
        System.out.println("(b) Intermediate");
        System.out.println("(c) Hard");
        System.out.print("Selected Level: ");
        Scanner sc = new Scanner(System.in);
        String chosenOption = sc.next();
        if (chosenOption.charAt(0) == 'a'){
            SudokuSet.setLevel(1);
            startSudoku();
        }
        else if (chosenOption.charAt(0) == 'b'){
            SudokuSet.setLevel(2);
            startSudoku();
        }
        else if (chosenOption.charAt(0) == 'c'){
            SudokuSet.setLevel(3);
            startSudoku();
        }
        else{
            System.out.println("Invalid Input. Please try again");
            System.out.println("");
            chooseLevel();
        }

    }

    public static void startSudoku(){
        SudokuSet.setBoard();
        System.out.println("\nYour Board is as follows:");
        printSudokuBoard(SudokuSet.getBoard());
        enterSudoku(1);
    }

    public static void enterSudoku(int attempt){
        if (attempt == 1){
            System.out.println("You have three attempts to solve the board");
        }
        else if (attempt == 2){
            System.out.println("You have two attempts to solve the board");
        }
        else if (attempt == 3){
            System.out.println("You have one attempts to solve the board");
        }
        else{
            System.out.println("You have no attempts to solve the board");
            System.out.println("");
            System.out.println("The correct board is shown below");
            printSudokuBoard(SudokuSet.getOriginalBoard());
            System.out.println("");
            menu();
        }

        if (attempt < 4){
            System.out.println("Enter Sudoku Board below");
            if (playSudoku()){
                System.out.println("You have solved the Sudoku correctly!");
            }
            else{
                System.out.println("Invalid Input or Incorrect Number. Please try again");
                System.out.println("\nYour Board is as follows");
                printSudokuBoard(SudokuSet.getBoard());
                enterSudoku(attempt + 1);
            }
        }

    }

    public static void printSudokuBoard(int[][] board){
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                if (board[row][column] == 0){
                    System.out.print("-" + " ");
                }
                else{
                    System.out.print(board[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }

    public static boolean playSudoku(){
        int[][] board = SudokuSet.getOriginalBoard();
        for (int row = 0; row < Sudoku.GRID_SIZE; row++){
            Scanner scRow = new Scanner(System.in);
            String currentRow = scRow.nextLine();
            Scanner scCol = new Scanner(currentRow);
            for (int column = 0; column < Sudoku.GRID_SIZE; column++){
                try{
                    int elem = scCol.nextInt();
                    if (board[row][column] != elem){
                        return false;
                    }
                }
                catch(Exception e){
                    return false;
                }
            }
        }
        return true;
    }



}
