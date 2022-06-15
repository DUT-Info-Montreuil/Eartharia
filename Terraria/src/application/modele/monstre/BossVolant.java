package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.item.Projectile;

public class BossVolant extends Volant{
	public BossVolant(Environnement env, int x, int y) {
		super(env, x, y, 150,6, 10, 16, 16,15);
	}
	@Override
	public void methodeAttaque(Acteur cible) {
		if(!(cible instanceof Monstre)) {
			int proximiteX = (int) Math.abs(caseX()-cible.caseX());
			int proximiteY = (int) Math.abs(caseY()-cible.caseY());
			System.out.println(proximiteX);
			System.out.println(proximiteY);
			if(proximiteX>5 && proximiteY>5) {
				super.methodeAttaque(cible);
			}
			else if(proximiteX<5 && proximiteY<5) {
				this.setDeplacement(2, Math.signum(this.caseX()-cible.caseX())<0);
				this.setDeplacement(3, !(Math.signum(this.caseX()-cible.caseX())<0));
				this.setDeplacement(0, Math.signum(this.caseY()-cible.caseY())<0);
				this.setDeplacement(1, !(Math.signum(this.caseY()-cible.caseY())<0));
			}else {
				this.setDeplacement(0, false);
				this.setDeplacement(1, false);
				this.setDeplacement(2, false);
				this.setDeplacement(3, false);
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
