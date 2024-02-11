import javax.swing.ImageIcon;
import java.awt.Image;

public class MockModel {

    public static void main(String[] args) {
        //ViewControl vc = new ViewControl(new MockModel());

    }

    public void pickPiece(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    public void movePiece(int x, int y) {
        // TODO Auto-generated method stub
        
    }


    public String getMessage() {
        // TODO Auto-generated method stub
        return "Hello";
    }

    public ImageIcon getStatus(int x, int y) {
        // TODO Auto-generated method stub
        ImageIcon imageIcon = new ImageIcon("assets/king_black.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }
}
