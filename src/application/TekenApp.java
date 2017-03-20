package application;
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TekenApp extends Application {
	private InstellingenDialoog hetDialoog;
	private LintBox hetLint;
  public TekenCanvas hetCanvas;
	public File huidigBestand = null;
	public Stage refStage;

	BorderPane borderPane = new BorderPane();

	public void start(Stage stage) {
		refStage = stage;
		VBox vbox = new VBox();
		MenuBar menuBar = new MenuBar();
		Menu menuBestand = new Menu("_Bestand");
		MenuItem nieuwBestand = new MenuItem("_Nieuw");
		nieuwBestand.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		nieuwBestand.setOnAction(e -> nieuwBestandAanmaken());

		MenuItem menuOpslaan = new Menu("_Opslaan");
		menuOpslaan.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		menuOpslaan.setOnAction(e -> {
			hetDialoog.afbeeldingOpslaan(false, this);
		});

		MenuItem menuOpslaanAls = new Menu("_Opslaan Als");
		menuOpslaanAls.setOnAction(e -> {
			hetDialoog.afbeeldingOpslaan(true, this);
		});

		MenuItem menuAppSluiten = new MenuItem("Sluiten");
		menuAppSluiten.setOnAction(e -> {
			System.exit(0);
		});

		menuBestand.getItems().addAll(nieuwBestand, menuOpslaan, menuOpslaanAls, menuAppSluiten);

		Menu menuOpties = new Menu("_Opties");
		MenuItem menuInstellingen = new MenuItem("_Instellingen");
		menuInstellingen.setOnAction(e -> {
			hetDialoog.Melding();
		});

		menuOpties.getItems().add(menuInstellingen);
		menuBar.getMenus().addAll(menuBestand, menuOpties);

		hetLint = new LintBox();
		hetLint.setPrefWidth(430);
		hetLint.setPrefHeight(50);
		//Replace Constructor with Builder
		hetCanvas = new TekenCanvasBuilder().sethL(hetLint).setWidth(430).setHeight(300).maakTekenCanvas();
    //Replace Constructor with Factory Method
		hetDialoog = InstellingenDialoog.createInstellingenDialoog(stage);
		vbox.getChildren().addAll(menuBar, hetLint);
		borderPane.setCenter(hetCanvas);
		borderPane.setPrefSize(430, 300);
		borderPane.setTop(vbox);

		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("TekenApp 2.0");
		stage.show();
	}

  public void nieuwBestandAanmaken() {
    hetCanvas = new TekenCanvasBuilder().sethL(hetLint).setWidth(430).setHeight(300).maakTekenCanvas();
    borderPane.setCenter(hetCanvas);
  }

	public static void main(String[] args) {
		launch(args);
	}
}
