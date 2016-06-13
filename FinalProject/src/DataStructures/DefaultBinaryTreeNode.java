package DataStructures;

/**
 * DefaultBinaryTreeNode.java
 * CS 201
 * Moe Pwint Phyu
 */

/**
 * DefaultBinaryTreeNode is the class for a basic binary tree node,
 * with data of type T and pointers to left and right children.
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T>
{	
	//declare variables
	private T data;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	/**
	 * Constructor
	 */
	public DefaultBinaryTreeNode(){
		//do nothing
		super();
	}
	/**
	 * Get the data stored at this node.
	 * @return Object data.
	 */
	public T getData(){
		return data;
	}

	/**
	 * Set the data stored at this node.
	 */
	public void setData(T data){
		this.data = data;
	}

	/**
	 * Get the left child.
	 * @return BinaryTreeNode that is left child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getLeftChild(){
		return leftChild;
	}

	/**
	 * Get the right child.
	 * @return BinaryTreeNode that is right child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getRightChild(){
		return rightChild;
	}

	/**
	 * Set the left child.
	 */
	public void setLeftChild( BinaryTreeNode<T> left ){
		this.leftChild = left;
	}

	/**
	 * Set the right child.
	 */
	public void setRightChild( BinaryTreeNode<T> right ){
		this.rightChild = right;
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * @return true if leaf node.
	 */
	public boolean isLeaf(){
		if(getLeftChild()==null && getRightChild()==null){
			return true;
		}
		return false;
	}

}
