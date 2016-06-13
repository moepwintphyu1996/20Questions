package DataStructures;

/*
 * CS 201
 * Moe Pwint Phyu
 */

public class DefaultBinaryTree<T> extends DefaultBinaryTreeNode<T> implements BinaryTree<T> 
{

	protected BinaryTreeNode<T> root;
	public DefaultBinaryTree(){
		super();
		this.root = null;
	}
	/**
	 * Testing whether my DefaultBinaryTree works or not
	 */
	public static void main(String[] args) {
		
		
		DefaultBinaryTree<String> dwarves = new DefaultBinaryTree<String>();
		/******************A NODE CORRESPONDING TO EACH DWARF ***********************/

		BinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String> ();
		grumpy.setData("Grumpy");

		BinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>();
		bashful.setData("Bashful");

		BinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>();
		doc.setData("Doc");

		BinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>();
		sneezy.setData("Sneezy");

		BinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>();
		sleepy.setData("Sleepy");

		BinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>();
		happy.setData("Happy");


		/******************BINARY TREE STRUCTURE OF DWARFS ********************/

		dwarves.setRoot(happy);

		happy.setRightChild(sleepy);

		sleepy.setRightChild(sneezy);

		happy.setLeftChild(doc);

		doc.setRightChild(grumpy);

		doc.setLeftChild(bashful);


		//print out tree in all three orders

		System.out.println("InOrder: "+ dwarves.inorderString());

		System.out.println("PreOrder: " + dwarves.preorderString());

		System.out.println("PostOrder: "+ dwarves.postorderString());
	}

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot(){
		if(root==null){
			return null;
		}
		return root;
	}

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root){
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty(){
		if(root==null){
			return true;
		}
		return false;
	}                                                                                                                                                                                                          

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	public LinkedList<T> inorderTraversal(){
		LinkedList<T> result = new LinkedList<T>();
		//starting from the root
		recurseInOrder(root,result);
		return result;

	}

	//left->node->right
	public void recurseInOrder(BinaryTreeNode<T> node, LinkedList<T> list){

		if(node ==null){
			return;
		}
		else if(node.isLeaf()){
			//insert the node's data to the end of the list
			list.insertLast(node.getData());
		}
		else{
			if(node.getLeftChild()!=null)
				//recurse until the left most node then it will insert data to the list
				recurseInOrder(node.getLeftChild(),list);
			//inserting the data of the node
			list.insertLast(node.getData());
			if(node.getRightChild()!=null)
				//recurse until the right most node then it will insert data to the list
				recurseInOrder(node.getRightChild(),list);
		}

	}
	/**
	 * Get the data of this tree using preorder traversal.
	 * Modified so that it will only print out the leaves
	 * 
	 * @return preorder List.
	 */
	public LinkedList<T> preorderTraversal(){
		LinkedList<T> result = new LinkedList<T>();
		//starting from the root
		recursePreOrder(root,result);
		return result;
	}


	public void recursePreOrder(BinaryTreeNode<T> node, LinkedList<T> list){
		if(node ==null){
			return;
		}
		else if(node.isLeaf()){
			//insert the node's data to the end of the list
			list.insertLast(node.getData());
		}
		else{
			if(node.getLeftChild()!=null)
				//recurse until the left most node then it will insert data to the list
				recursePreOrder(node.getLeftChild(),list);
			if(node.getRightChild()!=null)
				//recurse until the right most node then it will insert data to the list
				recursePreOrder(node.getRightChild(),list);
		}

	}
	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 */
	public LinkedList<T> postorderTraversal(){
		LinkedList<T> result = new LinkedList<T>();
		//starting from the root
		recursePostOrder(root,result);
		return result;

	}

	public void recursePostOrder(BinaryTreeNode<T> node, LinkedList<T> list){
		if(node ==null){
			return;
		}
		else if(node.isLeaf()){
			//insert the node's data to the end of the list
			list.insertLast(node.getData());
		}
		else{
			if(node.getLeftChild()!=null)
				//recurse until the left most node then it will insert data to the list
				recursePostOrder(node.getLeftChild(),list);
			if(node.getRightChild()!=null)
				//recurse until the right most node then it will insert data to the list
				recursePostOrder(node.getRightChild(),list);
			//inserting the data of the node
			list.insertLast(node.getData());
		}

	}

	/**
	 * Print the tree using inorder traversal.
	 * 
	 * @return inorder String
	 */
	public String inorderString(){
		LinkedList<T> list = new LinkedList<T>();
		list = inorderTraversal();
		return list.toString();
	}

	/**
	 * Print the tree using preorder traversal.
	 * 
	 * @return preorder String
	 */
	public String preorderString(){
		LinkedList<T> list = new LinkedList<T>();
		list = preorderTraversal();
		return list.toString();
	}

	/**
	 * Print the tree using postorder traversal.
	 * @return postorder String
	 */
	public String postorderString(){
		LinkedList<T> list = new LinkedList<T>();
		list = postorderTraversal();
		return list.toString();
	}
}