package com.sudoku;

import com.sudoku.exception.OccupiedFieldException;

public class SudokuRunner {

    public static void main(String[] args) {

        UserInput userInput = new UserInput();
        SudokuGame game = new SudokuGame();

        while (true) {
            System.out.println(game.getBoard());
            UserResponse userResponse = userInput.getInput();
            switch (userResponse.getResponseStatus()) {
                case ENDGAME -> System.exit(0);
                case SUDOKU -> game.resolveSudoku();
                case COORDINATES_VALUE -> {
                    try {
                        game.placeNumberOnTheBoard(userResponse);
                    } catch (OccupiedFieldException e) {
                        System.out.println("The field is occupied, try another.");
                    }
                }
            }
        }

    }
}
