package experiment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class ExampleGUI extends JFrame{

	public void GUI_Example()
	{
		// Create a layout with 2 rows
		setLayout(new GridLayout(2,0));
		JPanel panel = createNamePanel();
		add(panel);
		panel = createButtonPanel();
		add(panel);
	}
	private JPanel createNamePanel() {
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements 
		panel.setLayout(new GridLayout(1,2));
		JLabel nameLabel = new JLabel("Name");
		JTextField name = new JTextField(20);
		panel.add(nameLabel);
		panel.add(name);
		panel.setBorder(new TitledBorder (new 
				EtchedBorder(), "Who are you?"));
		return panel;
	}
	private JPanel createButtonPanel() {
		// no layout specified, so this is flow
		JButton agree = new JButton("I agree");
		JButton disagree = new JButton("I disagree");
		JPanel panel = new JPanel();
		panel.add(agree);
		panel.add(disagree);
		return panel;
	}
	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Example");
		frame.setSize(250, 150);
		// Create the JPanel and add it to the JFrame
		ExampleGUI gui = new ExampleGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}

}
