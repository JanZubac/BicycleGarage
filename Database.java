package BicycleGarage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Database {
	protected HashMap<User, Set<Bicycle>> db;
	@SuppressWarnings("unused")
	private int size;
	private ArrayList<String> array;

	public Database() {
		db = new HashMap<User, Set<Bicycle>>();
		size = 0;
		array = new ArrayList<String>();
	}

	/*
	 * Adds user if no bicycle is specified.
	 */
	public void addUser(User user) { // FIXAD
		db.put(user, new HashSet<Bicycle>());
		size++;
	}

	public void loadFile(String file) throws IOException {
		BufferedReader saveFile = new BufferedReader(new FileReader(file));
		String name, SSN, email, address, pin, temp1;
		String temp = saveFile.readLine();
		while (!temp.equals("END")) {
			// saveFile.
			// while(saveFile.readLine() != "END"){
			name = temp;
			SSN = saveFile.readLine();
			email = saveFile.readLine();
			address = saveFile.readLine();
			pin = saveFile.readLine();
			User user = new User(name, SSN, email, address);
			addUser(user);
			user.setPin(pin);
			temp1 = saveFile.readLine();
			while (!temp1.equals("NO ")) { // klarar bara ladda en cykel.
				Bicycle b = new Bicycle();
				b.setBicID(temp1);
				// b.setBarCode(saveFile.readLine());
				addBicycle(user, b);
				temp1 = saveFile.readLine();
			}
			temp = saveFile.readLine();
		}
		saveFile.close();

	}

	public void saveFile(String file) throws IOException {
		FileWriter saveFile = new FileWriter(new File(file));
		Set<User> set = db.keySet();
		// saveFile.write("Database \r\n");
		for (User user : set) {
			saveFile.write(user.getName() + "\r\n");
			// saveFile.
			saveFile.write(user.getSSN() + "\r\n");
			saveFile.write(user.getEmail() + "\r\n");
			saveFile.write(user.getAddress() + "\r\n");
			saveFile.write(user.getPin() + "\r\n");
			Set<Bicycle> set1 = db.get(user);
			for (Bicycle b : set1) {
				saveFile.write(b.getBicID() + "\r\n");
				// saveFile.write(b.getBarCode() + "\r\n");
			}
			saveFile.write("NO \r\n");
		}
		saveFile.write("END");
		saveFile.close();
	}

	public Set<Bicycle> get(User user) {
		Set<Bicycle> set = db.get(user);
		return set;
	}

	/*
	 * Attaches a bicycle to user. If no user is present user is added as well.
	 */
	public String addBicycle(User user, Bicycle bicycle) { // FIXAD borde kanske
															// lägga till så att
															// barcode
															// returneras.
		Set<Bicycle> set = db.get(user);
		if (set.size() < 3) {
			while (array.contains(bicycle.getBicID())) {
				bicycle.generateBicID();
			}
			array.add(bicycle.getBicID());
			db.get(user).add(bicycle);
			return bicycle.getBicID();
		} else {
			return null;
		}

	}

	public boolean legitPin(String PinCode) {
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getPin().equals(PinCode)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Removes user and all bicycles bound to user.
	 */
	public void removeUser(User user) { // FIXAD
		db.remove(user);
		size--;
	}

	public void removeBicycle(String SSN, String BicycleID) { // FIXAD LEGIT
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getSSN().equals(SSN)) {
				Set<Bicycle> set1 = db.get(user);
				for (Bicycle b : set1) {
					if (b.getBicID().equals(BicycleID)) {
						set1.remove(b);
						db.put(user, set1);
					}
				}
			}
		}

	}

	/*
	 * Returns users name if user is present in the database.
	 */
	public User getContactInfo(String SSN) { // FIXAD
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getSSN().equals(SSN)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(String BicID) {
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> b = db.get(user);
			for (Bicycle a : b) {
				if (a.getBicID().equals(BicID)) {
					return user;
				}
			}

		}
		return null;
	}

	/*
	 * Returns an array of all users with String name.
	 */
	/*
	 * public ArrayList<User> getUser(String name) { // FIXAD Set<User> set =
	 * db.keySet(); ArrayList<User> array = new ArrayList<User>(); for (User
	 * user : set) { if (user.getName().equals(name)) { array.add(user); } }
	 * return array; }
	 */

	/*
	 * Returns the PinCode of the user with String SSN.
	 */
	public String getPinCode(String SSN) {// FIXAD
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getSSN().equals(SSN)) {
				return user.getPin();
			}
		}
		return null;
	}

	/*
	 * public String getSSNUser(String Name) { Set<User> set = db.keySet(); for
	 * (User user : set) { if (user.getName().equals(Name)) { return
	 * user.getSSN(); } } return null; }
	 */
	/*
	 * Returns the owner of the bicycle with barcodes social security number.
	 */

	public Bicycle getBicycle(String BicID) { // Fixad
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> set1 = db.get(user);
			for (Bicycle b : set1) {
				if (b.getBicID().equals(BicID)) {
					return b;
				}
			}

		}
		return null;

	}
/*
	public Bicycle getBicycle1(String BicycleID) { // Fixad
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> set1 = db.get(user);
			for (Bicycle b : set1) {
				if (b.getBicID().equals(BicycleID)) {
					return b;
				}
			}

		}
		return null;

	}
*/
	public Set<Bicycle> getBicycles(String SSN) { // Fixad
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getSSN().equals(SSN)) {
				return db.get(user);
			}
		}
		return null;
	}

	public String getBicycleUser(String BicID) { // FIXAD
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> b = db.get(user);
			for (Bicycle a : b) {
				if (a.getBicID().equals(BicID)) {
					return user.getSSN();
				}
			}

		}
		return null;
	}

	/*public String getbarCodeUser(String BicId) { // FIXAD
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> b = db.get(user);
			for (Bicycle a : b) {
				if (a.getBicID().equals(BicId)) {
					return user.getSSN();
				}
			}

		}
		return null;

	}*/

	public Set<User> keySet() {
		return db.keySet();
	}

}
