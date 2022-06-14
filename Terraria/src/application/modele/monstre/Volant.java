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
		super(env, x, y,20 , 5, 8, 16, 16,9);
		super.setPeutTomber(false);
	}
	protected Volant(Environnement env, int x, int y, int hp,int vitesse, int atq, int xBox, int yBox,int vision) {
		super(env, x, y,20 , vitesse, atq, xBox, yBox,vision);
		super.setPeutTomber(false);
	}
	@Override
	public void methodeAttaque(Acteur cible) {
		if(!(cible instanceof Monstre)) {
			if(Math.signum(this.caseX()-cible.caseX())<0) {
				this.setDeplacement(2, false);
				this.setDeplacement(3, true);
			}
			else {
				this.setDeplacement(3, false);
				this.setDeplacement(2, true);
			}
			if(Math.signum(this.caseY()-cible.caseY())<0) {
				this.setDeplacement(0, false);
				this.setDeplacement(1, true);
			}
			else {
				this.setDeplacement(1, false);
				this.setDeplacement(0, true);
			}
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
		System.out.println("mouvement");
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
