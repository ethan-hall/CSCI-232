// Ethan Hall
// CSCI 232: Lab4
// 1 May 2015

import java.util.Collections;
import java.util.LinkedList;

class Lab4 {
    // Input points as array
    static double[][] input =
                   {{2.0, 7.0}, {4.0, 13.0},
                    {5.0, 8.0}, {10.0, 5.0},
                    {14.0, 9.0}, {15.0, 5.0},
                    {17.0, 7.0}, {19.0, 10.0},
                    {22.0, 7.0}, {25.0, 10.0},
                    {29.0, 14.0}, {30.0, 2.0}};

    // Main method
    public static void main(String[] args) {
        LinkedList points = new LinkedList<Point>();

        System.out.println("Input points:"); // print input points
        for (int i = 0; i < input.length; i++) {
            System.out.print("(" + input[i][0] + ", " + input[i][1] + ") ");
            points.add(new Point(input[i][0], input[i][1], i)); // save points as LinkedList of class Points
        }

        System.out.println("\n-------------------------"); // line spacing
        System.out.println("Brute force check:");
        Pair minPairBrute = bruteForceClosestPair(points);
        System.out.println("\t(" + minPairBrute.point1.x + ", " + minPairBrute.point1.y + ") " + "(" + minPairBrute.point2.x + ", " + minPairBrute.point2.y + ")");
        System.out.println("\td= " + minPairBrute.distance);

        System.out.println("-------------------------");
        System.out.println("Divide and conquer:");
        Pair minPair = closestPair(points);
        System.out.println("-------------------------");
        System.out.println("Final result:\tP1: (" + minPair.point1.x + "," + minPair.point1.y + ")" +
                "\tP2: (" + minPair.point2.x + "," + minPair.point2.y + ")\tDistance: " + minPair.distance);
    }

    //
    static Pair closestPair(LinkedList<Point> points) {
        System.out.println("Solving Problem: Point[" + points.getFirst().index + "]...[" + points.getLast().index + "]");

        if (points.size() < 4) { // if 3 or less points, use brute force method (will be faster)
            Pair temp = bruteForceClosestPair(points);
            System.out.println("\tFound result:\tP1:(" + temp.point1.x + "," + temp.point1.y + ")" +
                    "\tP2:(" + temp.point2.x + "," + temp.point2.y + ")\tDistance: " + temp.distance);
            return temp;
        }

        LinkedList<Point> pointsX = new LinkedList<>(points);
        LinkedList<Point> pointsY = new LinkedList<>(points);
        sortX(pointsX); // sort points by x coordinate
        sortY(pointsY); // sort points by y coordinate

        int m = (int) Math.ceil(pointsX.size() / 2);

        System.out.println("\tDividing at Point[" + m + "]"); // print progress

        LinkedList subset1 = new LinkedList<>(); // sublist of points 1
        for (int i = 0; i <= m; i++) { // split list of points at point m
            subset1.add(pointsX.get(i));
        }

        LinkedList subset2 = new LinkedList<>(); // sublist of points 2
        for (int i = m; i < pointsX.size(); i++) { // split list of points at point m
            subset2.add(pointsX.get(i));
        }

        Pair d1 = closestPair(subset1);
        Pair d2 = closestPair(subset2);

        System.out.println("Combining Problems:\tPoint[" + d1.point1.index + "]...Point[" + d1.point2.index +
                "] and Point[" + d2.point1.index + "]...Point[" + d2.point2.index + "]");

        Pair minPair = new Pair(); // will store current closest points
        if (d1.distance < d2.distance) { // d1 is smaller
            minPair = d1; // update minPair
            System.out.println("\tFound result:\tP1:(" + d1.point1.x + "," + d1.point1.y + ")" +
                    "\tP2:(" + d1.point2.x + "," + d1.point2.y + ")\tDistance: " + d1.distance);
        } else { // d2 is smaller
            minPair = d2; // update minPair
            System.out.println("\tFound result:\tP1:(" + d2.point1.x + "," + d2.point1.y + ")" +
                    "\tP2:(" + d2.point2.x + "," + d2.point2.y + ")\tDistance: " + d2.distance);
        }


        Pair d3 = closestPairXCut(pointsX.get(m), pointsY, minPair);

        if (d1.distance < d2.distance && d1.distance < d3.distance) // if d1 is min, set new minPair
            minPair = d1;
        else if (d2.distance < d1.distance && d2.distance < d3.distance) // if d2 is min, set new minPair
            minPair = d2;
        else if (d3.distance < d1.distance && d3.distance < d2.distance) // if d3 is min, set new minPair
            minPair = d3;

        return minPair; // return closest pair
    }

