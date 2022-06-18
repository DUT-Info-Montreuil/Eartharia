package application.modele.fonctionnalitees;

import java.nio.file.Paths;
import java.util.ArrayList;

import application.modele.Acteur;
import application.modele.acteur.Perso;
import application.modele.item.BatonMagique;
import application.modele.item.Epee;
import application.modele.item.Hache;
import application.modele.item.Pioche;
import application.modele.item.Projectile;
import application.modele.item.arc;
import application.modele.monstre.BossSol;
import application.modele.monstre.BossVolant;
import application.modele.monstre.Sol;
import application.modele.monstre.Volant;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class Constante {
	private static ArrayList<Integer> BlocPierre = BlocPierre();
	private static ArrayList<Integer> BlocBois = BlocBois();
	private static ArrayList<Integer> BlocTerre = BlocTerre();
	private static ArrayList<Integer> BlocTransparent = BlocTransparent();

	private static ArrayList<Integer> BlocTransparent(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		//Bloc transparent avec image comme bloc '0'
		listBloc.add(0);
		listBloc.add(17);
		listBloc.add(89);
		listBloc.add(90);
		for (int i = 276; i <= 289; i++)
			listBloc.add(i);
		for (int i = 119; i <= 272; i+=17) 
			listBloc.add(i);
		//Bloc transparent avec image comme bloc '0'
		listBloc.add(34);//Bloc eau
		for (int i=239; i<=254; i++)
			listBloc.add(i);
		
		listBloc.add(110);
		listBloc.add(127);
		listBloc.add(111);
		listBloc.add(128);
		listBloc.add(104);
		listBloc.add(105);
		listBloc.add(150);
		
		for (int i=174; i<=178; i++)
			listBloc.add(i);
		
		for (int i=182; i<=186; i++)
			listBloc.add(i);
		
		for (int i=165; i<=169; i++)
			listBloc.add(i);

		for (int i=256; i<=271; i++)
			listBloc.add(i);
		
		for (int i : BlocBois())
			listBloc.add(i);

		return listBloc;
	}
	private static ArrayList<Integer> BlocPierre(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(44); //Pierre
		listBloc.add(76); //Pierre + cuivre
		listBloc.add(78); //Pierre + fer
		listBloc.add(81); //Pierre + plomb
		return listBloc;
	}
	private static ArrayList<Integer> BlocBois(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		listBloc.add(209); //Bois chene
		listBloc.add(210); //Bois bouleau
		listBloc.add(211); //Bois Sapin
		listBloc.add(212); //Bois Palmier
		for (int i=191; i<=194; i++)
			listBloc.add(i);

		return listBloc;
	}
	private static ArrayList<Integer> BlocTerre(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		for (int i = 233; i < 237; i++) {
			listBloc.add(i); //Bloc terre			
		}
		return listBloc;
	}
	
	public static boolean estUnBlocSolide(int idBloc) {
		return !BlocTransparent.contains(idBloc);
	}
	public static boolean estUnBlocPierre(int idBloc) {
		return Constante.BlocPierre.contains(idBloc);
	}
	public static boolean estUnBlocTerre(int idBloc) {
		return Constante.BlocTerre.contains(idBloc);
	}
	public static boolean estUnBlocBois(int idBloc) {
		return Constante.BlocBois.contains(idBloc);
	}
	
	public static int view=40;
	
	public static String chemin(Acteur a) {
		if(a instanceof Perso)
			return "perso";
		else if(a instanceof BossVolant)
			return "bossVolant";
		else if(a instanceof BossSol)
			return "boss";
		else if(a instanceof Sol)
			return "sol";
		else if(a instanceof Volant)
			return "ghost";
		else if(a instanceof Projectile)
			return ((Projectile)a).getType();
		return"";
	}
	public static Background backgroundJeu(Perso p) {
		if (p.getX()<96*16) 
			return new Background(new BackgroundImage(new Image("ressources/caverneOcean.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));
		else if (p.getX()>=96*16 && p.getX()<296*16 && p.getY()>=39*16)
			return new Background(new BackgroundImage(new Image("ressources/lave.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));
		else if (p.getX()>=296*16 && p.getY()<26*16)
			return new Background(new BackgroundImage(new Image("ressources/desert.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));
		else if (p.getX()>=296*16 && p.getY()>=26*16)
			return new Background(new BackgroundImage(new Image("ressources/lave.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));
	return new Background(new BackgroundImage(new Image("ressources/foret.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));

}
public static String cheminSons(Perso p) {
	if (p.getEquipe() instanceof Epee)
		return "src/ressources/bruitEpee.mp3";
	if (p.getEquipe() instanceof BatonMagique)
		return "src/ressources/bruitBDF.mp3";
	if (p.getEquipe() instanceof arc)
		return "src/ressources/bruitFleche.mp3";
	if (p.getEquipe() instanceof Pioche)
		return "src/ressources/bruitPioche.mp3";
	if (p.getEquipe() instanceof Hache)
		return "src/ressources/bruitHache.mp3";
return null;
}
public static void setMusics(Perso p) {
	MediaPlayer musicForet = new MediaPlayer(new Media(Paths.get("src/ressources/MusicGeneral.wav").toUri().toString()));
//	musicForet.setAutoPlay(p.getX()>=96*16 && p.getX()<296*16 && p.getY()<39*16);

	MediaPlayer musicCaverne = new MediaPlayer(new Media(Paths.get("src/ressources/MusicCaverne.mp3").toUri().toString()));
//	musicCaverne.setAutoPlay(p.getX()>=96*16 && p.getX()<296*16 && p.getY()>=39*16);
//	
	MediaPlayer musicOcean = new MediaPlayer(new Media(Paths.get("src/ressources/MusicOcean.mp3").toUri().toString()));
//	musicOcean.setAutoPlay(p.getX()<96*16);

	MediaPlayer musicBoss =new MediaPlayer(new Media(Paths.get("src/ressources/MusicCaverne.mp3").toUri().toString()));
//	musicBoss.setAutoPlay(p.getX()>=296*16 && p.getY()>=26*16);

	MediaPlayer musicDesert = new MediaPlayer(new Media(Paths.get("src/ressources/MusicDesert.mp3").toUri().toString()));
//	musicDesert.setAutoPlay(p.getX()>=296*16 && p.getY()<26*16);
				
	if (p.getX()<96*16 && musicOcean.getStatus()!=musicOcean.getStatus().PLAYING) { 
			musicOcean.play();
		}
		else if (p.getX()>=96*16 && p.getX()<296*16 && p.getY()>=39*16 && musicCaverne.getStatus()!=musicCaverne.getStatus().PLAYING) { 
			musicCaverne.play();
		}
		else if (p.getX()>=296*16 && p.getY()<26*16 && musicDesert.getStatus()!=musicDesert.getStatus().PLAYING) {	
			musicDesert.play();
		}
		else if (p.getX()>=296*16 && p.getY()>=26*16 && musicBoss.getStatus()!=musicBoss.getStatus().PLAYING) {
			musicBoss.play();
		}
		else if (p.getX()>=96*16 && p.getX()<296*16 && p.getY()<39*16 && musicForet.getStatus()!=musicForet.getStatus().PLAYING) {
			musicForet.play();
		}
}
public static int setTailleBarPV(Acteur a) {
	if (a instanceof Sol)
		return 32;
	if(a instanceof Volant)
		return 20;
return 0;	
}

}