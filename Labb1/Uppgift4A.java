import javax.swing.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.*;

public class Uppgift4A {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
         } catch (Exception e) {
                    e.printStackTrace();
         }
        
        System.out.println("Start!");
        MyFrame ourFrame = new MyFrame();
    }
}

class MyFrame extends JFrame implements ActionListener {

    // dold JFrame kod

    public MyFrame() { // konstruktor
        setSize(300, 300);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.RED);
        setTitle("Oskar och Hugo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Uppgift 3
        MyButton btn1 = new MyButton();
        MyButton btn2 = new MyButton();
        //ActionListener actionListener1 = new ActionListener();
        //ActionListener actionListener2 = new ActionListener();
        btn1.setBounds(25,100,95,30);  
        btn2.setBounds(200,100,95,30);
        btn2.toggleState();
        add(btn1);
        add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((MyButton) e.getSource()).toggleState();
    }
}

// Uppgift 2
class MyButton extends JButton {
    Color color1 = new Color(255, 0, 0);
    Color color2 = new Color(0, 255, 0);
    String text1 = "Press here";
    String text2 = "Klicka här";
    boolean state = false;

    public MyButton() {
        setText(text1);
        setBackground(color1);
    }

    public void toggleState() {
        if (state) {
            setBackground(color1);
            setText(text1);
            state = false;
        }
        else {
            setBackground(color2);
            setText(text2);
            state = true;
        }
    }
}