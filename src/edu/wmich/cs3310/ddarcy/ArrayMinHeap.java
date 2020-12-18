package edu.wmich.cs3310.ddarcy;

/**
 * CLASS DESCRIPTION:
 * This class is a array based min heap.  It uses the attributes below to create a binary search tree with many different methods which include
 * insert, the constructor, search, get methods, set methods, etc.
 * @author Daniel Darcy
 */

public class ArrayMinHeap {

	//Declare variables
	private MyNode[] Heap;
	private int size;
	private int maxsize;
	private long averageTime;
	private long totalTime;
	int foundCounter;
	private static final int FRONT = 1;

	/**
	 * Declares minHeap with maxSize.
	 * @param maxsize
	 */
	public ArrayMinHeap(int maxsize){
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new MyNode[this.maxsize + 1];
		//Heap[0] = null;
	}
	
	/**
	 * Constructor
	 */
	public ArrayMinHeap() {
		size = 0;
		averageTime = 0;
		totalTime = 0;
		foundCounter = 0;
	}

	/**
	 * Returns parent index.
	 * @param pos
	 * @return parent
	 */
	private int parent(int pos) {
		return pos / 2;
	}

	/**
	 * Returns leftChild index.
	 * @param pos
	 * @return index
	 */
	private int leftChild(int pos) {
		return (2 * pos);
	}

	/**
	 * Returns right child index.
	 * @param pos
	 * @return index
	 */
	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	/**
	 * Returns true if isleaf, false if not.
	 * @param pos
	 * @return isLeaf
	 */
	private boolean isLeaf(int pos) {
		if (pos >=  (size / 2)  &&  pos <= size) { 
			return true;
		}
		return false;
	}

	/**
	 * Swap the two different indexes.
	 * @param fpos
	 * @param spos
	 */
	private void swap(int fpos, int spos) {
		MyNode tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	/**
	 * Maintains the correct structure.
	 * @param pos
	 */
	public void minHeapify(int pos) {
		if (!isLeaf(pos)){ 
			if ( (Heap[pos].getStoredData()).compareTo(Heap[leftChild(pos)].getStoredData()) > 0  ||
					(Heap[pos].getStoredData()).compareTo(Heap[rightChild(pos)].getStoredData()) > 0) {
				if ((Heap[rightChild(pos)].getStoredData()).compareTo(Heap[leftChild(pos)].getStoredData()) < 0){
					swap(pos, leftChild(pos));
					minHeapify(leftChild(pos));
				}
				else {
					swap(pos, rightChild(pos));
					minHeapify(rightChild(pos));
				}
			}
		}
	}

	/**
	 * Inserts node with String attribute.
	 * @param element
	 */
	public void insert(String element) {
		MyNode newNode = new MyNode(element);
		Heap[++size] = newNode;
		int current = size;

		try {
			if(size > 2) {
				while ((Heap[parent(current)].getStoredData()).compareTo(Heap[current].getStoredData()) > 0) {
					swap(current,parent(current));
					current = parent(current);
				}
			}
		}catch(Exception e) {
			//System.out.println("Error: " + e); 		//TESTTT
		}
	}

	/**
	 * Prints out nodes.
	 */
	public void print() {
		for (int i = 1; i <= size / 2; i++ ) {
			try {
				System.out.print(" PARENT : " + Heap[i].getStoredData() + " LEFT CHILD : " + Heap[2*i].getStoredData() 
						+ " RIGHT CHILD :" + Heap[2 * i  + 1].getStoredData());
				System.out.println();
			}catch(Exception e) {
				//TEST
			}
		} 
	}

	/**
	 * Prints out in pre-order traversal.
	 */
	public void preorderPrint() {
		System.out.println("\nPREORDER TRAVERSAL:");
		for(int i = 1; i <= size; i++) {
			System.out.print(Heap[i].getStoredData() + ", ");
			if(i % 12 == 0) {
				System.out.println(" ");
			}
		}
	}

	/**
	 * Minimum heapify.
	 */
	public void minHeap() {
		for (int pos = (size / 2); pos >= 1 ; pos--) {
			minHeapify(pos);
		}
	}

	/**
	 * Removes top node.
	 * @return poppedNode;
	 */
	public MyNode remove() {
		MyNode popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--]; 
		minHeapify(FRONT);
		return popped;
	}

	/**
	 * Returns minimum data.
	 * @return minData
	 */
	public String getMinData() {
		String storedData;
		storedData = Heap[1].getStoredData();
		return storedData;
	}

	/**
	 * Returns maximum data.
	 * @param index
	 * @return maxData
	 */
	public String getMaxData(int index) {
		String storedData;
		storedData = Heap[index].getStoredData();
		return storedData;
	}

	/**
	 * Searches for specified String.
	 * @param findMe
	 * @return index
	 */
	public int search(String findMe) {
		int turn = -1;
		int depth = -1;
		long startTime = 0;
		long endTime = 0;
		long searchTime = 0;

		startTime = System.nanoTime();
		for(int i = 1; i < size; i++) {
			if(Heap[i].getStoredData().compareTo(findMe) == 0) {
				endTime = System.nanoTime();
				searchTime = endTime - startTime;
				totalTime = totalTime + searchTime;
				foundCounter++;
				System.out.println(findMe + " was found!");
				System.out.println("Array index is " + i);
				if(i == 1) {
					System.out.println("Tree depth is " + 0);
					depth = 0;
				}
				else if(i <= 3) {
					System.out.println("Tree depth is " + 1);
					depth = 1;
				}
				else if(i <= 7 ) {
					System.out.println("Tree depth is " + 2);
					depth = 2;
				}
				else if(i <= 15) {
					System.out.println("Tree depth is " + 3);
					depth = 3;
				}
				else if(i <= 31) {
					System.out.println("Tree depth is " + 4);
					depth = 4;
				}
				else if(i <= 63) {
					System.out.println("Tree depth is " + 5);
					depth = 5;
				}
				else if(i <= 127) {
					System.out.println("Tree depth is " + 6);
					depth = 6;
				}
				else if(i <= 255) {
					System.out.println("Tree depth is " + 7);
					depth = 7;
				}
				else {
					depth = ((i/8)+2);
					System.out.println("Tree depth is " + depth);
				}
				turn = i;
				if(isLeaf(i) == true) {
					System.out.println("This node is a leaf");
				}
				else if(isLeaf(i) == false) {
					System.out.println("This node is not a leaf");
				}
				System.out.println("Size of subtree is " + (getSize()/((depth*2)+1)));		//NEEDS WORK
				System.out.println("Search time is " + searchTime + " nanoseconds\n");
				break;
			}
			else {
				//System.out.println("Never Found");
				turn = -1;
			}
		}
		return turn;
	}

	/**
	 * Returns size of data structure.
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Prints out message if found.
	 * @param findMe
	 */
	public void searchh(String findMe) {
		for(int i = 1; i < size; i++) {
			if(Heap[i].getStoredData().compareTo(findMe) == 0) {
				System.out.print("\nItems found! \n");
			}
		}
	}

	/**
	 * Returns average search time.
	 * @return averageSearchTime
	 */
	public long getAverageTime() {
		averageTime = (long) totalTime/foundCounter;
		return averageTime;
	}
}
