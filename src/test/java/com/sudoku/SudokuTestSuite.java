package com.sudoku;

import com.sudoku.exception.OccupiedFieldException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTestSuite {

    @Test
    void testSudokuBoardToString() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();

        //When
        String resultBoard = sudokuBoard.toString();
        String expectedBoard = """
                    1   2   3   4   5   6   7   8   9
                  -------------------------------------
                1 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                2 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                3 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                4 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                5 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                6 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                7 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                8 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                9 |   |   |   |   |   |   |   |   |   |
                  -------------------------------------
                """;

        //Then
        assertEquals(expectedBoard, resultBoard);
    }

    @Test
    void testSudokuElementSetValueRemovesFromPossibleValues() {
        //Given
        SudokuElement sudokuElement = new SudokuElement();

        //When
        sudokuElement.setValue(4);
        List<Integer> valuesAfterSet = sudokuElement.getPossibleValues();

        //Then
        assertEquals(8, valuesAfterSet.size());
        assertFalse(valuesAfterSet.contains(4));
    }

    @Test
    void testSudokuGameColumnAlreadyContainsValue() throws OccupiedFieldException {
        //Given
        SudokuGame sudokuGame= new SudokuGame();
        UserResponse userResponse = new UserResponse(3,8,9,UserResponseStatus.COORDINATES_VALUE);
        sudokuGame.placeNumberOnTheBoard(userResponse);

        //When
        UserResponse userResponse1 = new UserResponse(3, 1, 9, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse2 = new UserResponse(3, 9, 9, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse3 = new UserResponse(3, 5, 9, UserResponseStatus.COORDINATES_VALUE);

        UserResponse userResponse4 = new UserResponse(3, 1, 1, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse5 = new UserResponse(3, 9, 4, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse6 = new UserResponse(3, 5, 7, UserResponseStatus.COORDINATES_VALUE);

        //Then
        assertTrue(sudokuGame.columnAlreadyContainsValue(userResponse1));
        assertTrue(sudokuGame.columnAlreadyContainsValue(userResponse2));
        assertTrue(sudokuGame.columnAlreadyContainsValue(userResponse3));

        assertFalse(sudokuGame.columnAlreadyContainsValue(userResponse4));
        assertFalse(sudokuGame.columnAlreadyContainsValue(userResponse5));
        assertFalse(sudokuGame.columnAlreadyContainsValue(userResponse6));
    }

    @Test
    void testSudokuGameRowAlreadyContainsValue() throws OccupiedFieldException {
        //Given
        SudokuGame sudokuGame= new SudokuGame();
        UserResponse userResponse = new UserResponse(3,4,1,UserResponseStatus.COORDINATES_VALUE);
        sudokuGame.placeNumberOnTheBoard(userResponse);

        //When
        UserResponse userResponse1 = new UserResponse(1, 4, 1, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse2 = new UserResponse(6, 4, 1, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse3 = new UserResponse(9, 4, 1, UserResponseStatus.COORDINATES_VALUE);

        UserResponse userResponse4 = new UserResponse(1, 4, 3, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse5 = new UserResponse(6, 4, 5, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse6 = new UserResponse(9, 4, 8, UserResponseStatus.COORDINATES_VALUE);

        //Then
        assertTrue(sudokuGame.rowAlreadyContainsValue(userResponse1));
        assertTrue(sudokuGame.rowAlreadyContainsValue(userResponse2));
        assertTrue(sudokuGame.rowAlreadyContainsValue(userResponse3));

        assertFalse(sudokuGame.rowAlreadyContainsValue(userResponse4));
        assertFalse(sudokuGame.rowAlreadyContainsValue(userResponse5));
        assertFalse(sudokuGame.rowAlreadyContainsValue(userResponse6));
    }

    @Test
    void testSudokuGameSectionAlreadyContainsValue() throws OccupiedFieldException {
        //Given
        SudokuGame sudokuGame= new SudokuGame();
        UserResponse userResponse = new UserResponse(3,4,2,UserResponseStatus.COORDINATES_VALUE);
        sudokuGame.placeNumberOnTheBoard(userResponse);

        //When
        UserResponse userResponse1 = new UserResponse(1, 4, 2, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse2 = new UserResponse(2, 6, 2, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse3 = new UserResponse(3, 5, 2, UserResponseStatus.COORDINATES_VALUE);

        UserResponse userResponse4 = new UserResponse(1, 4, 3, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse5 = new UserResponse(2, 6, 7, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse6 = new UserResponse(2, 2, 2, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse7 = new UserResponse(9, 8, 2, UserResponseStatus.COORDINATES_VALUE);
        UserResponse userResponse8 = new UserResponse(4, 4, 2, UserResponseStatus.COORDINATES_VALUE);

        //Then
        assertTrue(sudokuGame.sectionAlreadyContainsValue(userResponse1));
        assertTrue(sudokuGame.sectionAlreadyContainsValue(userResponse2));
        assertTrue(sudokuGame.sectionAlreadyContainsValue(userResponse3));

        assertFalse(sudokuGame.sectionAlreadyContainsValue(userResponse4));
        assertFalse(sudokuGame.sectionAlreadyContainsValue(userResponse5));
        assertFalse(sudokuGame.sectionAlreadyContainsValue(userResponse6));
        assertFalse(sudokuGame.sectionAlreadyContainsValue(userResponse7));
        assertFalse(sudokuGame.sectionAlreadyContainsValue(userResponse8));
    }

    @Test
    void testSudokuBoardDeepCopy() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.getSudokuRowList().get(3).getSudokuElementList().get(3).getPossibleValues().removeIf(v -> v > 3);

        //When
        SudokuBoard sudokuBoardDeepCopied = sudokuBoard.deepCopy();
        sudokuBoard.getSudokuRowList().get(3).getSudokuElementList().get(3).getPossibleValues().removeIf(v -> v > 2);
        sudokuBoard.getSudokuRowList().get(3).getSudokuElementList().get(5).setValue(4);

        //Then
        assertEquals(2, sudokuBoard.getSudokuRowList().get(3).getSudokuElementList().get(3).getPossibleValues().size());
        assertEquals(3, sudokuBoardDeepCopied.getSudokuRowList().get(3).getSudokuElementList().get(3).getPossibleValues().size());
        assertEquals(4, sudokuBoard.getSudokuRowList().get(3).getSudokuElementList().get(5).getValue());
        assertEquals(-1, sudokuBoardDeepCopied.getSudokuRowList().get(3).getSudokuElementList().get(5).getValue());
    }
}
