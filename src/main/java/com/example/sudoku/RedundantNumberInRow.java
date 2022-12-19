package com.example.sudoku;

import javafx.scene.control.Button;

import static com.example.sudoku.Sudoku.GridSize;

public class RedundantNumberInRow implements Runnable{
    int[][] board;
    int row;
    int number;
    Button result;

    public RedundantNumberInRow(int[][] board, int row, int number, Button result) {
        this.board = board;
        this.row = row;
        this.number = number;
        this.result = result;
    }


    private static boolean isNumberInRowRedundant(int[][] board, int row, int number){
        int result = 0;
        for (int column = 0; column < GridSize; column++){
            if(board[row][column] == number)
                result++;

            if(result > 1)
                return true; // Means that the number is redundant more than once
        }

        return false;
    }


    @Override
    public void run() {
        if(isNumberInRowRedundant(board,row,number)){
            result.setStyle("-fx-background-color: #ff0000;");
        }
    }
}
