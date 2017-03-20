package application;

/**
 * Created by Nikky on 20-3-2017.
 */

//Middleman klasse nieuw aangemaakt om de "Remove middleman" refactor methode toe te passen

public class Middleman {
  Bar bar;
  public Bar getBar() {
    return bar;
  }
}

class Bar {
  private Middleman colorValue1;
  public Bar(Middleman colorValue) {
    colorValue1 = colorValue;
  }
  public Middleman getColorValue() {
    return colorValue1;
  }
}

class Client {
  Middleman a;
  Middleman impValue = a.getBar().getColorValue();
}
