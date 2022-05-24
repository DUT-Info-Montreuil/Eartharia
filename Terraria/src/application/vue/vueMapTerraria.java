package application.vue;

import java.io.FileInputStream;
import application.modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class vueMapTerraria {
	private Environnement terrain;
	private TilePane TileP;
	private Image imgTileP;
	private ImageView imgV;

	public vueMapTerraria(Environnement env, TilePane tilePane) {
		this.terrain = env;
		this.TileP = tilePane;
		
		initTerrain();	
	}
	private void initTerrain() {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/tuile_zelda.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgTileP = new Image(fichierTileSet);
		this.TileP.setPrefColumns(terrain.getColonne());
		for (int i = 0; i < terrain.getLigne(); i++) {
			for (int j = 0; j < terrain.getColonne(); j++) {
				imgV = new ImageView(this.imgTileP);
				afficherMap(imgV,this.terrain.getCase(i, j));
			}
		}
	}
	public void afficherMap(ImageView img, int id) {
		int x;
		int y;
		id=id-1;
		y = (int) (id/(imgTileP.getHeight()/16));
		x = (int) (id%(imgTileP.getWidth()/16));
		x = x*16;
		y = y*16;
		img.setViewport(new Rectangle2D(x,y, 16,16));
		img.setId(null);
		this.TileP.getChildren().add(img);
	}
}
