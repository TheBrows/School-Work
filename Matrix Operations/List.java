//Paige Riola - priola - 101 Tantalo - PA3 - 5/14/2018
//List.java - a list ADT of Objects
@SuppressWarnings("overrides")
class List{
	
	//private Node class
	
	@SuppressWarnings("overrides")
	private class Node{
		Object data;
		Node next;
		Node prev;
		
		Node(Object data){
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		public String toString(){
			return String.valueOf(data);
		}
		
		public boolean equals(Object x){
			boolean eq = false;
			Node that;
			if(x instanceof Node){
				that = (Node) x;
				eq = (this.data.equals(that.data));
			}
			return eq;
		}
	}
	
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	
	// Constructor
	
	List(){
		front = null;
		back = null;
		cursor = null;
		length = 0;
	} // Creates a new empty list.
	
	// Access functions
	
	int length(){
		return length;
	} // Returns the number of elements in this List.
	
	int index(){
		if(cursor == null){
			return -1;
		}
		Node N = front;
		int index = 0;
		while(N != cursor && N != null){
			index++;
			N = N.next;
		}
		return index;
	} // If cursor is defined, returns the index of the cursor element,
	// otherwise returns -1.
	
	Object front(){
		if(length() < 1){
			throw new RuntimeException("List Error: front() called on empty List");
		}
		return front.data;
	} // Returns front element. Pre: length()>0
	
	Object back(){
		if(length() < 1){
			throw new RuntimeException("List Error: back() called on empty List");
		}
		return back.data;
	} // Returns back element. Pre: length()>0
	
	Object get(){
		if(length() < 1){
			throw new RuntimeException("List Error: get() called on empty List");
		}
		if(index() < 0){
			throw new RuntimeException("List Error: get() called on null cursor");
		}
		return cursor.data;
	} // Returns cursor element. Pre: length()>0, index()>=0
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object x){
		boolean eq = false;
		List P;
		Node N, M;

		if(x instanceof List){
			P = (List) x;
			eq = (this.length==P.length);
			N = this.front;
			M = P.front;
			while( eq && N != null ){
				eq = N.equals(M);
				N = N.next;
				M = M.next;
			}
		}
		return eq;
	} // Returns true if and only if this List and L are the same
	// integer sequence. The states of the cursors in the two Lists
	// are not used in determining equality.
	
	// Manipulation procedures
	
	void clear(){
		while(back != null){
			deleteBack();
		}
	} // Resets this List to its original empty state.
	
	void moveFront(){
		if(length > 0){
			cursor = front;
		}
	} // If List is non-empty, places the cursor under the front element,
	// otherwise does nothing.
	
	void moveBack(){
		if(length > 0){
			cursor = back;
		}
	} // If List is non-empty, places the cursor under the back element,
	// otherwise does nothing.
	
	void movePrev(){
		cursor = cursor.prev;
	} // If cursor is defined and not at front, moves cursor one step toward
	// front of this List, if cursor is defined and at front, cursor becomes
	// undefined, if cursor is undefined does nothing.
	
	void moveNext(){
		cursor = cursor.next;
	} // If cursor is defined and not at back, moves cursor one step toward
	// back of this List, if cursor is defined and at back, cursor becomes
	// undefined, if cursor is undefined does nothing.
	
	void prepend(Object data){
		if(length()==0){
			front = back = new Node(data);
		}
		else{
			Node N = new Node(data);
			front.prev = N;
			N.next = front;
			front = N;
		}
		length++;
	} // Insert new element into this List. If List is non-empty,
	// insertion takes place before front element.
	
	void append(Object data){
		if(length()==0){
			front = back = new Node(data);
		}
		else{
			Node N = new Node(data);
			back.next = N;
			N.prev = back;
			back = N;
		}
		length++;
	} // Insert new element into this List. If List is non-empty,
	// insertion takes place after back element.
	
	void insertBefore(Object data){
		if(length() == 0){
			front = back = new Node(data);
			length++;
		}
		else if(index() == 0){
			prepend(data);
		}
		else{
			Node N = new Node(data);
			N.next = cursor;
			N.prev = cursor.prev;
			N.prev.next = N;
			N.next.prev = N;
			length++;
		}
	} // Insert new element before cursor.
	// Pre: length()>0, index()>=0
	
	void insertAfter(Object data){
		if(length() == 0){
			front = back = new Node(data);
			length++;
		}
		else if(index() == length()-1){
			append(data);
		}
		else{
			Node N = new Node(data);
			N.next = cursor.next;
			N.prev = cursor;
			N.prev.next = N;
			N.next.prev = N;
			length++;
		}
	} // Inserts new element after cursor.
	// Pre: length()>0, index()>=0
	
	void deleteFront(){
		if(length() < 1){
			throw new RuntimeException("List Error: front() called on empty List");
		}
		else if(length() == 1){
			front = back = cursor = null;
			length--;
		}
		else{
			if(index() == 0){
				cursor = null;
			}
			front = front.next;
			front.prev = null;
			length--;
		}
	} // Deletes the front element. Pre: length()>0
	
	void deleteBack(){
		if(length() < 1){
			throw new RuntimeException("List Error: front() called on empty List");
		}
		else if(length() == 1){
			front = back = cursor = null;
			length--;
		}
		else{
			if(index() == length()-1){
				cursor = null;
			}
			back = back.prev;
			back.next = null;
			length--;
		}
	} // Deletes the back element. Pre: length()>0
	
	void delete(){
		if(length() < 1 || index() < 0){
			throw new RuntimeException("List Error: front() called on empty List");
		}
		else if(length() == 1){
			front = back = cursor = null;
			length--;
		}
		else if(cursor == front){
			deleteFront();
			cursor = null;
		}
		else if(cursor == back){
			deleteBack();
			cursor = null;
		}
		else{
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			cursor = null;
			length--;
		}
	} // Deletes cursor element, making cursor undefined.
	// Pre: length()>0, index()>=0
	
	// Other methods
	
	public String toString(){
		String list = "";
		Node N = front;
		while(N != null){
			list += N.toString();
			if(N != back){
				list += " ";
			}
			N = N.next;
		}
		return list;
	} // Overrides Object's toString method. Returns a String
	// representation of this List consisting of a space
	// separated sequence of integers, with front on left.
	
	List copy(){
		List L = new List();
		L.cursor = null;
		Node N = front;
		while(N != null){
			L.append(N.data);
			N = N.next;
		}
		return L;
	}
}