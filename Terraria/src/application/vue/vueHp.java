package application.vue;

import application.modele.personnage.Perso;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class vueHp {

	private Perso perso;
	private Image imgCoeurPlein;
	private Image imgDemiCoeur;
	private Image imgCoeurVide;

	private ImageView imgVH;
	private TilePane tPane;

	public vueHp(Perso perso, TilePane tPane) {
		this.perso=perso;
		this.tPane=tPane;
		imgCoeurPlein= new Image("ressources/coeur.png", 16, 16, true, true);
		imgDemiCoeur= new Image("ressources/demicoeur.png", 16, 16, true, true);
		imgCoeurVide= new Image("ressources/coeurvide.png", 16, 16, true, true);
		coeurHp();
		refresh();
	}

	public void refresh () {
		System.out.println("refresh");
		tPane.getChildren().clear();
		coeurHp();
	}
	public void coeurHp() {
		System.out.println("hp : " + perso.getHp());
		System.out.println("hpMax : " + perso.getHpMax());
		int decalage = 0;
		if (this.perso.getHp()%2==1) 
			decalage =1;
		for (int i = (perso.getHp())/50; i <perso.getHpMax()/50 -decalage; i++) {
			System.out.println("i : " + i);
			imgVH = new ImageView(imgCoeurVide);
			tPane.getChildren().add(imgVH);
			System.out.println("+Coeur vide");
		}

		if (this.perso.getHp()%2==1) {
			imgVH= new ImageView(imgDemiCoeur);
			tPane.getChildren().add(imgVH);
			System.out.println("+Coeur Moitié");

		}

		for (int j = 0; j < perso.getHp()/50; j++) {
			imgVH = new ImageView(imgCoeurPlein);
			tPane.getChildren().add(imgVH);
			System.out.println("+Coeur plein");

		}
	}

//	public void coeurHp() {
//		int decalage = 0;
//		int pvmax =this.perso.getHpMax()/25;
//		int pv =this.perso.getHp()/25;
//		int pvPerdu = (this.perso.getHpMax()-this.perso.getHp())/25;
//		System.out.println(pvmax);
//		System.out.println(pv);
//		System.out.println(pvPerdu);
//
//		for (int i = 0; i <pvPerdu-pv%2; i+=2) {
//			imgVH = new ImageView(imgCoeurVide);
//			tPane.getChildren().add(imgVH);
//			//Coeur vide
//		}
//		if (pv%2==1) {
//			imgVH= new ImageView(imgDemiCoeur);
//			tPane.getChildren().add(imgVH);
//			//Coeur Moitié
//		}
//		for (int i = 0; i <pv-pv%2; i+=2) {
//			imgVH = new ImageView(imgCoeurPlein);
//			tPane.getChildren().add(imgVH);
//			//Coeur plein
//		}
//	}
}