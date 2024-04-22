package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] auxMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        //acima
        auxPosition.setValues(position.getRow() - 1, position.getColumn());

        while(getBoard().positionExists(auxPosition) &&  !getBoard().thereIsAPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;

            auxPosition.setRow(auxPosition.getRow()-1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //esquerda
        auxPosition.setValues(position.getRow(), position.getColumn()-1);

        while(getBoard().positionExists(auxPosition) &&  !getBoard().thereIsAPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;

            auxPosition.setColumn(auxPosition.getColumn()-1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //direita
        auxPosition.setValues(position.getRow(), position.getColumn()+1);

        while(getBoard().positionExists(auxPosition) &&  !getBoard().thereIsAPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;

            auxPosition.setColumn(auxPosition.getColumn()+1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //abaixo
        auxPosition.setValues(position.getRow()+1, position.getColumn());

        while(getBoard().positionExists(auxPosition) &&  !getBoard().thereIsAPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;

            auxPosition.setRow(auxPosition.getRow()+1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }


        return auxMatrix;
    }
}
