import javax.swing.UIManager;

public class TicTacToeGame {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Boardgame game = new TicTacToeModel();
        ViewControl vc = new ViewControl(game, 3);
    }
}
