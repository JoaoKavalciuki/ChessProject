import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.enums.Color;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static ChessPosition readPosition(Scanner sc){
        try {

            String input = sc.nextLine();
            char column = input.charAt(0);
            int row = Integer.parseInt(input.substring(1));

            return new ChessPosition(column, row);
        } catch (RuntimeException exception){
            throw new InputMismatchException("Error reading ChessPosition. Valid values are a1 until h8");
        }
    }

    public static  void  printMatch(ChessMatch match, List<ChessPiece> capturedPieces){
        printBoard(match.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);

        System.out.println();
        System.out.print("Turn: " + match.getTurn());
        System.out.println();

        if(!match.isInCheckMate()){
            System.out.println("Waiting player: " + match.getCurrentPlayer());

            if(match.isInCheck()){
                System.out.println("YOU ARE IN CHECK");
            }
        } else {
            System.out.println("CHECKMATE");
            System.out.println("Winner: " + match.getCurrentPlayer());
        }

    }

    public static void printBoard(ChessPiece[][] pieces){
        for(int i = 0; i<pieces.length; i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j< pieces.length; j++){
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        for(int i = 0; i<pieces.length; i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j< pieces.length; j++){
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background){
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        if(piece == null){
            System.out.print("-" + ANSI_RESET);
        } else {
            if(piece.getColor() == Color.WHITE){

                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_PURPLE + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printCapturedPieces(List<ChessPiece> capturedPieces){
        List<ChessPiece> whitePieces = capturedPieces.stream().filter(piece -> piece.getColor() == Color.WHITE).toList();
        List<ChessPiece> blackPieces = capturedPieces.stream().filter(piece -> piece.getColor() == Color.BLACK).toList();

        System.out.println("Captured pieces: ");
        System.out.print(ANSI_WHITE);
        System.out.println("White: " + whitePieces);
        System.out.print(ANSI_RESET);

        System.out.print(ANSI_PURPLE);
        System.out.print("Black: " + blackPieces);
        System.out.print(ANSI_RESET);
        System.out.println();
    }
}