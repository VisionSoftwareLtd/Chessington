package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        PlayerColour myColour;
        List<Move> moves;
        Coordinates to;
        Move moveOne;
        Move moveTwo;
        int row;

        myColour = getColour();
        row = from.getRow();
        moves = new ArrayList<>();
        if (myColour == PlayerColour.BLACK) {
            to = from.plus(1, 0);
        } else {
            to = from.plus(-1,0);
        }
        moveOne = new Move(from, to);

        moveTwo = null;
        if (myColour == PlayerColour.BLACK && row == 1) {
            to = from.plus(2, 0);
            moveTwo = new Move(from, to);
        } else if (myColour == PlayerColour.WHITE && row == 6){
            to = from.plus(-2,0);
            moveTwo = new Move(from, to);
        }

        moves.add(moveOne);
        if (moveTwo != null) {
            moves.add(moveTwo);
        }
        return moves;
    }
}
