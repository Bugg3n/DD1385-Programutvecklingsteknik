import javax.swing.text.View;

public class MockObject implements Boardgame {

    public static void main(String[] args) {
        MockObject m = new MockObject();
        ViewControl vc = new ViewControl(m, 5);
    }

    public boolean move(int x, int y) {
        return true;
    }
    
    public String getStatus(int x, int y) {
        return "0";
    }
    
    public String getMessage() {
        return "Weo";
    }
}
