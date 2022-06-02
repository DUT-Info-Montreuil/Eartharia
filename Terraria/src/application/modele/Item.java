package application.modele;

public abstract class Item {

	private int idItem;
	private int quantite;
	public static int compteur=0;
	private String id;

	public Item (int idItem) {
		this.idItem = idItem ;
		this.quantite = 1;
		this.id="I"+compteur;
		compteur++;
	}
	public Item (int idItem,int quantite) {
		this.idItem = idItem ;
		this.quantite = quantite;
		this.id="I"+compteur;
		compteur++;
	}
	public int getQuantite() {
		return this.quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean equals(Item item) {
		return item.getIdItem()==idItem;
	}
	public void addQuantite(int nombre) {
		setQuantite(quantite+nombre);
	}
	public int getIdItem() {
		return idItem;
	}
	public String getId() {
		return id;
	}
}