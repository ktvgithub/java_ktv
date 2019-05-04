package ru.stqa.pft.sundbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    Point p1 = new Point(64.0, 75.0);
    Point p2 = new Point(121.0, 95.0);

    System.out.println("Расстояние между точками " +
            "p1(" + p1.x + "," + p1.y + ")" + " и " +
            "p2(" + p2.x + "," + p2.y + ")" + " = " +
            Math.floor(p1.distance(p2)));

  }

}



