import javax.swing.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.*;

public class UppgiftX {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        int nButtons = Integer.parseInt(args[0]);
        String[] buttonNames = new String[nButtons*2];
        for (int i = 0; i < buttonNames.length; i++) {
            buttonNames[i] = args[i+1];
        }
        
        MyFrame ourFrame = new MyFrame(nButtons, buttonNames);
    }
}

class MyFrame extends JFrame{
    MyButton[] buttons;

    public MyFrame(int nButtons, String[] buttonNames) {
        setSize(300, 600);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.RED);
        setTitle("Oskar och Hugo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new MyButton[nButtons];
        for (int i = 0; i < nButtons; i++) {
            buttons[i] = new MyButton(buttonNames[i*2], buttonNames[i*2+1]);
            buttons[i].setBounds(25, 100 + 50*i, 95, 30);
            add(buttons[i]);
        }
        for (int i = 0; i < nButtons; i++) {
            for (int j = 0; j < nButtons; j++) {
                if (i != j) {
                    buttons[i].ear.addButton(buttons[j]);
                }
            }
        }
    }
}

class MyButton extends JButton {
    Color color1 = new Color(255, 0, 0);
    Color color2 = new Color(0, 255, 0);
    String text1;
    String text2;
    Ear ear;
    boolean state = false;

    public MyButton(String name1, String name2) {
        text1 = name1;
        text2 = name2;
        setText(text2);
        setBackground(color1);
        ear = new Ear(this);
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

class Ear implements ActionListener {
    MyButton button;
    public Ear(MyButton btn){
        this.button = btn;
    }

    public void addButton(MyButton button) {
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.toggleState();
    }
}