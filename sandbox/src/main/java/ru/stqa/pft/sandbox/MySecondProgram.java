package ru.stqa.pft.sandbox;

public class MySecondProgram {

    public static void main(String[] args) {

               Point p1 = new Point(5,10);
               Point p2 = new Point(20, 20);

        //       System.out.println("Расстояние между точками p1 и p2 = " + distance(p1,p2));

        System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));

    }

    //  public static double distance(Point p1, Point p2) {

    // return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    // return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
    //   }
}
