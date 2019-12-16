package application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.LectureDonnees;
import modele.Vehicule;

/**
* Construit l'interface graphique et donne toutes les propriétés aux éléments.
*
* @author Ankit Patel
* @version 1.6.3 2019-12-15
*/
public class Interface extends Application {

	private Pane gameGrid;
	private Stage gameStage, victoryStage;
	private Scene scene, sceneG, sceneV;
	private BorderPane gameRoot;
	private Label lbl1, lbl2, lbl3, lblTemps, lblMoves, lblRestart;
	private LocalDateTime tempsDebut;
	
	/**
	* Construit et démarre l'application.
	*
	* Cette méthode contient tous les éléments qui constituent les
	* différentes panes de l'application.
	*/
	@Override
	public void start(Stage primaryStage) {
		try {
//				-------------------			
//				PAGE D'INTRODUCTION
//			
			VBox vBox1 = new VBox();
			vBox1.setSpacing(30);
			HBox hBox1 = new HBox();
			hBox1.setSpacing(30);
			GestionClick gc = new GestionClick();
			
			Image fondBleu = new Image("fondecran.png");
			BackgroundSize fondTaille = new BackgroundSize(100,100,true,true,false,true); 
			BackgroundImage fondImage = new BackgroundImage(fondBleu, BackgroundRepeat.SPACE,BackgroundRepeat.SPACE,BackgroundPosition.DEFAULT,fondTaille);
			Background fondAzul = new Background(fondImage);
			vBox1.setBackground(fondAzul);
			
			Glow glowOn = new Glow();
			glowOn.setLevel(0.3);
			
			Label lbl0 = new Label("par Ankit Patel");
			Image logo = new Image("logo.gif");
			lbl0.setGraphic(new ImageView(logo));
			lbl0.setFont(Font.font("Impact",20));
			lbl0.setTextFill(Color.YELLOW);
			lbl0.setContentDisplay(ContentDisplay.TOP);
			lbl0.setOnMouseEntered(mouseE -> {lbl0.setEffect(glowOn);});
			lbl0.setOnMouseExited(mouseL -> {lbl0.setEffect(null);});
			lbl0.setOnMouseClicked(troll -> {Platform.exit(); System.exit(0);});
			
			lbl1 = new Label("Facile");
			Image imgFacile = new Image("mini_facile.png");
			lbl1.setGraphic(new ImageView(imgFacile));
			lbl1.setFont(Font.font("Arial",20));
			lbl1.setTextFill(Color.LAWNGREEN);
			lbl1.setContentDisplay(ContentDisplay.TOP);
			lbl1.setOnMouseEntered(mouseE -> {lbl1.setEffect(glowOn);});
			lbl1.setOnMouseExited(mouseL -> {lbl1.setEffect(null);});
			lbl1.setOnMouseClicked(gc);
			
			lbl2 = new Label("Moyen");
			Image imgMoyen = new Image("mini_moyen.png");
			lbl2.setGraphic(new ImageView(imgMoyen));
			lbl2.setFont(Font.font("Arial",20));
			lbl2.setTextFill(Color.ORANGE);
			lbl2.setContentDisplay(ContentDisplay.TOP);
			lbl2.setOnMouseEntered(mouseE -> {lbl2.setEffect(glowOn);});
			lbl2.setOnMouseExited(mouseL -> {lbl2.setEffect(null);});
			lbl2.setOnMouseClicked(gc);
			
			lbl3 = new Label("Difficile");
			Image imgDiff = new Image("mini_diff.png");
			lbl3.setGraphic(new ImageView(imgDiff));
			lbl3.setFont(Font.font("Arial",20));
			lbl3.setTextFill(Color.RED);
			lbl3.setContentDisplay(ContentDisplay.TOP);
			lbl3.setOnMouseEntered(mouseE -> {lbl3.setEffect(glowOn);});
			lbl3.setOnMouseExited(mouseL -> {lbl3.setEffect(null);});
			lbl3.setOnMouseClicked(gc);
			
			hBox1.getChildren().addAll(lbl1, lbl2, lbl3);
			hBox1.setAlignment(Pos.CENTER);
			vBox1.getChildren().addAll(lbl0, hBox1);
			vBox1.setAlignment(Pos.CENTER);
			
			scene = new Scene(vBox1,600,400);
			scene.setCursor(new ImageCursor(new Image("icone.png")));
			primaryStage.getIcons().add(new Image("icone.png"));
			primaryStage.setTitle("Rushhour par Ankit");
			primaryStage.setScene(scene);
			primaryStage.show();
//						
//			PAGE D'INTRODUCTION
//			-------------------
			
//				-----------			
//				PAGE DU JEU
//
			gameStage = new Stage();
			gameRoot = new BorderPane();
			gameGrid = new Pane();
			Image imgGrille= new Image("grille.gif");
			BackgroundSize grilleTaille = new BackgroundSize(100,80,true,true,true,false); 
			BackgroundImage fondGrille = new BackgroundImage(imgGrille, BackgroundRepeat.SPACE,BackgroundRepeat.SPACE,BackgroundPosition.DEFAULT,grilleTaille);
			Background fondGrid = new Background(fondGrille);
			gameGrid.setBackground(fondGrid);
			
			BorderStrokeStyle styleGris = new BorderStrokeStyle(StrokeType.CENTERED, StrokeLineJoin.MITER, StrokeLineCap.BUTT,10,0,null);
			BorderStroke bordureGris = new BorderStroke(Color.GRAY, styleGris, new CornerRadii(15), new BorderWidths(10), new Insets(0));
			Border bordure = new Border(bordureGris);
					
			VBox vBox2 = new VBox();
			vBox2.setPadding(new Insets(30));
			vBox2.setSpacing(50);
			vBox2.setAlignment(Pos.CENTER);
			
			lblTemps = new Label("00:00");
			lblTemps.setAlignment(Pos.CENTER);
			lblTemps.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
			lblTemps.setTextFill(Color.FIREBRICK);
			lblTemps.setPrefSize(150, 100);
			lblTemps.setBorder(bordure);
//				lblTemps.setOnMouseClicked(click -> {System.exit(1);});					// APP QUIT ON CLICK SUR TEMPS
			
			VBox vBox3 = new VBox();
			vBox3.setSpacing(5);
			lblMoves = new Label("0");
			lblMoves.setAlignment(Pos.CENTER);
			lblMoves.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
			lblMoves.setBorder(bordure);
			lblMoves.setPrefSize(150, 100);
			Text txtMoves = new Text("nombres de déplacements");
			txtMoves.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 14));
			vBox3.getChildren().addAll(lblMoves, txtMoves);
			
