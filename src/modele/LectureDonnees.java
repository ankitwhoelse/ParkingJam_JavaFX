package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LectureDonnees {

	int intDiff;
	ArrayList<Vehicule> cars;
	
	public LectureDonnees(int intDiff) {
		
		cars = new ArrayList<Vehicule>();
		BufferedReader br = null;
		StringTokenizer st;
		String strLigne;
		String fichier = "";
		Vehicule car;

		try {
			if (intDiff == 1) 			// Difficulté FACILE
				fichier = "facile.txt";
			else if (intDiff == 2) 		// Difficulté MOYENNE
				fichier = "moyen.txt";
			else if (intDiff == 3)		// Difficulté DIFFICILE
				fichier = "difficile.txt";
						
			br = new BufferedReader(new FileReader(fichier));
			while ((strLigne = br.readLine()) != null) {
				st = new StringTokenizer(strLigne, ",");
				String couleur = st.nextToken().toLowerCase();
				int longeur = Integer.parseInt(st.nextToken());
				int colonne = Integer.parseInt(st.nextToken());
				int ligne = Integer.parseInt(st.nextToken());
				char direction = st.nextToken().charAt(0);
				String strLong;
					if (longeur == 2) 
						strLong = "auto";
					else 
						strLong = "camion";
				String strFichier = strLong + "_" + direction + "_" + couleur + ".gif";
								
				car = new Vehicule(couleur, longeur, colonne, ligne, direction, strFichier);
				
				
				cars.add(car);
			} 
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<Vehicule> getCars() {
		return cars;
	}

	
	
	
}
