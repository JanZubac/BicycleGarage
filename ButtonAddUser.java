package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonAddUser extends JMenuItem implements ActionListener {

	private GarageGUI gui;
	private Database db;

	public ButtonAddUser(GarageGUI gui, Database db) {
		super("Add User");
		this.gui = gui;
		this.db = db;
		addActionListener(this);

	}

	public boolean correctFormat(String SSN) {
		char character;
		if (SSN.length() != 11) {
			return false;
		}
		for (int i = 0; i <= 10; i++) {
			character = SSN.charAt(i);
			if (i != 6) {
				if (!Character.isDigit(character)) {
					return false;
				}
			} else {
				String a = "-";
				char b = a.charAt(0);
				if (Character.compare(character, b) != 0) {
					return false;
				}
			}
		}
		return true;

	}

	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name");
		if (name == null) {
			return;
		}

		String SSN = JOptionPane
				.showInputDialog("Enter social security number");

		if (SSN == null) {
			return;
		}
		if (!correctFormat(SSN)) {
			gui.text.setText("Social security number was in an incorrect format.");
			return;
		}

		String Email = JOptionPane.showInputDialog("Enter email");
		if (Email == null) {
			return;
		}

		String Address = JOptionPane.showInputDialog("Enter Address");
		if (Address == null) {
			return;
		}

		User user = new User(name, SSN, Email, Address);
		db.addUser(user);
		gui.text.setText("User registered");
		// Skriv ut PIN-kod osv.
	}

}
