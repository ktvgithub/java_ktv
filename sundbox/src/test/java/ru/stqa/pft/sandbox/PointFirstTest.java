package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointFirstTest {
  @Test
  public void testFirstArea() {
    Point p1 = new Point(7.0, 25.0);
    Point p2 = new Point(15.0, 30.0);
    Assert.assertEquals(Math.floor(p1.distance(p2)), 9.0);
  }
}
