package com.storassa.javaee.lezionisci;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Window.Type;

public class InsertClientFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JTextField fiscalCodeTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField noteTextField;

	private Main main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertClientFrame frame = new InsertClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor with Main
	 */
	public InsertClientFrame(Main _main) {
		this();
		main = _main;
	}

	/**
	 * Empty Constructor
	 */
	public InsertClientFrame() {
		setModal(true);
		setType(Type.POPUP);
		setAlwaysOnTop(true);

		initComponents();

	}

	// action performed by OK button
	private void okButtonPressed() {

		if (checkData() == true) {
			Client client = new Client(fiscalCodeTextField.getText());
			client.setAddress(addressTextField.getText());
			client.setName(nameTextField.getText());
			client.setNote(noteTextField.getText());
			client.setPhone(Integer.parseInt(phoneTextField.getText()));
			client.setSurname(surnameTextField.getText());
			main.insertClient(client);
			dispose();
		}
	}

	private boolean checkData() {

		String check = checkFiscalCode(fiscalCodeTextField.getText());
		if (check.length() != 0)
			JOptionPane.showMessageDialog(this, check);
		
		else {
			check = checkName(nameTextField.getText());
			if (check.length() != 0)
				JOptionPane.showMessageDialog(this, check);
			
			else { 
				check = checkName(surnameTextField.getText());
				if (check.length() != 0)
				JOptionPane.showMessageDialog(this, check);
			}
		}
		
		if (check.length() != 0)
			return false;
		
		return true;
	}
	
	private String checkName(String n) {
		if (n.equals(""))
			return "Il nome o il cognome non possono essere nulli";
		else {
		    if (!isAlphaNumeric(n))
		    return "Controlla il nome o il cognome";
		}
		
		return "";
	}
	
	private boolean isAlphaNumeric(String s) {
		String pattern= "^[a-zA-Z]*$";
	    return s.matches(pattern);
	}
	
	private String checkFiscalCode(String cf) {
		int i, s, c;
		String cf2;
		int setdisp[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24,
				23 };
		if (cf.length() == 0)
			return "";
		if (cf.length() != 16)
			return "La lunghezza del codice fiscale non é\n"
					+ "corretta: il codice fiscale dovrebbe essere lungo\n" + "esattamente 16 caratteri.";
		cf2 = cf.toUpperCase();
		for (i = 0; i < 16; i++) {
			c = cf2.charAt(i);
			if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z'))
				return "Il codice fiscale contiene dei caratteri non validi:\n"
						+ "i soli caratteri validi sono le lettere e le cifre.";
		}
		s = 0;
		for (i = 1; i <= 13; i += 2) {
			c = cf2.charAt(i);
			if (c >= '0' && c <= '9')
				s = s + c - '0';
			else
				s = s + c - 'A';
		}
		for (i = 0; i <= 14; i += 2) {
			c = cf2.charAt(i);
			if (c >= '0' && c <= '9')
				c = c - '0' + 'A';
			s = s + setdisp[c - 'A'];
		}
		if (s % 26 + 'A' != cf2.charAt(15))
			return "Il codice fiscale non &egrave; corretto:\n" + "il codice di controllo non corrisponde.";

		return "";
	}

	// -------------------------------------------------
	// CONSTRUCTOR OF FRAME
	// -------------------------------------------------

	private void initComponents() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Inserimento nuovo cliente", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		nameTextField = new JTextField();
		nameTextField.setBounds(97, 39, 166, 20);
		panel_1.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setBounds(11, 42, 46, 14);
		panel_1.add(nameLabel);

		JLabel surnameLabel = new JLabel("Cognome");
		surnameLabel.setBounds(11, 75, 46, 14);
		panel_1.add(surnameLabel);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(97, 70, 166, 20);
		panel_1.add(surnameTextField);
		surnameTextField.setColumns(10);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okButtonPressed();
			}
		});
		okButton.setBounds(440, 270, 89, 23);
		panel_1.add(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(341, 270, 89, 23);
		panel_1.add(cancelButton);

		JLabel fiasclCodeLabel = new JLabel("Codice fiscale");
		fiasclCodeLabel.setBounds(10, 117, 65, 14);
		panel_1.add(fiasclCodeLabel);

		fiscalCodeTextField = new JTextField();
		fiscalCodeTextField.setBounds(97, 114, 166, 20);
		panel_1.add(fiscalCodeTextField);
		fiscalCodeTextField.setColumns(10);

		JLabel addressLabel = new JLabel("Indirizzo");
		addressLabel.setBounds(11, 168, 65, 14);
		panel_1.add(addressLabel);

		JLabel phoneLabel = new JLabel("Telefono");
		phoneLabel.setBounds(11, 204, 46, 14);
		panel_1.add(phoneLabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(97, 165, 166, 20);
		panel_1.add(addressTextField);
		addressTextField.setColumns(10);

		phoneTextField = new JTextField();
		phoneTextField.setBounds(97, 201, 166, 20);
		panel_1.add(phoneTextField);
		phoneTextField.setColumns(10);

		JLabel noteLabel = new JLabel("Note");
		noteLabel.setBounds(288, 42, 46, 14);
		panel_1.add(noteLabel);

		noteTextField = new JTextField();
		noteTextField.setToolTipText("Eventuali note descrittive o warning");
		noteTextField.setBounds(323, 39, 211, 179);
		panel_1.add(noteTextField);
		noteTextField.setColumns(10);
	}
}
