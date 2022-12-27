package com.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private final List<SudokuRow> sudokuRowList;

    public SudokuBoard() {
        this.sudokuRowList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            sudokuRowList.add(new SudokuRow());
        }
    }

    public List<SudokuRow> getSudokuRowList() {
        return sudokuRowList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(1600);
        stringBuilder.append("""
                    1   2   3   4   5   6   7   8   9
                  -------------------------------------
                """);
        for (int i = 1; i <= sudokuRowList.size(); i++) {
            stringBuilder.append(i).append(" |");
            for (SudokuElement sudokuElement: sudokuRowList.get(i-1).getSudokuElementList()) {
                stringBuilder.append(sudokuElement.toString());
                stringBuilder.append("|");
            }
            stringBuilder.append("\n  -------------------------------------\n");
        }
        return stringBuilder.toString();
    }
}
