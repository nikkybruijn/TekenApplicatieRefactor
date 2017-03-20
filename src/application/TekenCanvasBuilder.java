package application;

//Gegenereerd door "Replace constructor by builder"
public class TekenCanvasBuilder {
  private final MySuperClass superClass = new MySuperClass();
  private LintBox hL;
    private int width;
    private int height;

  public TekenCanvasBuilder sethL(LintBox hL) {
        TekenCanvasBuilder.this.hL = hL;
        return TekenCanvasBuilder.this;
    }

    public TekenCanvasBuilder setWidth(int width) {
        TekenCanvasBuilder.this.width = width;
        return TekenCanvasBuilder.this;
    }

    public TekenCanvasBuilder setHeight(int height) {
        TekenCanvasBuilder.this.height = height;
        return TekenCanvasBuilder.this;
    }
    //Change method signature
    public TekenCanvas maakTekenCanvas() {
        return new TekenCanvas(new TekenCanvas.CanvasProperties(hL, width, height));
    }

  //Replace inheritance with delegation
  public void openMethod() {
    superClass.openMethod();
  }

  //Replace inheritance with delegation
  private class MySuperClass extends SuperClass {
    public void openMethod() {

    }
  }
}
