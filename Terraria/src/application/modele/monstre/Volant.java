package application.modele.monstre;

import java.util.Random;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class Volant  extends Monstre{

	public Volant(Environnement env, int x, int y) {
		super(env, x, y,20 , 3, 8, 16, 16,4);
		super.setPeutTomber(false);
		super.setSaut(true);
	}
	protected Volant(Environnement env, int x, int y, int hp,int vitesse, int atq, int xBox, int yBox,int vision) {
		super(env, x, y,20 , vitesse, atq, xBox, yBox,vision);
		super.setPeutTomber(false);
		super.setSaut(true);
	}
	@Override
	public void methodeAttaque(Acteur cible) {
		if(!(cible instanceof Monstre)) {
				this.setDeplacement(2, !(Math.signum(this.caseX()-cible.caseX())<0));
				this.setDeplacement(3, Math.signum(this.caseX()-cible.caseX())<0);
				this.setDeplacement(0, !(Math.signum(this.caseY()-cible.caseY())<0));
				this.setDeplacement(1, Math.signum(this.caseY()-cible.caseY())<0);
		}
		else {
			this.setDeplacement(0, false);
			this.setDeplacement(1, false);
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
	}
	@Override
	public void mouvement() {
		if(new Random().nextInt(100)<10) {
			int trajectoire = (int) super.choixDeplacement();
			if (trajectoire == 0) {
				this.setDeplacement(2, false);
				this.setDeplacement(3, false);
			}
			else if (trajectoire == 1) {
				this.setDeplacement(2, false);
				this.setDeplacement(3, true);
			}
			else {
				this.setDeplacement(3, false);
				this.setDeplacement(2, true);
			}
			
			trajectoire = (int) super.choixDeplacement();
			if (trajectoire == 0) {
				this.setDeplacement(0, false);
				this.setDeplacement(1, false);
			}
			else if (trajectoire == 1) {
				this.setDeplacement(1, false);
				this.setDeplacement(0, true);
			}
			else {
				this.setDeplacement(0, false);
				this.setDeplacement(1, true);
			}
		}
	}
	public void attaque(Acteur cible) {
		cible.dommage(getDegatAttaque()/2);
		for (Acteur acteur : getEnv().aProximiter(this, 1)) {
			if(!(acteur instanceof Monstre)) {
				acteur.dommage(getDegatAttaque()/2);
			}
		}
	}
}
