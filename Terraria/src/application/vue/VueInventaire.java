package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;

import application.modele.Item;
import application.modele.fonctionnalitees.Description;
import application.modele.fonctionnalitees.Saut;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

	private ObservableList<Item> inventaire;
	private Image img_inventaire;
	private TilePane tPaneInventaire;
	private TilePane tPaneInventaireRapide;
	private boolean visibility;

	public VueInventaire(TilePane tPaneInventaireRapide,TilePane tpInventaire,  ObservableList<Item> inventaire){
		this.tPaneInventaire=tpInventaire;
		this.tPaneInventaireRapide = tPaneInventaireRapide;
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
		this.tPaneInventaire.setPrefSize(128, 128);
		this.tPaneInventaire.setVisible(false);

		this.tPaneInventaireRapide.setPrefSize(128, 32);
		background();
	}

	public void ouvFerInv() {
		visibility=!visibility;
		this.tPaneInventaire.setVisible(visibility);
	}

	private void background() {
		Image img = new Image("ressources/Inventaire.png");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		tPaneInventaire.setBackground(bGround);	

		img = new Image("ressources/InventaireRapide.png");
		bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		bGround = new Background(bImg);
		tPaneInventaireRapide.setBackground(bGround);

		tPaneInventaireRapide.setTranslateX(5);
		tPaneInventaireRapide.setTranslateY(5);
		tPaneInventaire.setTranslateX(5);
		tPaneInventaire.setTranslateY(47);

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
		if(this.tPaneInventaireRapide.getChildren().size()<4)
			tPaneInventaireRapide.getChildren().add(img);
		else
			this.tPaneInventaire.getChildren().add(img);
	}
	public Item getItem(ImageView img) {
		return inventaire.get(tPaneInventaire.getChildren().indexOf(img));
	}
	public void descriptionItem(Label label,Item i,double x ,double y) {
		label.setVisible(true);
		label.setText(" id : "+i.getId()+"\n Quantité : "+i.getQuantite());
		label.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		label.setTranslateX(x);
		label.setTranslateY(y);
		new Timer().schedule(new Description(label), 5000);
	}
}
