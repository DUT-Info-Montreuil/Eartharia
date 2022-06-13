package application.vue;

import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.Constante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VuePerso {
	
	private Perso perso;
	protected Image img_perso;
	protected ImageView imgVP;
	protected Pane pane;
	
	
	public VuePerso (Pane pane, Perso perso) {
		this.pane=pane;
		this.img_perso = new Image("ressources/perso/perso_idle_right.png");
		this.imgVP = new ImageView(img_perso);
		this.perso = perso;
		animation();
//		Rectangle r = new Rectangle(16, 16);
//		r.setFill(Color.RED);
//		r.xProperty().bind(perso.getxProperty());
//		r.yProperty().bind(perso.getyProperty());
//		this.pane.getChildren().add(r);
		Affichage();
		bindPosition();
	}
	
	public ImageView getImgV() {
		return this.imgVP;
	}
	private void animation() {
		this.perso.getDeplacement(0).addListener((obs, old, nouv)-> {
			this.pane.getChildren().remove(imgVP);
			if(nouv && perso.getDeplacement()[2].get())
				imgVP=new ImageView(new Image("ressources/perso/perso_jump_left.png"));
			else if(nouv && perso.getDeplacement()[3].get())
				imgVP=new ImageView(new Image("ressources/perso/perso_jump_right.png"));
			else if(nouv)
				imgVP=new ImageView(new Image("ressources/perso/perso_jump_right.png"));
			else
				imgVP=new ImageView(new Image("ressources/perso/perso_jump.png"));
			this.pane.getChildren().add(imgVP);
			bindPosition();
		});
		
		this.perso.getDeplacement(2).addListener((obs, old, nouv)-> {
			this.pane.getChildren().remove(imgVP);
			if(nouv)
				imgVP=new ImageView(new Image("ressources/perso/perso_run_left.gif"));
			else
				imgVP=new ImageView(new Image("ressources/perso/perso_idle_left.png"));
			this.pane.getChildren().add(imgVP);
			bindPosition();
		});
		this.perso.getDeplacement(3).addListener((obs, old, nouv)-> {
			this.pane.getChildren().remove(imgVP);
			if(nouv)
				imgVP=new ImageView(new Image("ressources/perso/perso_run_right.gif"));
			else
				imgVP=new ImageView(new Image("ressources/perso/perso_idle_right.png"));
			this.pane.getChildren().add(imgVP);
			bindPosition();
		});
		
	}
	public void Affichage () {
		this.pane.getChildren().add(imgVP);
	}
	public void bindPosition() {
		this.imgVP.setLayoutX((Constante.view/2)*16);
		this.imgVP.setLayoutY((Constante.view/2)*16);
	}
	
	
}
