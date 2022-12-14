package com.example.sudoku;

import javafx.scene.control.Button;

public class RedundantNumberInBox implements Runnable{
    int[][] board;
    int row;
    int column;
    int number;
    Button result;

    public RedundantNumberInBox(int[][] board, int row, int column, int number, Button result) {
        this.board = board;
        this.row = row;
        this.column = column;
        this.number = number;
        this.result = result;
    }

    private static boolean isNumberInBoxRedundant(int[][] board, int row, int column, int number){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        int result = 0;
        for (int i = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number)
                    result++;
            }
        }

        if(result > 1)
            return true; // Means that the number is redundant more than once
        else
            return false;
    }

    @Override
    public void run() {
        if(isNumberInBoxRedundant(board,row,column,number)){
            this.result.setStyle("-fx-background-color: #ff0000; ");
        }
    }
}
