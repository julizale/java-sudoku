package com.sudoku;

import com.sudoku.exception.OccupiedFieldException;

public class SudokuGame {

    private final SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public boolean resolveSudoku() {

        boolean madeChanges = false;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column ++) {
                SudokuElement element = board.getSudokuRowList().get(row).getSudokuElementList().get(column);
                if (element.isEmpty()) {
                    if (element.getPossibleValues().size() == 1) {
                        int value = element.getPossibleValues().get(0);
                        element.setValue(value);
                        UserResponse rep = new UserResponse(column +1,row +1,value,UserResponseStatus.COORDINATES_VALUE);
                        removePossibleValuesFromColumn(rep);
                        removePossibleValuesFromRow(rep);
                        removePossibleValuesFromSection(rep);
                        madeChanges = true;
                    }
                }
            }
        }
        if (madeChanges) resolveSudoku();
        //temporary
        return true;
    }

    public void placeNumberOnTheBoard(UserResponse input) throws OccupiedFieldException {

        SudokuElement element = board.getSudokuRowList().get(input.getRow()-1)
                .getSudokuElementList().get(input.getColumn()-1);
        if (!element.isEmpty()) {
            throw new OccupiedFieldException();
        } else if (rowAlreadyContainsValue(input) || columnAlreadyContainsValue(input) || sectionAlreadyContainsValue(input) ) {
            System.out.println("Wrong move! Row, column, or section already contains specified value.");
        } else {
            element.setValue(input.getValue());
            element.getPossibleValues().clear();
            removePossibleValuesFromColumn(input);
            removePossibleValuesFromRow(input);
            removePossibleValuesFromSection(input);
        }
    }

    public boolean rowAlreadyContainsValue(UserResponse input){
        SudokuRow row = board.getSudokuRowList().get(input.getRow()-1);
        for (SudokuElement element: row.getSudokuElementList()) {
            if (element.getValue() == input.getValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean columnAlreadyContainsValue(UserResponse input) {
        for (SudokuRow row: board.getSudokuRowList()) {
            if (row.getSudokuElementList().get(input.getColumn()-1).getValue() == input.getValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean sectionAlreadyContainsValue(UserResponse input) {

        int rowLowBound = getSectionLowBoundIndex(input.getRow());
        int rowHiBound = getSectionHiBoundIndex(input.getRow());
        int columnLowBound = getSectionLowBoundIndex(input.getColumn());
        int columnHiBound = getSectionHiBoundIndex(input.getColumn());

        for (int row = rowLowBound; row < rowHiBound; row++) {
            for (int column = columnLowBound; column < columnHiBound; column++) {
                if (board.getSudokuRowList().get(row).getSudokuElementList().get(column).getValue() == input.getValue()){
                    return true;
                }
            }
        }
        return false;
    }

    public void removePossibleValuesFromRow(UserResponse input){
        SudokuRow row = board.getSudokuRowList().get(input.getRow()-1);
        for (SudokuElement element: row.getSudokuElementList()) {
            element.getPossibleValues().removeIf(v -> v == input.getValue());
        }
    }

    public void removePossibleValuesFromColumn (UserResponse input) {
        for (SudokuRow row: board.getSudokuRowList()) {
            row.getSudokuElementList().get(input.getColumn()-1).getPossibleValues().removeIf(v -> v == input.getValue());
        }
    }
    public void removePossibleValuesFromSection(UserResponse input) {

        int rowLowBound = getSectionLowBoundIndex(input.getRow());
        int rowHiBound = getSectionHiBoundIndex(input.getRow());
        int columnLowBound = getSectionLowBoundIndex(input.getColumn());
        int columnHiBound = getSectionHiBoundIndex(input.getColumn());

        for (int row = rowLowBound; row < rowHiBound; row++) {
            for (int column = columnLowBound; column < columnHiBound; column++) {
                board.getSudokuRowList().get(row).getSudokuElementList().get(column).getPossibleValues().removeIf(v -> v == input.getValue());
            }
        }
    }

    private int getSectionLowBoundIndex(int i) {
        return switch (i) {
            case 1,2,3 -> 0;
            case 4,5,6 -> 3;
            case 7,8,9 -> 6;
            default -> -1;
        };
    }

    private int getSectionHiBoundIndex(int i) {
        return switch (i) {
            case 1,2,3 -> 3;
            case 4,5,6 -> 6;
            case 7,8,9 -> 9;
            default -> -1;
        };
    }
}
