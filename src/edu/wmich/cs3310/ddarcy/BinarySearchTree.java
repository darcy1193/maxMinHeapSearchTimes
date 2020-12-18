package edu.wmich.cs3310.ddarcy;

/**
 * CLASS DESCRIPTION:
 * This class is a BInary Search Tree.  It uses the attributes below to create a binary search tree with many different methods which include
 * insert, the constructor, search, get methods, set methods, etc.
 * @author Daniel Darcy
 */

public class BinarySearchTree {

	//Declare Variables
	MyNode root;
	MyNode[] Heap = new MyNode[10000];
	private int size;
	private long averageTime;
	private long totalTime;
	private int foundCounter;
	private int counter1;
	private int tempHeight;

	/**
	 * Constructor for declaration.
	 */
	BinarySearchTree() { 
		root = null; 
		size = 0;
		averageTime = 0;
		totalTime = 0;
		foundCounter = 0;
		counter1 = 0;
		tempHeight = 0;

	}

	/**
	 * Inserts node with specific record.
	 * @param record
	 */
	void insert(String record) {
		root = insertRec(root, record);
	}

	/**
	 * Recursive function to insert and maintain binary search tree.
	 * @param root
	 * @param record
	 * @return insertedNode
	 */
	MyNode insertRec(MyNode root, String record) {

		/* If the tree is empty, return a new node */
		if (root == null) {
			root = new MyNode(record);
			Heap[0] = root;
			return root;
		}

		/* Otherwise, recur down the tree */
		if(root.getStoredData() == null || record == null) {
			
		}
		else if (record.compareTo(root.getStoredData()) < 0) {
			root.setLeft(insertRec(root.getLeft(), record));
		}
		else if (record.compareTo(root.getStoredData()) > 0) {
			root.setRight(insertRec(root.getRight(), record));
		}
		/* return the (unchanged) node pointer */
		size++;
		return root;
	}

	/**
	 * Returns root.
	 * @return root
	 */
	public MyNode getRoot() {
		return root;
	}

	/**
	 * Sets root.
	 * @param root
	 */
	public void setRoot(MyNode root) {
		this.root = root;
	}

	/**
	 * Prints in-order traversal.
	 */
	public void inorder()  {
		System.out.println("IN-ORDER TRAVERSAL:");
		inorderRec(root);
	}

	/**
	 * Prints better in-order traversal.
	 * @param root
	 */
	public void inorderRec(MyNode root) {
		if (root != null) {
			inorderRec(root.getLeft());
			System.out.print(root.getStoredData() + ", ");
			inorderRec(root.getRight());
			counter1++;
			if(counter1 % 12 == 0) {
				System.out.println("");
			}
		}
	}

	/**
	 * Returns true if leaf, false if not.
	 * @param node
	 * @return isLeaf
	 */
	public boolean isLeaf(MyNode node) {
		boolean leaf;
		if(node.getLeft() == null && node.getRight() == null) {
			leaf = true;
		}
		else {
			leaf = false;
		}
		return leaf;
	}

	/**
	 * Returns size.
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Searches for node with specified String and returns that node.
	 * @param root
	 * @param storedData
	 * @return foundNode
	 */
	public MyNode search(MyNode root, String storedData){
		long startTime = System.nanoTime();
		// Base Cases: root is null or key is present at root
		if (root==null || root.getStoredData().compareTo(storedData) == 0) {
			if(root.getStoredData().compareTo(storedData) == 0) {
				foundCounter++;
				long endTime = System.nanoTime();
				totalTime = totalTime + (endTime - startTime);
			}

			return root;
		}


		// val is greater than root's key
		if (root.getStoredData().compareTo(storedData) > 0)
			return search(root.getLeft(), storedData);

		// val is less than root's key
		return search(root.getRight(), storedData);
	}

	/**
	 * Returns minimum value.
	 * @return minValue
	 */
	public String getMinData() {
		String minData = null;
		MyNode tempNode = new MyNode();
		tempNode = root;

		if(size == 0) {
			minData = null;
		}
		else if(size == 1) {
			minData = root.getStoredData();
		}
		else if(size == 2) {
			if(root.getLeft() == null) {
				minData = root.getStoredData();
			}
			else {
				minData = root.getLeft().getStoredData();
			}
		}
		else {
			while(tempNode.getLeft() != null) {
				tempNode = tempNode.getLeft();
				minData = tempNode.getStoredData();
			}
		}
		return minData;
	}

	/**
	 * Returns maximum value.
	 * @return maxValue
	 */
	public String getMaxData() {
		String maxData = null;
		MyNode tempNode = new MyNode();
		tempNode = root;

		if(size == 0) {
			maxData = null;
		}
		else if(size == 1) {
			maxData = root.getStoredData();
		}
		else if(size == 2) {
			if(root.getRight() == null) {
				maxData = root.getStoredData();
			}
			else {
				maxData = root.getRight().getStoredData();
			}
		}
		else {
			while(tempNode.getRight() != null) {
				tempNode = tempNode.getRight();
				maxData = tempNode.getStoredData();
			}
		}
		return maxData;
	}

	/**
	 * Returns average search time.
	 * @return averageSearchTime
	 */
	public long getAverageTime() {
		long averageTime = (long) totalTime/foundCounter;
		return averageTime;
	}
	
	/**
	 * Returns size of subtree.
	 * @param root
	 * @return numberOfNodes
	 */
	public int numberOfNodes(MyNode root){
	   // This node.
	   int result = 1;

	   // Plus all the nodes from the left node.
	   if (root.getLeft() != null)
	       result += numberOfNodes(root.getLeft());

	   // Plus all the nodes from the right node.
	   if (root.getRight() != null)
	       result += numberOfNodes(root.getRight());

	   return result;
	}
	
	/**
	 * Returns height of searchedNode.
	 * @param node
	 * @return height
	 */
	public int findHeight(MyNode node) {
		tempHeight++;
		
		  if (node == null) 
			  return 0;
		  int tempMax = (1 + Math.max(findHeight(node.getLeft()), findHeight(node.getRight()))); 
		  		  
		  return tempHeight;
	}
	
	/**
	 * Sets height attribute to 0.
	 */
	public void resetTempHeight() {
		tempHeight = 0;
	}
	
	/**
	 * Returns sub-tree size.
	 * @param root
	 * @return size
	 */
	public int subTreeSize(MyNode root) {
		MyNode tempNode = new MyNode();
		tempNode = root;
		int leftCounter = 0;
		int rightCounter = 0;
		
		while(tempNode.getLeft() != null || tempNode.getRight() != null) {
			if(tempNode.getRight() != null && tempNode.getLeft() == null) {
				rightCounter++;
				tempNode = tempNode.getRight();
			}
			if(tempNode.getRight() == null && tempNode.getLeft() != null) {
				leftCounter++;
				tempNode = tempNode.getLeft();
			}
			if(tempNode.getRight() != null && tempNode.getLeft() != null) {
				rightCounter++;
				tempNode = tempNode.getRight();
			}
			if(tempNode.getRight() == null && tempNode.getLeft() == null) {
				break;
			}
		}
		return (leftCounter + rightCounter);

	}


}

