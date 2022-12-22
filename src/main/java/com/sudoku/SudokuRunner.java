package com.sudoku;

import com.sudoku.exception.OccupiedFieldException;
import com.sudoku.exception.ValueOutOfBoundsException;

public class SudokuRunner {

    public static void main(String[] args) throws ValueOutOfBoundsException {

        UserInput userInput = new UserInput();
        SudokuGame game = new SudokuGame();

        while (true) {
            System.out.println(game.getBoard());
            UserResponse userResponse = userInput.getInput();
            switch (userResponse.getResponseStatus()) {
                case ENDGAME -> System.exit(0);
                case SUDOKU -> System.exit(0);
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
