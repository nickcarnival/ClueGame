/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MakeAGuess extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JPanel leftPanel = new JPanel(new GridLayout(2, 0));
	private JPanel rightPanel = new JPanel(new GridLayout(2, 0));

	public MakeAGuess() {
		setTitle("Make A Guess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private void createLayout() {
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
	}
}
