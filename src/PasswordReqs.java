/*
 * Object that has a single instance for holding current password requirements
 */
public class PasswordReqs {
	
	private static PasswordReqs instance;
	
	private int passwordCharacters = 0;
	private int passwordNumbers = 0;
	private int passwordSpecials = 0;
	
	private boolean passwordHasUserName = true;
	private boolean passwordHasName = true;
	private boolean passwordHasBirth = true;
	
	private PasswordReqs(){
		
	}
	
	static {
		instance = new PasswordReqs();
	}
	
	public static PasswordReqs getInstance() {
		return instance;
	}
	
	public int getPasswordCharacters(){
		return passwordCharacters;
	}
	
	public void setPasswordCharacters(int characters){
		passwordCharacters = characters;
	}
	
	public int getPasswordNumbers(){
		return passwordNumbers;
	}
	
	public void setPasswordNumbers(int numbers){
		passwordNumbers = numbers;
	}
	
	public int getPasswordSpecials(){
		return passwordSpecials;
	}
	
	public void setPasswordSpecials(int specials){
		passwordSpecials = specials;
	}
	
	public boolean getPasswordHasUserName(){
		return passwordHasUserName;
	}
	
	public void setPasswordHasUserName(boolean hasUserName){
		passwordHasUserName = hasUserName;
	}
	
	public boolean getPasswordHasName(){
		return passwordHasName;
	}
	
	public void setPasswordHasName(boolean hasName){
		passwordHasName = hasName;
	}
	
	public boolean getPasswordHasBirth(){
		return passwordHasBirth;
	}
	
	public void setPasswordHasBirth(boolean hasBirth){
		passwordHasBirth = hasBirth;
	}

}
