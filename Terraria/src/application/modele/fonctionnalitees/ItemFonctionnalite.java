package application.modele.fonctionnalitees;

import java.util.Timer;

import application.modele.Item;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class ItemFonctionnalite {
	private Node node;
	private double startX;
	private double startY;
	private GridPane inventaire;
	private GridPane inventaireRapide;

	public ItemFonctionnalite(Node node ,GridPane grid1,GridPane grid2) {
		this.inventaire=grid1;
		this.inventaireRapide=grid2;
		this.node=node;
		makeDrag();
	}
	private void makeDrag() {
		node.setOnMouseClicked(mouse ->{
			if(mouse.getButton() == MouseButton.PRIMARY) {
				startX = node.getTranslateX()+mouse.getX();
				startY = node.getTranslateY()+mouse.getY();
			}
		});
		node.setOnMouseDragged(mouse ->{
			if(mouse.getButton() == MouseButton.PRIMARY) {
				node.setTranslateX(node.getTranslateX()+mouse.getX());
				node.setTranslateY(node.getTranslateY()+mouse.getY());
			}
		});
		node.setOnMouseReleased(mouse ->{
			if(isInside((GridPane) node.getParent())) {
				Tableau.modif((GridPane) node.getParent(),node);
			}
			else if(isInside(otherParent())) {
				Tableau.changement(otherParent(), (GridPane)node.getParent(), node);
			}
			else {
				node.setTranslateX(startX);
				node.setTranslateY(startY);
			}
		});
	}
	private boolean isInside(GridPane otherParent) {
		double x = node.getParent().getLayoutX()+node.getTranslateX()+GridPane.getColumnIndex(node)*32;
		double y = node.getParent().getLayoutY()+node.getTranslateY()+GridPane.getRowIndex(node)*32;
		if(x<otherParent.getLayoutX() || y<otherParent.getLayoutY() || x>=otherParent.getLayoutX()+otherParent.getWidth()|| y>=otherParent.getLayoutY()+otherParent.getHeight())
			return false;
		return true;
	}
	private GridPane otherParent() {
		if(inventaire.getId().equals(node.getParent().getId()))
			return inventaireRapide;
		else
			return inventaire;
	}

}