			lblRestart = new Label("Réinitialiser");
			lblRestart.setAlignment(Pos.CENTER);
			lblRestart.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblRestart.setPrefSize(150, 75);
			lblRestart.setBorder(bordure);
			lblRestart.setOnMouseClicked(gc);
			
			Label lblQuitter = new Label("Retour au menu");
			lblQuitter.setAlignment(Pos.CENTER);
			lblQuitter.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblQuitter.setPrefSize(150, 75);
			lblQuitter.setBorder(bordure);
			lblQuitter.setOnMouseClicked(e -> {gameStage.close(); primaryStage.show();});
			
			vBox2.getChildren().addAll(lblTemps, vBox3, lblRestart, lblQuitter);
			gameRoot.setRight(vBox2);
			gameRoot.setCenter(gameGrid);
			sceneG = new Scene(gameRoot,777,585);
			gameStage.setScene(sceneG);
			gameStage.setResizable(false);
			gameStage.getIcons().add(new Image("icone.png"));
//						
//				PAGE DU JEU
//				-----------
			
		} catch(Exception e) {}
		
	} 	// FIN DE METHODE START
	

	private class GestionClick implements EventHandler<MouseEvent>, Runnable {
			
		private LectureDonnees lecD;
		private ArrayList<Vehicule> cars;
		private double X;	double  Y;
		private int moves = 0;
		private Boolean dragged = false;
		private double[][] grille = new double[6][6];
		
		public void handle(MouseEvent e) {
					
			if (e.getSource()==lbl1) { 				// JEU FACILE
				gameStage.setTitle("Jeu facile");
				lecD = new LectureDonnees(1);
				
			} else if (e.getSource()==lbl2) { 		// JEU MOYEN
				gameStage.setTitle("Jeu moyen");
				lecD = new LectureDonnees(2);
				
			} else if (e.getSource()==lbl3 ) { 		// JEU DIFFICILE
				gameStage.setTitle("Jeu difficile");
				lecD = new LectureDonnees(3);
			}
			
													// CONSTRUCTION DE TABLE DE JEU
			if (e.getSource() == lbl1 || e.getSource()==lbl2 || e.getSource()==lbl3) {
				Glow glow = new Glow();
				glow.setLevel(0.5);
							
				tempsDebut = LocalDateTime.now();
				cars = lecD.getCars();
				gameGrid.getChildren().clear();
				moves = 0;
				lblMoves.setText("0");
													// CONSTRUCTION DE LA VOITURE
				for (Vehicule voiture : cars) {
					voiture.setLayoutX(voiture.getDblX());
					voiture.setLayoutY(voiture.getDblY());
					
					int colon = voiture.getIntColonne();
					int ligne = voiture.getIntLigne();
					
					for (int i = 0; i < voiture.getIntLongeur(); i++) {
						grille[ligne][colon] = 1;
						if (voiture.getChrDirection() == 'H')
							colon++;
						else 
							ligne++;
					}
					
					voiture.setOnMouseEntered(enter -> {
						voiture.setEffect(glow);
						if (voiture.getChrDirection()=='H')
							sceneG.setCursor(Cursor.H_RESIZE);
						else
							sceneG.setCursor(Cursor.V_RESIZE);
						});
					
					voiture.setOnMouseExited(exit -> {voiture.setEffect(null);sceneG.setCursor(Cursor.DEFAULT);});
					
					voiture.setOnMousePressed(mousePressed -> {
						dragged = false;
						X = mousePressed.getX();
						Y = mousePressed.getY();
					});
					
					voiture.setOnMouseDragged(mouseDragged -> {
						dragged = true;
						
							// POSITION DE VOITURE AVEC MOUVEMENT DE LA SOURIS
						double posX = (mouseDragged.getX() - X + voiture.getLayoutX());
						double posY = (mouseDragged.getY() - Y + voiture.getLayoutY());		
						
							//	BLOCKAGE AUX MARGES VERTICALES
						if (voiture.getChrDirection() == 'H' && (posX >= 45 && posX <= (45+6*72) - voiture.getIntLongeur()*72)) {
							voiture.setLayoutX(posX);
						}	//	BLOCKAGE AUX MARGES HORIZONTALES
						else if (voiture.getChrDirection() == 'V' && (posY >= 70 && posY <= (70+6*72) - voiture.getIntLongeur()*72)) {
							voiture.setLayoutY(posY);
						}

						
						int newCaseX = (int) (posX - 45)/72;
						int newCaseY = (int) (posY - 45)/72;
						if (newCaseX < 0) newCaseX = 0;		if (newCaseX > 5) newCaseX = 5;
						if (newCaseY < 0) newCaseY = 0;		if (newCaseY > 5) newCaseY = 5;
						
						if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==3 && newCaseX > 3) newCaseX=3; 
						if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==2 && newCaseX > 4) newCaseX=4;
						if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==3 && newCaseY > 3) newCaseY=3;
						if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==2 && newCaseY > 4) newCaseY=4;
					    
						int new2CaseX = newCaseX;
						int new2CaseY = newCaseY;
						
						for (int i = 0; i < voiture.getIntLongeur(); i++) {
							grille[new2CaseX][new2CaseY] = 0;
							
							if (voiture.getChrDirection()=='H')
								new2CaseX++;
							else
								new2CaseY++;
						}
						
//						for (int i = 0; i < voiture.getIntLongeur(); i++) 	// COLLISION
//							//voiture horizontale
//							if (voiture.getChrDirection()=='H')
//								//auto
//								if (voiture.getIntLongeur()==2)
//									if (newCaseX+2 <=5 && grille[newCaseX+2][newCaseY] == 1 ) 
//										System.out.println("crash a droite - A");
//									else if (newCaseX-1 >= 0 && grille[newCaseX-1][newCaseY] == 1) 
//										System.out.println("crash a gauche - A");
//								//camion
//								else
//									if (newCaseX+3 <= 5 && grille[newCaseX+3][newCaseY] == 1) 
//										System.out.println("crash a droite - C");
//									else if (newCaseX-1 >=0 && grille[newCaseY-1][newCaseY] == 1) 
//										System.out.println("crash a gauche - C");
//							//voiture verticale
//							else 
//								//auto
//								if (voiture.getIntLongeur()==2)
//									if (newCaseY+2 <= 5 && grille[newCaseX][newCaseY+2] == 1 )
//										System.out.println("crash en bas - A");
//									else if (newCaseY-1 >= 0 && grille[newCaseX][newCaseY-1] == 1)
//										System.out.println("crash en haut - A");
//								//camion
//								else 
//									if (newCaseY+3 <= 5 && grille[newCaseX][newCaseY+3] == 1 )
//										System.out.println("crash en bas - C");
//									else if (newCaseY-1 >= 0 && grille[newCaseX][newCaseY-1] == 1)
//										System.out.println("crash en haut - C");

					
					});
						
					voiture.setOnMouseReleased(mouseReleased -> {
						if (dragged == true) {
							double posX = (mouseReleased.getX() - X + voiture.getLayoutX());
							double posY = (mouseReleased.getY() - Y + voiture.getLayoutY());

							int newCaseX = (int) (posX - 45)/72;
							int newCaseY = (int) (posY - 45)/72;
														
							if ( ( ((posX-45)/72) % 10) > 0.5 ) newCaseX++; 
								
							if ( ( ((posY-70)/72) % 10) > 0.5 ) newCaseY++;
						
							if (newCaseX < 0) newCaseX = 0;		if (newCaseX > 5) newCaseX = 5;
							if (newCaseY < 0) newCaseY = 0;		if (newCaseY > 5) newCaseY = 5;
							
							if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==3 && newCaseX > 3) newCaseX=3; 
							if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==2 && newCaseX > 4) newCaseX=4;
							if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==3 && newCaseY > 3) newCaseY=3;
							if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==2 && newCaseY > 4) newCaseY=4;
						
							if (voiture.getChrDirection()=='H')
								voiture.setLayoutX(45 + newCaseX*72);
							else
								voiture.setLayoutY(70 + newCaseY*72);

