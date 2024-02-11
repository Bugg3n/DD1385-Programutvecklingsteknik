
public class TicTacToeModel implements Boardgame {
    
    static final char EMPTY = ' ';
    static final char X = 'X';
    static final char O = 'O';

    private char[][] board;
    private int round;
    private char current_player;
    private int[][] moves_X;
    private int[][] moves_O;
    private String message;

    public TicTacToeModel() {
        board = new char[3][3];
        round = 0;
        current_player = X;
        moves_X = new int[][] {{0,0}, {0,0}, {0,0}};
        moves_O = new int[][] {{0,0}, {0,0}, {0,0}};

        for (int x = 0; x<2; x++){
            for (int y = 0; y<2; y++){
                board[x][y] = EMPTY;
            }
        }
    }

    private int[] get_oldest_move(char player) {
        if (player == X) {
            return moves_X[((round - 1) / 2) % 3];
        } else { 
            return moves_O[((round - 2) / 2) % 3];
        }
    }

    public boolean move(int x, int y) {
        message = "Move successful";
        if ((board[x][y] == X) || (board[x][y] == O)){
            message = "Move unsuccessful";
            return false;
        } else {
            round += 1;
            int[] coords = get_oldest_move(current_player);
            if (round >= 6) {
                board[coords[0]][coords[1]] = EMPTY;
            }
            if (current_player == X) {
                moves_X[((round - 1) / 2) % 3] = new int[] {x, y};
                board[x][y] = X;
                current_player = O;
            } else {
                moves_O[((round - 2) / 2) % 3] = new int[] {x, y};
                board[x][y] = O;
                current_player = X;
            }
            return true;
        }
    }

    public String getStatus(int x, int y) {
        if (board[x][y] == EMPTY) {
            return " ";
        } else {
            return String.format("%2c", board[x][y]);
        }
    }

    public String getMessage() {
        return message;
    }
    
}
