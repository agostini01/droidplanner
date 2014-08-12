package org.droidplanner.desktop;

import org.droidplanner.desktop.logic.Logic;
import org.droidplanner.desktop.ui.Map;
import org.droidplanner.desktop.ui.MainUI;

public class Application {

	private static Logic logic;
	private static Map map;

	public static void main(String[] args) {
		logic = new Logic();
		map = new Map();
		map.setVisible(true);
		map.setJMenuBar(new MainUI(logic.dronesMap));

		new Thread(logic).start();

		logic.dronesMap.get(0).events.addDroneListener(map);
	}

}
