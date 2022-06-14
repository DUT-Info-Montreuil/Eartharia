package application.modele;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.modele.Exception.CollisionException;
import application.modele.Exception.LimiteMapException;
import application.modele.acteur.Perso;
import application.modele.acteur.Pnj;
import application.modele.fonctionnalitees.Constante;
import application.modele.item.Projectile;
import application.modele.monstre.BossSol;
import application.modele.monstre.Sol;
import application.modele.monstre.Volant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private int colonne,ligne;
	private ObservableList <Bloc> map ;
	private Perso perso;
	private int gravite;
	private ObservableList<Acteur> listActeur;
	private int temps = 0;
	private ObservableList<Projectile> projectiles;

	public Environnement() {
		initialisation();
		this.gravite = 2;
		projectiles = FXCollections.observableArrayList();
		perso = new Perso(this, 0, 0);
		listActeur= FXCollections.observableArrayList(
				//			new Sol(this, 3, 10)
				//				new Sol(this, 10, 10),
				//				new Sol(this, 15, 4),
								new Volant(this, 3,6)
				//new BossSol(this, 16, 2)
				);
	}

	private void initialisation(){
		Object ob;
		try {
			ob = new JSONParser().parse(new FileReader("src/JSONFile.json"));
			JSONObject Jsonbject = (JSONObject) ob;

			@SuppressWarnings("rawtypes")
			JSONObject layers = (JSONObject) ((ArrayList) Jsonbject.get("layers")).get(0);
			this.colonne  = ((Long) layers.get("width")).intValue();
			this.ligne = ((Long) layers.get("height")).intValue();
			this.map = FXCollections.<Bloc>observableArrayList();
			JSONArray data = (JSONArray) layers.get("data");
			int idBloc;
			for (int i = 0; i < ligne*colonne; i++) {
				idBloc = (((Long)data.get(i)).intValue());
				map.add(new Bloc(idBloc));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void vueNombre() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j <colonne; j++) {
				System.out.print(this.map.get(i*ligne+j).getId()+"\t");
			}
			System.out.println();
		}
	}

	public boolean boxCollisionActeur(int ligne, int colonne){
		for (Acteur acteur : listActeur) {
			if(acteur.caseX()==colonne && acteur.caseY()==ligne)
				return true;
		}
		return false;
	}
	public boolean boxCollisionBloc(int ligne, int colonne){
		return this.map.get(ligne*this.colonne+colonne).estSolide();
	}
	public void unTour() {
		if(!perso.surDuSol() && perso.peutTomber())try {this.perso.tombe(gravite);} catch (Exception e) {}
		this.perso.agir();
		for(int i = this.listActeur.size() -1; i>= 0; i --) {
			Acteur act = listActeur.get(i);
			if(act.estMort()){
				this.listActeur.remove(act);
			}
			else {
				if(!(act instanceof Volant)&& !act.surDuSol() && perso.peutTomber())
					try {act.tombe(gravite);} catch (Exception e) {}
				act.agir();
			}
		}
		this.lancerProjectiles();
	}
	public int getTemp() {
		return temps;
	}

	public void addListProjectiles(Projectile p) {
		projectiles.add(p);
	}
	public void lancerProjectiles() {
		for (int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).lancer();
		}
	}
	//	public boolean verifAutourProjectile(Projectile p) {
	//		if (!this.getBloc((int)p.getX(), p.getY()).estSolide()) {
	//			return true;
	//		}
	//		return false;
	//	}

	public ObservableList<Projectile> getListProjectiles() {
		return this.projectiles;
	}
	public int getIdTuile(int ligne, int colonne) {
		return this.map.get(ligne*this.colonne+colonne).getIdTuile();
	}
	public Bloc getBloc(int ligne, int colonne) {
		return this.map.get(ligne*this.colonne+colonne);
	}
	public int getColonne() {
		return colonne;
	}
	public int getLigne() {
		return ligne;
	}
	public Perso getPerso () {
		return this.perso;
	}
	public ObservableList<Bloc> getMap() {
		return map;
	}
	public ObservableList <Acteur> getListeActeur(){
		return this.listActeur;
	}

	public void setBlock(int yClic, int xClic,int idTuile) {
		getBloc(yClic,xClic).setIdTuile(idTuile);
		getBloc(yClic,xClic).setCollision(Constante.estUnBlocSolide(idTuile));
	}
	public void ajoutBloc(int ligne, int colonne,int idTuile) {
		map.remove(getBloc(ligne,colonne));
		map.add(ligne*this.colonne+colonne, new Bloc(idTuile));
	}
	public void destructBlock(int ligne, int colonne) {
		map.remove(getBloc(ligne,colonne));
		map.add(ligne*this.colonne+colonne, new Bloc(0));
	}

	public ArrayList<Acteur> aProximiter(Acteur me,int range) {
		ArrayList<Acteur> listA= new ArrayList<Acteur>();
		if(me.getId()!=perso.getId() &&
			(Math.abs(me.caseY()-perso.caseY())<=range)	&&
			(Math.abs(me.caseX()-perso.caseX())<=range)	) {
			listA.add(perso);
		}
		for(Acteur acteur : listActeur){
			if(me.getId()!=acteur.getId() &&
					(Math.abs(me.caseY()-acteur.caseY())<=range)	&&
					(Math.abs(me.caseX()-acteur.caseX())<=range)	) {
				listA.add(acteur);
			}
		}
		System.out.println(listA);
		return listA;
	}
	public Acteur plusProche(Acteur me,int range) {
		Acteur a = perso;
		for(Acteur acteur : listActeur){
			if(me.getId()!=acteur.getId()){
				if (Math.abs(a.caseX()-me.caseX())> Math.abs(acteur.caseX()-me.caseX()) &&
						Math.abs(a.caseY()-me.caseY())> Math.abs(acteur.caseY()-me.caseY())){
					a=acteur;
				}
			}
		}
		if (Math.abs(a.caseX()-me.caseX())<=range && Math.abs(a.caseY()-me.caseY())<=range) {
			return a;
		}
		return null;
	}
	public Acteur getActeurs (String id) {
		for (Acteur a : this.listActeur){
			if(a.getId().equals(id))
				return a;
		}
		return null;

	}
	public ArrayList<Acteur> ennemiPresent() {
		ArrayList<Acteur> ennemis=new ArrayList<Acteur>();
		for (int i=0; i<listActeur.size(); i++) {
			if ((listActeur.get(i).getX()<=this.perso.getX()+1 || listActeur.get(i).getX()>=this.perso.getX()-1 || listActeur.get(i).getY()>=this.perso.getY()-1 || (listActeur.get(i).getX()>=this.perso.getX()-1 && listActeur.get(i).getY()>=this.perso.getY()-1) || (listActeur.get(i).getX()<=this.perso.getX()+1 && listActeur.get(i).getY()>=this.perso.getY()-1)) && listActeur.get(i) instanceof Pnj ) {
				ennemis.add(listActeur.get(i));
			}
		}
		return ennemis; 
	}
}