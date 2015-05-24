package BicycleGarage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ParkingSpotsButton extends JMenuItem implements ActionListener {
	private GarageGUI gui;
	private Garage gr;

	public ParkingSpotsButton(GarageGUI gui, Database db, Garage gr) {
		super("Amount of spots");
		this.gui = gui;
		this.gr = gr;
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		int a = gr.getEmptySpots();
		gui.text.setText(a + " parking spots available");
	}

}
