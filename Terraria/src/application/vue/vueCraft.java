 package application.vue;

import java.io.FileInputStream;

import application.modele.Item;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.fonctionnalitees.ItemFonctionnalite;
import application.modele.fonctionnalitees.Tableau;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class vueCraft {
	private ObservableList<Item> craft;
	private TilePane craftPane;
	private Image img_item;
	private Image img_bloc;
	private boolean visibility;

	public vueCraft(TilePane craftPane,ObservableList<Item> craft){
		this.craftPane=craftPane;
		this.craft=craft;
		this.visibility = true;
		craftPane.setLayoutX(100);
		craftPane.setLayoutY(100);
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
	public void ouvFerCraft(boolean peutCraft) {
		if(peutCraft || visibility==true) {
			visibility=!visibility;
			this.craftPane.setVisible(visibility);
			if(visibility) 
				this.craftPane.toFront();
			else
				this.craftPane.toBack();
		}
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
