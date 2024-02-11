import java.util.LinkedList;
import javax.swing.ImageIcon;


public class Bishop extends Piece {

    ImageIcon icon;

    public Bishop(int color, Board board) {
        super(color, board);
        if (color == Piece.WHITE) {
            icon = resizeImageIcon(new ImageIcon("assets/bishop_white.png"), 50);
        } else {
            icon = resizeImageIcon(new ImageIcon("assets/bishop_black.png"), 50);
        }
    }

    public ImageIcon getIcon() {
            return icon;
        }

    public LinkedList<Coord> getMoveCandidates() {

        LinkedList<Coord> moveTo = new LinkedList<>();
        // Höger framåt
        for (int x = getCoord().getX()+1, y = getCoord().getY()+1 ; x < 8 && y < 8; x++, y++){        
            moveTo.add(new Coord(x, y));
            if (board.getPiece(x, y) != null){
                break;
            }
        }
        // Höger bakåt
        for (int x = getCoord().getX()+1, y = getCoord().getY()-1 ; x < 8 && y > -1; x++, y--){        
            moveTo.add(new Coord(x, y));
            if (board.getPiece(x, y) != null){
                break;
            }
        }
        // Vänster framåt
        for (int x = getCoord().getX()-1, y = getCoord().getY()+1 ; x > -1 && y < 8; x--, y++){        
            moveTo.add(new Coord(x, y));
            if (board.getPiece(x, y) != null){
                break;
            }
        }
        // Vänster Bakåt
        for (int x = getCoord().getX()-1, y = getCoord().getY()-1 ; x > -1 && y > -1; x--, y--){        
            moveTo.add(new Coord(x, y));
            if (board.getPiece(x, y) != null){
                break;
            }
        }
        // Returnerar de rutor tornet kan gå till
        return moveTo;
    }
}
