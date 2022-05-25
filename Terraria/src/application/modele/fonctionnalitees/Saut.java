package application.modele.fonctionnalitees;

import java.util.TimerTask;

import application.modele.Acteur;

public class Saut extends TimerTask {

	private Acteur a;
	public Saut(Acteur a) {
		this.a=a;
	}

	@Override
	public void run() {
		a.setSaut(false);
	}
}
