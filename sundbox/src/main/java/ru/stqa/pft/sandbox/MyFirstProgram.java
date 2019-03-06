package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    Point p1 = new Point(7.0, 25.0);
    Point p2 = new Point(15.0, 30.0);

    System.out.println("Расстояние между точками " + "p1("+p1.x+","+p1.y+")" + " и " + "p2("+p2.x+","+p2.y+")" + " = " + distance(p1, p2));

  }


  public static double distance(Point p1, Point p2) {

    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }

}



