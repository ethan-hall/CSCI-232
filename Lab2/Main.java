package lab2;

// Ethan Hall
// CSCI 232, Lab2
// 4 March 2015
// ethanhall7@gmail.com

import java.util.Random;
import java.util.Scanner;

// Driver for HashTable.java
public class Main {

    public static void main(String[] args) { // main execution
        // title screen
        System.out.println("--------------------Ethan Hall--------------------");
        System.out.println("------------------CSCI 232, Lab3------------------");

        // get hash table size
        System.out.println("Enter size of hash table: ");
        Scanner io = new Scanner(System.in);
        int tableLength = io.nextInt();
	    HashTable hashT = new HashTable(tableLength); // create HashTable of size

        // populate with random values (0-99)
        System.out.println("Enter initial number of items: ");
        int numItems = io.nextInt();
        Random rand = new Random();
        for (int i = 0; i < numItems; i++) {
            hashT.insert(rand.nextInt(100));
        }
        hashT.display(); // print populated hash table

        // menu loop
        boolean menu = true;
        do {
            System.out.println("Enter first letter of show, insert, find, or quit: ");
            char choice = io.next().charAt(0);
            switch (choice){
                case 's':
                    hashT.display();
                    break;
                case 'i':
                    System.out.println("Enter value to insert: ");
                    int insertValue = io.nextInt(); // store user input
                    hashT.insert(insertValue);
                    break;
                case 'f':
                    System.out.print("Enter value to search for: ");
                    int searchValue = io.nextInt(); // store user input
                    int searchResult = hashT.search(searchValue);
                    if (searchResult != -1) {
                        System.out.println("'" + searchValue + "'" + " found at index " + searchResult + ".");
                        break;
                    }
                    else {
                        System.out.println("'" + searchValue + "'" + " not found.");
                        break;
                    }
                case 'q':
                    System.out.println("Goodbye.");
                    menu = false; // break out of while loop
                    break;
                default:
                    System.out.println("Invalid entry. Please try again.");
                    break;
            }
        } while (menu);

    }
}
