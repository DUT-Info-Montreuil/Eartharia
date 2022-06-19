package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.Acteur;
import application.vue.VueMenuJeux;

public class MenuTimer extends TimerTask {

	private VueMenuJeux a;
	public MenuTimer(VueMenuJeux a) {
		this.a=a;
	}

	@Override
	public void run() {
		a.utilisation();
	}
}
