package chess;

import board.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Error instantiating ChessPosition. Valid values are fom a-1 until h-8");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition(){
        int matrixRow = 8 - this.row;
        int matrixColumn = this.column - 'a';

        return new Position(matrixRow, matrixColumn);
    }

    protected static ChessPosition fromPosition(Position position){

        char chessColumn = (char) ( 'a' - position.getColumn());
        int chessRow = 8 - position.getRow();
        return new ChessPosition(chessColumn, chessRow);
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
