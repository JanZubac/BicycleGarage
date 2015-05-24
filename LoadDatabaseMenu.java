package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class LoadDatabaseMenu extends JMenuItem implements ActionListener {	
	private GarageGUI gui;
	private Database db;
	private Garage gr;
	
	public LoadDatabaseMenu (GarageGUI gui, Database db, Garage gr) {
		super("Load User");
		this.gui = gui;
		this.db = db;
		this.gr = gr;
		addActionListener(this);
	
	}

	public void actionPerformed(ActionEvent e) {
		try {
			db.loadFile("Database.txt");
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		/*try {
			gr.loadFile("Garage.txt");
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}*/
	}

}

