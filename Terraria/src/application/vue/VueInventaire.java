package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.modele.Item;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

	private ObservableList<Item> inventaire;
	private Image img_inventaire;
	private TilePane tPane;
	private boolean visibility;

	public VueInventaire(TilePane tPane,  ObservableList<Item> inventaire){
		this.tPane=tPane;
		this.visibility = false;
		this.inventaire=inventaire;		
		paneSet();
		try {
			initItem();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void paneSet() {
		this.tPane.setPrefSize(128, 128);
		this.tPane.setVisible(false);
		background();
	}
	
	public void ouvFerInv() {
		this.tPane.setVisible(visibility);
		visibility=!visibility;
	}

	private void background() {
		Image img = new Image("ressources/Inventaire.png");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		tPane.setBackground(bGround);
	}
	public void initItem() throws FileNotFoundException {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/equipment.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.img_inventaire = new Image(fichierTileSet);
	}
	public void afficherItem(int id) {
		int x;
		int y;
		y = (int) (id/(img_inventaire.getHeight()/32));
		x = (int) (id%(img_inventaire.getWidth()/32));
		x = x*32;
		y = y*32;
		ImageView img = new ImageView(img_inventaire);
		img.setViewport(new Rectangle2D(x,y, 32,32));
		img.setId(String.valueOf(id));
		this.tPane.getChildren().add(img);
	}
	public Item getItem(ImageView img) {
		return inventaire.get(tPane.getChildren().indexOf(img));
	}
}
