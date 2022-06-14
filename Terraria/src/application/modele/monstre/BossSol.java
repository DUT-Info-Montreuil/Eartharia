package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossSol extends Monstre {
	

	public BossSol(Environnement env, int x, int y, Perso p) {
		super(env, x, y, 1, 20, 10, 16, 16, p);
	}

	@Override
	public void agir() {
		this.mouvement(this.getPerso());
	}

	public void mouvement(Perso p) {
		try {
			super.tombe(8);
		} catch (Exception e) {}
		
		if(Math.abs(this.getX() - p.getX()) < 100 ) {
//			System.out.println(p.getX() + " position perso" + this.getX() + " position monstre");
//			System.out.println(p.getHp() + " HP PERSO");

			
			if(!proximiteMouvement(p) ) {
			try {
			if (this.getY() + 32 > p.getY())
				this.saut();
			if (this.getX() + 32 > p.getX())
				this.gauche();
			if (this.getX() + 32 < p.getX())
				this.droite();
		} catch (Exception e) {}
			}
		}
	}

}
