package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;

public class volant  extends Monstre{

	public volant(Environnement env, int x, int y) {
		super(env, x, y, 1, 20, 10, 16, 16);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void agir() {//se deplace et attaquer 
//		try {
//			if(this.env.getTemp() % 4 == 0)
//			super.tombe(16);
//			
//		} catch (Exception e) {
//
//		}
		//System.out.println("id" + this.getIdMonstre());
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
