package application.modele.monstre;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;

public class BossVolant extends Monstre{
	private Perso perso;

	public BossVolant(Environnement env, int x, int y, Perso p) {
		super(env, x, y, 1, 20, 10, 16, 16, p);
		this.perso = p;
	}

	@Override
	public void agir() {
		this.mouvement(this.perso);
		
	}

	public void mouvement (Perso p) {
		System.out.println("MOUVEMENT");
		System.out.println(p);
		if(this.caseY() > p.caseY()) {
			System.out.println("Iffffffffffffff");
			try {
				this.saut();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ERREUR SAUT");
			}
		}
//		if(this.caseY() > p.caseY()|| this.caseY() < p.caseY() ) {
//			try {
//				this.tombe(2);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println("ERREUR TOMBE");
//
//			}
//		}
		if(this.caseX()+1 > p.caseX()) {
			try {
				this.gauche();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ERREUR GAUCHE");

			}
		}
		if(this.caseX() < p.caseX()) {
			try {
				this.droite();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ERREUR DROITE");

			}
		}
		
	}

}
