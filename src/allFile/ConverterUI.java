package allFile;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.nio.file.attribute.GroupPrincipal;

/**
 * 
 * @author Nuttapatprom CHongamorkulprapa
 * 
 *
 */
public class ConverterUI extends JFrame {
	/**
	 * All attribute
	 */
	private JPanel contentPane;
	private JButton convertButton;
	private UnitConverter unitConverter;
	private JTextField inputField;
	private JTextField outputField;
	private JComboBox<Unit> fromUnitBox;
	private JComboBox<Unit> toUnitBox;
	private JMenu mnUnitType;
	private JMenuItem mntmLength;
	private JMenuItem mntmArea;
	private JMenuItem mntmWeight;
	private JMenuItem mntmVolume;
	private JMenuItem mntmExit;
	private UnitFactory unitFactory;
	private UnitType[] unitTypes;
	private Unit[] unitLength;
	private Unit[] unitArea;
	private Unit[] unitWeight;
	private Unit[] unitVolume;
	private String keepFromLeft = "";
	private String keepFromRight = "";

	/**
	 * Launch the application.
	 * @param uc is object UnitConverter for program 
	 */
	public ConverterUI(UnitConverter uc) {
		this.unitConverter = uc;
		this.setTitle("Length Converter");
		initComponents();
	}

	/**
	 * Initialize compinents GUI
	 */
	private void initComponents() {
		// all of part about GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 108);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnUnitType = new JMenu("Unit Type");
		menuBar.add(mnUnitType);

		mntmLength = new JMenuItem("Length");
		mnUnitType.add(mntmLength);
		mntmLength.addActionListener(new ChangeUnitToLength());

		mntmArea = new JMenuItem("Area");
		mnUnitType.add(mntmArea);
		mntmArea.addActionListener(new ChangeUnitToArea());

		mntmWeight = new JMenuItem("Weight");
		mnUnitType.add(mntmWeight);
		mntmWeight.addActionListener(new ChangeUnitToWeight());

		mntmVolume = new JMenuItem("Volume");
		mnUnitType.add(mntmVolume);
		mntmVolume.addActionListener(new ChangeUnitToVolume());

		mntmExit = new JMenuItem("Exit");
		mnUnitType.add(mntmExit);
		mntmExit.addActionListener(new forButtonExit());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		unitTypes = unitFactory.getInstance().getUnitTypes();
		unitLength = unitTypes[0].getUnit();
		unitArea = unitTypes[1].getUnit();
		unitWeight = unitTypes[2].getUnit();
		unitVolume = unitTypes[3].getUnit();

		inputField = new JTextField();
		inputField.setBounds(10, 11, 150, 23);
		contentPane.add(inputField);
		inputField.setColumns(10);

		fromUnitBox = new JComboBox<Unit>(unitLength);
		fromUnitBox.setBounds(163, 11, 122, 23);
		contentPane.add(fromUnitBox);

		JLabel labeleq = new JLabel("=");
		labeleq.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labeleq.setBounds(290, 15, 16, 12);
		contentPane.add(labeleq);

		outputField = new JTextField();
		outputField.setBounds(309, 11, 150, 23);
		contentPane.add(outputField);
		outputField.setColumns(10);

		toUnitBox = new JComboBox<Unit>(unitLength);
		toUnitBox.setBounds(465, 11, 122, 23);
		contentPane.add(toUnitBox);

		JButton convertButton = new JButton("Convert!");
		convertButton.setBounds(589, 11, 89, 23);
		contentPane.add(convertButton);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(682, 11, 89, 23);
		contentPane.add(btnClear);

		ButtonGroup groupRadio = new ButtonGroup();

		this.setVisible(true);

		// all of part about listener
		ActionListener convertLst = new ConvertListener();
		KeyListener keyLst = new ConvertListener();
		convertButton.addActionListener(convertLst);
		inputField.addActionListener(convertLst);
		outputField.addActionListener(convertLst);
		inputField.addKeyListener(keyLst);
		outputField.addKeyListener(keyLst);

		ActionListener claerLst = new ClearButtonListener();
		btnClear.addActionListener(claerLst);

	}

