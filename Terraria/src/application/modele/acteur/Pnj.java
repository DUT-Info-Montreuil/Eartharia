package application.modele.acteur;

import application.modele.Acteur;
import application.modele.Environnement;
import javafx.beans.property.*;

public class Pnj extends Acteur {
	private BooleanProperty interact_on;
	public static int count = 0;
	public String interationText;
	
	public Pnj(Environnement env, int x, int y,String message) {
		super(env, x, y,1, 4, 16,16,10);
		this.interact_on = new SimpleBooleanProperty(false);
		this.interationText = message;
		System.out.println("interaction text"+count+":"+interationText);
		count ++;
	}
	public String getInterationText() {
		return interationText;
	}

	@Override
	public void agir() {
		try {
			this.tombe(3);
		} catch (Exception e) {

		}
	}
	public boolean interactionPnj() {
		if(Math.abs(getEnv().getPerso().caseX() - this.caseX()) <=1) {
			interact_on.set(true);
			return true;
		}
		interact_on.set(false);
		return false;
	}

	public BooleanProperty getInteract_onProperty() {
		return interact_on;
	}
	public void setInteract_on(boolean interact_on) {
		this.interact_on.setValue(interact_on);
	}
	public boolean getInteract_on() {
		return interact_on.getValue();
	}
}