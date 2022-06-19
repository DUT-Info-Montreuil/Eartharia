package application.modele.acteur;

import java.util.Random;
import java.util.Timer;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.fonctionnalitees.timer.CoolDown;
import application.modele.fonctionnalitees.timer.Saut;

public abstract class Monstre extends Acteur{
	private int vision;
	private int type;
	public Monstre(Environnement env, int x, int y, int hp,int vitesse, int atq, int xBox, int yBox, int vision) {
		super(env, x, y,hp,vitesse, xBox, yBox,atq);
		this.vision=vision;
	}

	@Override
	public void agir() {
		Acteur cible = getEnv().plusProche(this, vision);
		if(cible!=null) {
			methodeAttaque(cible);
			if(peutAttaquer()) {
				new Timer().schedule(new CoolDown(this), 1500);
				attaque(cible);
				setAttaque(false);
			}
		}
		else
			this.mouvement();
		super.agir();
	}
	public abstract void attaque(Acteur cible);
	public abstract void mouvement();
	public int choixDeplacement() {
		int r = new Random().nextInt(3);
		if(r==2)
			return -1;
		else if(r==1)
			return 1;
		else
			return 0;
	}
	public abstract void methodeAttaque(Acteur cible) ;
}


/*	public boolean proximiteMouvement (Perso p) { 
			return Math.abs(this.getX()) <=16;
		}
		public boolean proximiteAttaque (Perso p) { 
			return Math.abs(this.getX() - p.getX()) <=16;		
		}
		public void mouvement (Perso p) throws Exception {
			if(!proximiteMouvement(p) ) {
				try {
					if (this.getEnv().getTemp() % 30 == 0)
						this.droite();
					if (this.getEnv().getTemp() % 30 == 3) 
						this.gauche();
				} catch (Exception e) {}
			}else 
				throw new Exception() ;
		}
		public void attaquer(Perso p) {
			if(this.getEnv().getTemp() %40 == 0) {
				if(proximiteAttaque(p)) {
				p.dommage(this.getDegatAttaque());
				}
			}
		}
 */

