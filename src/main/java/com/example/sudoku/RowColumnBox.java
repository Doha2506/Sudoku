package com.example.sudoku;

public class RowColumnBox {
    private boolean row = false;
    private boolean column = false;
    private boolean box = false;

    public RowColumnBox(){

    }

    public RowColumnBox(boolean row, boolean column, boolean box) {
        this.row = row;
        this.column = column;
        this.box = box;
    }

    public boolean isRow() {
        return row;
    }

    public boolean isColumn() {
        return column;
    }

    public boolean isBox() {
        return box;
    }

    public void setRow(boolean row) {
        this.row = row;
    }

    public void setColumn(boolean column) {
        this.column = column;
    }

    public void setBox(boolean box) {
        this.box = box;
    }
}
