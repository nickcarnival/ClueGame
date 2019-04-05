/*
 * Nicholas Carnival
 * Jordan Newport
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * This class controls all of the GUI
 */

public class ControlPanel extends JFrame{

	public ControlPanel() {

		setTitle("Clue Game");
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createLayout();
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			System.out.println(action.getSource());
		}
		
	}
	public void createLayout() {
        JLabel label = new JLabel("File");
        JLabel whoseTurn = new JLabel("Whose turn? ");
        JLabel myCards = new JLabel("My Cards");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton makeAccusationButton = new JButton("Make Accusation");
        JButton nextPlayerButton = new JButton("Next Player");

        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        makeAccusationButton.setPreferredSize(new Dimension(300, 50));
        nextPlayerButton.setPreferredSize(new Dimension(300, 50));

        bottomPanel.add(nextPlayerButton);
        bottomPanel.add(makeAccusationButton);


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        topPanel.add(label);

        add(mainPanel);
        setVisible(true);
	}
	public static void main(String args[]) {
		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		
	}

}
