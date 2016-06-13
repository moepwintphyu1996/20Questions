package DataStructures;

public class LinkedList<T>{

	//setting head to null
	private LinkedListNode<T> head = null;

	//constructor
	public LinkedList(){
		//do nothing
	}

	//Get data stored in head node of list.
	public T getFirst(){
		return head.getData();
	}

	//Get the head node of the list.
	public LinkedListNode<T> getFirstNode(){
		return head;
	}

	//Get data stored in tail node of list
	public T getLast(){
		//if the list is empty 
		if(getLastNode()==null){
			return null;
		}
		return getLastNode().getData();

	}


	//Get the tail node of the list
	public LinkedListNode<T> getLastNode(){
		LinkedListNode<T> currentNode = new LinkedListNode<T>();
		currentNode = head;
		//case 1 
		//where there is no list
		if(isEmpty()){
			return null;
		}
		//case 2 
		//there is only head and head is the tail
		else if(head.getNext()==null){
			return head;
		}
		//case 3 
		//there is a LinkedList
		else{
			//we will update the currentNode until the currentNode points to null which means that currentNode is the tail now
			while(currentNode.getNext()!=null){
				currentNode = currentNode.getNext();
			}
			//return tail
			return currentNode;
		}
	}	

	//Insert a new node with data at the head of the list.
	public void insertFirst( T data ){
		//creating a new Node called first
		LinkedListNode<T> first = new LinkedListNode<T>();
		//set the data of first Node
		first.setData(data);
		//make the first point to head
		first.setNext(head);
		//make first the head of the list
		head = first;

	}


	//Insert a new node with data after currentNode
	public void insertAfter( LinkedListNode<T> currentNode, T data ){
		//case 1 
		//list is empty 
		if(isEmpty()){
			//call insertFirst
			insertFirst(data);
		}

		else{

			LinkedListNode<T> addedNode = new LinkedListNode<T>();
			//declare a node to temporarily hold the node after current Node
			LinkedListNode<T> temp = currentNode.getNext();


			if(currentNode.getNext()!=null){
				//setting the pointer of the current Node to addedNode
				currentNode.setNext(addedNode);
				//setting the data
				addedNode.setData(data);
				//setting the pointer of addedNode to temp
				addedNode.setNext(temp);
			}

			//if currentNode is the tail
			if(currentNode.getNext()==null){
				//set the pointer of currentNode to addedNode
				currentNode.setNext(addedNode);
				//set the data of the addedNode
				addedNode.setData(data);
			}


		}
	}

	//Insert a new node with data at the tail of the list.
	public void insertLast( T data ){
		LinkedListNode<T> tail = new LinkedListNode<T>();
		//case 1 
		//the list is Empty
		if(isEmpty()){
			insertFirst(data);
		}
		else if(getLastNode()!=null){
			//set the pointer of the last Node to tail
			getLastNode().setNext(tail);
			//set the data of the tail
			tail.setData(data);
		}

	}


	//Remove the first node
	public void deleteFirst(){
		LinkedListNode<T> firstNode = head;
		if(firstNode!=null){
			LinkedListNode<T> secondNode = firstNode.getNext();
			//set the head to the second node
			head = secondNode;
		}


	}

	//Remove the last node
	public void deleteLast(){
		//if the list is not empty
		if(!isEmpty()){
			//declare two nodes called currentNode and previousNode
			LinkedListNode<T> currentNode = new LinkedListNode<T>();
			LinkedListNode<T> previousNode = new LinkedListNode<T>();
			//if head is the tail and we have to delete head, set the head to null
			if(head==getLastNode()){
				head = null;
			}
			else{
				currentNode = head;
				//until currentNode is not the tail
				while(currentNode.getNext()!=null){
					//set previousNode to currentNode
					previousNode = currentNode;
					//set currentNode to the node after currentNode
					currentNode = currentNode.getNext();
				}
				//set the pointer of the node before the tail to null
				previousNode.setNext(null);
			}

		}



	}


	//Remove node following currentNode
	//If no node exists (i.e., currentNode is the tail), do nothing
	public void deleteNext( LinkedListNode<T> currentNode ){
		LinkedListNode<T> deletedNode = currentNode.getNext();

		//no node exists
		if(deletedNode==null){
			//do nothing
			return;
		}

		else if(deletedNode.getNext()!=null){
			LinkedListNode<T> temp = deletedNode.getNext();
			//setting the pointer of the current node to the two nodes after currentNode
			currentNode.setNext(temp);
		}

	}

	//Return the number of nodes in this list.
	public int size(){
		//declare a variable and initialize to 0
		int listSize = 0;
		LinkedListNode<T> currentNode = new LinkedListNode<T>();
		currentNode = head;

		while(currentNode!=null){
			//keep updating the currentNode up until the tail
			currentNode = currentNode.getNext();
			//increment the size
			listSize++;
		}
		return listSize;
	}


	//Check if list is empty.
	//return true if list contains no items
	public boolean isEmpty(){
		if(head==null){
			return true;
		}
		else{
			return false;
		}
	}

	//Return a String representation of the list.
	public String toString(){
		LinkedListNode<T> currentNode = new LinkedListNode<T>();
		currentNode = head;
		String string = "";
		//if there is nothing in the string
		if(currentNode==null){
			return "";
		}
		else{
			while(currentNode.getNext()!=null){
				string+=currentNode.getData() + "->";
				//keep updating the current node before the tail
				currentNode = currentNode.getNext();
			}
			//print out the tail
			string+=currentNode.getData();
		}
		return string;

	}

	//for testing purposes
	public static void main(String[]args){
		LinkedList<String> myList = new LinkedList<String>();
		myList.insertFirst("a");
		myList.insertFirst("v");
		myList.insertFirst("a");
		myList.insertFirst("l");
		myList.insertFirst("o");
		myList.insertFirst("i");
		myList.insertAfter(myList.head.getNext(),"j" );
		myList.insertAfter(myList.head.getNext(), "e");
		myList.insertAfter(myList.head.getNext(), "v");
		myList.insertAfter(myList.head, "l");
		myList.deleteNext(myList.head.getNext().getNext().getNext().getNext());

		System.out.println(myList);

	}
}
