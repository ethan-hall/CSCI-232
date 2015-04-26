// Ethan Hall
// CSCI 232: Lab4
// 30 April 2015

import java.util.Arrays;

public class Lab4 {
    
    public double[][] bruteForceClosestPair(double[][] points) {
        double minDistance = Double.MAX_VALUE; // current minimum distance
        int[] minPoints = new int[2]; // indexes (rows) of current min points

        if (points.length < 1) // error catching: only one point in array
            return new double[][] {{Double.MAX_VALUE, Double.MAX_VALUE}, {Double.MAX_VALUE, Double.MAX_VALUE}};

        minDistance = distance(points[0][0], points[0][0], points[1][0], points[1][1]); // initial minDistance
        minPoints[0] = 0; // initial minPoints
        minPoints[1] = 1; // initial minPoints

        // brute force
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double tempD = distance(points[i][0], points[i][1] , points[j][0], points[j][1]);
                if (minDistance > tempD) { // compare new min distance
                    minDistance = tempD;
                    minPoints[0] = i;
                    minPoints[1] = j;
                }
            }
        }
        // return array of the 2 closest points
        return new double[][] { {points[minPoints[0]][0], points[minPoints[0]][1]} , {points[minPoints[1]][0], points[minPoints[1]][1]} };
    }

    // Computes the distance between two points
    public double distance(double x1, double y1, double x2, double y2) {
        double d = Math.sqrt((Math.pow((y2 - y1), 2)) + (Math.pow((x2 - x1), 2)));
        return d;
    }
    
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
