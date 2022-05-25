package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
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
				afficherMap(imgV,this.terrain.getIdTuile(i, j),this.terrain.getBloc(i, j).getId());
			}
		}
	}
	public void afficherMap(ImageView img, int idTuile,int id) {
		int x;
		int y;
		idTuile=idTuile-1;
		y = (int) (idTuile/(imgTileP.getHeight()/16));
		x = (int) (idTuile%(imgTileP.getWidth()/16));
		x = x*16;
		y = y*16;
		img.setViewport(new Rectangle2D(x,y, 16,16));
		img.setId(String.valueOf(id));
		this.TileP.getChildren().add(img);
	}
	public void refreshTuile(ImageView img, int idTuile,int id) {
		int x;
		int y;
		idTuile=idTuile-1;
		y = (int) (idTuile/(imgTileP.getHeight()/16));
		x = (int) (idTuile%(imgTileP.getWidth()/16));
		x = x*16;
		y = y*16;
		img.setViewport(new Rectangle2D(x,y, 16,16));
		img.setId(String.valueOf(id));
	}
	public void refresh(int idBlock,int idTuile) {
		ImageView v =(ImageView) TileP.lookup("#"+idBlock);
		System.out.println(TileP.lookup("#"+idBlock));
		refreshTuile(v,idTuile,idBlock);
	}
}
