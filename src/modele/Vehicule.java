package modele;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vehicule extends ImageView{

	ArrayList<Vehicule> cars;
	String strCouleur;
	int intLongeur;
	int intColonne;
	int intLigne;
	char chrDirection;
	String strFichier;
	
	public Vehicule(String strCouleur, int intLongeur, int intColonne, int intLigne, char chrDirection, String strFichier) {
		this.strCouleur = strCouleur;
		this.intLongeur = intLongeur;
		this.intColonne = intColonne;
		this.intLigne = intLigne;
		this.chrDirection = chrDirection;
		this.strFichier = strFichier;
		this.setImage(new Image(strFichier));
	}

	public String getStrCouleur() {
		return strCouleur;
	}

	public int getIntLongeur() {
		return intLongeur;
	}

	public int getIntColonne() {
		return intColonne;
	}

	public int getIntLigne() {
		return intLigne;
	}


	public char getChrDirection() {
		return chrDirection;
	}

	public String getStrFichier() {
		return strFichier;
	}
	
	@Override
	public String toString() {
		return "Vehicule [strCouleur=" + strCouleur + ", intLongeur=" + intLongeur + ", intColonne=" + intColonne
				+ ", intLigne=" + intLigne + ", chrDirection=" + chrDirection + ", strFichier=" + strFichier + "]";
	}
	

}
