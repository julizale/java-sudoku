package com.sudoku;

import com.sudoku.exception.InvalidValueException;
import com.sudoku.exception.OccupiedFieldException;

public class SudokuGame {

    private final SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public void placeNumberOnTheBoard(UserResponse input) throws OccupiedFieldException, InvalidValueException {

        SudokuElement element = board.getSudokuRowList().get(input.getRow()-1)
                .getSudokuElementList().get(input.getColumn()-1);
        if (!element.isEmpty()) {
            throw new OccupiedFieldException();
        } else if (rowAlreadyContainsValue(input) || columnAlreadyContainsValue(input) || sectionAlreadyContainsValue(input) ) {
            throw new InvalidValueException();
        } else {
            element.setValue(input.getValue());
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

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuElement element = board.getSudokuRowList().get(row).getSudokuElementList().get(col);
                if (element.isEmpty()) {
                    for (int value = 1; value <= 9; value++) {
                        if (isValid(row, col, value)) {
                            System.out.println(board);
                            element.setValue(value);
                            if (solve()) {
                                return true;
                            } else {
                                element.setValue(-1);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int number) {
        UserResponse rep = new UserResponse(col + 1, row + 1, number, UserResponseStatus.COORDINATES_VALUE);
        return (!rowAlreadyContainsValue(rep) && !columnAlreadyContainsValue(rep) && !sectionAlreadyContainsValue(rep));
    }
}
