import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class ViewControl extends JFrame implements ActionListener {

    private Boardgame game;
    private int size;
    private Square[][] board;
    private JLabel mess;

    ViewControl (Boardgame gm, int n){
        game = gm;
        size = n;
        setSize(300, 300);
        
        setLayout(null);
        getContentPane().setBackground(Color.RED);
        setTitle("Boardgame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Square[size][size];
        mess = new JLabel();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Square(i, j, game.getStatus(i, j));
                board[i][j].setBounds(50*i, 50*j, 50, 50);
                board[i][j].addActionListener(this);
                add(board[i][j]);
            }
        }
        mess.setBounds(0, 50*size, 200, 50);
        add(mess);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square s = (Square) e.getSource();
        game.move(s.x, s.y);
        mess.setText(game.getMessage());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].updateStatus(game.getStatus(i, j));
            }
        }
    }
    
}

class Square extends JButton {
    int x;
    int y;

    Square(int x,  int y, String status) {
        this.setText(status);
        this.setBackground(new Color(255,111,0));
        this.x = x;
        this.y = y;
    }

    public void updateStatus(String status) {
        this.setText(status);
    }
}