import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch match = new ChessMatch();


        while(true){
            try{
                UI.clearScreen();
                UI.printBoard(match.getPieces());

                System.out.print("Source: ");
                ChessPosition source = UI.readPosition(sc);

                System.out.print("Target: ");
                ChessPosition target = UI.readPosition(sc);

                ChessPiece captturedPiece = match.performNewMove(source, target);

            } catch(ChessException | InputMismatchException exception){
                System.out.println(exception.getMessage());
                sc.nextLine();
            }
        }

    }
}