package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;

public class Sol extends Monstre {

	public Sol(Environnement env, int x, int y) {
		super(env, x, y,1 , 20, 10, 16, 16);
		// TODO Auto-generated constructor stub
	}

//	public void mouvementDroite() {
//		this.x.set(getX() + 8);
//	}
//
//	public void mouvementGauche() {
//		this.x.set(getX() - 8);
//	}
//
//	public void mouvementHaut() {
//		this.y.set(getY() - 8);
//	}
//
//	public void mouvementBas() {
//		this.y.set(getY() + 8);
//	}

	@Override
	public void agir() {//se deplace et attaquer 
		//System.out.println("HP : " + this.getHp());
		//System.out.println("ID : " + this.getId());
		System.out.println("SOL : " + this.getId());

		try {
			if(this.env.getTemp() % 4 == 0)
			super.tombe(16);
			
		} catch (Exception e) {

		}
		try {
			if (this.env.getTemp() % 20 == 0) {
				super.droite();
			}
			if (this.env.getTemp() % 20 == 1) {
				this.gauche();
			}
		} catch (Exception e) {
			
		}
	}
	@Override
	public void attaquer(Acteur a) {
		// TODO Auto-generated method stub
		
	}

}
