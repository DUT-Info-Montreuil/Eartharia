package application.vue;

import java.io.FileInputStream;
import application.modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMapTerraria {
	private Environnement terrain;
	private TilePane TileP;
	private Image imgTileP;
	private ImageView imgV;

	public VueMapTerraria(Environnement env, TilePane tilePane) {
		this.terrain = env;
		this.TileP = tilePane;
		
		initTerrain();	
	}
	private void initTerrain() {
		FileInputStream fichierTileSet = null;
		try {
			fichierTileSet = new FileInputStream("src/ressources/TuileMap.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.imgTileP = new Image(fichierTileSet);
		this.TileP.setPrefColumns(terrain.getColonne());
		for (int i = 0; i < terrain.getLigne(); i++) {
			for (int j = 0; j < terrain.getColonne(); j++) {
				afficherMap(this.terrain.getIdTuile(i, j),this.terrain.getBloc(i, j).getId());
			}
		}
	}
	public void afficherMap(int idTuile,int id) {
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
		this.TileP.getChildren().add(img);
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
		this.TileP.getChildren().add(index,img);
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
		refreshTuile(v,idTuile,idBlock);
	}
}