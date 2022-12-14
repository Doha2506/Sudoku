package com.example.sudoku;

import javafx.scene.control.Button;

import static com.example.sudoku.Sudoku.GridSize;

public class NumberInRow implements Runnable{

    int[][] board;
    int row;
    int number;
    RowColumnBox result;

    public NumberInRow(int[][] board, int row, int number,RowColumnBox result) {
        this.board = board;
        this.row = row;
        this.number = number;
        this.result = result;
    }

    private static boolean isNumberInRow(int[][] board, int row, int number){
        for (int column = 0; column < GridSize; column++){
            if(board[row][column] == number)
                return true;
        }
        return false;
    }

    @Override
    public void run() {
        if(isNumberInRow(board,row,number))
            result.setRow(true);
        else
            result.setRow(false);

    }
}
