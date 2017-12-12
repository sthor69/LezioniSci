package com.storassa.javaee.lezionisci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertLessonActionListener implements ActionListener {
	
	Main main;
	
	public InsertLessonActionListener(Main _main) {
		main = _main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		InsertLessonFrame frame = new InsertLessonFrame(main);
		frame.setVisible(true);
		
	}

}
