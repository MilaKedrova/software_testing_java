package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.junit.Test;

public class PointTests {

    @Test
    public void testArea() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(10, 10);
        Assert.assertEquals(p1.distance(p2), 12.727922061357855);

    }
}

