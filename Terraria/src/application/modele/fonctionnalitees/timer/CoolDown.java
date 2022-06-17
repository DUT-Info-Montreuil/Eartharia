package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.Acteur;

public class CoolDown extends TimerTask {

	private Acteur a;
	public CoolDown(Acteur a) {
		this.a=a;
	}

	@Override
	public void run() {
		a.setAttaque(true);
	}
}
