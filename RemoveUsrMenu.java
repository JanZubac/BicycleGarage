package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class RemoveUsrMenu extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Database db;
	private Garage gr;

	public RemoveUsrMenu(GarageGUI gui, Database db, Garage gr) {
		super("Remove User");
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
		User user = db.getContactInfo(SSN);
		if (!gr.containsKey(user)) {
			db.removeUser(user);
			gui.text.setText("User Removed");
		} else {
			gui.text.setText("User has bicycles in the garage and cannot be removed.");
		}

	}

}
