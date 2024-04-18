package chess;

import board.Board;
import board.Position;
import chess.enums.Color;
import chess.pieces.*;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] matchPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i = 0; i< board.getRows(); i++){
            for(int j = 0; j< board.getColumns(); j++){
                matchPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }

        return matchPieces;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        board.placePiece(new Rook(this.board, Color.BLACK), new Position(0,0));
        board.placePiece(new Knight(this.board, Color.BLACK), new Position(0,1));
        board.placePiece(new Bishop(this.board, Color.BLACK), new Position(0,2));
        board.placePiece(new King(this.board, Color.BLACK), new Position(0,3));
        board.placePiece(new Queen(this.board, Color.BLACK), new Position(0,4));
        board.placePiece(new Bishop(this.board, Color.BLACK), new Position(0,5));
        board.placePiece(new Knight(this.board, Color.BLACK), new Position(0,6));
        board.placePiece(new Rook(this.board, Color.BLACK), new Position(0,7));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,0));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,1));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,2));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,3));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,4));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,5));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,6));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,7));


        board.placePiece(new Rook(this.board, Color.WHITE), new Position(7,0));
        board.placePiece(new Knight(this.board, Color.WHITE), new Position(7,1));
        board.placePiece(new Bishop(this.board, Color.WHITE), new Position(7,2));
        board.placePiece(new King(this.board, Color.WHITE), new Position(7,3));
        board.placePiece(new Queen(this.board, Color.WHITE), new Position(7,4));
        board.placePiece(new Bishop(this.board, Color.WHITE), new Position(7,5));
        board.placePiece(new Knight(this.board, Color.WHITE), new Position(7,6));
        board.placePiece(new Rook(this.board, Color.WHITE), new Position(7,7));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,0));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,1));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,2));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,3));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,4));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,5));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,6));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,7));



    }
}
