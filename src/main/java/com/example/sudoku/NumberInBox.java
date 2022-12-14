package com.example.sudoku;

import javafx.scene.control.Button;

public class NumberInBox implements Runnable{
    int[][] board;
    int row;
    int column;
    int number;
    RowColumnBox result;

    public NumberInBox(int[][] board, int row, int column, int number, RowColumnBox result) {
        this.board = board;
        this.row = row;
        this.column = column;
        this.number = number;
        this.result = result;
    }

    private static boolean isNumberInBox(int[][] board, int row, int column, int number){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number)
                    return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        if(isNumberInBox(board,row,column,number))
            result.setBox(true);
        else
            result.setBox(false);
    }
}
