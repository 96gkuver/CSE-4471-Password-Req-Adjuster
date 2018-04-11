import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;


public class AdminWindow {
	private JFrame mainFrame;
	
	private JPanel charPanel;
	private JPanel numPanel;
	private JPanel specialPanel;

	private JPanel userNamePanel;
	private JPanel userNameYesNoPanel;
	private JPanel namePanel;
	private JPanel nameYesNoPanel;
	private JPanel birthPanel;
	private JPanel birthYesNoPanel;

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
	
	private Font font = new Font("Arial", Font.PLAIN, 30);
	
	public AdminWindow(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		mainFrame = new JFrame("Password Requirement Adjuster (Admin)");
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setLayout(new GridLayout(8, 1));
	
		instrLabel = new JLabel("Enter your password requirements:", JLabel.CENTER);
		instrLabel.setFont(font);

		// character question
		charPanel = new JPanel();
		charPanel.setLayout(new GridLayout(2, 1));
		
		charLabel = new JLabel("Required amount of characters:");
		charLabel.setFont(font);
		charField = new JTextField(Integer.toString(passReqs.getPasswordCharacters()));
		charField.setFont(font);
		
		charPanel.add(charLabel);
		charPanel.add(charField);
		
		// number question
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(2, 1));
		
		numLabel = new JLabel("Required amount of numbers:");
		numLabel.setFont(font);
		numField = new JTextField(Integer.toString(passReqs.getPasswordNumbers()));
		numField.setFont(font);
		
		numPanel.add(numLabel);
		numPanel.add(numField);
		
		// special character question
		specialPanel = new JPanel();
		specialPanel.setLayout(new GridLayout(2, 1));
		
		specialLabel = new JLabel("Required amount of special characters:");
		specialLabel.setFont(font);
		specialField = new JTextField(Integer.toString(passReqs.getPasswordSpecials()));
		specialField.setFont(font);
		
		specialPanel.add(specialLabel);
		specialPanel.add(specialField);
		
		// username question
		userNamePanel = new JPanel();
		userNamePanel.setLayout(new GridLayout(2, 1));
		
		userNameYesNoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		userNameLabel = new JLabel("Should usernames be allowed in the password?");
		userNameLabel.setFont(font);
		
		yesUserNameButton = new JRadioButton("yes");
		yesUserNameButton.setFont(font);
		noUserNameButton = new JRadioButton("no");
		noUserNameButton.setFont(font);
		
		if (passReqs.getPasswordHasUserName()){
			yesUserNameButton.setSelected(true);
		}else{
			noUserNameButton.setSelected(true);
		}

		userNameGroup = new ButtonGroup();
		userNameGroup.add(yesUserNameButton);
		userNameGroup.add(noUserNameButton);
		
		userNamePanel.add(userNameLabel);

		userNameYesNoPanel.add(yesUserNameButton);
		userNameYesNoPanel.add(noUserNameButton);
		
		userNamePanel.add(userNameYesNoPanel);
		
		// name question
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2, 1));
		
		nameYesNoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		nameLabel = new JLabel("Should the account holder's name be allowed in the password?");
		nameLabel.setFont(font);
		
		yesNameButton = new JRadioButton("yes");
		yesNameButton.setFont(font);
		noNameButton = new JRadioButton("no");
		noNameButton.setFont(font);
		
		if (passReqs.getPasswordHasName()){
			yesNameButton.setSelected(true);
		}else{
			noNameButton.setSelected(true);
		}

		nameGroup = new ButtonGroup();
		nameGroup.add(yesNameButton);
		nameGroup.add(noNameButton);
		
		namePanel.add(nameLabel);

		nameYesNoPanel.add(yesNameButton);
		nameYesNoPanel.add(noNameButton);
		
		namePanel.add(nameYesNoPanel);
		
		// birthday question
		birthPanel = new JPanel();
		birthPanel.setLayout(new GridLayout(2, 1));
		
		birthYesNoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		birthLabel = new JLabel("Should the account holder's birthday be allowed in the password?");
		birthLabel.setFont(font);
		
		yesBirthButton = new JRadioButton("yes");
		yesBirthButton.setFont(font);
		noBirthButton = new JRadioButton("no");
		noBirthButton.setFont(font);

		if (passReqs.getPasswordHasBirth()){
			yesBirthButton.setSelected(true);
		}else{
			noBirthButton.setSelected(true);
		}

		birthGroup = new ButtonGroup();
		birthGroup.add(yesBirthButton);
		birthGroup.add(noBirthButton);
		
		birthPanel.add(birthLabel);

		birthYesNoPanel.add(yesBirthButton);
		birthYesNoPanel.add(noBirthButton);
		
		birthPanel.add(birthYesNoPanel);
		
		// enter button
		enterPanel = new JPanel();
		enterPanel.setLayout(new FlowLayout());
		
		enterButton = new JButton("Adjust Requirements");
		enterButton.setPreferredSize(new Dimension(500, 100));
		enterButton.setFont(font);
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
					errors = errors + "Number of characters must be an integer<br>";
					charField.setText("0");
				}
				
				try{
					int tempNum = Integer.parseInt(numField.getText());
					passReqs.setPasswordNumbers(tempNum);
				} catch (Exception err){
					errors = errors + "Number of numbers must be an integer<br>";
					numField.setText("0");
				}
	
				try{
					int tempSpec = Integer.parseInt(specialField.getText());
					passReqs.setPasswordSpecials(tempSpec);
				} catch (Exception err){
					errors = errors + "Number of special characters must be an integer<br>";
					specialField.setText("0");
				}
				
				UIManager.put("OptionPane.buttonFont", new FontUIResource(font));
				if (errors.length() > 0){
					errors = "<html><body>Your requirements have the following errors:<br><br>"+errors+"<br></body></html>";
					JLabel temp = new JLabel(errors);
					temp.setFont(font);
					JOptionPane.showMessageDialog(mainFrame, temp, "Requirment Errors", JOptionPane.ERROR_MESSAGE);
					
				}else{
					JLabel temp = new JLabel("Requirements successfully adjusted!");
					temp.setFont(font);
					JOptionPane.showMessageDialog(mainFrame, temp, "Requirements Adjusted", JOptionPane.INFORMATION_MESSAGE);
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
