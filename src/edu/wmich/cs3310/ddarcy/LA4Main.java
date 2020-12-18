package edu.wmich.cs3310.ddarcy;

/**
 * Daniel Darcy
 * CS 4430
 * Assignment 4
 * Gupta TH 1:00
 */

/**
 * PROGRAM DESCRIPTION:
 * This program starts by asking the user to input the files to fill the first and second array.  Once the user inputs the file names separated by a space the
 * program puts the data into 3 different data structures.  Once that is complete the program prints out specific data for each of the three different data types.
 * Once that is all done the program generates a random number n and picks that many strings from the first array.  It then puts them in the previously mentioned 3
 * data strucutures.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LA4Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		int firstCounter = 0;		//Counter for length of firstArray
		int secondCounter = 0;		//Counter for length of secondArray
		int thirdCounter = 0;
		int fourthCounter = 0;
		String[] firstArray = new String[50000];		//Array for firstFile
		String[] secondArray = new String[50000];	//Array for secondFile
		String files;		//Both file names in one string
		
		//PARTA
		Scanner userInput = new Scanner(System.in);		//Scanner used for user input
		System.out.println("Enter first the input file name followed by the output file name separated by a space on the line below: ");
		files = userInput.nextLine();		//User Inputs file names
		
		String[] fileSplitted = files.split("\\s+");		//Split user inputted line
		String firstFile = fileSplitted[0];		//Store firstFileName
		String secondFile = fileSplitted[1];		//Store secondFileName
		
		File readMeFirst = new File(firstFile);
		Scanner inputFileOne = new Scanner(readMeFirst);		//Instantiate Scanner for first file
		File readMeSecond = new File(secondFile);
		Scanner inputFileTwo = new Scanner(readMeSecond);		//Instantiate Scanner for second file
		ArrayMinHeap firstHeap = new ArrayMinHeap(10000);		//heap for first array
		long totalTime = 0;
		long averageTime = 0;
		
		while(inputFileOne.hasNextLine()) {		//While there is a next line in the file
			firstArray[firstCounter] = inputFileOne.nextLine();		//Assign array item from file	
			long timeElapsed = 0;
			long startTime = System.nanoTime();
			firstHeap.insert(firstArray[firstCounter]);
			long endTime = System.nanoTime();
			timeElapsed = endTime - startTime;
			totalTime = totalTime + timeElapsed;	
			firstHeap.minHeapify(firstCounter);					//MAYBE NOT
			firstCounter++;		//Increment counter
		}
		averageTime = (long) totalTime/firstCounter;			//Calculate averageTime
		System.out.println("\nAverage insert time:   " + averageTime + " nanoseconds");
		
		String max = firstHeap.getMaxData(firstCounter);
		String min = firstHeap.getMinData();
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		
		long minstartTime = System.nanoTime();		//Print out time for minSearch
		//find min
		firstHeap.searchh(min);
		long minendTime = System.nanoTime();
		long minSearchTime = minendTime - minstartTime;
		System.out.println("Search for min took " + minSearchTime + " nanoseconds.");
		
		long maxstartTime = System.nanoTime();		//Print out time for maxSearch
		//find max
		firstHeap.searchh(max);
		long maxendTime = System.nanoTime();
		long maxSearchTime = maxendTime - maxstartTime;
		System.out.println("Search for max took " + maxSearchTime + " nanoseconds.");
		
		while(inputFileTwo.hasNextLine()) {		//While there is a next line in the file
			secondArray[secondCounter] = inputFileTwo.nextLine();		//Assign array item from file			
			secondCounter++;		//Increment counter
		}
		firstHeap.preorderPrint();
		System.out.println("\n");

		//Search for each value in 2nd array
		for(int i = 0; i < secondCounter; i++) {
			firstHeap.search(secondArray[i]);
		}

		System.out.println("Average search took " + firstHeap.getAverageTime() + " nanoseconds");
		System.out.println("-------------------------------------------------------------------------------------");
		//END OF PARTA/B   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//PARTC		
		NodeMaxHeap secondHeap = new NodeMaxHeap(10000);		//heap for first array
		long totalTime2 = 0;
		long averageTime2 = 0;
		
		for(int j = 0; j < firstCounter; j++) {		//While there is a next line in the file	
			long timeElapsed2 = 0;
			long startTime2 = System.nanoTime();
			secondHeap.insert(firstArray[thirdCounter]);
			long endTime2 = System.nanoTime();
			timeElapsed2 = endTime2 - startTime2;
			totalTime2 = totalTime2 + timeElapsed2;
			secondHeap.maxHeapify(thirdCounter);					//MAYBE NOT
			thirdCounter++;		//Increment counter
		}
		averageTime2 = (long) totalTime2/thirdCounter;			//Calculate averageTime
		System.out.println("Average insert time:   " + averageTime2 + " nanoseconds");
		
		String max2 = secondHeap.getMaxData(thirdCounter);
		String min2 = secondHeap.getMinData();
		System.out.println("Min: " + min2);
		System.out.println("Max: " + max2);
		
		long minstartTime2 = System.nanoTime();		//Print out time for minSearch
		//find min
		secondHeap.searchh(min2);
		long minendTime2 = System.nanoTime();
		long minSearchTime2 = minendTime2 - minstartTime2;
		System.out.println("Search for min took " + minSearchTime2 + " nanoseconds.");
		
		long maxstartTime2 = System.nanoTime();		//Print out time for maxSearch
		//find max
		secondHeap.searchh(max2);
		long maxendTime2 = System.nanoTime();
		long maxSearchTime2 = maxendTime2 - maxstartTime2;
		System.out.println("Search for max took " + maxSearchTime2 + " nanoseconds.");
		
		secondHeap.postorderPrint();
		System.out.println("\n");

		//Search for each value in 2nd array
		for(int i = 0; i < secondCounter; i++) {
			secondHeap.search(secondArray[i]);
		}
		System.out.println("Average search took " + secondHeap.getAverageTime() + " nanoseconds");
		System.out.println("-------------------------------------------------------------------------------------");
		
		//PARTD		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		BinarySearchTree searchTree = new BinarySearchTree();		//heap for first array
		long totalTime3 = 0;
		long averageTime3 = 0;
		
		for(int j = 0; j < firstCounter; j++) {		//While there is a next line in the file
			long timeElapsed3 = 0;
			long startTime3 = System.nanoTime();
			searchTree.insert(firstArray[fourthCounter]);
			long endTime3 = System.nanoTime();
			timeElapsed3 = endTime3 - startTime3;
			totalTime3 = totalTime3 + timeElapsed3;
			fourthCounter++;		//Increment counter
		}
		averageTime3 = (long) totalTime3/fourthCounter;			//Calculate averageTime
		System.out.println("Average insert time:   " + averageTime3 + " nanoseconds");
		
		String max3 = searchTree.getMaxData();
		String min3 = searchTree.getMinData();
		System.out.println("Min: " + min3);
		System.out.println("Max: " + max3 + "\n");
		
		long minstartTime3 = System.nanoTime();		//Print out time for minSearch
		//find min
		searchTree.search(searchTree.getRoot(), min3);
		long minendTime3 = System.nanoTime();
		long minSearchTime3 = minendTime3 - minstartTime3;
		System.out.println("Items found!");
		System.out.println("Search for min took " + minSearchTime3 + " nanoseconds.");
		
		long maxstartTime3 = System.nanoTime();		//Print out time for maxSearch
		//find max
		searchTree.search(searchTree.getRoot(), max3);
		long maxendTime3 = System.nanoTime();
		long maxSearchTime3 = maxendTime3 - maxstartTime3;
		System.out.println("Search for max took " + maxSearchTime3 + " nanoseconds.\n");
		
		searchTree.inorder();
		System.out.println("\n");
		MyNode foundNode = new MyNode();
		//Search for each value in 2nd array
		for(int i = 0; i < fourthCounter; i++) {
			long tempStartTime = System.nanoTime();
			foundNode = searchTree.search(searchTree.getRoot(), secondArray[i]);
			long tempEndTime = System.nanoTime();
			if(foundNode != null) {
				System.out.println("\n" + foundNode.getStoredData() + " was found!");
				System.out.println("Tree depth is " + searchTree.findHeight(foundNode));
				searchTree.resetTempHeight();
				if(searchTree.isLeaf(foundNode) == true) {
					System.out.println("This node is a leaf");
				}
				else {
					System.out.println("This node is not a leaf");
				}
				System.out.println("Size of subtree is " + searchTree.subTreeSize(foundNode));
				System.out.println("Search time is " + (tempEndTime - tempStartTime) + " nanoseconds");
			}
		}
		System.out.println("\nAverage search took " + searchTree.getAverageTime() + " nanoseconds");
		System.out.println("-------------------------------------------------------------------------------------");
			
		//PARTE		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Random rand = new Random();
		int  n = 500;//rand.nextInt(firstCounter) + 1;
		String[] randomArray = new String[n];
		System.out.println("n = " + n);
		for(int i = 0; i < n; i++) {
			int k = rand.nextInt(firstCounter) + 1;
			randomArray[i] = firstArray[k];
		}	
		ArrayMinHeap arrayMinHeap = new ArrayMinHeap(10000);
		NodeMaxHeap nodeMaxHeap = new NodeMaxHeap(10000);
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		
		for(int j = 0; j < n; j++) {
			//System.out.println("ADDED");
			//System.out.println(randomArray[j]);
			arrayMinHeap.insert(randomArray[j]);
			nodeMaxHeap.insert(randomArray[j]);
			binarySearchTree.insert(randomArray[j]);
		}
		
		System.out.println("\nArrayMinHeap created...");
		System.out.println("NodeMaxHeap created...");
		System.out.println("BinarySearchTree created...");
		
	}
}
