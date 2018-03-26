import java.awt.Dimension;
import java.awt.FlowLayout;
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
	
	public OptionWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		optionFrame = new JFrame("Password Requirement Adjuster");
		optionFrame.setSize(400, 400);
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

		adminButton = new JButton("Admin");
		adminButton.setPreferredSize(new Dimension(300, 50));
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				new AdminWindow();
			}
		});

		userButton = new JButton("User");
		userButton.setPreferredSize(new Dimension(300, 50));
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
