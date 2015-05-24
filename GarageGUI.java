package BicycleGarage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.FlowLayout;
import java.util.ArrayList;

@SuppressWarnings({ "serial", "unused" })
public class GarageGUI extends JFrame {

	private JFrame frame;
	protected JTextArea text;
	private Database db;
	private String pin;
	private Garage gr;
	private PinCodeTerminal terminal;
	private BarcodeReader entry;
	private BarcodeReader exit;
	private BarcodePrinter printer;
	private ElectronicLock entrylock, exitlock;
	private ArrayList<String> pins;

	public GarageGUI() {
		Database db = new Database();
		gr = new Garage(db);
		terminal = new PinCodeTerminalTestDriver();
		entry = new BarcodeReaderEntryTestDriver();
		exit = new BarcodeReaderExitTestDriver();
		entrylock = new ElectronicLockTestDriver("Entry");
		exitlock = new ElectronicLockTestDriver("Exit");
		printer = new BarcodePrinterTestDriver();
		pins = new ArrayList<String>();
		frame = new JFrame("Bicycle Garage");
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(
				new FlowLayout(FlowLayout.CENTER, 5, 5));

		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		menu.setVisible(true);

		JPanel textpanel = new JPanel();
		text = new JTextArea(13, 35);
		text.setEditable(false);
		textpanel.add(new JScrollPane(text));
		add(textpanel, BorderLayout.CENTER);
		frame.add(textpanel);
		textpanel.setVisible(true);

		JMenu user = new JMenu("User");
		menu.add(user);

		JMenuItem addUsr = new ButtonAddUser(this, db);
		user.add(addUsr);

		JMenuItem rmvUsr = new RemoveUsrMenu(this, db, gr);
		user.add(rmvUsr);

		JMenu srhUsr = new JMenu("Search User");
		user.add(srhUsr);

		JMenuItem srhUsrBic = new SearchUserBicycleID(this, db);
		srhUsr.add(srhUsrBic);

		JMenuItem srhUsrSsn = new ButtonSearchSSN(this, db);
		srhUsr.add(srhUsrSsn);

		JMenuItem srhUsrNam = new JMenuItem("Search using name");
		srhUsr.add(srhUsrNam);

		JMenuItem lstUsr = new ListUsersMenu(this, db);
		user.add(lstUsr);

		JMenu Bicycle = new JMenu("Bicycle");
		menu.add(Bicycle);

		JMenuItem add = new ButtonAddBicycle(this, db);
		Bicycle.add(add);

		JMenuItem rmv = new ButtonRemoveBicycle(this, db, gr);
		Bicycle.add(rmv);

		JMenuItem srch = new JMenuItem("Search");
		Bicycle.add(srch);

		JMenuItem tmPk = new JMenuItem("Time parked");
		Bicycle.add(tmPk);

		JMenu System = new JMenu("System");
		menu.add(System);

		JMenuItem gnrBrc = new JMenuItem("Generate Barcode");
		System.add(gnrBrc);

		JMenuItem maMo = new JMenuItem("Maintenance Mode");
		System.add(maMo);

		JMenuItem save = new SaveDatabaseMenu(this, db, gr);
		System.add(save);

		JMenuItem load = new LoadDatabaseMenu(this, db, gr);
		System.add(load);

		JMenuItem ParkingSpots = new ParkingSpotsButton(this, db, gr);
		System.add(ParkingSpots);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GarageGUI gui = new GarageGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void entryBarcode(String code) {
		if (db.getBicycle(code) != null
				&& pins.contains(db.getUser(code).getPin())) {
			gr.parkBic(code);
		}

	}

	public void entryCharacter(char c) { // 3 misslyckade försök, spärrning i 30
											// sek.
		StringBuilder str = new StringBuilder();
		str.append(c);
		if (str.length() == 4) {
			if (db.legitPin(str.toString())) {
				pins.add(str.toString());
				terminal.lightLED(PinCodeTerminal.GREEN_LED, 3);
				entrylock.open(15);
			} else {
				terminal.lightLED(PinCodeTerminal.RED_LED, 3);
			}
		}

	}

	public void exitBarcode(String code) {
		if (gr.containsBicycle(code)) {
			exitlock.open(15);
		}

	}

	public void printBarCode(String BicID) {
		printer.printBarcode(BicID);
	}
}
/*
 * JMenuItem nbrBic = new JMenuItem("Amount of Bicycles"); Bicycle.add(nbrBic);
 */
