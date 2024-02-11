import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;


public class Pawn extends Piece {

    ImageIcon icon;

    public Pawn(int color, Board board) {
        super(color, board);
        if (color == Piece.WHITE) {
            icon = resizeImageIcon(new ImageIcon("assets/pawn_white.png"), 50);
        } else {
            icon = resizeImageIcon(new ImageIcon("assets/pawn_black.png"), 50);
        }
    }

    public ImageIcon getIcon() {
            return icon;
        }

    public LinkedList<Coord> getMoveCandidates() {
        LinkedList<Coord> moveTo = new LinkedList<>();

        Coord newCoord = new Coord(getX(), getYForward(1));
        if (Board.isOnBoard(newCoord) && board.getPiece(newCoord) == null) {
            moveTo.add(newCoord);
            
            if ((color == WHITE && getY() == 6) || (color == BLACK && getY() == 1)) {
                newCoord = new Coord(getX(), getYForward(2));
                if (Board.isOnBoard(newCoord) && board.getPiece(newCoord) == null) {
                    moveTo.add(newCoord);
                }
            }
        }
        List<Integer> xCandidates = Arrays.asList(-1, 1);
        
        for (Integer x : xCandidates) {
            newCoord = new Coord(getX() + x, getYForward(1));
            if (Board.isOnBoard(newCoord) 
                    && board.getPiece(newCoord) != null
                    && board.getPiece(newCoord).getColor() != color) {
                moveTo.add(newCoord);
            }
        }

        return moveTo;
    }
}
