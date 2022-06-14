package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
import application.modele.item.Projectile;

public class BossVolant extends Volant{
	public BossVolant(Environnement env, int x, int y) {
		super(env, x, y, 150,6, 10, 16, 16,15);
	}
	@Override
	public void methodeAttaque(Acteur cible) {
		boolean gardeDistance = Math.abs(this.caseX()-cible.caseX())==15;
		if(!(cible instanceof Monstre)) {
			if (gardeDistance) {
				boolean direction = Math.signum(this.caseX()-cible.caseX())<0;
				this.setDeplacement(2, !direction);
				this.setDeplacement(3, direction);
				direction = Math.signum(this.caseY()-cible.caseY())<0;
				this.setDeplacement(0, !direction);
				this.setDeplacement(1, direction);
			}
		}
		else {
			this.setDeplacement(0, false);
			this.setDeplacement(1, false);
			this.setDeplacement(2, false);
			this.setDeplacement(3, false);
		}
	}
	public void attaque(Acteur cible) {
		Projectile p = new Projectile(11, cible.getX(), cible.getY(),this);
		getEnv().addListProjectiles(p);
	}
	//	@Override
	//	public void agir() {
	////		this.mouvement(this.perso);
	//	}
	//
	//	public void mouvement (Perso p) {
	//		System.out.println("MOUVEMENT");
	//		System.out.println(p);
	//		if(this.caseY() > p.caseY()) {
	//			System.out.println("Iffffffffffffff");
	//			try {
	//				this.saut();
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				System.out.println("ERREUR SAUT");
	//			}
	//		}
	////		if(this.caseY() > p.caseY()|| this.caseY() < p.caseY() ) {
	////			try {
	////				this.tombe(2);
	////			} catch (Exception e) {
	////				// TODO Auto-generated catch block
	////				System.out.println("ERREUR TOMBE");
	////
	////			}
	////		}
	//		if(this.caseX()+1 > p.caseX()) {
	//			try {
	//				this.gauche();
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				System.out.println("ERREUR GAUCHE");
	//
	//			}
	//		}
	//		if(this.caseX() < p.caseX()) {
	//			try {
	//				this.droite();
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				System.out.println("ERREUR DROITE");
	//
	//			}
	//		}
	//		
	//	}


}
