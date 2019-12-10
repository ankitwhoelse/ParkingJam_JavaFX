package application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import modele.LectureDonnees;
import modele.Vehicule;

public class Interface extends Application {

	Pane gameGrid;
	Stage gameStage;
	BorderPane gameRoot;
	Label lbl1, lbl2, lbl3, lblTemps;
	LocalDateTime tempsDebut;
	
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
			
			Scene scene = new Scene(vBox1,600,400);
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
			
			VBox vBox3 = new VBox();
			vBox3.setSpacing(5);
			Label lblMoves = new Label("0");
			lblMoves.setAlignment(Pos.CENTER);
//			lblMoves.set
			lblMoves.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
			lblMoves.setBorder(bordure);
			lblMoves.setPrefSize(150, 100);
			Text txtMoves = new Text("nombres de déplacements");
			txtMoves.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 14));
			vBox3.getChildren().addAll(lblMoves, txtMoves);
			
			Label lblRestart = new Label("Réinitialiser");
			lblRestart.setAlignment(Pos.CENTER);
			lblRestart.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblRestart.setPrefSize(150, 75);
			lblRestart.setBorder(bordure);
			lblRestart.setOnMouseClicked(e -> {
				System.out.println("Windows Key + X, puis U suivi d'un R");
			});
			
			Label lblQuitter = new Label("Retour au menu");
			lblQuitter.setAlignment(Pos.CENTER);
			lblQuitter.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
			lblQuitter.setPrefSize(150, 75);
			lblQuitter.setBorder(bordure);
			lblQuitter.setOnMouseClicked(e -> {gameStage.close();});
			
			vBox2.getChildren().addAll(lblTemps, vBox3, lblRestart, lblQuitter);
			gameRoot.setRight(vBox2);
			gameRoot.setCenter(gameGrid);
			Scene sceneG = new Scene(gameRoot,777,586);
			gameStage.setScene(sceneG);
			gameStage.setResizable(false);
			gameStage.getIcons().add(new Image("icone.png"));
//						
//				PAGE DU JEU
//				-----------
			
		} catch(Exception e) {}
		
	} 	// FIN DE METHODE START
	
	
	public class GestionClick implements EventHandler<MouseEvent>, Runnable {
			
		LectureDonnees lecD;
		ArrayList<Vehicule> cars;
		Vehicule car;
		GestionDrag gd;
		double X;
		double Y;
		
		
		public void handle(MouseEvent e) {
			
			if (e.getSource()==lbl1) { 				// JEU FACILE
				gameStage.setTitle("Jeu facile");
				lecD = new LectureDonnees(1);
				
			} else if (e.getSource()==lbl2) { 	// JEU MOYEN
				gameStage.setTitle("Jeu moyen");
				lecD = new LectureDonnees(2);
				
			} else if (e.getSource()==lbl3 ) { 	// JEU DIFFICILE
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

				for (Vehicule voiture : cars) {
					voiture.setLayoutX(45 + voiture.getIntColonne()*72 );
					voiture.setLayoutY(70 + voiture.getIntLigne()*72 );
					voiture.setOnMouseEntered(enter -> {voiture.setEffect(glow);});
					voiture.setOnMouseExited(exit -> {voiture.setEffect(null);});
					
					voiture.setOnDragDetected(dragDetected -> {
						X = e.getX(); Y = e.getY();
						voiture.setLayoutX(X);
						voiture.setLayoutY(Y);
					});
					
					
					
					gameGrid.getChildren().add(voiture);
				}

				new Thread(this).start();
				gameStage.showAndWait();
			}
			
			
			
		}	// fin de methode handle mouse click

		
		public class GestionDrag implements EventHandler<DragEvent> {

			public void handle(DragEvent d) {
				
				
			}	// fin de methode handle drag
			
		}	// FIN DE LA MÉTHODE GESTION DRAG
		
		
		@Override
		public void run() {
			try {
				while (true) {
					LocalDateTime tempsActuel = LocalDateTime.now();
					long diffSec = Duration.between(tempsDebut, tempsActuel).getSeconds();
					long diffMin = Duration.between(tempsDebut, tempsActuel).toMinutes();
					System.out.println(diffMin + ":" + (diffSec-(diffMin*60)));
					Platform.runLater(() -> lblTemps.setText(" " + diffMin + ":" + (diffSec-(diffMin*60))));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {}
		}	
		
		
	}	// FIN DE METHODE GESTION CLICK

	
	
} // FIN DE CLASSE INTERFACE

