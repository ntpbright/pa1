package src;

public class ConverterUIApp {
	public static void main(String[] args) {
		// create the object and GUI
		UnitConverter unit = new UnitConverter();
		ConverterUI converterUI = new ConverterUI(unit);
	}

}
