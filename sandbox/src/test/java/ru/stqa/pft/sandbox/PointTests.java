package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test

    public void testDifferenceX(){

        Point p1 = new Point(5,10);
        Point p2 = new Point(20, 20);

        Assert.assertEquals(p2.x-p1.x, 15.0);
    }


    @Test

    public void testPowX(){

        Point p1 = new Point(5,10);
        Point p2 = new Point(20, 20);

        Assert.assertEquals(Math.pow((p2.x - p1.x), 2), 225.0);
    }

    @Test
    public void testDistance(){

        Point p1 = new Point(5,10);
        Point p2 = new Point(20, 20);

        Assert.assertEquals(p1.distance(p2), 18.027756377319946);
    }
}
