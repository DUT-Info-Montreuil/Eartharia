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
import application.modele.fonctionnalitees.Constante;
import application.modele.monstre.BossSol;
import application.modele.monstre.Sol;
import application.modele.monstre.volant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private int colonne,ligne;
	private ObservableList <Bloc> map ;
	private Perso perso;
	private int gravite;
	private ObservableList<Acteur> listActeur;
	private int temps = 0;

	public Environnement() {
		initialisation();
		this.gravite = 2;
		perso = new Perso(this, 0, 0);
		listActeur= FXCollections.observableArrayList(new Sol(this, 10, 10),
				new Sol(this, 0, 10)
				,new Sol(this, 15, 4),
				new volant(this, 0,6),
				new BossSol(this, 9, 9, this.perso)

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
				map.add(new Bloc(i,idBloc,Constante.estUnBlocSolide(idBloc)));
			}
			//vueNombre();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public boolean boxCollisionBloc(int ligne, int colonne){
		return this.map.get(ligne*this.colonne+colonne).estSolide();
	}
	public void vueNombre() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j <colonne; j++) {
				System.out.print(this.map.get(i*ligne+j).getId()+"\t");
			}
			System.out.println();
		}
	}
	public void unTour() {
		this.gravite();
//		for(int i = this.listActeur.size() -1; i>= 0; i --) {
//			Acteur monstre = listActeur.get(i);
//			if(monstre.estMort()){
//			this.listActeur.remove(i);
//			}
//		}
		for( Acteur a : listActeur ) {
			a.agir();
		}
		
		if (temps%8==0){
			}
			temps++;
	}
	public void gravite() {
		//plus tard faire un for each pour la liste acteur
		try {
			if(!perso.surDuSol())
				this.perso.tombe(gravite);
		}catch (LimiteMapException e) {
			System.out.println("fin limite map");
		}catch (CollisionException e) {
			System.out.println("Boite de collision touche un bloc");
		}catch (Exception e) {
			e.printStackTrace();
		};
	}
	public int getTemp() {
		return this.temps;
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
	public Acteur getActeurs () {
		Acteur act = null;
		for (Acteur a : this.listActeur){
			act = a;
		}
		return act;
	}
	public ObservableList <Acteur> getListeActeur(){
		return this.listActeur;
	}
	public void setBlock(int yClic, int xClic,int idTuile) {
		getBloc(yClic,xClic).setIdTuile(idTuile);
		getBloc(yClic,xClic).setCollision(Constante.estUnBlocSolide(idTuile));
	}
}