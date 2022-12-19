package com.example.sudoku;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.sudoku.Sudoku.GridSize;

public class SolveSudoku {

    private static boolean isValidPlacement(int[][] board, int row, int column, int number,RowColumnBox result){

        ExecutorService exService = Executors.newFixedThreadPool(2);

        Runnable rowThread = new NumberInRow(board,row,number,result);
        exService.execute(rowThread);

        Runnable columnThread = new NumberInColumn(board,column,number,result);
        exService.execute(columnThread);

        Runnable boxThread = new NumberInBox(board,row,column,number,result);
        exService.execute(boxThread);

        exService.shutdown();

        while (!exService.isTerminated()){ // if executor is still running, it will stuck here until finishing

        }

        if(result.isRow() || result.isColumn() || result.isBox()) // Number exists in row or column or box
            return false; // InValid Placement
        else
            return true; // Valid Placement

    }


    public boolean solve(int[][] board, RowColumnBox result){
        for (int row = 0; row < GridSize; row++){
            for(int column = 0; column < GridSize; column++){
                if(board[row][column] == 0){
                    for(int number = 1; number <= GridSize; number++){
                        if(isValidPlacement(board, row, column, number, result)) {
                            board[row][column] = number;
                            if (solve(board, result))
                                return true; // Solved Successfully
                            else
                                board[row][column] = 0;
                        }

                    }
                    return false; // I can't solve that board
                }

            }
        }
        return true;
    }
}
