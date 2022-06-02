package application.modele.item;

import application.modele.Environnement;
import application.modele.Item;

public class Arme extends Item {
	
	private int degats;
	private Environnement env;

	public Arme(int id, int degats, Environnement env) {
		super(id);
		this.degats=degats;
		this.env=env;
		// TODO Auto-generated constructor stub
	}

	public Arme(int id, int quantite, int degats, Environnement env) {
		super(id, quantite);
		this.degats=degats;
		this.env=env;
		// TODO Auto-generated constructor stub
	}

	public void Attaque() {
		if(this.env.ennemiPresent().size()!=0 /*&& this.env.getPerso().getEquipe() instanceof Arme*/) {
			for (int i=0; i<this.env.ennemiPresent().size(); i++) {
				this.env.ennemiPresent().get(i).setHp(this.degats);
			}
		}
	}

}