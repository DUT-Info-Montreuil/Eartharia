package application.vue;

import application.modele.Item;
import application.modele.personnage.Perso;
import javafx.collections.ObservableList;
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
		tPane.getChildren().clear();
		coeurHp();
	}
	
	public void coeurHp() {
		
		for (int i = perso.getHpMax()-1; i > perso.getHp(); i=i-2) {
			this.perso.limiteHp();
			imgVH = new ImageView(imgCoeurVide);
			tPane.getChildren().add(imgVH);
		}
		
		if (this.perso.getHp()%2==1) {
			imgVH= new ImageView(imgDemiCoeur);
			tPane.getChildren().add(imgVH);
		}
		
		for (int i = 0; i < perso.getHp()-1; i=i+2) {
			imgVH = new ImageView(imgCoeurPlein);
			tPane.getChildren().add(imgVH);
		}
	}
}