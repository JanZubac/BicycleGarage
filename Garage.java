package BicycleGarage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Garage {
	private HashMap<User, Set<Bicycle>> Garage;
	private int size;
	// private HashMap<User, Set<Bicycle>> db;
	private Database db;

	public Garage(Database db) {
		Garage = new HashMap<User, Set<Bicycle>>();
		this.db = db;
		size = 0;
	}

	public void loadFile(String file) throws IOException { // KAN EJ TESTAS ÄN.
		BufferedReader saveFile = new BufferedReader(new FileReader(file));
		String SSN, BicID, temp1;
		String temp = saveFile.readLine();
		while (!temp.equals("END")) {
			SSN = temp;
			User user = db.getContactInfo(SSN);
			temp1 = saveFile.readLine();
			while (!temp1.equals("NO")) {
				BicID = saveFile.readLine();
				add(user, db.getBicycle1(BicID));
				temp1 = saveFile.readLine();
			}

		}
		saveFile.close();

	}

	public Set<User> keySet() {
		return Garage.keySet();

	}

	public void saveFile(String file) throws IOException {
		FileWriter saveFile = new FileWriter(new File(file));
		Set<User> set = Garage.keySet();
		for (User user : set) {
			saveFile.write(user.getSSN() + "\r\n");
			Set<Bicycle> set1 = Garage.get(user);
			for (Bicycle b : set1) {
				saveFile.write(b.getBicID() + "\r\n");
			}
			saveFile.write("NO");
		}
		saveFile.write("END");
		saveFile.close();
	}

	public void add(User user, Bicycle b) { // Används i load.
		Set<Bicycle> set = Garage.get(user);
		set.add(b);
		Garage.put(user, set);
		size++;
	}

	/*
	 * public void parkBic(String PinCode, String BarCode) { // TÄNK PÅ PINKOD
	 * // BOIS?!?!?!?!??!?!?!?!?! Set<User> set = db.keySet(); for (User user :
	 * set) { Set<Bicycle> set1 = db.get(user); if
	 * (user.getPin().equals(PinCode) && set1.contains(BarCode)) { for (Bicycle
	 * b : set1) { if (b.getBarCode().equals(BarCode) && set1.size() < 3) {
	 * Garage.get(user).add(b); size++; } } }
	 * 
	 * } }
	 */

	public void parkBic(String BicID) {
		User user = db.getUser(BicID);
		Set<Bicycle> set = db.get(user);
		Bicycle b = db.getBicycle(BicID);
		set.add(b);
		Garage.put(user, set);

	}

	public boolean containsBicycle(String BarCode) {
		Set<User> set = Garage.keySet();
		for (User user : set) {
			Set<Bicycle> set1 = Garage.get(user);
			for (Bicycle b : set1) {
				if (b.getBarCode().equals(BarCode)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Returns the amount of available parking spots in the garage.
	 */
	public int getEmptySpots() {
		return 300 - size;
	}

	/*
	 * Returns true if there are available parking spots in the garage.
	 */
	public boolean availableSpots() {
		return 300 - size > 0;
	}

	/*
	 * Removes bicycle from the garage
	 */
	public void unparkBic(String PinCode, String BarCode) {
		Set<User> set = Garage.keySet();
		for (User user : set) {
			if (user.getPin().equals(PinCode)) {
				Set<Bicycle> set1 = Garage.get(user);
				for (Bicycle b : set1) {
					if (b.getBarCode().equals(BarCode)) {
						set1.remove(b);
						Garage.put(user, set1);
						size--;
					}
				}
			}
		}
	}

	public boolean containsKey(User user) {
		if (Garage.containsKey(user)) {
			return true;
		}
		return false;
	}

	/*
	 * Returns true if the bicycle with the ID bicycleID is currently in the
	 * garage.
	 */
	public boolean containsBike(String bicycleID) {
		Set<User> set = Garage.keySet();
		for (User user : set) {
			Set<Bicycle> set1 = Garage.get(user);
			for (Bicycle b : set1) {
				if (b.getBicID().equals(bicycleID)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean correctPin(String PinCode) {
		Set<User> set = db.keySet();
		for (User user : set) {
			if (user.getPin().equals(PinCode)) {
				return true;
			}
		}
		return false;
	}

	public boolean correctPin1(String PinCode) {
		Set<User> set = Garage.keySet();
		for (User user : set) {
			if (user.getPin().equals(PinCode)) {
				return true;
			}
		}
		return false;
	}

	public Set<Bicycle> get(User user) {
		Set<Bicycle> set = Garage.get(user);
		return set;
	}
}

// Database db = new Database();
// Garage gr = new Garage(db);
/*
 * while (saveFile.readLine() != "Garage") { name = saveFile.readLine(); SSN =
 * saveFile.readLine(); email = saveFile.readLine(); address =
 * saveFile.readLine(); pin = saveFile.readLine(); User user = new User(name,
 * SSN, email, address); db.addUser(user); user.setPin(pin); if
 * (saveFile.readLine() != "\n") { Bicycle b = new Bicycle();
 * b.setBicID(saveFile.readLine()); b.setBarCode(saveFile.readLine());
 * db.addBicycle(user, b); } }
 */

// Set<User> set = db.keySet();
/*
 * saveFile.write("Database"); for (User user : set) {
 * saveFile.write(user.getName() + "\n"); saveFile.write(user.getSSN() + "\n");
 * saveFile.write(user.getEmail() + "\n"); saveFile.write(user.getAddress() +
 * "\n"); saveFile.write(user.getPin() + "\n"); Set<Bicycle> set1 =
 * db.get(user); if (set1 == null) { saveFile.write("NO"); } for (Bicycle b :
 * set1) { saveFile.write(b.getBicID() + "\n"); saveFile.write(b.getBarCode() +
 * "\n"); } }
 */