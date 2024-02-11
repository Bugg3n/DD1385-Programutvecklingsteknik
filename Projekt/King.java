import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;


public class King extends Piece {

    ImageIcon icon;

    public King(int color, Board board) {
        super(color, board);
        if (color == Piece.WHITE) {
            icon = resizeImageIcon(new ImageIcon("assets/king_white.png"), 50);
        } else {
            icon = resizeImageIcon(new ImageIcon("assets/king_black.png"), 50);
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public LinkedList<Coord> getMoveCandidates() {
        LinkedList<Coord> moveTo = new LinkedList<>();

        List<Integer> xCandidates = Arrays.asList(0, 1, 1, 1, 0, -1, -1, -1);
        List<Integer> yCandidates = Arrays.asList(1, 1, 0, -1, -1, -1, 0, 1);

        if (getCoord().getX() != 0)
            moveTo.add(new Coord(getCoord().getX()-1, getCoord().getY()));
        if (getCoord().getX() != 7)
            moveTo.add(new Coord(getCoord().getX()+1, getCoord().getY()));
        if (getCoord().getY() != 0)
            moveTo.add(new Coord(getCoord().getX(), getCoord().getY()-1));
        if (getCoord().getY() != 7)
            moveTo.add(new Coord(getCoord().getX(), getCoord().getY()+1));
        if (getCoord().getY() != 0 && getCoord().getX() != 0)
            moveTo.add(new Coord(getCoord().getX()-1, getCoord().getY()-1));
        if (getCoord().getX() != 0 && getCoord().getY() != 7)
            moveTo.add(new Coord(getCoord().getX()-1, getCoord().getY()+1));
        if (getCoord().getX() !=7 && getCoord().getY() != 0)
            moveTo.add(new Coord(getCoord().getX()+1, getCoord().getY()-1));
        if (getCoord().getX() !=7 && getCoord().getY() != 7)
            moveTo.add(new Coord(getCoord().getX()+1, getCoord().getY()+1));
        // Returnerar de rutor kungen kan gå till
        return moveTo;
    }
}
