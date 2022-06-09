package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Bloc;
import application.modele.Environnement;
import application.vue.VueMapTerraria;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.TilePane;

public class ObserveMap implements ListChangeListener<Bloc >{

	private TilePane tileMap;
	private VueMapTerraria vueMap;
	private Environnement env;

	public ObserveMap(TilePane tileMap,VueMapTerraria vueMap,Environnement env) {
		this.tileMap = tileMap;
		this.vueMap= vueMap;
		this.env=env;
	}
	@Override
	public void onChanged(Change<? extends Bloc> c) {
		System.out.println("changement");
		while (c.next()) {
			ajout(c.getAddedSubList());
		}
	}
	private void ajout(List<? extends Bloc> addedSubList) {
		for (Bloc item : addedSubList) {
			vueMap.refresh(item.getId(),item.getIdTuile());
		}
		System.out.println("Ajout");
	}
}