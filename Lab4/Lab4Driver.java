// Ethan Hall
// CSCI 232: Lab4
// 30 April 2015

public class Lab4Driver {

    // Toggles extra print functions for debug
    static boolean DEBUG = true;

    // 2D input array of (x,y) points
    static final double[][] input = {
            {5.0, 8.0}, {10.0, 5.0},
            {14.0, 9.0}, {15.0, 5.0},
            {17.0, 7.0}, {19.0, 10.0},
            {22.0, 7.0}, {25.0, 10.0},
            {29.0, 14.0}, {30.0, 2.0},
            {2.0, 7.0}, {4.0, 13.0}}; // From Lab4 handout for debug

    public static void main(String[] args) {
        Lab4 instance = new Lab4(); // instance of Lab4

        double[][] pointsX = instance.copyArray(input); // X sorted array from input points
        double[][] pointsY = instance.copyArray(input); // Y sorted array from input points

        if (DEBUG) { // to check array sorting
            System.out.println("-----Unsorted Points:-----");
            instance.printArray(input);
        }

        pointsX = instance.sortArrayX(pointsX); // sort points by x coordinate
        pointsY = instance.sortArrayY(pointsY); // sort points by y coordinate

        if (DEBUG) { // to check array sorting
            System.out.println("-----X Sorted Points:-----");
            instance.printArray(pointsX);
            System.out.println("-----Y Sorted Points:-----");
            instance.printArray(pointsY);
        }
    }
}
