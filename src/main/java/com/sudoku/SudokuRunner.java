package com.sudoku;

import com.sudoku.exception.InvalidValueException;
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
                case SUDOKU -> {
                    if (game.solve()) {
                        System.out.println(game.getBoard());
                        System.out.println("Sudoku solved!");
                    } else {
                        System.out.println("Unsolvable!");
                    }
                    System.exit(0);
                }
                case COORDINATES_VALUE -> {
                    try {
                        game.placeNumberOnTheBoard(userResponse);
                    } catch (OccupiedFieldException e) {
                        System.out.println("The field is occupied, try another.");
                    } catch (InvalidValueException e) {
                        System.out.println("Wrong move! Row, column, or section already contains specified value.");
                    }
                }
            }
        }
    }
}
