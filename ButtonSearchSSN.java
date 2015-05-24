package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonSearchSSN extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;

	public ButtonSearchSSN(GarageGUI gui, Database db) {
		super("Search using SSN");
		this.gui = gui;
		this.db = db;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		String SSN = JOptionPane.showInputDialog("Enter number");
		if (SSN == null) {
			return;
		}
		User user = db.getContactInfo(SSN);
		if (user == null) {
			gui.text.setText("Error!" + "\n"
					+ "The entered SSN did not belong to a registered user");
			return;
		}
		Set<Bicycle> bikes = db.getBicycles(SSN);
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + user.getName() + "\n" + "SSN: " + SSN + "\n"
				+ "Email: " + user.getEmail() + "\n" + "Address: "
				+ user.getAddress() + "\n" + "Pin: " + user.getPin());
		sb.append("\n" + "-------------" + "\n");
		sb.append("Bikes: " + "\n");
		for (Bicycle bike : bikes) {
			sb.append(bike.getBicID() + "\n");
		}
		gui.text.setText(sb.toString());

	}

}
