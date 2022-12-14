package com.example.sudoku;

import javafx.scene.control.Button;

import static com.example.sudoku.Sudoku.GridSize;

public class NumberInColumn implements Runnable{

    int[][] board;
    int column;
    int number;
    RowColumnBox result;

    public NumberInColumn(int[][] board, int column, int number, RowColumnBox result) {
        this.board = board;
        this.column = column;
        this.number = number;
        this.result = result;
    }

    private static boolean isNumberInColumn(int[][] board, int column, int number){
        for (int row = 0; row < GridSize; row++){
            if(board[row][column] == number)
                return true;
        }
        return false;
    }

    @Override
    public void run() {
        if(isNumberInColumn(board,column,number))
            result.setColumn(true);
        else
            result.setColumn(false);
    }
}
