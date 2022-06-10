package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Bloc;
import application.modele.Environnement;
import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.vue.VueMapTerraria;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
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
			suppresion(c.getRemoved());
		}
	}
	private void ajout(List<? extends Bloc> addedSubList) {
		for (Bloc item : addedSubList) {
			vueMap.afficherMap(env.getMap().indexOf(item),item.getIdTuile(), item.getId());;
		}
	}
	private void suppresion(List<? extends Bloc> getRemoved) {
		for (Bloc item : getRemoved) {
			Node n = this.tileMap.lookup("#"+item.getId());
			if(n!=null)
				this.tileMap.getChildren().remove(n);
		}
	}
}