package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        Move moveOne = getMoveOne(from, board);
        Move moveTwo = getMoveTwo(from);
        List<Move> moveDiagonals = getMoveDiagonals(from, board);

        if (moveOne != null) {
            moves.add(moveOne);
        }
        if (moveTwo != null) {
            moves.add(moveTwo);
        }
        moves.addAll(moveDiagonals);
        return moves;
    }

    private Move getMoveOne(Coordinates from, Board board) {
        Coordinates to;
        if (getColour() == PlayerColour.BLACK) {
            to = from.plus(1, 0);
        } else {
            to = from.plus(-1,0);
        }

        Piece piece;
        try {
            piece = board.get(to);
            if (piece == null) {
                return new Move(from, to);
            }
        } catch (IndexOutOfBoundsException e) {
            // Do nothing
        }
        return null;
    }

    private Move getMoveTwo(Coordinates from) {
        Coordinates to;
        if (getColour() == PlayerColour.BLACK && from.getRow() == 1) {
            to = from.plus(2, 0);
            return new Move(from, to);
        } else if (getColour() == PlayerColour.WHITE && from.getRow() == 6){
            to = from.plus(-2,0);
            return new Move(from, to);
        }
        return null;
    }

    private List<Move> getMoveDiagonals(Coordinates from, Board board) {
        List<Move> diagonalMoves = new ArrayList<>();
        Coordinates toLeft;
        Coordinates toRight;
        int rowDiff;
        if (getColour() == PlayerColour.BLACK) {
            rowDiff = 1;
        } else {
            rowDiff = -1;
        }
        toLeft = from.plus(rowDiff, -1);
        toRight = from.plus(rowDiff, 1);

        addDiagonalMove(from, board, diagonalMoves, toLeft);
        addDiagonalMove(from, board, diagonalMoves, toRight);
        return diagonalMoves;
    }

    private void addDiagonalMove(Coordinates from, Board board, List<Move> diagonalMoves, Coordinates coordinates) {
        Piece piece;
        try {
            piece = board.get(coordinates);
            if (piece != null && piece.getColour() != getColour()) {
                diagonalMoves.add(new Move(from, coordinates));
            }
        } catch (IndexOutOfBoundsException e) {
            // Do nothing
        }
    }
}
