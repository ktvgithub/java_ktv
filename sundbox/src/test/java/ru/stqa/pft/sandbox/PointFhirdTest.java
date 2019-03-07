package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointFhirdTest {
  @Test
  public void testFirstArea() {
    Point p1 = new Point(64.0, 75.0);
    Point p2 = new Point(121.0, 95.0);
    Assert.assertEquals(Math.floor(p1.distance(p2)), 60.0);
  }
}
