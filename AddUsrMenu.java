package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AddUsrMenu extends JMenuItem implements ActionListener {	
	@SuppressWarnings("unused")
	private GarageGUI gui;
	@SuppressWarnings("unused")
	private Database db;
	@SuppressWarnings("unused")
	private Garage gr;
	
	public AddUsrMenu (GarageGUI gui, Database db) {
		super("add");
		this.gui = gui;
		this.db = db;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name");
		if (name == null) {	return; }

		String SSN = JOptionPane.showInputDialog("Enter number");
		if (SSN == null) { return; }

		String Email = JOptionPane.showInputDialog("Enter email");
		if(Email == null) {return; }
 		
	}

}
