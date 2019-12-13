package modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vehicule extends ImageView{

	String strCouleur;
	int intLongeur;
	int intColonne;
	int intLigne;
	char chrDirection;
	String strFichier;
	double dblX;
	double dblY;
	
	public Vehicule(String strCouleur, int intLongeur, int intColonne, int intLigne, char chrDirection, String strFichier, double dblX, double dblY) {
		this.strCouleur = strCouleur;
		this.intLongeur = intLongeur;
		this.intColonne = intColonne;
		this.intLigne = intLigne;
		this.chrDirection = chrDirection;
		this.strFichier = strFichier;
		this.setImage(new Image(strFichier));
		this.dblX = dblX;
		this.dblY = dblY;
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

	public double getDblX() {
		return dblX;
	}

	public void setDblX(double dblX) {
		this.dblX = dblX;
	}

	public double getDblY() {
		return dblY;
	}

	public void setDblY(double dblY) {
		this.dblY = dblY;
	}
	

}
