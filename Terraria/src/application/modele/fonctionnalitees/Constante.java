package application.modele.fonctionnalitees;

import java.util.ArrayList;

import application.modele.Acteur;
import application.modele.acteur.Perso;
import application.modele.item.Projectile;
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

public class Constante {
	private static ArrayList<Integer> BlocPierre = BlocPierre();
	private static ArrayList<Integer> BlocBois = BlocBois();
	private static ArrayList<Integer> BlocTerre = BlocTerre();
	private static ArrayList<Integer> BlocTransparent = BlocTransparent();

	private static ArrayList<Integer> BlocTransparent(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		//Bloc transparent avec image comme bloc '0'
		listBloc.add(0);
		listBloc.add(89);
		listBloc.add(90);
		listBloc.add(253);
		listBloc.add(251);
		listBloc.add(17);
		listBloc.add(211);
		listBloc.add(268);
		listBloc.add(208); 
		listBloc.add(209); 
		listBloc.add(210); 
		listBloc.add(211); 
		listBloc.add(292); 
		listBloc.add(111);
		listBloc.add(127);
		listBloc.add(228);
		listBloc.add(269);
		listBloc.add(267);
		listBloc.add(268);
		listBloc.add(259);
		listBloc.add(191);
		listBloc.add(193);
		listBloc.add(104);
		listBloc.add(150);
		listBloc.add(263);
		listBloc.add(260);
		listBloc.add(266);
		listBloc.add(192);
		listBloc.add(128);
		listBloc.add(194);
		listBloc.add(127);
		listBloc.add(109);
		for (int i = 276; i <= 289; i++)
			listBloc.add(i);
		for (int i = 119; i <= 272; i+=17) 
			listBloc.add(i);
		listBloc.add(34);//Bloc eau

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
		listBloc.add(208); //Bois chene
		listBloc.add(209); //Bois bouleau
		listBloc.add(210); //Bois Sapin
		listBloc.add(211); //Bois Palmier
		return listBloc;
	}
	private static ArrayList<Integer> BlocTerre(){
		ArrayList<Integer> listBloc = new ArrayList<>();
		for (int i = 233; i < 237; i++) {
			listBloc.add(i); //Bloc terre			
		}
		listBloc.add(218); //Bloc glace
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
		Image img;
		if (p.getX()<96*16) 
			img =new Image("ressources/fond/caverneOcean.jpg");
		else if (p.getX()>=96*16 && p.getX()<296*16 && p.getY()>=39*16)
			img =new Image("ressources/fond/lave.jpg"); 
		else if (p.getX()>=296*16 && p.getY()<26*16)
			img =new Image("ressources/fond/desert.png");
		else if (p.getX()>=296*16 && p.getY()>=26*16)
			img =new Image("ressources/fond/lave.jpg");
		else
			img = new Image("ressources/fond/foret.jpg");	
		BackgroundImage bgimg = (new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)));
		Background bg = new Background(bgimg);
		return bg;

	}
}