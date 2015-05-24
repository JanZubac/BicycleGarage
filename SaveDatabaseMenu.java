package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class SaveDatabaseMenu extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	private Database db;
	
	private Garage gr;

	public SaveDatabaseMenu(GarageGUI gui, Database db, Garage gr) {
		super("Save Database");
		this.gui = gui;
		this.db = db;
		this.gr = gr;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			db.saveFile("Database.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		/*try {
			gr.saveFile("Garage.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/

	}

}
