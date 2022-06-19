package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.acteur.Perso;

public class Noyer extends TimerTask {

	private Perso p;
	public Noyer(Perso p) {
		this.p=p;
	}

	@Override
	public void run() {
		if(p.getOxygene()>0)
			p.getOxyProperty().set(p.getOxygene()-3);
	}
}
