import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;


public class Knight extends Piece {

    ImageIcon icon;

    public Knight(int color, Board board) {
        super(color, board);
        if (color == Piece.WHITE) {
            icon = resizeImageIcon(new ImageIcon("assets/knight_white.png"), 50);
        } else {
            icon = resizeImageIcon(new ImageIcon("assets/knight_black.png"), 50);
        }
    }

    public ImageIcon getIcon() {
            return icon;
        }

    public LinkedList<Coord> getMoveCandidates() {

        LinkedList<Coord> moveTo = new LinkedList<>();
        int[] movesx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] movesy = {1, -1, 2, -2, 2, -2, 1, -1};

        for (int i = 0; i<movesx.length; i++){
            int newY = getY()+movesy[i];
            int newX = getX()+movesx[i];

            if (Board.isOnBoard(newX, newY)){
                moveTo.add(new Coord(newX, newY));
            }
        }
        return moveTo;
    }
}
