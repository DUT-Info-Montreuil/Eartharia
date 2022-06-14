package application.modele.fonctionnalitees;

import java.util.List;

import application.modele.Environnement;
import application.modele.Item;
import application.modele.item.Arme;
import application.modele.item.BlocItem;
import application.modele.item.Outils;
import application.modele.item.Projectile;
import application.vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ObserveProjectile implements ListChangeListener<Projectile >{
	
	private Pane p;
	private VueProjectile vueProjectile;
	private Environnement env;
	
	
	public ObserveProjectile(Pane p, Environnement env) {
		this.p=p;
		this.env=env;
	}
	
	@Override
	public void onChanged(Change<? extends Projectile> c) {
		while (c.next()) {
			ajout(c.getAddedSubList());
			suppresion(c.getRemoved() );
		}		
	}
	
	private void ajout(List<? extends Projectile> addedSubList) {
		for (Projectile projectiles : addedSubList) {
			this.vueProjectile= new VueProjectile(projectiles, p);
		}
	}
	
	private void suppresion(List<? extends Projectile> getRemoved) {
		for (Projectile projectiles : getRemoved) {
//			if(this.env.verifAutourProjectile(projectiles)) {
//				this.p.getChildren().remove(projectiles);
//			}
		}
	}
}
