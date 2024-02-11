import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;


public class ViewControl extends JFrame implements ActionListener {

    private final int SIZE = 8;
    private ChessModel game;
    private Square[][] board;
    private JLabel mess;


    ViewControl (ChessModel cm) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        game = cm;
        setSize(500, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Chessgame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Square[SIZE][SIZE];
        mess = new JLabel();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Square(i, j, game.getPiece(new Coord(i, j)));
                board[i][j].setBounds(50*i, 50*j, 50, 50);
                board[i][j].addActionListener(this);
                add(board[i][j]);
            }
        }
        mess.setBounds(0, 50*SIZE, 400, 50);
        add(mess);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Coord coord = ((Square) e.getSource()).getCoord();
        game.handleInput(coord);
        triggerAutoMoveIfApplicable();
        mess.setText(game.getMessage());
        updateView();
    }

    private void triggerAutoMoveIfApplicable() {
        if (game.getSelectedPiece() != null) {
            game.makeAutoMoveIfOneMove();
            if (game.isInAutoMoveState()) {
                updatePiecesView();
                int n = JOptionPane.showOptionDialog(this,
                            "Would you like to accept the auto-move?",
                            "A Question",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, 
                            null,
                            null,
                            null);
                if (n == JOptionPane.YES_OPTION) {
                    game.setAutoMoveState(false);
                } else {
                    game.undoAutoMove();
                }
            }
        }
    }

    private void updatePiecesView() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Coord coord = new Coord(i, j);
                setDefaultColorAt(coord);
                setPieceAt(coord, game.getPiece(coord));
            }
        }
    }

    private void paintPossibleMoves(){
        LinkedList<Coord> possibleMoves = game.getLegalMoves();
        if (possibleMoves != null){            
            for (Coord move : possibleMoves)
                setPossibleMoveColorAt(move);
        }
    }

    private void updateView() {
        updatePiecesView();
        paintPossibleMoves();
    }

    private void setPieceAt(Coord coord, Piece piece) {
        board[coord.getX()][coord.getY()].setPiece(piece);
    }

    private void setPossibleMoveColorAt(Coord coord){
        board[coord.getX()][coord.getY()].setPossibleMoveColor();
    }

    private void setDefaultColorAt(Coord coord){
        board[coord.getX()][coord.getY()].setDefaultColor();
    }
}

class Square extends JButton {
    private static final Color LIGHT = new Color(255, 240, 204);
    private static final Color LIGHT_BROWN = new Color(204, 153, 102);
    public static final Color MOVE_TO_COLOR = Color.RED;
    public final Color DEFAULT_COLOR;
    private final Coord coord;

    Square(int x,  int y, Piece piece) {
        this.coord = new Coord(x, y);
        if (((this.coord.getX() + this.coord.getY())%2) == 0) {
            this.DEFAULT_COLOR = LIGHT;
        }
        else {
            this.DEFAULT_COLOR = LIGHT_BROWN;
        }
        if (piece != null) {
            this.setIcon(piece.getIcon());
        }
        this.setBackground(this.DEFAULT_COLOR);
    }

    public Coord getCoord() {
        return coord; 
    }
    
    public void setPiece(Piece piece) {
        if (piece != null) {
            this.setIcon(piece.getIcon());
        } else {
            this.setIcon(null);
        }
    }

    public void setDefaultColor() {
        this.setBackground(this.DEFAULT_COLOR);
    }
    public void setPossibleMoveColor(){
        this.setBackground(Square.MOVE_TO_COLOR);
    }
}