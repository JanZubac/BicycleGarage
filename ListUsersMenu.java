package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Set;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ListUsersMenu extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;
	@SuppressWarnings("unused")
	private Garage gr;

	public ListUsersMenu(GarageGUI gui, Database db) {
		super("List all Users");
		this.gui = gui;
		this.db = db;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Set<User> set = db.keySet();
		if (set.isEmpty() || set == null) {
			gui.text.setText("Error!" + "\n"
					+ "There are no users in database.");
		} else {
			for (User user : set) {
				Set<Bicycle> bikes = db.getBicycles(user.getSSN());
				StringBuilder sb = new StringBuilder();
				sb.append("Name: " + user.getName() + "\n" + "SSN: "
						+ user.getSSN() + "\n" + "Email: " + user.getEmail()
						+ "\n" + "Address: " + user.getAddress());
				sb.append("\n" + "-------------" + "\n");
				sb.append("Bikes: " + "\n");
				for (Bicycle bike : bikes) {
					sb.append(bike.getBicID() + "\n");
				}
				sb.append("\n" + "-------------" + "\n");
				gui.text.append(sb.toString());
			}
		}

	}

}
