package chess;

import board.Board;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] matchPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i = 0; i< board.getRows(); i++){
            for(int j = 0; j< board.getColumns(); i++){
                matchPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }

        return matchPieces;
    }
}
