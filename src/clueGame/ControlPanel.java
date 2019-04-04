/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JFrame{
	
	private JTextField titleBar;

	public ControlPanel() {

		setTitle("Clue Game");
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createLayout();
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			String message = "Hello" + titleBar.getText();
			JOptionPane.showMessageDialog(null, message);
		}
		
	}
	public void createLayout() {
		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		JLabel nameLabel = new JLabel("File");
		JButton nextPlayerButton = new JButton("Next Player");
		JButton makeAccusationButton = new JButton("Make an Accusation");

		titleBar = new JTextField(20);

		nextPlayerButton.addActionListener(new ButtonListener());

		topPanel.add(nameLabel, BorderLayout.NORTH);
		topPanel.add(titleBar, BorderLayout.CENTER);

		add(nextPlayerButton, BorderLayout.SOUTH);
		//add(makeAccusationButton, BorderLayout.SOUTH);

	}
	public static void main(String args[]) {
		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		
	}

}