//							System.out.println(newCaseX + ":" + newCaseY);

							if (newCaseX==4 && newCaseY==3 && voiture.getChrDirection()=='H') {
//								----------------
//								PAGE DU VICTOIRE
//								
								gameStage.close();
								BorderPane root3 = new BorderPane();
								VBox vBox3 = new VBox();
								
								Text txtVic = new Text("\n\nVICTOIRE");
								txtVic.setFont(Font.font("Impact",40));
								txtVic.setFill(Color.YELLOW);
								
								Label txtVic2 = new Label("Vous avez réussi en\n" + "   	 " +lblTemps.getText() 
								+ " avec " + lblMoves.getText() + "\n    déplacements.");
								txtVic2.setFont(Font.font("Impact",30));
								txtVic2.setTextFill(Color.YELLOW);
								Image imgVic = new Image("award.gif");
								txtVic2.setGraphic(new ImageView(imgVic));
								txtVic2.setContentDisplay(ContentDisplay.BOTTOM);
								txtVic2.setAlignment(Pos.CENTER);
								txtVic2.setOnMouseEntered(mE -> {txtVic2.setEffect(glow);
								sceneV.setCursor(new ImageCursor(imgVic));});
								txtVic2.setOnMouseExited(mE -> {txtVic2.setEffect(null);
								sceneV.setCursor(Cursor.DEFAULT);});
								txtVic2.setOnMouseClicked(mC -> {victoryStage.close();});
								
								vBox3.setAlignment(Pos.CENTER);
								vBox3.setSpacing(10);
								vBox3.getChildren().addAll(txtVic, txtVic2);
								root3.setCenter(vBox3);
								
								Image fondVic = new Image("victoire.jpg");
								BackgroundSize fondTail = new BackgroundSize(100,100,true,true,false,true); 
								BackgroundImage fondImg = new BackgroundImage(fondVic, BackgroundRepeat.SPACE,BackgroundRepeat.SPACE,BackgroundPosition.DEFAULT,fondTail);
								Background fondVict = new Background(fondImg);
								root3.setBackground(fondVict);
								
								sceneV = new Scene(root3,800,600);
								victoryStage = new Stage();
								victoryStage.getIcons().add(imgVic);
								victoryStage.setTitle("Victoire");
								victoryStage.setScene(sceneV);
								victoryStage.show();
//						
//								PAGE DU VICTOIRE
//								----------------
							}
							
							
							for (int i = 0; i < grille.length; i++) 
								for (int k = 0; k < grille.length; k++) 
									grille[i][k] = 0;
								
							for (Vehicule car : cars) {
								double newposX = (car.getLayoutX());
								double newposY = (car.getLayoutY());
								newCaseX = (int) (newposX - 45)/72;
								newCaseY = (int) (newposY - 45)/72;
								if (newCaseX < 0) newCaseX = 0;		if (newCaseX > 5) newCaseX = 5;
								if (newCaseY < 0) newCaseY = 0;		if (newCaseY > 5) newCaseY = 5;
												
								if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==3 && newCaseX > 3) newCaseX=3; 
								if (voiture.getChrDirection()=='H' && voiture.getIntLongeur()==2 && newCaseX > 4) newCaseX=4;
								if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==3 && newCaseY > 3) newCaseY=3;
								if (voiture.getChrDirection()=='V' && voiture.getIntLongeur()==2 && newCaseY > 4) newCaseY=4;
								
								for (int i = 0; i < car.getIntLongeur(); i++) {
									grille[newCaseY][newCaseX] = 1;
									if (car.getChrDirection() == 'H')
										newCaseX++;
									else 
										newCaseY++;
								}
							}
							
							moves++;
							lblMoves.setText(Integer.toString(moves));
						}
					});
										
					gameGrid.getChildren().add(voiture);
				}									// CONSTRUCTION DE LA VOITURE

				new Thread(this).start();
				gameStage.showAndWait();
			}
			
			if (e.getSource() == lblRestart) {
				tempsDebut = LocalDateTime.now();
				cars = lecD.getCars();
				moves = 0;
				lblMoves.setText("0");
				
				for (Vehicule voiture : cars) {
					voiture.setLayoutX(45 + voiture.getIntColonne()*72);
					voiture.setLayoutY(70 + voiture.getIntLigne()*72);
				}
				
//				//				VISUEL DE GRILLE	
//				for (int i = 0; i < grille.length; i++) {
//					for (int k = 0; k < grille.length; k++)
//						System.out.print("["+grille[i][k]+"]");
//					System.out.println();
//				}
				
			}
			
		}	// fin de methode handle mouse click


		
		/**
		* Mise en marche du chronomètre.
		*
		* La méthode débute le chronomètre quand le jeu commence. 
		* Le temps est affiché sur la pane du jeu puis est affiché à la victoire.
		*/
		@Override
		public void run() {
			try {
				while (true) {
					LocalDateTime tempsActuel = LocalDateTime.now();
					long diffSec = Duration.between(tempsDebut, tempsActuel).getSeconds();
					long diffMin = Duration.between(tempsDebut, tempsActuel).toMinutes();
//					System.out.println(diffMin + ":" + (diffSec-(diffMin*60)));
					Platform.runLater(() -> lblTemps.setText(" " + diffMin + ":" + (diffSec-(diffMin*60))));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {}
		}	
		
		
	}	// FIN DE METHODE GESTION CLICK

	
	
} // FIN DE CLASSE INTERFACE

