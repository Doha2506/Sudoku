package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SudokuController {

    private final int size = 9;
    @FXML
    private GridPane board;
    @FXML
    public Button result;

    Font font = Font.font("Comic Sans MS", 20.0F);

    @FXML
    void createGame(ActionEvent event) {
        if(board.getChildren().isEmpty()){
            setBoard();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You Already Initialized The Board !! ");
            alert.showAndWait();
        }
    }

    int[][] intialBoard =
            {
                    {7,0,2,0,5,8,6,1,0},
                    {0,5,6,1,7,3,8,2,4},
                    {1,8,4,6,2,9,5,3,7},

                    {0,7,1,5,6,4,3,9,2},
                    {6,0,3,8,9,2,7,5,1},
                    {2,0,5,3,1,7,4,6,8},

                    {3,2,9,7,8,6,1,0,5},
                    {4,1,8,2,3,5,9,7,6},
                    {5,6,7,9,4,1,2,8,3}
            };

    private void setBoard(){
        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                if(intialBoard[row][column] == 0){
                    TextField textField = new TextField("0");
                    textField.fontProperty().set(font);
                    textField.setAlignment(Pos.CENTER);
                    if(!setBoxColor( row, column, textField))
                        textField.setStyle("-fx-background-color: #fff; ");
                    board.add(textField, column, row);
                }else {
                    String value = Integer.toString(intialBoard[row][column]);
                    TextField text = new TextField(value);
                    text.fontProperty().set(font);
                    text.setAlignment(Pos.CENTER);
                    text.setDisable(true);
                    if(!setBoxColor( row, column, text))
                        text.setStyle("-fx-background-color: #fff; ");
                    board.add(text, column, row);
                }
            }
        }
    }

    private boolean setBoxColor(int row, int column, TextField tx){
        if( ((row >= 0 && row < 3) && (column >= 0 && column < 3)) ||
                ((row >= 6 && row < 9) && (column >= 0 && column < 3)) ||
                ((row >= 3 && row < 6)  && (column >= 3 && column < 6)) ||
                ((row >= 0 && row < 3) && (column >= 6 && column < 9)) ||
                ((row >= 6 && row < 9) && (column >= 6 && column < 9))
        )
        {
            tx.setStyle("-fx-background-color: #b2d7ec ; ");
            return true;
        }
        return false;
    }

    int[][] checkBoard = new int[size][size];

    private void setCheckBoard(){
        int row =0,column=0;
        for (Node currentNode : board.getChildren()){
            if (currentNode instanceof TextField){
                if(row==size){
                    row=0;column++;
                }
                TextField tx = (TextField) currentNode;
                if(tx.getText().equals(""))
                    tx.setText("0");
                checkBoard[row++][column] = Integer.parseInt(tx.getText());
            }
        }
        printBoard(checkBoard);
    }
    private static void printBoard(int[][] board){
        for (int row = 0; row < 9; row++){
            if(row % 3 == 0 && row != 0)
                System.out.println("---------------------");
            for(int column = 0; column < 9; column++){
                if(column % 3 == 0 && column != 0 )
                    System.out.print(" | ");
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    @FXML
    void checkSolution(ActionEvent event) {
        if(board.getChildren().isEmpty()) {
            result.setStyle("-fx-background-color: #ff0000;");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You Must Initialize The Board !! Please Click Create Button !!");
            alert.showAndWait();
            result.setStyle("-fx-background-color: #fff;");
        }else {
            setCheckBoard();
            if (new Sudoku().checkSolution(checkBoard, result)) {
                System.out.println(true);
                result.setStyle("-fx-background-color: #24b133; ");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Congratulations .. You Solve It Successfully  :) ");
                alert.showAndWait();
            } else {
                System.out.println(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Oops You made a mistake !! Please Try Again  :( ");
                alert.showAndWait();
                result.setStyle("-fx-background-color: #fff; ");
            }
        }
    }

    @FXML
    void solveGame(ActionEvent event) {
        if(board.getChildren().isEmpty()){
            result.setStyle("-fx-background-color: #ff0000;");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You Must Initialize The Board !! Please Click Create Button !!");
            alert.showAndWait();
            result.setStyle("-fx-background-color: #fff;");
        }else {
            if (new Sudoku().solveSudoku(intialBoard, new RowColumnBox())) {
                printSolutionOnBoard(intialBoard);
            } else {
                System.out.println(false); // can't solve the board
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Can't Solve this board :( ");
                alert.showAndWait();
            }
        }
    }

    void printSolutionOnBoard(int[][] solvedBoard){
        board.getChildren().clear();
        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                String value = Integer.toString(solvedBoard[row][column]);
                TextField text = new TextField(value);
                text.fontProperty().set(font);
                text.setAlignment(Pos.CENTER);
                text.setDisable(true);
                text.setStyle("-fx-background-color: #b2d7ec ; ");
                board.add(text, column, row);
            }
        }
    }


}
