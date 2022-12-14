package com.example.sudoku;

import javafx.scene.control.Button;

public class Sudoku {
    public static int GridSize = 9;
    int[][] board;
    Sudoku(){

    }
    public Sudoku(int gridSize,int[][] board){
        this.GridSize = gridSize;
        this.board = board;
    }

    public boolean solveSudoku(int[][] board, RowColumnBox result){
        return new SolveSudoku().solve(board, result);
    }

    public boolean checkSolution(int[][] board, Button result){
        return new CheckSolution().check(board, result);
    }

}
