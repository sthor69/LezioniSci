package com.storassa.javaee.lezionisci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyLessonActionListener implements ActionListener {
	
	private Main main;

	public ModifyLessonActionListener (Main _main) {
		main = _main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ModifyLessonFrame frame = new ModifyLessonFrame();
		frame.setVisible(true);

	}

}
