package com.sudoku;

import com.sudoku.exception.OccupiedFieldException;
import com.sudoku.exception.ValueOutOfBoundsException;

public class SudokuGame {
    public SudokuBoard getBoard() {
        return board;
    }

    private final SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public boolean resolveSudoku() {

        //temporary
        return true;
    }

    public void placeNumberOnTheBoard(UserResponse input) throws OccupiedFieldException, ValueOutOfBoundsException {

        SudokuElement element = board.getSudokuRowList().get(input.getRow()-1)
                .getSudokuElementList().get(input.getColumn()-1);
        if (!element.isEmpty()) {
            throw new OccupiedFieldException();
        } else if (rowAlreadyContainsValue(input) || columnAlreadyContainsValue(input) || sectionAlreadyContainsValue(input) ) {
            System.out.println("Wrong move! Row, column, or section already contains specified value.");
        } else {
            element.setValue(input.getValue());
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

        int rowLowBound = switch (input.getRow()) {
            case 1,2,3 -> 0;
            case 4,5,6 -> 3;
            case 7,8,9 -> 6;
            default -> -1;
        };
        int rowHiBound = switch (input.getRow()) {
            case 1,2,3 -> 3;
            case 4,5,6 -> 6;
            case 7,8,9 -> 9;
            default -> -1;
        };
        int columnLowBound = switch (input.getColumn()) {
            case 1,2,3 -> 0;
            case 4,5,6 -> 3;
            case 7,8,9 -> 6;
            default -> -1;
        };
        int columnHiBound = switch (input.getColumn()) {
            case 1,2,3 -> 3;
            case 4,5,6 -> 6;
            case 7,8,9 -> 9;
            default -> -1;
        };

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

        int rowLowBound = switch (input.getRow()) {
            case 1,2,3 -> 0;
            case 4,5,6 -> 3;
            case 7,8,9 -> 6;
            default -> -1;
        };
        int rowHiBound = switch (input.getRow()) {
            case 1,2,3 -> 3;
            case 4,5,6 -> 6;
            case 7,8,9 -> 9;
            default -> -1;
        };
        int columnLowBound = switch (input.getColumn()) {
            case 1,2,3 -> 0;
            case 4,5,6 -> 3;
            case 7,8,9 -> 6;
            default -> -1;
        };
        int columnHiBound = switch (input.getColumn()) {
            case 1,2,3 -> 3;
            case 4,5,6 -> 6;
            case 7,8,9 -> 9;
            default -> -1;
        };

        for (int row = rowLowBound; row < rowHiBound; row++) {
            for (int column = columnLowBound; column < columnHiBound; column++) {
                board.getSudokuRowList().get(row).getSudokuElementList().get(column).getPossibleValues().removeIf(v -> v == input.getValue());
            }
        }
    }
}
