package application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.IOException;

public class InstellingenDialoog extends Stage {
	private Stage refStage;
	TextField topTextField = new TextField();
	TextField bottomTextField = new TextField();
	Button cancelButton = new Button("Annuleren");
	Button acceptButton = new Button("Ok");

	private InstellingenDialoog(Stage owner) {
		super(StageStyle.UTILITY);
		refStage = owner;
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		this.setResizable(false);
		HBox hbox = new HBox(5);

		Label topLabel = new Label("Hoogte:");
		topLabel.setPrefWidth(250);
		Label bottomLabel = new Label("Breedte:");
		bottomLabel.setPrefWidth(250);

		cancelButton.setOnAction(e -> this.hide());

		acceptButton.setOnAction(e -> this.hide());
		acceptButton.setPrefWidth(75);

		VBox controlBox = new VBox(15);
		hbox.getChildren().addAll(cancelButton, acceptButton);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		controlBox.setAlignment(Pos.CENTER);
		controlBox.getChildren().addAll(topLabel, topTextField, bottomLabel, bottomTextField, hbox);
		controlBox.setPadding(new Insets(15));

		Scene scene = new Scene(controlBox);
		this.setScene(scene);
	}

  //Replace Constructor with Factory Method
  public static InstellingenDialoog createInstellingenDialoog(Stage owner) {
    return new InstellingenDialoog(owner);
  }

  public void Melding() {
		InstellingenDialoog al = createInstellingenDialoog(refStage);
		al.showAndWait();
	}

	//Move - Refactor methode
  //Make static
  static boolean afbeeldingOpslaan(boolean dialoogAltijdTonen, TekenApp tekenApp) {
      boolean imageSaved = false;

      if (tekenApp.huidigBestand == null || dialoogAltijdTonen) {
        FileChooser chooser = new FileChooser();
          chooser.setTitle("Bestand opslaan");
          chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"));
          chooser.setInitialFileName("Naamloos.png");
          tekenApp.huidigBestand = chooser.showSaveDialog(tekenApp.refStage);
      }

      if (tekenApp.huidigBestand != null) {
          SnapshotParameters params = new SnapshotParameters();
          WritableImage img = tekenApp.hetCanvas.snapshot(params, null);
          RenderedImage img2 = SwingFXUtils.fromFXImage(img, null);
          imageSaved = true;
          try {
              ImageIO.write(img2, "png", tekenApp.huidigBestand);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return imageSaved;
  }
}
