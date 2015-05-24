package BicycleGarage;

import java.util.Random;


public class User {
	private String Name;
	private String SSN;
	private String Email;
	private String Address;
	private String Pin;
	

	public User(String Name, String SSN, String Email, String Address) {
		this.Name = Name;
		this.SSN = SSN;
		this.Email = Email;
		this.Address = Address;
		generatePin();	

	}

	public String getName() {
		return Name;
	}

	public String getSSN() {
		return SSN;

	}

	public String getEmail() {
		return Email;
	}

	public String getAddress() {
		return Address;
	}

	public String getPin() {
		return Pin;
	}
	
	public void setPin(String PinCode) {
		Pin = PinCode;
	}
	

	private void generatePin() {
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			str.append(rand.nextInt(10));

		}
		Pin = str.toString();
	}

	/*
	 * public static void main(String[] args) { User Filip = new User("Filip",
	 * "1", "asd", "aewe"); Filip.generatePin(); System.out.println(Filip.Pin);
	 * }
	 */
}