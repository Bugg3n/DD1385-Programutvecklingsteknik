import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.LinkedList;

public abstract class Piece {

    public static final int WHITE = 0;
    public static final int BLACK = 1;

    private Coord coord;
    protected int color;
    protected Board board;

    protected Piece(int color, Board board) {
        this.color = color;
        this.board = board;
    }

    protected Coord getCoord() {
        return coord;
    }

    protected int getY() {
        return coord.getY();
    }

    protected int getX() {
        return coord.getX();
    }

    protected int getYForward(int steps) {
        if (color == WHITE) {
            return coord.getY() - steps;
        } else {
            return coord.getY() + steps;
        }
    }

    protected int getYBackward(int steps) {
        if (color == WHITE) {
            return coord.getY() + steps;
        } else {
            return coord.getY() - steps;
        }
    }

    protected int getXRight(int steps) {
        if (color == WHITE) {
            return coord.getX() + steps;
        } else {
            return coord.getX() - steps;
        }
    }

    protected int getXLeft(int steps) {
        if (color == WHITE) {
            return coord.getX() - steps;
        } else {
            return coord.getX() + steps;
        }
    }

    protected int getColor() {
        return color;
    }

    protected void setCoord(Coord coord) {
        this.coord = coord;
    }

    protected static ImageIcon resizeImageIcon(ImageIcon icon, int newSize) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(newSize, newSize, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    public abstract ImageIcon getIcon();

    public abstract LinkedList<Coord> getMoveCandidates();

    private void removeFriendlyFire(LinkedList<Coord> moveCandidates) {
        moveCandidates.removeIf(candidateCoord -> (board.getPiece(candidateCoord) != null && board.getPiece(candidateCoord).getColor() == this.color));
    }

    private void removeThreatenedMoves(LinkedList<Coord> moveCandidates) {
        moveCandidates.removeIf(moveCandidate -> (board.moveGivesCheck(moveCandidate, this)));
    }

    public LinkedList<Coord> getPossibleMoves() {
        LinkedList<Coord> moves = getMoveCandidates();
        removeFriendlyFire(moves);
        return moves;
    }

    public LinkedList<Coord> getLegalMoves() {
        LinkedList<Coord> moves = getMoveCandidates();
        removeFriendlyFire(moves);
        removeThreatenedMoves(moves);
        return moves;
    }

    public String toString() {
        return (this.color == 0?"White ":"Black ") + this.getClass().getName();
    }

    public String getType() {
        return this.getClass().getName();
    }
}
