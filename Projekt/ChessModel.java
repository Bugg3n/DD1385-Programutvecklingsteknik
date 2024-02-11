import java.util.LinkedList;

public class ChessModel {

    private Board board;
    private int turn;
    private Piece selectedPiece;
    private String lastAction;
    private boolean autoMoveState = false;

    public ChessModel() {
        board = new Board();
        turn = Piece.WHITE;
    }

    private void selectPiece(Coord coord) {
        selectedPiece = board.getPiece(coord);
        if (selectedPiece != null && selectedPiece.getColor() != turn) {
            selectedPiece = null;
        }
        if (selectedPiece != null) {
            lastAction = "Selected " + selectedPiece.toString();
        }
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    private void makeMove(Coord toCoord) {
        board.movePieceWithEffects(selectedPiece.getCoord(), toCoord);
        selectedPiece = null;
        turn = 1 - turn;
    }

    public void setAutoMoveState(boolean autoMoveState) {
        this.autoMoveState = autoMoveState;
    }

    public boolean isInAutoMoveState() {
        return autoMoveState;
    }

    public void makeAutoMoveIfOneMove() {
        if (selectedPiece != null) {
            LinkedList<Coord> legalMoves = selectedPiece.getLegalMoves();
            if (legalMoves.size() == 1) {
                lastAction = "Auto-moved " + selectedPiece.toString();
                makeMove(legalMoves.get(0));
                setAutoMoveState(true);
            }
        }
    }

    public void undoAutoMove() {
        if (autoMoveState) {
            board.undoMovePieceWithEffects();
            turn = 1 - turn;
            lastAction = "Undid auto-move";
            setAutoMoveState(false);
        }
    }
    
    public LinkedList<Coord> getLegalMoves(){
        if (selectedPiece != null){
            return selectedPiece.getLegalMoves();
        }
        return new LinkedList<>();
    }

    public Piece getPiece(Coord coord) {
        return board.getPiece(coord);
    }

    private void moveOrReselectPiece(Coord toCoord) {
        if (selectedPiece.getLegalMoves().contains(toCoord)) {
            lastAction = "Moved " + selectedPiece.toString();
            makeMove(toCoord);
        } else {
            lastAction = "Invalid move";
        }
        if (board.getPiece(toCoord) != null && board.getPiece(toCoord).getColor() == turn)  {
            selectPiece(toCoord);
            lastAction = "Selected " + selectedPiece.toString();
        }
    }

    public void handleInput(Coord coord) {
        if (getSelectedPiece() == null) {
            selectPiece(coord);
        } else {
            moveOrReselectPiece(coord);
        }
    }

    public String getMessage() {
        String message = "";
        if (turn == Piece.WHITE) {
            message = message + "White's turn";
        } else {
            message = message + "Black's turn";
        }
        if (selectedPiece != null) {
            message = message + ". " + lastAction;
        }
        if (board.isCheck(turn)) {
            message = message + ". Check!";
        }
        return message;
    }

    public static void main(String[] args) {
        ViewControl vc = new ViewControl(new ChessModel());
    }
}



