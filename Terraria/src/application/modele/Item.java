package application.modele;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {
    
	private int id;
    private IntegerProperty x;
    private IntegerProperty y;
    private IntegerProperty quantite;
    private Environnement env;
    private String descri;
    
    public Item (int x, int y, Environnement env, String descri) {
        this.x =new SimpleIntegerProperty(x) ;
        this.y =new SimpleIntegerProperty(y) ;
        this.env=env;
        this.descri=descri;
        this.id = 1 ;
        //this.quantite.setValue(1);
    }
    
    public int getX() {
        return this.x.getValue();
    }
    
    public IntegerProperty getxProperty() {
        return this.x;
    }
    
    public int getY() {
        return this.y.getValue();
    }
    
    public IntegerProperty getyProperty() {
        return this.y;
    }
    
    public String getDescri() {
        return this.descri;
    }
    
    public IntegerProperty getQuantite() {
    	return this.quantite;
    }
    
    public void addQuantite() {
    	this.quantite.setValue(quantite.getValue()+1);
    }

	@Override
	public int hashCode() {
		return Objects.hash(descri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(descri, other.descri);
	}

	public int getId() {
		return id;
	}
    
/*    public int caseX() {
        return this.x.get()/16;
    }
    public int caseY() {
        return this.y.get()/16;
    }*/
    
}