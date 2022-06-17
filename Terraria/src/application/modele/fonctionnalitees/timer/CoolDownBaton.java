package application.modele.fonctionnalitees.timer;

import java.util.TimerTask;

import application.modele.Acteur;
import application.modele.item.BatonMagique;
import application.modele.item.Projectile;

public class CoolDownBaton extends TimerTask {

	private BatonMagique a;
	public CoolDownBaton(BatonMagique a) {
		this.a=a;
	}

	@Override
	public void run() {
		a.setSize(true);
	}
}
