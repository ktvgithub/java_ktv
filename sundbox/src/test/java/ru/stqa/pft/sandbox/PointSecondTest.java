package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointSecondTest {
  @Test
  public void testFirstArea() {
    Point p1 = new Point(12.0, 3.0);
    Point p2 = new Point(21.0, 28.0);
    Assert.assertEquals(Math.floor(p1.distance(p2)), 26.0);
  }
}
