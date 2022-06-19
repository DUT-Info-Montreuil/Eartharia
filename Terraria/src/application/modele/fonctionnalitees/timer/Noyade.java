package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.Acteur;

public class Noyade extends TimerTask {

	private Acteur a;
	public Noyade(Acteur a) {
		this.a=a;
	}

	@Override
	public void run() {
		a.setPeutNoyer(true);
	}
}
