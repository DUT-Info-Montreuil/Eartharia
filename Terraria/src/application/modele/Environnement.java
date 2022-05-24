package application.modele;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.modele.fonctionnalitees.CollisionException;
import application.modele.fonctionnalitees.Constante;
import application.modele.fonctionnalitees.LimiteMapException;

public class Environnement {

	private int colonne,ligne;
	private Bloc [][] map ;
	private Perso perso;
	private int gravite;
	public Environnement() {
		initialisation();
		this.gravite = 2;
		perso = new Perso(this, 0, 0);
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
			this.map = new Bloc[ligne][colonne];
			JSONArray data = (JSONArray) layers.get("data");
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					int idBloc = ((Long)data.get(i*colonne+j)).intValue();
					map[i][j]=new Bloc(idBloc,Constante.estUnBlocSolide(idBloc));
				}
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
		return this.map[ligne][colonne].estSolide();
	}
	public void vueNombre() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j <colonne; j++) {
				System.out.print(map[i][j].getId()+"\t");
			}
			System.out.println();
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
	public int getCase(int ligne, int colonne) {
		return this.map[ligne][colonne].getId();
	}
	public Bloc getBloc(int ligne, int colonne) {
		return this.map[ligne][colonne];
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

}