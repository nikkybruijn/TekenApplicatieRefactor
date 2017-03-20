package application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LintBox extends HBox {
	ColorPicker colorPicker = new ColorPicker();

	public LintBox() {
		colorPicker.setPrefWidth(100);
		colorPicker.setValue(Color.BLACK);

		FlowPane flowPane = new FlowPane();
		flowPane.setVgap(20);
		flowPane.setPrefWidth(150);
		flowPane.setPadding(new Insets(5));

    createDrawShapeButtons(flowPane);

    TilePane tilePane = new CreateTilePane().invoke();

		Rectangle currentColor = new Rectangle();
		currentColor.setWidth(100);
		currentColor.setHeight(22);

		Rectangle[] r = new Rectangle[10];
		int i = 0;
    //Inline refactor method
    for (i = 0; i < new Color[]{ Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.BLUE, Color.FIREBRICK, Color.PURPLE, Color.GREEN }.length && i < 10; i++) {
			r[i] = new Rectangle();
			tilePane.getChildren().add(r[i]);
			r[i].setWidth(25);
			r[i].setHeight(25);
			r[i].setFill(new Color[]{ Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.BLUE, Color.FIREBRICK, Color.PURPLE, Color.GREEN }[i]);

			int number = i;
			r[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent t) {
					colorPicker.setValue(new Color[]{ Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.BLUE, Color.FIREBRICK, Color.PURPLE, Color.GREEN }[number]);
					currentColor.setFill(new Color[]{ Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.BLUE, Color.FIREBRICK, Color.PURPLE, Color.GREEN }[number]);
				}
			});
		}

		VBox vbox = new VBox(2);
		vbox.setPadding(new Insets(5));
		vbox.getChildren().addAll(colorPicker, currentColor);
		this.getChildren().addAll(vbox, tilePane, flowPane);
		currentColor.setFill(colorPicker.getValue());

		colorPicker.setOnAction(e -> {
			currentColor.setFill(colorPicker.getValue());
		});
	}

  //Extract method
  private void createDrawShapeButtons(FlowPane flowPane) {
    ToggleGroup group = new ToggleGroup();
    RadioButton radioButton1 = new RadioButton("Lijn");
    radioButton1.setToggleGroup(group);
    radioButton1.setSelected(true);
    radioButton1.setPrefSize(85, 0);
    RadioButton radioButton2 = new RadioButton("Cirkel");
    radioButton2.setToggleGroup(group);
    RadioButton radioButton3 = new RadioButton("Rechthoek");
    radioButton3.setToggleGroup(group);
    radioButton3.setPrefSize(85, 0);
    RadioButton radioButton4 = new RadioButton("Pen");
    radioButton4.setToggleGroup(group);
    flowPane.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);
  }

  public Paint getColor() {
		return colorPicker.getValue();
	}

	//Extract method object
  private class CreateTilePane {
    public TilePane invoke() {
      TilePane tilePane = new TilePane();
      tilePane.setPadding(new Insets(5));
      tilePane.setVgap(4);
      tilePane.setHgap(4);
      return tilePane;
    }
  }
}
