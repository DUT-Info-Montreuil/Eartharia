package application.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Environnement {
	
	private int ligne,colonne;
	private int [][] map ;
	private Perso perso;
	private int gravite;

	public Environnement(int x, int y) {
		perso = new Perso(this, x*16/2, y*16/2);
		this.ligne = x;
		this.colonne = y;
		this.map = new int [x][y];
		this.gravite = 1;
		this.gravite ++;
	}
	
	public void readMap () throws IOException {
		File file = new File("src/Terraria.csv");
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String ligne;
		String[] all_Line;
		try {
			int i = 0;
			while ((ligne = bfr.readLine()) != null) {
				all_Line = ligne.split(",");
				for(int j =0 ; j< all_Line.length; j++) {	
					this.map[i][j] = Integer.parseInt(all_Line[j].trim());
				}
				i++;
				
				//System.out.println(ligne);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		try {
			bfr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fall () {//mettre dans acteur pour l'appliquer à tous le monde
        if(this.perso.getY() < (this.getColonne()*16)) {
//            System.out.println("if");
//            System.out.println("coordonnées perso " + this.perso.getY());
//            System.out.println("coordonnées map " + this.getY()*16);
            try {
                this.perso.setY(this.perso.getY() + /*this.perso.getVitesseY()*/ this.gravite);
                //this.perso.setVitesseY(this.perso.getVitesseY() + this.gravite);
            } catch (Exception e) {
                
                System.out.println("fin limite map");
            };
            
        }
    }
	public int getCase(int ligne, int colonne) {
		return this.map[ligne][colonne];
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}
	
	public Perso getPerso () {
		return this.perso;
	}

}











