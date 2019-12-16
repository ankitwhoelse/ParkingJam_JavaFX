package modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* Classe de l'objet "Vehicule".
*
* @author Ankit Patel
* @version 1.1 2019-12-15
*/
public class Vehicule extends ImageView{

	private String strCouleur;
	private int intLongeur;
	private int intColonne;
	private int intLigne;
	private char chrDirection;
	private String strFichier;
	private double dblX;
	private double dblY;
	
	/**
	* Constructeur du Vehicule
	*/
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
	
	/**
	* Retourne strCouleur
	*	
	* @return la courleur du véhicule
	*/
	public String getStrCouleur() {
		return strCouleur;
	}
	
	/**
	* Retourne intLongeur
	*	
	* @return la longeur du véhicule
	*/
	public int getIntLongeur() {
		return intLongeur;
	}
	
	/**
	* Retourne intColonne
	*	
	* @return la colonne du véhicule lors du début de la partie
	*/
	public int getIntColonne() {
		return intColonne;
	}
	/**
	* Retourne intLigne
	*	
	* @return la ligne du véhicule lors du début de la partie
	*/
	public int getIntLigne() {
		return intLigne;
	}

	/**
	* Retourne chrDirection
	*	
	* @return la direction du véhicule
	*/
	public char getChrDirection() {
		return chrDirection;
	}
	
	/**
	* Retourne strFichier
	*	
	* @return le fichier contenant l'image du véhicule
	*/
	public String getStrFichier() {
		return strFichier;
	}

	/**
	* Retourne dblX
	*	
	* @return la coordonnée X
	*/
	public double getDblX() {
		return dblX;
	}

	/**
	* Initialise une nouvelle coordonnée X
	*
	* @param dblY <coordonnée X>
	*/
	public void setDblX(double dblX) {
		this.dblX = dblX;
	}

	/**
	* Retourne dblY
	*	
	* @return la coordonnée Y
	*/
	public double getDblY() {
		return dblY;
	}
	
	/**
	* Initialise une nouvelle coordonnée Y
	*
	* @param dblY <coordonnée Y>
	*/
	public void setDblY(double dblY) {
		this.dblY = dblY;
	}
	

}
