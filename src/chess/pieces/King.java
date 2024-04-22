package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece pieceAtPosition = (ChessPiece) getBoard().getPiece(position);
        return (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor());
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] auxMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);


        //acima
        auxPosition.setValues(position.getRow()-1, position.getColumn());
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //direita
        auxPosition.setValues(position.getRow(), position.getColumn()+1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //abaixo
        auxPosition.setValues(position.getRow()+1, position.getColumn());
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }
        //esquerda
        auxPosition.setValues(position.getRow(), position.getColumn()-1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //diagonal esquerda cima
        auxPosition.setValues(position.getRow()-1, position.getColumn()-1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //diagona direita cima

        auxPosition.setValues(position.getRow()-1, position.getColumn()+1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //diagonal esquerda baixo
        auxPosition.setValues(position.getRow()+1, position.getColumn()-1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        auxPosition.setValues(position.getRow()+1, position.getColumn()+1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        return auxMatrix;
    }
}
