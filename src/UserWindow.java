import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.util.StringUtils;

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
		mainFrame = new JFrame("Password Requirement Adjuster (User)");
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
				PasswordReqs passReqs = PasswordReqs.getInstance();
				
				int reqChars = passReqs.getPasswordCharacters();
				int reqNums = passReqs.getPasswordNumbers();
				int reqSpecials = passReqs.getPasswordSpecials();
				
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
				
				if (specialNums < reqSpecials){
					errors = errors+"Not enough special characters (minimum special characters: "+reqSpecials+")\n";
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
		mainFrame.add(namePanel);
		mainFrame.add(passPanel);
		mainFrame.add(enterPanel);
		mainFrame.setVisible(true);
	}

}
