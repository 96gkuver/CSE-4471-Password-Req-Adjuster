import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class OptionWindow {
	private JFrame optionFrame;
	
	private JPanel adminPanel;
	private JPanel userPanel;
	
	private JLabel optionLabel;

	private JButton adminButton;
	private JButton userButton;
	
	private Font font = new Font("Arial", Font.PLAIN, 40);
	
	public OptionWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		optionFrame = new JFrame("Password Requirement Adjuster");
		//optionFrame.setSize(400, 400);
		optionFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		optionFrame.setLayout(new GridLayout(3, 1));
		
		optionFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		adminPanel = new JPanel();
		adminPanel.setLayout(new FlowLayout());
		userPanel = new JPanel();
		userPanel.setLayout(new FlowLayout());
		
		optionLabel = new JLabel("What are you?", JLabel.CENTER);
		optionLabel.setFont(font);

		adminButton = new JButton("Admin");
		adminButton.setPreferredSize(new Dimension(600, 100));
		adminButton.setFont(font);
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				new AdminWindow();
			}
		});

		userButton = new JButton("User");
		userButton.setPreferredSize(new Dimension(600, 100));
		userButton.setFont(font);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				new UserWindow();
			}
		});
		
		adminPanel.add(adminButton);
		userPanel.add(userButton);
		
		optionFrame.add(optionLabel);
		optionFrame.add(adminPanel);
		optionFrame.add(userPanel);
		optionFrame.setVisible(true);
	}

}
