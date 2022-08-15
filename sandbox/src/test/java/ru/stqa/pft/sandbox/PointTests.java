package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.junit.Test;

public class PointTests {

    @Test
    public void testArea() {
        Point P = new Point(1, 1, 5, 5);
        Assert.assertEquals(P.distance(), 5.656854249492381);

    }
}

