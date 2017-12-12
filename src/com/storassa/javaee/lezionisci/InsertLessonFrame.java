package com.storassa.javaee.lezionisci;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class InsertLessonFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Main main;
	private JTextField priceText;
	private JTextField beginHrText;
	private JTextField endHrText;
	private JTextField placeText;
	private JTextField noteText;
	private Choice fiscalCodeChoice;
	private JPanel clientsPanel;
	JDateChooser dateChooser;
	JLabel clientsLabel;

	private List<Client> clients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertLessonFrame dialog = new InsertLessonFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InsertLessonFrame(Main _main) {
		this();
		main = _main;
		clients = new ArrayList<Client>();
		fillClientChooser();
	}

	/**
	 * Create the dialog.
	 */
	public InsertLessonFrame() {
		initComponents();
	}

	private void addButtonPerformed() {
		Client temp = main.getClientFromFiscalCode(fiscalCodeChoice.getSelectedItem());
		clients.add(temp);
		fillClientPanel();
	}

	private void okButtonPerformed() {
		Lesson lesson = new Lesson();
		lesson.setNote(noteText.getText());
		lesson.setPlace(placeText.getText());
		lesson.setPricePerHour(Double.parseDouble(priceText.getText()));

		try {
			String start = (((JTextField) dateChooser.getDateEditor().getUiComponent()).getText());
			SimpleDateFormat sdfmt1 = new SimpleDateFormat(dateChooser.getDateFormatString());
			Date startDate = sdfmt1.parse(start);
			lesson.setDate(startDate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		main.insertLesson(lesson);
		dispose();

	}

	private void fillClientChooser() {
		for (Client client : main.getClients()) {
			String text = client.getFiscalCode();
			fiscalCodeChoice.add(text);
		}
	}

	private void fillClientPanel() {
		if (clients != null) {
			String clientList = "<html>";
			for (Client client : clients) {
				clientList = clientList + client.getFiscalCode() + "<br>";
			}
			clientList += "</html>";
			clientsLabel.setText(clientList);
		}
		clientsLabel.repaint();
	}

	private void initComponents() {

		setBounds(100, 100, 450, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel fiscalCodeLabel = new JLabel("Inserire il codice fiscale del cliente");
		fiscalCodeLabel.setBounds(10, 16, 175, 14);
		contentPanel.add(fiscalCodeLabel);

		JButton addButton = new JButton("");
		addButton.setToolTipText("Aggiungi il cliente selezionato");
		addButton.setIcon(new ImageIcon(
				InsertLessonFrame.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonPerformed();
			}
		});
		addButton.setBounds(382, 10, 42, 23);
		contentPanel.add(addButton);

		clientsPanel = new JPanel();
		clientsPanel.setBorder(new TitledBorder(null, "Clienti gi\u00E0 selezionati", TitledBorder.LEFT,
				TitledBorder.TOP, null, null));

		clientsPanel.setBounds(27, 63, 379, 117);
		contentPanel.add(clientsPanel);
		clientsPanel.setLayout(null);

		clientsLabel = new JLabel("Nessun cliente selezionato");
		clientsLabel.setVerticalAlignment(SwingConstants.TOP);
		clientsLabel.setBounds(24, 23, 333, 83);
		clientsPanel.add(clientsLabel);
		
		fillClientPanel();

		JLabel dateLabel = new JLabel("Data");
		dateLabel.setBounds(10, 213, 46, 14);
		contentPanel.add(dateLabel);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(45, 210, 108, 20);
		contentPanel.add(dateChooser);

		JLabel priceLabel = new JLabel("Prezzo/hr");
		priceLabel.setBounds(10, 270, 56, 14);
		contentPanel.add(priceLabel);

		priceText = new JTextField();
		priceText.setBounds(76, 267, 86, 20);
		contentPanel.add(priceText);
		priceText.setColumns(10);

		JLabel beginHrLabel = new JLabel("Ora inizio");
		beginHrLabel.setBounds(165, 213, 60, 14);
		contentPanel.add(beginHrLabel);

		beginHrText = new JTextField();
		beginHrText.setBounds(235, 210, 42, 20);
		contentPanel.add(beginHrText);
		beginHrText.setColumns(10);

		JLabel endHrLabel = new JLabel("Ora fine");
		endHrLabel.setBounds(307, 213, 46, 14);
		contentPanel.add(endHrLabel);

		endHrText = new JTextField();
		endHrText.setBounds(364, 210, 42, 20);
		contentPanel.add(endHrText);
		endHrText.setColumns(10);

		placeText = new JTextField();
		placeText.setBounds(290, 267, 86, 20);
		contentPanel.add(placeText);
		placeText.setColumns(10);

		JLabel placeLabel = new JLabel("Luogo");
		placeLabel.setBounds(234, 270, 46, 14);
		contentPanel.add(placeLabel);

		JLabel noteLabel = new JLabel("Note");
		noteLabel.setBounds(10, 353, 46, 14);
		contentPanel.add(noteLabel);

		noteText = new JTextField();
		noteText.setBounds(69, 314, 337, 93);
		contentPanel.add(noteText);
		noteText.setColumns(10);

		fiscalCodeChoice = new Choice();
		fiscalCodeChoice.setBounds(191, 10, 185, 20);
		contentPanel.add(fiscalCodeChoice);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				okButtonPerformed();
			}
		});

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}
}
