package com.example.sudoku;

import javafx.scene.control.Button;

import static com.example.sudoku.Sudoku.GridSize;

public class RedundantNumberInColumn implements Runnable{

    int[][] board;
    int column;
    int number;
    Button result;

    public RedundantNumberInColumn(int[][] board, int column, int number, Button result) {
        this.board = board;
        this.column = column;
        this.number = number;
        this.result = result;
    }

    private static boolean isNumberInColumnRedundant(int[][] board, int column, int number){
        int result = 0;
        for (int row = 0; row < GridSize; row++){
            if(board[row][column] == number)
                result++;

            if(result > 1)
                return true; // Means that the number is redundant more than once
        }

        return false;
    }

    @Override
    public void run() {
        if(isNumberInColumnRedundant(board,column,number)){
            this.result.setStyle("-fx-background-color: #ff0000; ");
        }
    }
}
