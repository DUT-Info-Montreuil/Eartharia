package application.modele.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Acteur;
import application.modele.Environnement;
import application.modele.Exception.CollisionException;
import application.modele.Exception.InventairePleinException;
import application.modele.Exception.LimiteMapException;
import application.modele.fonctionnalitees.Constante;
import application.modele.item.BlocItem;
import application.modele.item.Pelle;
import application.modele.monstre.BossSol;

public class TestClassMehedi {

	Environnement env = new Environnement("src/JSONFileTest.json");
	@BeforeEach
	public void init() {
		env = new Environnement("src/JSONFileTest.json");
		System.out.println("\n\t\t233 c'est la terre \t & \t 0 c'est l'aire");
	}

	@Test
	public final void testSolide() {
		System.out.println("TestClassMehedi.testSolide()");
		env.vueNombre();
		assertTrue(env.getBloc(9, 0).estSolide(), "cas ou le bloc est solide");
		assertTrue(env.getBloc(7, 4).estSolide(), "cas ou le bloc est solide");
		assertFalse(env.getBloc(0, 0).estSolide(), "cas ou le bloc est non solide");
		assertFalse(env.getBloc(5, 6).estSolide(), "cas ou le bloc est non solide");
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testGravite() {
		System.out.println("TestClassMehedi.testGravite()");
		env.vueNombre();
		assertFalse(env.getPerso().surDuSol(), "cas ou il est en l'aire");
		env.unTour();
		//A chaque tour tt les perso qui sont aps sur le sol tombe
		env.vueNombre();
		assertTrue(env.getPerso().surDuSol(), "cas ou il est sur le sol");
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testProximiter() {
		System.out.println("TestClassMehedi.testProximiter()");
		assertTrue(env.aProximiter(env.getPerso(), 2).isEmpty(),"cas ou un enemie est a proximiter");
		env.getListeActeur().get(0).setX(env.getPerso().getX()+48);
		env.getListeActeur().get(0).setY(env.getPerso().getY());		
		assertTrue(env.aProximiter(env.getPerso(), 2).isEmpty(),"cas ou un enemie est a proximiter");
		env.vueNombre();
		env.getListeActeur().get(0).setX(env.getPerso().getX()+16);
		env.getListeActeur().get(0).setY(env.getPerso().getY());
		assertNotNull(env.aProximiter(env.getPerso(), 2),"cas ou un enemie est a proximiter");
		env.vueNombre();
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testDeplacement() {
		System.out.println("TestClassMehedi.testDeplacement()");
		Acteur test =new BossSol(env, 0, 0);
		env.addMonster(test);
		assertThrows(LimiteMapException.class,() -> {
			test.deplacement(-16, 0);
		});
		Acteur test2 =new BossSol(env, 5, 7);
		env.addMonster(test);
		assertThrows(CollisionException.class,() -> {
			test2.deplacement(-16, 0);
		});
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testPeutCasser() {
		System.out.println("TestClassMehedi.testPeutCasser()");
		// en soit ici on regarde juste si la methode de Constante fonctionne
		assertTrue(Constante.estUnBlocTerre(env.getIdTuile(8, 4)));
		assertTrue(Constante.estUnBlocTerre(env.getIdTuile(9, 8)));
		assertFalse(Constante.estUnBlocTerre(env.getIdTuile(0, 0)));
		assertFalse(Constante.estUnBlocPierre(env.getIdTuile(0, 0)));
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testPeutCraft() {
		System.out.println("TestClassMehedi.testPeutCraft()");
		env.ajoutBloc(6, 7, 190);//Ajout d'un table loin du joueur
		assertFalse(env.getPerso().peutcraft());
		env.ajoutBloc(6, 2, 190);//Ajout d'un table proche du joueur
		assertTrue(env.getPerso().peutcraft());
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testPeutCraftObject() {
		System.out.println("TestClassMehedi.testPeutCraft()");
		//Il faut 3 bois
		assertFalse(env.getPerso().getCraft().canCraft(190),"Cas ou on a pas 4 bois");
		try {env.getPerso().addInventaire(new BlocItem(208,3));} catch (InventairePleinException e) {}
		assertFalse(env.getPerso().getCraft().canCraft(190),"Cas ou il manque 1 bois");
		try {env.getPerso().addInventaire(new BlocItem(208,1));} catch (InventairePleinException e) {}
		assertTrue(env.getPerso().getCraft().canCraft(190),"Cas ou on a 4 bois");
		assertFalse(env.getPerso().getCraft().canCraft(0),"Cas ou on a  enconre 4 bois mais pas 4 pierre");
		try {env.getPerso().addInventaire(new BlocItem(44,4));} catch (InventairePleinException e) {}
		assertTrue(env.getPerso().getCraft().canCraft(0),"Cas ou on a 4 bois et 4 pierre");

		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testPeutAjouterObject() {
		System.out.println("TestClassMehedi.testPeutAjouterObject()");
		assertDoesNotThrow(() -> {
			env.getPerso().addInventaire(new BlocItem(208,3));
		});
		for (int i = 1; i < 15;i++) {
			try {env.getPerso().addInventaire(new BlocItem(i,5));} catch (InventairePleinException e) {}
		}
		assertDoesNotThrow(() -> {
			env.getPerso().addInventaire(new BlocItem(208,3));
		});
		try {env.getPerso().addInventaire(new BlocItem(100,5));} catch (InventairePleinException e) {}
		assertThrows(InventairePleinException.class,() -> {
			env.getPerso().addInventaire(new Pelle());
		});
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
	@Test
	public final void testPeutAjouterObjectPresent() {
		System.out.println("TestClassMehedi.testPeutAjouterObject()");
		assertTrue(env.getPerso().estPasPresent(new Pelle()));
		try {env.getPerso().addInventaire(new Pelle());} catch (InventairePleinException e) {}
		assertFalse(env.getPerso().estPasPresent(new Pelle()));
		System.out.print("-------------------------------------------------------------------------------------\n");
	}
}
