package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("wold");
    hello("user");
    hello("Tatyana");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p = new Point (2,5,3,8);
    System.out.println("Расстояние между точками" + " = " + distance(p));

  }


  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance(Point p) {
    return Math.sqrt(Math.sqrt(p.x2-p.x1)+Math.sqrt(p.y2-p.y1));
  }

}