    static Pair closestPairXCut(Point m, LinkedList<Point> pointsY, Pair minPair) {
        LinkedList<Point> tempList = new LinkedList<>();
        for (int i = 0; i < pointsY.size(); i++) {
            Point temp = pointsY.get(i);
            if ((temp.x - m.x) < minPair.distance)
                tempList.add(temp);
        }

        Pair newPair = new Pair();

        for (int i = 0; i < tempList.size(); i++) { // compare current point to up to 6 other points
            Point temp = pointsY.get(i);
            if (temp.x < m.x) {
                if (i - 3 > -1) {
                    newPair.set(tempList.get(i - 3), tempList.get(i - 2));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
                if (i - 2 > -1) {
                    newPair.set(tempList.get(i - 2), tempList.get(i - 1));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
                if (i - 1 > -1) {
                    newPair.set(tempList.get(i - 1), tempList.get(i));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
                if (i + 1 < tempList.size()) {
                    newPair.set(tempList.get(i), tempList.get(i + 1));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
                if (i + 2 < tempList.size()) {
                    newPair.set(tempList.get(i + 1), tempList.get(i + 2));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
                if (i + 3 < tempList.size()) {
                    newPair.set(tempList.get(i + 2), tempList.get(i + 3));
                    if (newPair.distance < minPair.distance)
                        minPair.set(newPair.point1, newPair.point2);
                }
            }
        }
        if (newPair.distance < minPair.distance) // if newPair is closest pair
            return newPair; // return newPair
        else
            return minPair; // return minPair
    }

    // Brute force method to find closest pair
    static Pair bruteForceClosestPair(LinkedList<Point> points) {
        int numPoints = points.size();
        if (numPoints < 2) // Error catching: less than 2 points
            return null;
        Pair closestPair = new Pair(points.get(0), points.get(1));
        if (numPoints > 2) {
            for (int i = 0; i < numPoints - 1; i++) {
                Point point1 = points.get(i);
                for (int j = i + 1; j < numPoints; j++) {
                    Point point2 = points.get(j);
                    if (distance(point1, point2) < closestPair.distance) // if new distance is < old
                        closestPair.set(point1, point2); // update closestPair
                }
            }
        }
        return closestPair;
    }

    static public double distance(Point point1, Point point2) {
        double xdis = point2.x - point1.x; // distance between coordinates on x axis
        double ydis = point2.y - point1.y; // distance between coordinates on y axis
        return Math.hypot(xdis, ydis); // return hypotenuse (distance between points)
    }

    // Sort points by X
    static void sortX(LinkedList<Point> points) {
        Collections.sort(points, (point1, point2) -> {
                    if (point1.x < point2.x)
                        return -1;
                    if (point1.x > point2.x)
                        return 1;
                    return 0;
                }
        );
    }

    // Sort points by Y
    static void sortY(LinkedList<Point> points) {
        Collections.sort(points, (point1, point2) -> {
                    if (point1.y < point2.y)
                        return -1;
                    if (point1.y > point2.y)
                        return 1;
                    return 0;
                }
        );
    }

    // Nested Point class, holds x and y coordinates
    static class Point {
        private final double x; // x coordinate
        private final double y; // y coordinate
        private int index; // index to keept track of pair across lists

        public Point(double x, double y, int index) { // constructor
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    // Nested Pair class, holds two Point objects and a distance
    static class Pair {
        private Point point1;
        private Point point2;
        private double distance; // distance between point1 and point2

        public Pair(Point point1, Point point2) {
            this.point1 = point1;
            this.point2 = point2;
            distance = distance(point1, point2); // calculate distance
        }

        public Pair() {
        } // empty constructor

        public void set(Point point1, Point point2) { // set Pair values
            this.point1 = point1;
            this.point2 = point2;
            distance = distance(point1, point2); // calculate distance
        }
    }
}
