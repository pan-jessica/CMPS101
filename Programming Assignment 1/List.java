/*
 * Created by Jessica Pan on 10/1/2018
 * CruzID: jeypan 
 * PA1
 * CMPS101
 */

public class List {
	
	private class Node { //Node inner class
		
		int data;
		Node prev;
		Node next;
				
		Node(int data) {
			this.data = data;
			prev = null;
			next = null;
		}
		
		Node (int data, Node nextNode, Node prevNode) {
			this.data = data;
			next = nextNode;
			prev = prevNode;
		}
		
		public String toString() { //toString() overrides the Object's toString() method
			return String.valueOf(data);
		}
	}
	
	// Different fields of List
		private Node front;
		private Node cursor;
		private Node back;
		private int index;
		private int length;
		
	List() { 	//Constructor
		front = null;
		cursor = null;
		back = null;
		index = -1; //undefined
		length = 0;
	}
	
	int front() {	//Returns the front element. Precondition: length() > 0
		if (length > 0) {
			return front.data;
		}
		return -1;
	}
	
	int back() {		//Returns the back element. Precondition: length() > 0
		if (length > 0) {
			return back.data;
		}
		return -1;
	}
	
	int index() {		//Returns index and/or the number of elements in this List
		return index;
	}
	
	int length() {		//Returns number of elements in the List
		return length;
	}
	
	
	// Precondition: length() > 0, index() >= 0
	int get() {		
		//Returns the cursor element. 
		if (length > 0) {
			return cursor.data;
		}
		return -1;
	}
	
	boolean equals(List L) {		
		//the cursor is ignored in both lists and returns true if List and L have the same integer sequence.
		Node firstList = L.front;
		Node secondList = this.front;
		
		if (L.length != this.length) {
			return false;
		}
		
		if (back != null && L.back != null) {
			if (L.back.data != this.back.data) {
				return false;
			}
		}
		
		if (firstList != null) {
			while (firstList.next != null) {
				if (firstList != null && secondList != null) {
					if (firstList.data != secondList.data) {
						return false;
					}
				}
				firstList = firstList.next;
				secondList = secondList.next;
			}
		}
		return true;
	}
	
	void clear() { 
		//Resets List to origin empty state
		cursor = front;
		if (cursor != null) {
			while (cursor.next != null) {
				Node temp = cursor;
				cursor = cursor.next;
				temp.prev = null;
				temp.next = null;
			}
			front = null;
			cursor = null;
			back = null;
			index = -1;
			length = 0;
		}
	}
	
	void moveFront() {		
		//places cursor under the first/front element if List is not empty, else does nothing
		if (length > 0) {
			cursor = front;
			index = 0;
		}
	}
	
	void moveBack() {		
		//places cursor under last/back element if List isn't empty, else does nothing
		if (length > 0) {
			cursor = back;
			index = length - 1;
		}
	}
	
	void movePrev() {		
		// 1. if cursor is defined & not at the front, move cursor 1 step toward front of List
		// 2. if cursor is defined & at the front, cursor is undefined
		// 3. if cursor is undefined, function does nothing
		if (cursor != null && !cursor.equals(front)) {
			cursor = cursor.prev;
			--index;
		} else if (cursor != null && cursor.equals(front)){
			cursor = null;
			index = -1;
		} else {
			return;
		}
	}
	
	void moveNext() {
		// 1. if cursor is defined & not at the back, move cursor 1 step backward toward back of List
		// 2. if cursor is defined & at the back, cursor is undefined
		// 3. if cursor is undefined, function does nothing
		if (cursor != null && !cursor.equals(back)) { 
			cursor = cursor.next;
			++index;
		} else if (cursor != null && cursor.equals(back)) {
			cursor = null;
			index = -1;
		} else {
			return;
		}
	}
	
	void prepend (int data) {
		//inserts new element into List
		//if List isn't empty, insertion happens before the front element
		if (length > 0) {
			Node newFront = new Node(data, front, null);
			front.prev = newFront;
			front = newFront;
			++length;
			++index;
		} else {
			Node newFront = new Node(data);
			front = newFront;
			back = newFront;
			++length;
			++index;
		}
	}
	
	void append (int data) {
		//inserts new element into back of the List, if List isn't empty, insertion happens after back element
		 if (length > 0) {
			Node newBack = new Node(data, null, back);
			back.next = newBack;
			back = newBack;
			++length;
		} else {
			Node newBack = new Node(data);
			front = newBack;
			back = newBack;
			++length;
			++index;
		}
	}
	
