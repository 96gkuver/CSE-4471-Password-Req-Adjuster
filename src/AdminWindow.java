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
	private JPanel userNamePanel;
	private JPanel namePanel;
	private JPanel birthPanel;
	private JPanel enterPanel;

	private JLabel instrLabel;
	private JLabel charLabel;
	private JLabel numLabel;
	private JLabel specialLabel;
	private JLabel userNameLabel;
	private JLabel nameLabel;
	private JLabel birthLabel;
	
	private JTextField charField;
	private JTextField numField;
	private JTextField specialField;
	
	private JRadioButton yesUserNameButton;
	private JRadioButton noUserNameButton;
	private JRadioButton yesNameButton;
	private JRadioButton noNameButton;
	private JRadioButton yesBirthButton;
	private JRadioButton noBirthButton;
	
	private ButtonGroup userNameGroup;
	private ButtonGroup nameGroup;
	private ButtonGroup birthGroup;
	
	private JButton enterButton;
	
	// get instance of current password requirements
	private PasswordReqs passReqs = PasswordReqs.getInstance();

	
	public AdminWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		mainFrame = new JFrame("Password Requirement Adjuster (Admin)");
		mainFrame.setSize(400, 800);
		mainFrame.setLayout(new GridLayout(8, 1));
	
		instrLabel = new JLabel("Enter your password requirements:", JLabel.CENTER);

		// character question
		charPanel = new JPanel();
		charPanel.setLayout(new GridLayout(2, 1));
		
		charLabel = new JLabel("Required amount of characters:");
		charField = new JTextField(Integer.toString(passReqs.getPasswordCharacters()));
		
		charPanel.add(charLabel);
		charPanel.add(charField);
		
		// number question
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(2, 1));
		
		numLabel = new JLabel("Required amount of numbers:");
		numField = new JTextField(Integer.toString(passReqs.getPasswordNumbers()));
		
		numPanel.add(numLabel);
		numPanel.add(numField);
		
		// special character question
		specialPanel = new JPanel();
		specialPanel.setLayout(new GridLayout(2, 1));
		
		specialLabel = new JLabel("Required amount of special characters:");
		specialField = new JTextField(Integer.toString(passReqs.getPasswordSpecials()));
		
		specialPanel.add(specialLabel);
		specialPanel.add(specialField);
		
		// username question
		userNamePanel = new JPanel();
		userNamePanel.setLayout(new GridLayout(3, 1));
		
		userNameLabel = new JLabel("Should usernames be allowed in the password?");
		
		yesUserNameButton = new JRadioButton("yes");
		noUserNameButton = new JRadioButton("no");
		
		if (passReqs.getPasswordHasUserName()){
			yesUserNameButton.setSelected(true);
		}else{
			noUserNameButton.setSelected(true);
		}

		userNameGroup = new ButtonGroup();
		userNameGroup.add(yesUserNameButton);
		userNameGroup.add(noUserNameButton);
		
		userNamePanel.add(userNameLabel);
		userNamePanel.add(yesUserNameButton);
		userNamePanel.add(noUserNameButton);
		
		// name question
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(3, 1));
		
		nameLabel = new JLabel("Should the account holder's name be allowed in the password?");
		
		yesNameButton = new JRadioButton("yes");
		noNameButton = new JRadioButton("no");
		
		if (passReqs.getPasswordHasName()){
			yesNameButton.setSelected(true);
		}else{
			noNameButton.setSelected(true);
		}

		nameGroup = new ButtonGroup();
		nameGroup.add(yesNameButton);
		nameGroup.add(noNameButton);
		
		namePanel.add(nameLabel);
		namePanel.add(yesNameButton);
		namePanel.add(noNameButton);
		
		// birthday question
		birthPanel = new JPanel();
		birthPanel.setLayout(new GridLayout(3, 1));
		
		birthLabel = new JLabel("Should the account holder's birthday be allowed in the password?");
		
		yesBirthButton = new JRadioButton("yes");
		noBirthButton = new JRadioButton("no");

		if (passReqs.getPasswordHasBirth()){
			yesBirthButton.setSelected(true);
		}else{
			noBirthButton.setSelected(true);
		}

		birthGroup = new ButtonGroup();
		birthGroup.add(yesBirthButton);
		birthGroup.add(noBirthButton);
		
		birthPanel.add(birthLabel);
		birthPanel.add(yesBirthButton);
		birthPanel.add(noBirthButton);
		
		// enter button
		enterPanel = new JPanel();
		enterPanel.setLayout(new FlowLayout());
		
		enterButton = new JButton("Adjust Requirements");
		enterButton.setPreferredSize(new Dimension(300, 50));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				passReqs.setPasswordHasUserName(yesUserNameButton.isSelected());
				passReqs.setPasswordHasName(yesNameButton.isSelected());
				passReqs.setPasswordHasBirth(yesBirthButton.isSelected());
							
				String errors = "";
								
				// set password requirements based on input
				try{
					int tempChar = Integer.parseInt(charField.getText());
					passReqs.setPasswordCharacters(tempChar);
				} catch (Exception err){
					errors = errors + "Number of characters must be an integer\n";
					charField.setText("0");
				}
				
				try{
					int tempNum = Integer.parseInt(numField.getText());
					passReqs.setPasswordNumbers(tempNum);
				} catch (Exception err){
					errors = errors + "Number of numbers must be an integer\n";
					numField.setText("0");
				}
	
				try{
					int tempSpec = Integer.parseInt(specialField.getText());
					passReqs.setPasswordSpecials(tempSpec);
				} catch (Exception err){
					errors = errors + "Number of special characters must be an integer\n";
					specialField.setText("0");
				}
				
				if (errors.length() > 0){
					errors = "Your requirements have the following errors:\n\n"+errors+"\n";
					JOptionPane.showMessageDialog(mainFrame, errors, "Requirment Errors", JOptionPane.ERROR_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(mainFrame, "Requirements successfully adjusted!", "Requirements Adjusted", JOptionPane.INFORMATION_MESSAGE);
					mainFrame.dispose();
				}

			}
		});
		
		enterPanel.add(enterButton);
		
		mainFrame.add(instrLabel);
		mainFrame.add(charPanel);
		mainFrame.add(numPanel);
		mainFrame.add(specialPanel);
		mainFrame.add(userNamePanel);
		mainFrame.add(namePanel);
		mainFrame.add(birthPanel);
		mainFrame.add(enterPanel);
		mainFrame.setVisible(true);
	}

}
