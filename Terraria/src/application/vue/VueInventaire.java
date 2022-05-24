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
	private ImageView imgVI;
	private TilePane tPane;
	private boolean visibility;

	public VueInventaire(TilePane tPane,  ObservableList<Item> inventaire) {
		this.tPane=tPane;
		this.imgVI=new ImageView(img_inventaire);
		this.inventaire=inventaire;
		this.visibility = false;
		background();
		try {
			initItem();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ouvFerInv() {
		this.tPane.setVisible(visibility);
		visibility=!visibility;
	}

	private void background() {
		Image img = new Image("ressources/Inventaire.jpeg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		tPane.setBackground(bGround);
	}
	public void initItem() throws FileNotFoundException {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/imageItem.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.img_inventaire = new Image(fichierTileSet);
	}
	public void afficherItem(int id) {
		int x;
		int y;
		int colonne;
		int ligne;

		y = id/(int) img_inventaire.getHeight()/16;;
		x = id%(int) img_inventaire.getWidth()/16;;
		colonne = x*16;
		ligne = y*16;
		ImageView imgv= new ImageView(this.img_inventaire);
		imgv.setViewport(new Rectangle2D(colonne,ligne, 16,16));
		this.tPane.getChildren().add(imgv);
	}

}
