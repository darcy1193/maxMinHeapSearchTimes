package edu.wmich.cs3310.ddarcy;

/**
 * CLASS DESCRIPTION:
 * This class is for my nodes.  It uses the attributes below to create a node with many different methods which include
 * insert, the constructor, search, get methods, set methods, etc.
 * @author Daniel Darcy
 */

public class MyNode {
	
	//Declare Variables
	private int height;
	private String storedData;
	private MyNode left, right;
	private int position;
	private int depth;
	
	/**
	 * Constructor to declare a node.
	 */
	public MyNode() {
		height = 0;
		storedData = null;
		position = 0;
		depth = 0;
	}
	
	/**
	 * Instantiates node with specified attributes.
	 * @param height
	 * @param leftChild
	 * @param rightChild
	 * @param parent
	 * @param storedData
	 * @param position
	 */
	public MyNode(int height, MyNode leftChild, MyNode rightChild, MyNode parent, String storedData, int position) {
		this.height = height;
		this.storedData = storedData;
		this.position = position;
	}
	
	/**
	 * Creates a node with specified String value.
	 * @param storedData
	 */
	public MyNode(String storedData) {
		this.storedData = storedData;
		height = -1;
		position = 0;
		left = right = null;
		depth = 0;
	}

	/**
	 * Sets the left child.
	 * @param left
	 */
	public void setLeft(MyNode left) {
		this.left = left;
	}

	/**
	 * Sets the right child.
	 * @param right
	 */
	public void setRight(MyNode right) {
		this.right = right;
	}

	/**
	 * Returns the height.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns stored data.
	 * @return storedData
	 */
	public String getStoredData() {
		return storedData;
	}
	
	/**
	 * Returns left child.
	 * @return leftChild
	 */
	public MyNode getLeft() {
		return left;
	}
	
	/**
	 * Return right child.
	 * @return rightChild
	 */
	public MyNode getRight() {
		return right;
	}

	/**
	 * Sets stored data.
	 * @param storedData
	 */
	public void setStoredData(String storedData) {
		this.storedData = storedData;
	}
	
	/**
	 * Returns the position attribute of node.
	 * @return position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Sets the position attribute.
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
