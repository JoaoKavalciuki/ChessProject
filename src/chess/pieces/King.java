package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {

    private ChessMatch match;
    public King(Board board, Color color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece pieceAtPosition = (ChessPiece) getBoard().getPiece(position);
        return (pieceAtPosition == null || pieceAtPosition.getColor() != this.getColor());
    }

    //jogada roque entre rei e torre
    private boolean testRookCastling(Position position){
        ChessPiece piece = (ChessPiece) getBoard().getPiece(position);

        if(piece instanceof Rook && piece.getMoveCount() == 0 && piece.getColor() == this.getColor()){
            return true;
        }

        return false;
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

        //Roque

        if(getMoveCount() == 0 && !this.match.isInCheck()){
            //right
            Position rightTowerPosition = new Position(position.getRow(), position.getColumn()+3);
            if(testRookCastling(rightTowerPosition)){
                Position p1 = new Position(position.getRow(), position.getColumn()+1);
                Position p2 = new Position(position.getRow(), position.getColumn()+2);

                if(!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2)){
                    auxMatrix[position.getRow()][position.getColumn()+2] = true;
                }
            }

            //left
            Position leftTowerPosition = new Position(position.getRow(), position.getColumn()-4);

            if(testRookCastling(leftTowerPosition)){
                Position p1 = new Position(position.getRow(), position.getColumn()-1);
                Position p2 = new Position(position.getRow(), position.getColumn()-2);
                Position p3 = new Position(position.getRow(), position.getColumn()-3);

                if(!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2) && !getBoard().thereIsAPiece(p3)){
                    auxMatrix[position.getRow()][position.getColumn()-2] = true;
                }
            }
        }

        return auxMatrix;
    }
}
