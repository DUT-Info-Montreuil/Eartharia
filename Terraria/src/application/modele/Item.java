package application.modele;

public abstract class Item {
    
	private int id;
    private int quantite;
    
    public Item (int id) {
    	this.id = id ;
        this.quantite = 1;
    }
    public Item (int id,int quantite) {
    	this.id = id ;
        this.quantite = quantite;
    }
    public int getQuantite() {
    	return this.quantite;
    }
    public void setQuantite(int quantite) {
    	this.quantite = quantite;
    }
    public boolean equals(Item item) {
		return item.getId()==id;
	}
	public void addQuantite(int nombre) {
		setQuantite(quantite+nombre);
    }
	public int getId() {
		return id;
	}
}