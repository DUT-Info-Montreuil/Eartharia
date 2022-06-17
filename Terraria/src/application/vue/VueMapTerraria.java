package application.vue;

import java.io.FileInputStream;
import application.modele.Environnement;
import application.modele.fonctionnalitees.Constante;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMapTerraria {
	private Environnement terrain;
	private TilePane tilemap;
	private Image imgTileP;
	private ImageView imgV;

	public VueMapTerraria(Environnement env, TilePane tilePane) {
		this.terrain = env;
		this.tilemap = tilePane;
		
		initTerrain();
		scrollMap();
	}
	private void scrollMap() {
		tilemap.layoutXProperty().bind(terrain.getPerso().getxProperty().multiply(-1).add((Constante.view/2)*16));
		tilemap.layoutYProperty().bind(terrain.getPerso().getyProperty().multiply(-1).add((Constante.view/2)*16));
	}
	private void initTerrain() {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/TuileMap.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgTileP = new Image(fichierTileSet);
		this.tilemap.setPrefColumns(terrain.getColonne());
		for (int i = 0; i < terrain.getLigne(); i++) {
			for (int j = 0; j < terrain.getColonne(); j++) {
				afficherMap(i*terrain.getColonne()+j,this.terrain.getIdTuile(i, j),this.terrain.getBloc(i, j).getId());
			}
		}
	}
	public void afficherMap(int index ,int idTuile,int id) {
		int x;
		int y;
		ImageView img = new ImageView(this.imgTileP);
		idTuile=idTuile-1;
		x = (int) (idTuile%(imgTileP.getWidth()/16));
		y = (int) (idTuile/(imgTileP.getHeight()/16));
		x = x*16;
		y = y*16;
		img.setViewport(new Rectangle2D(x,y, 16,16));
		img.setId(String.valueOf(id));
		this.tilemap.getChildren().add(index,img);
	}	
}