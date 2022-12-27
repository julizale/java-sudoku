package com.sudoku;

public class UserResponse {

    private final int column;
    private final int row;
    private final int value;
    private final UserResponseStatus userResponseStatus;

    public UserResponse(final int column, final int row, final int value, final UserResponseStatus userResponseStatus) {
            this.column = column;
            this.row = row;
            this.value = value;
            this.userResponseStatus = userResponseStatus;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }

    public UserResponseStatus getResponseStatus() {
        return userResponseStatus;
    }
}
