import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class UserWindow {
	private JFrame mainFrame;
	
	private JPanel firstNamePanel;
	private JPanel lastNamePanel;
	private JPanel birthPanel;
	private JPanel userNamePanel;
	private JPanel passPanel;
	private JPanel enterPanel;

	private JLabel instrLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel birthLabel;
	private JLabel userNameLabel;
	private JLabel passLabel;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JFormattedTextField birthField;
	private JTextField userNameField;
	private JTextField passField;
	
	private JButton enterButton;
	
	public UserWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("## / ## / ####");//the # is for numeric values
            mask.setPlaceholderCharacter('#');
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		mainFrame = new JFrame("Password Requirement Adjuster (User)");
		mainFrame.setSize(400, 600);
		mainFrame.setLayout(new GridLayout(7, 1));
	
		instrLabel = new JLabel("Create an account:", JLabel.CENTER);

		// first name prompt
		firstNamePanel = new JPanel();
		firstNamePanel.setLayout(new GridLayout(2, 1));
		
		firstNameLabel = new JLabel("Enter your first name:");
		firstNameField = new JTextField();
		
		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(firstNameField);
		
		// last name prompt
		lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new GridLayout(2, 1));
		
		lastNameLabel = new JLabel("Enter your last name:");
		lastNameField = new JTextField();
		
		lastNamePanel.add(lastNameLabel);
		lastNamePanel.add(lastNameField);

		// date of birth prompt
		birthPanel = new JPanel();
		birthPanel.setLayout(new GridLayout(2, 1));
		
		birthLabel = new JLabel("Enter your birthday:");
		birthField = new JFormattedTextField(mask);
		
		birthPanel.add(birthLabel);
		birthPanel.add(birthField);

		// user name prompt
		userNamePanel = new JPanel();
		userNamePanel.setLayout(new GridLayout(2, 1));
		
		userNameLabel = new JLabel("Create a user name:");
		userNameField = new JTextField();
		
		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameField);
		
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
				PasswordReqs passReqs = PasswordReqs.getInstance();
				
				int reqChars = passReqs.getPasswordCharacters();
				int reqNums = passReqs.getPasswordNumbers();
				int reqSpecials = passReqs.getPasswordSpecials();
				
				boolean reqHasUserName = passReqs.getPasswordHasUserName();
				boolean reqHasName = passReqs.getPasswordHasName();
				boolean reqHasBirth = passReqs.getPasswordHasBirth();
				
				String curPassword = passField.getText();
				
				String errors = "";
				
				// check if password is the greater than the minimum length
				if (curPassword.length() < reqChars){
					errors = errors+"Not long enough (minimum characters: "+reqChars+")\n";
				}
				
				// count amount of numbers in password
				int passNums = 0;
				for (int x = 0; x < curPassword.length(); x++){
					if (Character.isDigit(curPassword.charAt(x))){
						passNums += 1;
					}
				}
				
				// check if password has greater than the minimum number of numbers
				if (passNums < reqNums){
					errors = errors+"Not enough numbers (minimum numbers: "+reqNums+")\n";
				}
				
				// count amount of numbers in password
				int specialNums = 0;
				for (int x = 0; x < curPassword.length(); x++){
					if (!Character.isDigit(curPassword.charAt(x)) && !Character.isAlphabetic(curPassword.charAt(x))){
						specialNums += 1;
					}
				}
				
				// check if password has greater than the minimum number of special characters
				if (specialNums < reqSpecials){
					errors = errors+"Not enough special characters (minimum special characters: "+reqSpecials+")\n";
				}
				
				// if the password cannot contain the account holder's user name
				if (!reqHasUserName){
					if (curPassword.toLowerCase().contains(userNameField.getText().toLowerCase())){
						errors = errors+"Password cannot contain your user name\n";
					}
				}
				
				// if the password cannot contain the account holder's name
				if (!reqHasName){
					if (curPassword.toLowerCase().contains(firstNameField.getText().toLowerCase()) 
							|| curPassword.toLowerCase().contains(lastNameField.getText().toLowerCase())){
						errors = errors+"Password cannot contain your first name or last name\n";
					}
				}
				
				// if password cannot contain the account holder's birthday
				if (!reqHasBirth){
					String birthDate = birthField.getText().substring(0, 2)+birthField.getText().substring(5, 7)+birthField.getText().substring(10, 14);
					String monthDay = birthField.getText().substring(0, 2)+birthField.getText().substring(5, 7);
					String year = birthField.getText().substring(10, 14);
					
					if (curPassword.contains(birthDate) || curPassword.contains(monthDay) || curPassword.contains(year)){
						errors = errors+"Password cannot contain your birthday in any form (month/day, year, or actual date)\n";
					}
				}
				
				if (errors.length() > 0){
					errors = "Your password has the following errors:\n\n"+errors+"\n";
					JOptionPane.showMessageDialog(mainFrame, errors, "Password Errors", JOptionPane.ERROR_MESSAGE);
					
					passField.setText("");
				}else{
					JOptionPane.showMessageDialog(mainFrame, "Account successfully created!", "Account Created", JOptionPane.INFORMATION_MESSAGE);
					mainFrame.dispose();
				}
				
			}
		});
		
		enterPanel.add(enterButton);
		
		mainFrame.add(instrLabel);
		mainFrame.add(firstNamePanel);
		mainFrame.add(lastNamePanel);
		mainFrame.add(birthPanel);
		mainFrame.add(userNamePanel);
		mainFrame.add(passPanel);
		mainFrame.add(enterPanel);
		mainFrame.setVisible(true);
	}

}
