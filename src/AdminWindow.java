import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class AdminWindow {
	private JFrame mainFrame;
	
	private JPanel charPanel;
	private JPanel numPanel;
	private JPanel specialPanel;
	private JPanel enterPanel;

	private JLabel instrLabel;
	private JLabel charLabel;
	private JLabel numLabel;
	private JLabel specialLabel;
	
	private JTextField charField;
	private JTextField numField;
	private JTextField specialField;
	
	private JButton enterButton;
	
	public AdminWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		mainFrame = new JFrame("Password Requirement Adjuster (Admin)");
		mainFrame.setSize(400, 800);
		mainFrame.setLayout(new GridLayout(5, 1));
	
		instrLabel = new JLabel("Enter your password requirements:", JLabel.CENTER);

		// character question
		charPanel = new JPanel();
		charPanel.setLayout(new GridLayout(2, 1));
		
		charLabel = new JLabel("Required amount of characters:");
		charField = new JTextField();
		
		charPanel.add(charLabel);
		charPanel.add(charField);
		
		// number question
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(2, 1));
		
		numLabel = new JLabel("Required amount of numbers:");
		numField = new JTextField();
		
		numPanel.add(numLabel);
		numPanel.add(numField);
		
		// special character question
		specialPanel = new JPanel();
		specialPanel.setLayout(new GridLayout(2, 1));
		
		specialLabel = new JLabel("Required amount of special characters:");
		specialField = new JTextField();
		
		specialPanel.add(specialLabel);
		specialPanel.add(specialField);
		
		// enter button
		enterPanel = new JPanel();
		enterPanel.setLayout(new FlowLayout());
		
		enterButton = new JButton("Adjust Requirements");
		enterButton.setPreferredSize(new Dimension(300, 50));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				mainFrame.dispose();
			}
		});
		
		enterPanel.add(enterButton);
		
		mainFrame.add(instrLabel);
		mainFrame.add(charPanel);
		mainFrame.add(numPanel);
		mainFrame.add(specialPanel);
		mainFrame.add(enterPanel);
		mainFrame.setVisible(true);
	}

}
