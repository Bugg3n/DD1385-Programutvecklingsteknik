import java.util.LinkedList;
import javax.swing.ImageIcon;


public class Queen extends Piece {

    ImageIcon icon;

    public Queen(int color, Board board) {
        super(color, board);
        if (color == Piece.WHITE) {
            icon = resizeImageIcon(new ImageIcon("assets/queen_white.png"), 50);
        } else {
            icon = resizeImageIcon(new ImageIcon("assets/queen_black.png"), 50);
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
        for (int x = getCoord().getX()+1; x < 8; x++){        
            moveTo.add(new Coord(x, getCoord().getY()));
            if (board.getPiece(x, getCoord().getY()) != null){
                break;
            }
        }
        // Vänster
        for (int x = getCoord().getX()-1; x > -1; x--){        
            moveTo.add(new Coord(x, getCoord().getY()));
            if (board.getPiece(x, getCoord().getY()) != null){
                break;
            }
        }
        // Framåt
        for (int y = getCoord().getY()+1; y < 8; y++){
            moveTo.add(new Coord(getCoord().getX(), y));
            if (board.getPiece(getCoord().getX(), y) != null){
                break;
            }
        }
        // Bakåt
        for (int y = getCoord().getY()-1; y > -1; y--){
            moveTo.add(new Coord(getCoord().getX(), y));
            if (board.getPiece(getCoord().getX(), y) != null){
                break;
            }
        }
        // Returnerar de rutor tornet kan gå till
        return moveTo;
    }
}
