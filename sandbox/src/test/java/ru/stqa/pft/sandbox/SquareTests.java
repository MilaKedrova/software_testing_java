package ru.stqa.pft.sandbox;

//import org.junit.Assert;
import org.testng.Assert;
import org.junit.Test;


public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(50);
        Assert.assertEquals(s.area(), 25.0);

    }
}