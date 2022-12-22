package com.sudoku;

import com.sudoku.exception.ValueOutOfBoundsException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuElement {

    public static final int EMPTY = -1;
    private int value = EMPTY;
    private final List<Integer> possibleValues;

    public SudokuElement() {
        possibleValues = IntStream.range(1,10)
                .boxed()
                .collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setValue(int value) throws ValueOutOfBoundsException, IllegalArgumentException {
        if (value < 1 || value > 9) {
            throw new ValueOutOfBoundsException("Sudoku element should have value 1-9");
        } else if (!possibleValues.contains(value)) {
            throw new IllegalArgumentException();
        } else {
            this.value = value;
            possibleValues.removeIf(possibleValue -> possibleValue == value);
        }
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "   " : " " + value + " ";
    }

    public boolean isEmpty() {
        return value == EMPTY;
    }
}
