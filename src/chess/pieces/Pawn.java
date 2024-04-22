package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessException;
import chess.ChessPiece;
import chess.enums.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] auxMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        if(getColor() == Color.WHITE){
            auxPosition.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            auxPosition.setValues(position.getRow()-2, position.getColumn());
            Position targetPostion = new Position(position.getRow()-1, position.getColumn());

            if(!getBoard().positionExists(targetPostion) || getBoard().thereIsAPiece(targetPostion)){
                throw new ChessException("There is alredy a piece at target position or it does not exists");
            }

            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition) && this.getMoveCount() == 0){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }


            //diagonal esquerda
            auxPosition.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //diagonal direita
            auxPosition.setValues(position.getRow()-1, position.getColumn()+1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
        } else {
            auxPosition.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            auxPosition.setValues(position.getRow()+2, position.getColumn());
            Position targetPostion = new Position(position.getRow()+1, position.getColumn());

            if(!getBoard().positionExists(targetPostion) || getBoard().thereIsAPiece(targetPostion)){
                throw new ChessException("There is alredy a piece at target position or it does not exists");
            }

            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition) && this.getMoveCount() == 0){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }


            //diagonal esquerda
            auxPosition.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
            auxPosition.setValues(position.getRow()+1, position.getColumn()+1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
        }

        return  auxMatrix;
>>>>>>> chess/chess-piece
    }
}
