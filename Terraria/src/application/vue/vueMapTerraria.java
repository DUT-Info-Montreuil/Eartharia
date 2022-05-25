package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class vueMapTerraria {
	
	private Environnement terrain;
	private TilePane TileP;
	private Image imgTileP;
	private int imgWidth;
	private ImageView imgV;
	
	public vueMapTerraria(Environnement terrain, TilePane tileP) throws FileNotFoundException {

		this.terrain = terrain;
		this.TileP = tileP;
		initTerrain();
	}
	
	public void initTerrain() throws FileNotFoundException {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/tuile_zelda.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgTileP = new Image(fichierTileSet);
		imgWidth=(int) imgTileP.getWidth()/16;

		this.TileP.setPrefColumns(20);
		for (int ligne = 0; ligne < this.terrain.getLigne(); ligne++) {
			for(int colonne =0; colonne< this.terrain.getColonne(); colonne++){
				imgV = new ImageView(this.imgTileP);
				afficherMap(imgV,this.terrain.getCase(ligne, colonne));
				//System.out.println("get case " + this.terrain.getCase(19,0));
			}
		}
	}
		
	
	public void afficherMap(ImageView img, int id) {
			int x;
			int y;
			int colonne;
			int ligne;
			
			y = id/imgWidth;
			x = id%imgWidth;
			colonne = x*16;
			ligne = y*16;

		img.setViewport(new Rectangle2D(colonne,ligne, 16,16));
		//System.out.println("colonne "+colonne+" ligne "+ligne+ " du viewport"+ "id tuile cote modele" + id);
		this.TileP.getChildren().add(img);
	}

}
