package com.storassa.javaee.lezionisci;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ModifyClientFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JPanel mainPanel;
	private Main main;
	List<JPanel> panels;
	List<JLabel> labels;
	List<JLabel> fiscalCodeLabels;
	List<JButton> deleteButtons;
	List<JButton> modifyButtons;
	JPanel singleClientPanel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModifyClientFrame dialog = new ModifyClientFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModifyClientFrame(Main _main) {
		this();
		main = _main;
		
		panels = new ArrayList<JPanel>();
		labels = new ArrayList<JLabel>();
		fiscalCodeLabels = new ArrayList<JLabel>();
		deleteButtons = new ArrayList<JButton>();
		modifyButtons = new ArrayList<JButton>();
		
		reload();
		
	}
	
	private void reload() {
		mainPanel.removeAll();

		for (Client client : main.getClients()) {
			
			singleClientPanel = new JPanel();
			mainPanel.add(singleClientPanel);
			singleClientPanel.setLayout(new BoxLayout(singleClientPanel, BoxLayout.X_AXIS));
			
			JLabel fiscalCodeLabel = new JLabel(client.getFiscalCode() + "          ");
			fiscalCodeLabels.add(fiscalCodeLabel);
			JButton deleteButton = new JButton("Rimuovi");
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = deleteButtons.indexOf(e.getSource());
					String fiscCode = fiscalCodeLabels.get(index).getText().trim();
					main.removeClient(fiscalCodeLabels.get(index).getText().trim());
					reload();
				}
			});
			
			deleteButtons.add(deleteButton);
			JButton modifyButton = new JButton("Modifica");
			modifyButtons.add(modifyButton);
			
			singleClientPanel.add(fiscalCodeLabel);
			singleClientPanel.add(deleteButton);
			singleClientPanel.add(modifyButton);
			
			mainPanel.add(singleClientPanel);
		}
		
		mainPanel.repaint();

	}

	/**
	 * Create the dialog.
	 */
	public ModifyClientFrame() {
		setBounds(100, 100, 450, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Elenco dei clienti inseriti a sistema", TitledBorder.LEFT,
				TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		mainPanel = new JPanel();
		contentPanel.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
	}
}
