package application.modele;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.modele.acteur.Monstre;
import application.modele.acteur.Perso;
import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.Constante;
import application.modele.fonctionnalitees.LimiteMapException;
import application.modele.monstre.Sol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Environnement {

	private int colonne,ligne;
	private ObservableList <Bloc> map ;
	private Perso perso;
	private int gravite;
	private ObservableList<Acteur> listActeur;
	public Environnement() {
		initialisation();
		this.gravite = 2;
		this.listActeur= FXCollections.observableArrayList(
				new Sol(this, 15, 10));
		perso = new Perso(this, 0, 0);
	}

	public void initialisation(){
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
			for (int i = 0; i < ligne*colonne; i++) {
				int idBloc = (((Long)data.get(i)).intValue());
				map.add(new Bloc(i,idBloc,Constante.estUnBlocSolide(idBloc)));
			}
			vueNombre();
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
				//System.out.print(this.map.get(i*ligne+j).getId()+"\t");
			}
			//System.out.println();
		}
	}
	public void unTour() {
		for(int i = this.listActeur.size() -1; i>= 0; i --) {
			Acteur monstre = listActeur.get(i);
			if(monstre.estMort()){
			this.listActeur.remove(i);
			}
		}
		for( Acteur m : listActeur ) {
			m.agir();
		}

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
	
//	public void getIdBLoc (int position) {
//		this.map.get(position);
//	}
//	
//	public void setCase (int colonne, int ligne) {
//		map.get(getCase(ligne, colonne)).getId();
//	}
	
	public ObservableList<Bloc> listBloc(){
		return this.map;
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
	public ObservableList <Acteur> getActeurs(){
		return this.listActeur;
	}
	public void ajoutMonstre (Monstre m) {
		this.listActeur.add(m);
	}

	public void setBlock(int yClic, int xClic,int idTuile) {
		getBloc(yClic,xClic).setIdTuile(idTuile);
		getBloc(yClic,xClic).setCollision(Constante.estUnBlocSolide(idTuile));
	}
}