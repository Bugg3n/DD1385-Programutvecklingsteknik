import java.util.*;

public class FifteenModel implements Boardgame {

    private int[][] board;
    private String message;

    public FifteenModel() {
        board = new int[4][4];
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i<16; i++){
            order.add(i);
        }
        Collections.shuffle(order);
        
        for (int x = 0; x<4; x++){
            for (int y = 0; y<4; y++){
                board[x][y] = order.get(4*x+y);
            }
        }
    }

    public boolean move(int x, int y) {
        message = "Move successful";
        if (x < 0 || x > 3 || y < 0 || y > 3) {
            message = "Move unsuccessful";
            return false;
        }
        if (x+1 < 4 && board[x+1][y] == 0) {
            board[x+1][y] = board[x][y];
            board[x][y] = 0;
            return true;
        } else if (x-1 >= 0 && board[x-1][y] == 0) {
            board[x-1][y] = board[x][y];
            board[x][y] = 0;
            return true;
        } else if (y+1 < 4 && board[x][y+1] == 0) {
            board[x][y+1] = board[x][y];
            board[x][y] = 0;
            return true;
        } else if (y-1 >= 0 && board[x][y-1] == 0) {
            board[x][y-1] = board[x][y];
            board[x][y] = 0;
            return true;
        } else {
            message = "Move unsuccessful";
            return false;
        }
    }

    public String getStatus(int x, int y) {
        if (board[x][y] == 0) {
            return "  ";
        } else {
            return String.format("%2s", Integer.toString(board[x][y]));
        }
    }

    public String getMessage() {
        return message;
    }
    
}
