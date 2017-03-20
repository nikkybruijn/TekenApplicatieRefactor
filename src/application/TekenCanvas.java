package application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;

public class TekenCanvas extends Canvas {

  //Extract constant
	public static final int X_COORDINATE = 5;
	public static final int Y_COORDINATE = 5;

	//Extract parameter object
	public TekenCanvas(CanvasProperties canvasProperties)  {
		super(canvasProperties.getWidth(), canvasProperties.getHeight());
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.strokeRect(X_COORDINATE, Y_COORDINATE, canvasProperties.getWidth() - 9, canvasProperties.getHeight() - 10);

		this.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.beginPath();
				gc.setStroke(canvasProperties.gethL().getColor());
			}
		});

		this.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.lineTo(event.getX(), event.getY());
				gc.stroke();
			}
		});

		this.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				gc.closePath();
			}
		});
	}

  //Extract parameter object
  //Extract interface
  static class CanvasProperties implements EenInterface {
    private final LintBox hL;
    private final int width;
    private final int height;

    CanvasProperties(LintBox hL, int width, int height) {
      this.hL = hL;
      this.width = width;
      this.height = height;
    }

    @Override
    public LintBox gethL() {
      return hL;
    }

    @Override
    public int getWidth() {
      return width;
    }

    @Override
    public int getHeight() {
      return height;
    }
  }
}
