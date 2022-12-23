package com.sudoku;

public class BackTrackDataObject {

    private final SudokuBoard sudokuBoard;
    private final UserResponse wildGuess;

    public BackTrackDataObject(final SudokuBoard sudokuBoard, final UserResponse wildGuess) {
        this.sudokuBoard = sudokuBoard.deepCopy();
        this.wildGuess = wildGuess;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public UserResponse getWildGuess() {
        return wildGuess;
    }
}
