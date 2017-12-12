package com.storassa.javaee.lezionisci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class RemoveClientActionListener implements ActionListener {

	Main main;
	Icon icon = null;
	
	public RemoveClientActionListener(Main _main) {
		main = _main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Client> clients = main.getClients();
		List<String> fiscalCodes = new ArrayList<String>();
		for (Client c : clients) 
			fiscalCodes.add(c.getFiscalCode());
		
		Object[] possibilities = fiscalCodes.toArray();
		
		String s = (String)JOptionPane.showInputDialog(
		                    main.getFrame(),
		                    (Object)("Inserisci il codice fiscale"),
		                    "Elimina cliente",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    (Object)"ham");
		
		main.removeClient(s);

	}

}
