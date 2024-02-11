import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class RPS extends JFrame implements ActionListener {

	static final HashMap<String, Integer> map = new HashMap<String, Integer>() {{
		put("STEN", 0);
		put("SAX", 1);
		put("PASE", 2);
	}};

	Gameboard myboard, computersboard;
	int counter = 0; // To count ONE ... TWO and on THREE you play
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	JButton closebutton;
	RPSClient client;

	class ExitListener implements ActionListener {
		ExitListener() {}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	RPS() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		closebutton = new JButton("Close");
		closebutton.addActionListener(new ExitListener());
		client = new RPSClient("Player");
		myboard = new Gameboard("Player", this);
		computersboard = new Gameboard("Computer");
		JPanel boards = new JPanel();
		boards.setLayout(new GridLayout(1, 2));
		boards.add(myboard);
		boards.add(computersboard);
		add(boards, BorderLayout.CENTER);
		add(closebutton, BorderLayout.SOUTH);
		setSize(350, 650);
		setVisible(true);
	}

	public static void main(String[] u) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new RPS();
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch (counter) {
			case 0:
				myboard.resetColor();
				computersboard.resetColor();
				myboard.setLower("ONE");
				computersboard.setLower("ONE");
				break;
			case 1:
				myboard.setLower("TWO");
				computersboard.setLower("TWO");
				break;
			case 2:
				String choice = button.getActionCommand();
				client.send(choice);
				myboard.markPlayed(choice);
				myboard.setUpper(choice);
				String computerChoice = client.receive();
				computersboard.markPlayed(computerChoice);
				computersboard.setUpper(computerChoice);
				switch (result(choice, computerChoice)) {
					case "win":
						myboard.wins();
						myboard.setLower("WIN!");
						computersboard.setLower("LOSE!");
						break;
					case "lose":
						computersboard.wins();
						myboard.setLower("LOSE!");
						computersboard.setLower("WIN!");
						break;
					case "draw":
						myboard.setLower("DRAW!");
						computersboard.setLower("DRAW!");
						break;
					default:
						System.out.println("Error in result");
				}
				break;
			default:
				break;
		}
		counter = (counter + 1) % 3;
	}

	private String result(String choice1, String choice2) {
		System.out.println(choice1 + choice2);
		int c1Number = map.get(choice1);
		int c2Number = map.get(choice2);
		System.out.println(c1Number + " " + c2Number);
		if ((c1Number + 1) % 3 == c2Number)
			return "win";
		else if ((c1Number - 1) % 3 == c2Number)
			return "lose";
		else
			return "draw";
	}
}