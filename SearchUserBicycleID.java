package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SearchUserBicycleID extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;
	@SuppressWarnings("unused")
	private Garage gr;

	public SearchUserBicycleID(GarageGUI gui, Database db) {
		super("Search using BicycleID");
		this.gui = gui;
		this.db = db;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		String BicycleID = JOptionPane.showInputDialog("Enter BicycleID");
		if (BicycleID == null) {
			return;
		}
		Bicycle b = db.getBicycle1(BicycleID);
		if (b == null) {
			gui.text.setText("Error! \n" + "Invalid BicycleID");
			return;
		}
		Set<User> set = db.keySet();
		for (User user : set) {
			Set<Bicycle> set2 = db.get(user);
			if (set2.contains(b)) {
				StringBuilder sb = new StringBuilder();
				sb.append("Name: " + user.getName() + "\n" + "SSN: "
						+ user.getSSN() + "\n" + "Email: " + user.getEmail()
						+ "\n" + "Address: " + user.getAddress());
				sb.append("\n" + "-------------" + "\n");
				sb.append("Bikes: " + "\n");
				Set<Bicycle> set1 = db.getBicycles(user.getSSN());
				for (Bicycle bike : set1) {
					sb.append(bike.getBicID() + "\n");
				}
				gui.text.setText(sb.toString());
			}

		}

	}

}
