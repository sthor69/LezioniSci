package com.storassa.javaee.lezionisci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModifyClientActionListener implements ActionListener {

	private Main main;
	
	public ModifyClientActionListener(Main _main) {
		main = _main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ModifyClientFrame frame = new ModifyClientFrame(main);
		frame.setVisible(true);

	}

}
