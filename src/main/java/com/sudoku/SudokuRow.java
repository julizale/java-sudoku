package com.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private final List<SudokuElement> sudokuElementList;

    public SudokuRow() {
        sudokuElementList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            sudokuElementList.add(new SudokuElement());
        }
    }

    public SudokuRow(final List<SudokuElement> sudokuElementList) {
        this.sudokuElementList = sudokuElementList;
    }

    public List<SudokuElement> getSudokuElementList() {
        return sudokuElementList;
    }

}
