package application.modele.fonctionnalitees;

import java.util.HashMap;

import application.modele.Item;
import javafx.collections.ObservableList;

public class ConstantCraft {

	public static HashMap<Integer, int[][]> contruction = construct();
	
	private static HashMap<Integer, int[][]> construct() {
		HashMap<Integer, int[][]> menu = new HashMap<Integer, int[][]>();
		int[][] hache = {{2,208},{4,44}};
		int[][] pioche = {{2,208},{3,44}};
		int[][] epee = {{2,208},{2,44}};
		int[][] batonMagique = {{2,208},{2,44}};
		int[][] craftTable = {{4,208}};
		menu.put(0,hache);//0 c'est id de la hache
		menu.put(19,pioche);//19 c'est id de la pioche
		menu.put(59,epee);//59 c'est id de la epee
		menu.put(16,batonMagique);//16 c'est id de la batonMagique
		menu.put(190,craftTable);//190 c'est id de la craftTable
		return menu;
	}
	public static int[][] getCraft(Integer id) {
		return contruction.get(id);
	}
}
