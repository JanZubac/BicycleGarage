package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonRemoveBicycle extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;
	private Garage gr;

	public ButtonRemoveBicycle(GarageGUI gui, Database db, Garage gr) {
		super("Remove Bicycle");
		this.gui = gui;
		this.db = db;
		this.gr = gr;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		String SSN = JOptionPane.showInputDialog("Enter SSN");
		if (SSN == null) {
			return;
		}
		String BikeID = JOptionPane.showInputDialog("Enter Bike ID");
		if (BikeID == null) {
			return;
		}
		if (gr.containsBike(BikeID)) {
			gui.text.setText("Error! \n "
					+ "The specified bike is in the garage, unable to remove it from the system");
		}
		Set<Bicycle> set = db.getBicycles(SSN);
		if (set == null) {
			gui.text.setText("Error! \n" + "No user with SSN: " + SSN
					+ " was found");
			return;
		}
		for (Bicycle bike : set) {
			if (bike.getBicID().equals(BikeID)) {
				db.removeBicycle(SSN, bike.getBicID());
				gui.text.setText("Bicycle removed");
			} else {
				gui.text.setText("Error! \n" + "No bicycle with ID: " + BikeID
						+ " was found");
			}
		}

	}

}
