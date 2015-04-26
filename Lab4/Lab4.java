// Ethan Hall
// CSCI 232: Lab4
// 30 April 2015

import java.util.Arrays;

public class Lab4 {
    
    // Sorts points by x coordinates
    public double[][] sortArrayX(double points[][]) {
        Arrays.sort(points, (a, b) -> Double.compare(a[0], b[0]));
        return points;
    }

    // Sorts points by y coordinates
    public double[][] sortArrayY(double points[][]) {
        Arrays.sort(points, (a, b) -> Double.compare(a[1], b[1]));
        return points;
    }

    // Returns a copy of an array
    public double[][] copyArray(double points[][]) {
        double[][] temp = new double[points.length][points[0].length];
        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points[i].length; j++)
                temp[i][j] = points[i][j];
        return temp;
    }

    // Prints an array of points to console
    public void printArray(double points[][]) {
        for (int i = 0; i < points.length; i++) {
            if (i > 0) // prevent extra line space in beginning of print
                System.out.println();
            for (int j = 0; j < points[i].length; j++) {
                if (points[i][j] > 9) // format row spacing for readability (range 0-100)
                    System.out.print("   " + points[i][j]);
                else
                    System.out.print("   " + points[i][j] + " ");
            }
        }
        System.out.println();
    }
    
}
