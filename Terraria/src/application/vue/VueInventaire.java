package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;

import application.modele.Item;
import application.modele.Exception.InventaireCaseVideException;
import application.modele.Exception.ItemNonTrouverException;
import application.modele.fonctionnalitees.Description;
import application.modele.fonctionnalitees.ItemFonctionnalite;
import application.modele.fonctionnalitees.Tableau;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

	private ObservableList<Item> inventaire;
	private Image img_item;
	private Image img_bloc;
	private GridPane tPaneInventaire;
	private GridPane tPaneInventaireRapide;
	private boolean visibility;

	public VueInventaire(GridPane tPaneInventaireRapide,GridPane tpInventaire,  ObservableList<Item> inventaire){
		this.tPaneInventaire=tpInventaire;
		this.tPaneInventaireRapide = tPaneInventaireRapide;
		this.visibility = false;
		this.inventaire=inventaire;		
		paneSet();
		background();
		initItem();
	}

	private void paneSet() {
		this.tPaneInventaire.setPrefSize(128, 96);
		this.tPaneInventaire.setVisible(false);
		this.tPaneInventaire.setId("inventaire");
		this.tPaneInventaireRapide.setId("inventaireRapide");
		this.tPaneInventaireRapide.setPrefSize(128, 32);
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

		tPaneInventaireRapide.setLayoutX(5);
		tPaneInventaireRapide.setLayoutY(5);
		tPaneInventaire.setLayoutX(5);
		tPaneInventaire.setLayoutY(47);
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

	public void ouvFerInv() {
		visibility=!visibility;
		this.tPaneInventaire.setVisible(visibility);
		if(visibility) 
			this.tPaneInventaire.toFront();
		else
			this.tPaneInventaire.toBack();
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
		if(this.tPaneInventaireRapide.getChildren().size()<4)
			Tableau.add(tPaneInventaireRapide,img);
		else
			Tableau.add(tPaneInventaire,img);
		new ItemFonctionnalite(img,tPaneInventaire,tPaneInventaireRapide);
	}
	public void afficherItemOutils(int idOutils,String id) {
		afficherItem(idOutils, id, 32, new ImageView(img_item));
	}
	public void afficherItemBloc(int idTuile,String id) {
		afficherItem(idTuile-1,id, 16,new ImageView(img_bloc));
	}

	public Item getItem(ImageView img)throws ItemNonTrouverException {
		for (Item item : inventaire) {
			if(item.getId()==img.getId())
				return item;
		}
		throw new ItemNonTrouverException();
	}
	public Item getItem(int place)throws ItemNonTrouverException {
		Node node = Tableau.getCell(tPaneInventaireRapide, 0, place);
		for (Item item : inventaire) {
			if(item.getId()==node.getId())
				return item;
		}
		throw new ItemNonTrouverException();
	}
}