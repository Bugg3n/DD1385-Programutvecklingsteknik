import javax.swing.UIManager;

public class FifteenGame {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Boardgame game = new FifteenModel();
        ViewControl vc = new ViewControl(game, 4);
    }
}
