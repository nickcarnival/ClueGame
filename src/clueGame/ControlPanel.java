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
        JLabel board = new JLabel("board");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("File");

        JPanel topPanel = new JPanel();
        JPanel boardPanel = new JPanel();

        JPanel cardPanel = new JPanel();
        JLabel whoseTurn = new JLabel("Whose turn? ");
        JLabel myCards = new JLabel("My Cards");

        JPanel peopleCardPanel = new JPanel();
        JLabel peopleCardLabel = new JLabel("People Cards");

        JPanel weaponCardPanel = new JPanel();
        JLabel weaponCardLabel = new JLabel("Weapon Cards");

        JPanel roomCardPanel = new JPanel();
        JLabel roomCardLabel = new JLabel("Room Cards");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton makeAccusationButton = new JButton("Make Accusation");
        JButton nextPlayerButton = new JButton("Next Player");

        topPanel.add(label);
        
        boardPanel.add(board);
        
        board.setPreferredSize(new Dimension(400, 400));
        cardPanel.setPreferredSize(new Dimension(100, 680));

        //setting border colors
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        weaponCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        peopleCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        roomCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));



        //adding each type of card to the card panel
        cardPanel.add(weaponCardPanel, BorderLayout.NORTH);
        cardPanel.add(peopleCardPanel, BorderLayout.CENTER);
        cardPanel.add(roomCardPanel, BorderLayout.SOUTH);

        weaponCardPanel.add(weaponCardLabel);
        peopleCardPanel.add(peopleCardLabel);
        roomCardPanel.add(roomCardLabel);

        cardPanel.add(whoseTurn);

        //setting the button size
        makeAccusationButton.setPreferredSize(new Dimension(300, 50));
        nextPlayerButton.setPreferredSize(new Dimension(300, 50));

        bottomPanel.add(nextPlayerButton);
        bottomPanel.add(makeAccusationButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);
        setVisible(true);
	}
	public static void main(String args[]) {
		ControlPanel cp = new ControlPanel();
		cp.setVisible(true);
		
	}

}
