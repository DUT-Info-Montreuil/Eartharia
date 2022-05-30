 package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {
    
	private int id;
    private IntegerProperty quantite;
    
    public Item (int id) {
    	this.id = id ;
        this.quantite = new SimpleIntegerProperty(1);
    }
    public Item (int id,int quantite) {
    	this.id = id ;
        this.quantite = new SimpleIntegerProperty(quantite);
    }
    public IntegerProperty getQuantiteProperty() {
    	return this.quantite;
    }
    public int getQuantite() {
    	return this.quantite.get();
    }
    public boolean equals(Item item) {
		return item.getId()==id;
	}
	public void addQuantite(int nombre) {
    	this.quantite.setValue(quantite.getValue()+nombre);
    }
	public int getId() {
		return id;
	}
}