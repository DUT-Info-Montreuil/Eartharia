package application.vue;

import java.io.FileInputStream;
import java.util.Iterator;

import application.modele.Item;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.fonctionnalitees.Constante;
import application.modele.fonctionnalitees.ItemFonctionnalite;
import application.modele.fonctionnalitees.Tableau;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueCraft {
	private ObservableList<Item> craft;
	private TilePane craftPane;
	private Image img_item;
	private Image img_bloc;
	private boolean visibility;

	public VueCraft(TilePane craftPane,ObservableList<Item> craft){
		this.craftPane=craftPane;
		this.craft=craft;
		paneSet();
		background();
		initItem();
	}
	private void paneSet() {
		this.craftPane.setPrefSize(84, 148);
		this.visibility = false;
		craftPane.setVisible(visibility);
		for (int iterator = 0;iterator<=((Pane) this.craftPane.getParent()).getChildren().size(); iterator++) {
			this.craftPane.toBack();
		}
	}
	private void initItem(){
		try {
			FileInputStream fichierTileSet  = new FileInputStream("src/ressources/equipement.png");
			this.img_item = new Image(fichierTileSet);
			fichierTileSet = new FileInputStream("src/ressources/TuileMap.png");
			this.img_bloc = new Image(fichierTileSet);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void background() {
		Image img = new Image("ressources/CraftMenu.png");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		craftPane.setBackground(bGround);	
		craftPane.setLayoutX((Constante.view/2)*16-craftPane.getPrefWidth()*2);
		craftPane.setLayoutY((Constante.view/2)*16-craftPane.getPrefHeight()/2);
	}
	public void ouvFerCraft(boolean peutCraft){
		if(peutCraft) {
			visibility=!visibility;
			this.craftPane.setVisible(visibility);
			if(visibility)
				for (int iterator = 0;iterator<=((Pane) this.craftPane.getParent()).getChildren().size(); iterator++) {
					this.craftPane.toFront();
					System.out.println("front");
		}
			else
				for (int iterator = 0;iterator<=((Pane) this.craftPane.getParent()).getChildren().size(); iterator++) 
					this.craftPane.toBack();
		}
	}
	public boolean pause() {
		return visibility;
	}

	private void afficherItem(int idItem,String id,int dimention, ImageView img) {
		int y = (int) (idItem/(img.getImage().getHeight()/dimention));
		int x = (int) (idItem%(img.getImage().getWidth()/dimention));
		x = x*dimention;
		y = y*dimention;
		img.setViewport(new Rectangle2D(x,y, dimention,dimention));
		img.setFitHeight(32);
		img.setFitWidth(32);
		img.setId(id);
		craftPane.getChildren().add(img);
	}
	public void afficherItemOutils(int idOutils,String id) {
		afficherItem(idOutils, id, 32, new ImageView(img_item));
	}
	public void afficherItemBloc(int idTuile,String id) {
		afficherItem(idTuile-1,id, 16,new ImageView(img_bloc));
	}

	public Item getItem(ImageView img)throws ItemNonTrouverException {
		for (Item item : craft) {
			if(item.getId()==img.getId())
				return item;
		}
		throw new ItemNonTrouverException();
	}
}
