/*
 * Object that has a single instance for holding current password requirements
 */
public class PasswordReqs {
	
	private static PasswordReqs instance;
	
	private int passwordCharacters = 0;
	private int passwordNumbers = 0;
	private int passwordSpecials = 0;
	
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

}
