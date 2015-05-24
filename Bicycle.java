package BicycleGarage;

import java.util.Random;

public class Bicycle {
	private String barCode;
	private String bicId;
	//private BarcodePrinter printer;

	public Bicycle() {
		// generateBarCode();
		//printer = new BarcodePrinterTestDriver();
		generateBicID();
		//printer.printBarcode(bicId);

	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String BarCode) {
		barCode = BarCode;
	}

	public String getBicID() {
		return bicId;
	}

	public void setBicID(String BicycleID) {
		bicId = BicycleID;
	}

	protected void generateBarCode() {
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 11; i++) {
			str.append(rand.nextInt(10));
		}
		barCode = str.toString();
	}

	protected void generateBicID() {
		Random rand = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			str.append(rand.nextInt(10));

		}
		bicId = str.toString();
	}

	/*
	 * protected void generateBicID(){ StringBuilder str = new StringBuilder();
	 * for(int i = 6; i < 11; i++){ str.append(barCode.charAt(i)); } bicId =
	 * str.toString(); }
	 */

}
