import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UserWindow {
	private JFrame mainFrame;
	
	private JPanel namePanel;
	private JPanel passPanel;
	private JPanel enterPanel;

	private JLabel instrLabel;
	private JLabel nameLabel;
	private JLabel passLabel;
	
	private JTextField nameField;
	private JTextField passField;
	
	private JButton enterButton;
	
	public UserWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		mainFrame = new JFrame("Password Requirement Adjuste (User)");
		mainFrame.setSize(400, 600);
		mainFrame.setLayout(new GridLayout(4, 1));
	
		instrLabel = new JLabel("Create an account:", JLabel.CENTER);

		// username prompt
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2, 1));
		
		nameLabel = new JLabel("Create a username:");
		nameField = new JTextField();
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		// password prompt
		passPanel = new JPanel();
		passPanel.setLayout(new GridLayout(2, 1));
		
		passLabel = new JLabel("Create a password:");
		passField = new JTextField();
		
		passPanel.add(passLabel);
		passPanel.add(passField);
		
		// enter button
		enterPanel = new JPanel();
		enterPanel.setLayout(new FlowLayout());
		
		enterButton = new JButton("Create Account");
		enterButton.setPreferredSize(new Dimension(300, 50));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				mainFrame.dispose();
			}
		});
		
		enterPanel.add(enterButton);
		
		mainFrame.add(instrLabel);
		mainFrame.add(namePanel);
		mainFrame.add(passPanel);
		mainFrame.add(enterPanel);
		mainFrame.setVisible(true);
	}

}
