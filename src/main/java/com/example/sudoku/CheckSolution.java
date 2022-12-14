package com.example.sudoku;

import javafx.scene.control.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.sudoku.Sudoku.GridSize;

public class CheckSolution {

    private static boolean isRedundantNumber(int[][] board, int row, int column, int number, Button result){

        ExecutorService exService = Executors.newFixedThreadPool(2);

        Runnable rowThread = new RedundantNumberInRow(board,row,number,result);
        exService.execute(rowThread);

        Runnable columnThread = new RedundantNumberInColumn(board,column,number,result);
        exService.execute(columnThread);

        Runnable boxThread = new RedundantNumberInBox(board,row,column,number,result);
        exService.execute(boxThread);

        exService.shutdown();

        while (!exService.isTerminated()){ // if executor is still running, it will stuck here until finishing

        }

        if(result.getStyle().equals("-fx-background-color: #fff; "))
            return false;
        else
            return true;

    }

    public boolean check(int[][] board, Button result){
        for (int row = 0; row < GridSize; row++) {
            for (int column = 0; column < GridSize; column++) {
                if(board[row][column] < 1 || board[row][column] > 9){
                    result.setStyle("-fx-background-color: #ff0000; ");
                    return false;
                }else{
                    result.setStyle("-fx-background-color: #fff; ");
                    for(int number = 1; number <= GridSize; number++) {
                        if (isRedundantNumber(board, row, column, number, result)) { // if not redundant number
                            System.out.println("redundant in number " + number +"  in row " + row + " and column "+ column);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
