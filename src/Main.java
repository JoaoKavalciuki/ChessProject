import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while(true){
            try{
                UI.clearScreen();
                UI.printMatch(match, capturedPieces);
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readPosition(sc);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);

                System.out.print("Target: ");
                ChessPosition target = UI.readPosition(sc);

                ChessPiece capturedPiece = match.performNewMove(source, target);

                if(capturedPiece != null){
                    capturedPieces.add(capturedPiece);
                }

            } catch(ChessException | InputMismatchException exception){
                System.out.println(exception.getMessage());
                sc.nextLine();
            }
        }

    }
}