package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonAddBicycle extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;

	public ButtonAddBicycle(GarageGUI gui, Database db) {
		super("Add Bicycle");
		this.gui = gui;
		this.db = db;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Bicycle bike = new Bicycle();
		String SSN = JOptionPane.showInputDialog("Enter owners SSN");
		if (SSN == null) {
			return;
		} else {
			User user = db.getContactInfo(SSN);
			if (user == null) {
				gui.text.setText("Error!" + "\n"
						+ "The entered SSN did not belong to a registered user");
				return;
			}
			String barCode = db.addBicycle(user, bike);
			if (barCode == null) {
				gui.text.setText("This user already has three bicycles registered.");
			} else {
				gui.text.setText("Bike added to user with SSN: " + SSN);
				gui.printBarCode(bike.getBicID());
				

			}
		}
	}
}