	//Preconditions: length() > 0, index() >= 0
	void insertBefore (int data) {
		//inserts the new element before the cursor
		if (length() <= 0) {
			return;
		} else if (index() < 0) {
			return;
		}
		
		Node newNode = new Node(data);
		
		if (index() == 0) {
			prepend(data);
		} else {
			newNode.next = cursor;
			newNode.prev = cursor.prev;
			cursor.prev.next = newNode;
			cursor.prev = newNode;
			++index;
			++length;
		}
	}
	
	//Preconditions: length() > 0, index() >= 0
	void insertAfter (int data) {
		//inserts a new element after the cursor
		if (length() <= 0) {
			return;
		} else if (index() < 0) {
			return;
		}
		
		Node newNode = new Node(data);
		
		if (index() == length() - 1) {
			append(data);
		} else {
			newNode.prev = cursor;
			newNode.next = cursor.next;
			cursor.next.prev = newNode;
			cursor.next = newNode;
			++length;
		}
	}
	
	void advanceTo(int i) {
		if (i == index) 
			return;
		if (i >= length() || i < 0) {
			cursor = null;
			index = -1;
			return;
		}
		if (i == 0) {
			cursor = front;
		}
		if (i == length() - 1) {
			cursor = back;
		} else {	//direction values: 0=left, 1=right
			int direction = 0;
			
			//3 possible distances
			int dis1 = length() - i;	//dis1 = from the back
			int dis2 = i;				//dis2 = from the front
			int dis3 = Math.abs(index -  i);	//dis3 = from the cursor
			
			//finding the shortest distance
			int min = dis1;
			if (min > dis2)
				min = dis2;
			if (min > dis3) 
				min = dis3;
			
			//Traversal
			if (min == dis1) {	//traversal from back
				cursor = back;
				index = length() - 1;
				direction = 0;
			} else if (min == dis2) { 	//traversal from front
				cursor = front;
				index = 0;
				direction = 1;
			} else if (min == dis3) { 	//traversal from cursor
				if (i > index){
					direction = 1;
				}
			}
			
			//traverse the list
			for (int j = 0; j < i; j++) {
				if (direction == 1) {
					moveNext();
				} else {
					movePrev();
				}
			}
		}
		index = i;	
	}
	
	//Preconditions: length() > 0
	void deleteFront() {	//deleting front element
		if (length > 0) {
			if (length == 1) {
				front = null;
				back = null;
				cursor = null;
				index = -1;
				--length;
				return;
			} 

			front = front.next;
			if (front != null) {
				front.prev = null;
			}
			--index;
			--length;
		} else {
			front = null;
		}
	}
	
	//Preconditions: length() > 0
	void deleteBack() {		//deleting back element
		 if (length > 0) {
			 if (length == 1) {
				 front = null;
				 back = null;
				 cursor = null;
				 index = -1;
				 --length;
				 return;
			 }

			if (cursor == back) {
				cursor = null;
				index = -1;
			}
			 back = back.prev;
			if (back != null) {
				back.next = null;
			}
			--length;
		} else { 
			back = null;
		}
	}
	
	//Preconditions: length() > 0, index() >= 0
	void delete() {		//deleting element at cursor, cursor then becomes undefined
		if (length > 0 && index >= 0) {
			if (cursor == front) {
				deleteFront();
				cursor = null;
				index = -1;
			} else if (cursor == back) {
				deleteBack();
				cursor = null;
				index = -1;
			} else {
				cursor.next.prev = cursor.prev;
				cursor.prev.next = cursor.next;
				cursor = null;
				index = -1;
				--length;
			}
		}
	}
	
	public String toString() {		// overrides Object's toString method
		//returns a string representation of List with a space b/w each integer in the sequence, starting with front element
		String str = "";
		for (Node N = front; N != null; N = N.next) {
			str = str + N.toString() + " ";
		}
		return str;
	}
	
	List copy() {
		//returns a new List with the same integer sequence as current List
		//cursor in newList will be undefined regardless of state of cursor in current List. [current List won't be changed]
		List newList = new List();
		if (length == 0) {
			return newList;
		}
		
		Node tempNode = this.front;
		while (tempNode.next != null) {
			newList.append(tempNode.data);
			tempNode = tempNode.next;
		}
		newList.append(tempNode.data);
		
		return newList;
	}
	
	
	
}

