package edu.wmich.cs3310.ddarcy;

/**
 * CLASS DESCRIPTION:
 * This class is a node based max-heap.  It uses the attributes below to create a binary search tree with many different methods which include
 * insert, the constructor, search, get methods, set methods, etc.
 * @author Daniel Darcy
 */

public class NodeMaxHeap {

	//Declare Variables
	private MyNode[] Heap;
	private int size;
	private int maxsize;
	private long averageTime;
	private long totalTime;
	private int foundCounter;
	private static final int FRONT = 1;

	/**
	 * Declares a max heap with maxsize.
	 * @param maxsize
	 */
	public NodeMaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new MyNode[this.maxsize + 1];
	}

	/**
	 * Returns the parent node.
	 * @param pos
	 * @return parentPosition
	 */
	private int parent(int pos) {
		return pos / 2;
	}

	/**
	 * Returns left child.
	 * @param pos
	 * @return leftChild
	 */
	private int leftChild(int pos) {
		return (2 * pos);
	}

	/**
	 * Returns right child.
	 * @param pos
	 * @return rightChild
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
	 * Swap two different spots in the heap.
	 * @param fpos
	 * @param spos
	 */
	private void swap(int fpos,int spos) {
		MyNode tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	/**
	 * Swaps if needed to maintain maxHeap.
	 * @param pos
	 */
	public void maxHeapify(int pos) {
		if (!isLeaf(pos)) { 
			if ( (Heap[pos].getStoredData()).compareTo(Heap[leftChild(pos)].getStoredData()) < 0  
					|| (Heap[pos].getStoredData()).compareTo(Heap[rightChild(pos)].getStoredData()) < 0) {
				if (Heap[leftChild(pos)].getStoredData().compareTo(Heap[rightChild(pos)].getStoredData()) > 0) {
					swap(pos, leftChild(pos));
					maxHeapify(leftChild(pos));
				}else {
					swap(pos, rightChild(pos));
					maxHeapify(rightChild(pos));
				}
			}
		}
	}

	/**
	 * Inserts a new node with the String element.
	 * @param element
	 */
	public void insert(String element) {
		MyNode newNode = new MyNode(element);
		Heap[++size] = newNode;
		int current = size;

		try {
			if(size > 2) {
				while(Heap[current].getStoredData().compareTo(Heap[parent(current)].getStoredData()) > 0) {
					swap(current,parent(current));
					current = parent(current);
				}	
			}
		}catch(Exception e) {}
	}

	/**
	 * Prints the nodes.
	 */
	public void print() {
		for (int i = 1; i <= size / 2; i++ ) {
			System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i]
					+ " RIGHT CHILD :" + Heap[2 * i  + 1]);
			System.out.println();
		}
	}
	
	/**
	 * Post-order prints the nodes.
	 */
	public void postorderPrint() {
		System.out.println("\nPOSTORDER TRAVERSAL:");
		int temp = size;
		for(int i = temp; 1 <= i; i--) {
			System.out.print(Heap[i].getStoredData() + ", ");
			if(i % 12 == 0) {
				System.out.println(" ");
			}
		}
	}

	/**
	 * Max heapifys.
	 */
	public void maxHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			maxHeapify(pos);
		}
	}

	/**
	 * Removes top node.
	 * @return poppedNode
	 */
	public MyNode remove() {
		MyNode popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--]; 
		maxHeapify(FRONT);
		return popped;
	}

	/**
	 * Returns minimum value.
	 * @return minValue
	 */
	public String getMinData() {
		String storedData;
		storedData = Heap[1].getStoredData();
		return storedData;
	}

	/**
	 * Returns maximum value.
	 * @param index
	 * @return maxValue
	 */
	public String getMaxData(int index) {
		String storedData;
		storedData = Heap[index].getStoredData();
		return storedData;
	}

	/**
	 * Searches for specific string.
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
	 * Returns size of heap.
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Prints out message if item is found.
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


