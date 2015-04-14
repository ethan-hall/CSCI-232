// Ethan Hall
// 7 April 2015
// CSCI 232: Lab 3

import java.io.IOException;

public class Lab3 {
    private static int[][] graph; // declare adjacency matrix as 2d array

    public static void main(String args[]) {
        graph = new int[5][5]; // initialize adjacency matrix
        // initial values of edges
        graph[0][1] = 50;
        graph[0][3] = 80;
        graph[1][2] = 60;
        graph[1][3] = 90;
        graph[2][4] = 40;
        graph[3][2] = 20;
        graph[3][4] = 70;
        graph[4][1] = 50;

        System.out.println("Adjacency Matrix:");
        printMatrix(graph); // print original matrix

        floydsAlg(graph); // preform Floyd's Algorithm
    }

    // Executes Floyd's Algorithm on a given graph (as 5x5 2d array)
    static private void floydsAlg(int[][] graph) {
        for (int i = 0; i < 5; i++) {     // iterate through the adjacency matrix
            for (int j = 0; j < 5; j++) {
                if (graph[i][j] != 0) {
                    for (int a = 0; a < 5; a++) { // iterate through column of interest
                        if (graph[a][i] != 0) {
                            if (graph[a][j] == 0 || graph[a][j] > (graph[i][j]+graph[a][i])) { // if new path is shorter

                                graph[a][j] = graph[i][j] + graph[a][i]; // update path

                                System.out.println("Press Enter to continue:"); // wait for user
                                try {
                                    System.in.read();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("All-pairs shortest paths are:");
                                printMatrix(graph); // print matrix
                            }
                        }
                    }
                }

            }
        }
    }

    // Prints the adjacency matrix from a given 5x5 2d array
    static private void printMatrix(int[][] graph) {
        System.out.println("    A   B   C   D   E");
        System.out.println("  ====================");
        for (int i = 0; i < 5; i++) {
            System.out.print((char) (65 + i) + " "); // prints row label (A,B,C...)
            for (int j = 0; j < 5; j++) {
                System.out.print(" ");
                if (graph[i][j] == 0) {
                    System.out.print("---");
                } else {
                    System.out.format("%3s", graph[i][j]);
                }
            }
            System.out.println();
        }
    }
}
