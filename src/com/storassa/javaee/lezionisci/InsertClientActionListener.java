package com.storassa.javaee.lezionisci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertClientActionListener implements ActionListener {
	
	Main main;
	
	public InsertClientActionListener (Main _main) {
		main = _main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		InsertClientFrame frame = new InsertClientFrame(main);
		frame.setVisible(true);
	}
}
