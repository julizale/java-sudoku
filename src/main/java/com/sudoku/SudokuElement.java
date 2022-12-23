package com.sudoku;

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

    public SudokuElement(int value, List<Integer> possibleValues) {
        this.value = value;
        this.possibleValues = possibleValues;
    }

    public int getValue() {
        return value;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setValue(int value) throws IllegalArgumentException {
            this.value = value;
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "   " : " " + value + " ";
    }

    public boolean isEmpty() {
        return value == EMPTY;
    }
}
