 package application.vue;

import java.io.FileInputStream;

import application.modele.Item;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class vueCraft {
	private ObservableList<Item> inventaire;
	private ObservableList<Item> craft;
	private GridPane craftPane;
	private Image img_item;
	private Image img_bloc;
	private boolean visibility;

	public vueCraft(GridPane craftPane,ObservableList<Item> inventaire,ObservableList<Item> craft){
		this.craftPane=craftPane;
		this.visibility = false;
		this.inventaire=inventaire;		
		initItem();
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
}
