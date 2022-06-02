package application.modele.fonctionnalitees;

import java.util.TimerTask;

import javafx.scene.control.Label;

public class Description extends TimerTask {

	private Label label ;
	public Description(Label l ) {
		label = l;
	}

	@Override
	public void run() {
		label.setVisible(false);
	}

}
