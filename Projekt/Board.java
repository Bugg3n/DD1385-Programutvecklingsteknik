import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Board {
    private final static int SIZE = 8;

    private SquareModel[][] board;
    private List<Piece> blackPieces;
    private List<Piece> whitePieces;

    private Coord lastFrom = null;
    private Coord lastTo = null;
    private Piece lastFromPiece = null;
    private Piece lastToPiece = null;

    public Board() {
        board = new SquareModel[SIZE][SIZE];
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new SquareModel(new Coord(i, j), null);
            }
        }

        setPiece(new Coord(4, 0), new King(Piece.BLACK, this));
        setPiece(new Coord(4, 7), new King(Piece.WHITE, this));
        setPiece(new Coord(3, 0), new Queen(Piece.BLACK, this));
        setPiece(new Coord(3, 7), new Queen(Piece.WHITE, this));
        setPiece(new Coord(0, 0), new Rook(Piece.BLACK, this));
        setPiece(new Coord(0, 7), new Rook(Piece.WHITE, this));
        setPiece(new Coord(7, 0), new Rook(Piece.BLACK, this));
        setPiece(new Coord(7, 7), new Rook(Piece.WHITE, this));
        setPiece(new Coord(2, 0), new Bishop(Piece.BLACK, this));
        setPiece(new Coord(2, 7), new Bishop(Piece.WHITE, this));
        setPiece(new Coord(5, 0), new Bishop(Piece.BLACK, this));
        setPiece(new Coord(5, 7), new Bishop(Piece.WHITE, this));
        setPiece(new Coord(1, 0), new Knight(Piece.BLACK, this));
        setPiece(new Coord(1, 7), new Knight(Piece.WHITE, this));
        setPiece(new Coord(6, 0), new Knight(Piece.BLACK, this));
        setPiece(new Coord(6, 7), new Knight(Piece.WHITE, this));
        for (int i = 0; i < SIZE; i++) {
            setPiece(new Coord(i, 1), new Pawn(Piece.BLACK, this));
            setPiece(new Coord(i, 6), new Pawn(Piece.WHITE, this));
        }
    }


    private void setPiece(Coord coord, Piece piece) {
        if (getPiece(coord) != null) {
            if (getPiece(coord).getColor() == Piece.BLACK)
                blackPieces.remove(getPiece(coord));
            else
                whitePieces.remove(getPiece(coord));
        }
        board[coord.getX()][coord.getY()].setPiece(piece);
        if (piece != null) {
            piece.setCoord(coord);
            if (piece.getColor() == Piece.BLACK)
                blackPieces.add(piece);
            else
                whitePieces.add(piece);
        }
    }

    public Piece getPiece(Coord coord) {
        return board[coord.getX()][coord.getY()].getPiece();
    }

    public Piece getPiece(int x, int y) {
        return board[x][y].getPiece();
    }

    private void movePiece(Coord from, Coord to) {
        Piece piece = getPiece(from);
        setPiece(to, piece);
        setPiece(from, null);
    }

    public void movePieceWithEffects(Coord from, Coord to) {
        lastFrom = from;
        lastTo = to;
        lastFromPiece = getPiece(from);
        lastToPiece = getPiece(to);
        Piece piece = getPiece(from);
        movePiece(from, to);
        if (piece.getType().equals("Pawn") && to.getY() == 0 && piece.getColor() == Piece.WHITE
            || piece.getType().equals("Pawn") && to.getY() == 7 && piece.getColor() == Piece.BLACK) {
            promotePieceAt(to);
        }
    }

    public void undoMovePieceWithEffects() {
        movePiece(lastTo, lastFrom);
        setPiece(lastTo, lastToPiece);
        setPiece(lastFrom, lastFromPiece);
    }

    private void promotePieceAt(Coord coord) {
        setPiece(coord, new Queen(getPiece(coord).getColor(), this));
    }

    private boolean checkIfPieceAtCoordThreatensKing(Coord coord) {
        Piece piece = getPiece(coord);
        LinkedList<Coord> moves = piece.getPossibleMoves();
        int color = piece.getColor();
        for (Coord toCoord : moves) {
            if (getPiece(toCoord) != null && getPiece(toCoord).getType().equals("King") && getPiece(toCoord).getColor() != color) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheck(int color) {
        List<Piece> opponentPieces = (color == Piece.BLACK) ? whitePieces : blackPieces;
        for (Piece piece : opponentPieces) {
            if (checkIfPieceAtCoordThreatensKing(piece.getCoord())) {
                return true;
            }
        }
        return false;
    }

    public boolean moveGivesCheck(Coord toCoord, Piece piece) {
        Coord fromCoord = piece.getCoord();
        Piece preToPiece = getPiece(toCoord);
        movePiece(fromCoord, toCoord);
        boolean result = isCheck(piece.getColor());
        movePiece(toCoord, fromCoord);
        setPiece(toCoord, preToPiece);
        return result;
    }

    public static boolean isOnBoard(Coord coord) {
        return coord.getX() >= 0 && coord.getX() < SIZE && coord.getY() >= 0 && coord.getY() < SIZE;
    }

    public static boolean isOnBoard(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}

class SquareModel {
    private final Coord coord;
    Piece piece;

    public SquareModel(Coord coord, Piece piece) {
        this.coord = coord;
        this.piece = piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Coord getCoord() {
        return coord;
    }
}