	class ChangeUnitToLength implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fromUnitBox.removeAllItems();
			toUnitBox.removeAllItems();
			for (Unit u : unitLength)
				fromUnitBox.addItem(u);
			;
			for (Unit u : unitLength)
				toUnitBox.addItem(u);
			;
		}

	}

	class ChangeUnitToArea implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fromUnitBox.removeAllItems();
			toUnitBox.removeAllItems();
			for (Unit u : unitArea)
				fromUnitBox.addItem(u);
			;
			for (Unit u : unitArea)
				toUnitBox.addItem(u);
			;
		}

	}

	class ChangeUnitToWeight implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fromUnitBox.removeAllItems();
			toUnitBox.removeAllItems();
			for (Unit u : unitWeight)
				fromUnitBox.addItem(u);
			;
			for (Unit u : unitWeight)
				toUnitBox.addItem(u);
			;
		}

	}

	class ChangeUnitToVolume implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fromUnitBox.removeAllItems();
			toUnitBox.removeAllItems();
			for (Unit u : unitVolume)
				fromUnitBox.addItem(u);
			;
			for (Unit u : unitVolume)
				toUnitBox.addItem(u);
			;
		}
	}

	class forButtonExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}

	/**
	 * 
	 * Convert method invoke this method by action listener and key listener
	 */
	class ConvertListener implements ActionListener , KeyListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String getFromLeft = inputField.getText();
			String getFromRight = outputField.getText();
			if (!getFromLeft.equals(keepFromLeft)) {
				String s = inputField.getText().trim();
				if (s.length() > 0) {
					try {
						inputField.setForeground(Color.BLACK);
						double value = Double.valueOf(s);
						Unit fromUnit = (Unit) fromUnitBox.getSelectedItem();
						Unit toUnit = (Unit) toUnitBox.getSelectedItem();
						double valueOut = unitConverter.convert(value, fromUnit, toUnit);
						outputField.setText(String.valueOf(String.format("%.5g", valueOut)));
					} catch (NumberFormatException exception) {
						inputField.setForeground(Color.RED);
					}
				}
			}else if (!getFromRight.equals(keepFromRight)) {
				String s = outputField.getText().trim();
				if (s.length() > 0) {
					try {
						outputField.setForeground(Color.BLACK);
						double value = Double.valueOf(s);
						Unit toUnit = (Unit) fromUnitBox.getSelectedItem();
						Unit fromUnit = (Unit) toUnitBox.getSelectedItem();
						double valueOut = unitConverter.convert(value, fromUnit, toUnit);
						outputField.setText(String.valueOf(String.format("%.5g", valueOut)));
					} catch (NumberFormatException exception) {
						outputField.setForeground(Color.red);
					}
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			String getFromLeft = inputField.getText();
			String getFromRight = outputField.getText();
			if (!getFromLeft.equals(keepFromLeft)) {
				String s = inputField.getText().trim();
				if (s.length() > 0) {
					try {
						inputField.setForeground(Color.BLACK);
						double value = Double.valueOf(s);
						Unit fromUnit = (Unit) fromUnitBox.getSelectedItem();
						Unit toUnit = (Unit) toUnitBox.getSelectedItem();
						double valueOut = unitConverter.convert(value, fromUnit, toUnit);
						outputField.setText(String.valueOf(valueOut));
						keepFromRight = outputField.getText();
					} catch (NumberFormatException exception) {
						inputField.setForeground(Color.RED);
					}
				}
			}else if (!getFromRight.equals(keepFromRight)) {
				String s = outputField.getText().trim();
				if (s.length() > 0) {
					try {
						outputField.setForeground(Color.BLACK);
						double value = Double.valueOf(s);
						Unit toUnit = (Unit) fromUnitBox.getSelectedItem();
						Unit fromUnit = (Unit) toUnitBox.getSelectedItem();
						double valueOut = unitConverter.convert(value, fromUnit, toUnit);
						inputField.setText(String.valueOf(valueOut));
						keepFromLeft = inputField.getText();
					} catch (NumberFormatException exception) {
						outputField.setForeground(Color.red);
					}
				}
			}
			if(inputField.getText().equals("")){
				keepFromLeft = ""; 
			}else if(outputField.getText().equals("")){
				keepFromRight = "";
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	/**
	 * 
	 * @author ntpch this method clear text field by invoke from action listener
	 */
	class ClearButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			inputField.setText("");
			outputField.setText("");
		}
	}
}