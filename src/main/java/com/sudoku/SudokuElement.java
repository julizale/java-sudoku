package com.sudoku;

public class SudokuElement {

    public static final int EMPTY = -1;
    private int value = EMPTY;

    public SudokuElement() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) throws IllegalArgumentException {
            this.value = value;
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "   " : " " + value + " ";
    }

    public boolean isEmpty() {
        return value == EMPTY;
    }
}
