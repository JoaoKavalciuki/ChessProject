package board;

import chess.ChessPiece;

public class Board {
    int rows;
    int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1){
            throw new BoardException("Error at board creating board! It must have at least one column and one line");
        }

        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece getPiece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Position non existent");
        }

        return pieces[row][column];
    }

    public Piece getPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position non existent");
        }

        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("Position " + position + " alredy occupied");
        }

        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position, ChessPiece piece){
        if(!positionExists(position)){
            throw new BoardException("Position non existent");
        }

        if(!thereIsAPiece(position)){
            return null;
        }

        Piece aux = getPiece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExists(int row, int column){
        return ((row >= 0 && row <= rows) && (column >= 0 && column <= columns));
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());

    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position non existent");
        }

        return getPiece(position) != null;
    }

}
