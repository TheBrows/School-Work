// Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
// List.c - a list ADT of integers

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>
#include"List.h"

// private NodeObj type
typedef struct NodeObj{
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

// private Node type
typedef NodeObj* Node;

Node newNode(int data){
	Node N = malloc(sizeof(NodeObj));
	N->data = data;
	N->next = NULL;
	N->prev = NULL;
	return(N);
}

void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
		*pN = NULL;
	}
}

// private ListObj type
typedef struct ListObj{
	Node front;
	Node back;
	Node cursor;
	int length;
} ListObj;

List newList(void){
	List L;
	L = malloc(sizeof(ListObj));
	L->front = L->back = L->cursor = NULL;
	L->length = 0;
	return(L);
}

void freeList(List* pL){
	if(pL!=NULL && *pL!=NULL) { 
		while(length(*pL) != 0) { 
			clear(*pL); 
		}
		free(*pL);
		*pL = NULL;
	}
}

// Access functions

int length(List L){
	if(L == NULL){
		printf("List Error: calling length() on NULL List reference\n");
		exit(1);
	}
	return L->length;
} // Returns the number of elements in this List.

int index(List L){
	if(L == NULL){
		printf("List Error: calling index() on NULL List reference\n");
		exit(1);
	}
	if(L->cursor == NULL){
		return -1;
	}
	Node N = L->front;
	int index = 0;
	while(N!=L->cursor && N!=NULL){
		index++;
		N = N->next;
	}
	return index;
} // If cursor is defined, returns the index of the cursor element,
// otherwise returns -1.

int front(List L){
	if(L == NULL){
		printf("List Error: calling front() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1){
		printf("List Error: calling front() on empty List reference\n");
		exit(1);
	}
	return L->front->data;
} // Returns front element. Pre: length()>0

int back(List L){
	if(L == NULL){
		printf("List Error: calling back() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1){
		printf("List Error: calling back() on empty List reference\n");
		exit(1);
	}
	return L->back->data;
} // Returns back element. Pre: length()>0

int get(List L){
	if(L == NULL){
		printf("List Error: calling get() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1){
		printf("List Error: calling get() on empty List reference\n");
		exit(1);
	}
	if(index(L) < 0){
		printf("List Error: calling get() on empty cursor reference\n");
		exit(1);
	}
	return L->cursor->data;
} // Returns cursor element. Pre: length()>0, index()>=0

// equals()
// returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B){
   int eq = 0;
   Node N = NULL;
   Node M = NULL;

   if( A==NULL || B==NULL ){
	  printf("List Error: calling equals() on NULL List reference\n");
	  exit(1);
   }

   eq = ( A->length == B->length );
   N = A->front;
   M = B->front;
   while( eq && N!=NULL){
	  eq = (N->data==M->data);
	  N = N->next;
	  M = M->next;
   }
   return eq;
} // Returns true if and only if this List and L are the same
// integer sequence. The states of the cursors in the two Lists
// are not used in determining equality.

// Manipulation procedures

void clear(List L){
	if(L == NULL){
		printf("List Error: calling clear() on NULL List reference\n");
		exit(1);
	}
	while(L->back != NULL){
		deleteBack(L);
	}
} // Resets this List to its original empty state.

void moveFront(List L){
	if(L == NULL){
		printf("List Error: calling moveFront() on NULL List reference\n");
		exit(1);
	}
	if(length(L) > 0){
		L->cursor = L->front;
	}
} // If List is non-empty, places the cursor under the front element,
// otherwise does nothing.

void moveBack(List L){
	if(L == NULL){
		printf("List Error: calling moveBack() on NULL List reference\n");
		exit(1);
	}
	if(length(L) > 0){
		L->cursor = L->back;
	}
} // If List is non-empty, places the cursor under the back element,
// otherwise does nothing.

void movePrev(List L){
	if(L == NULL){
		printf("List Error: calling movePrev() on NULL List reference\n");
		exit(1);
	}
	L->cursor = L->cursor->prev;
} // If cursor is defined and not at front, moves cursor one step toward
// front of this List, if cursor is defined and at front, cursor becomes
// undefined, if cursor is undefined does nothing.

void moveNext(List L){
	if(L == NULL){
		printf("List Error: calling moveNext() on NULL List reference\n");
		exit(1);
	}
	L->cursor = L->cursor->next;
} // If cursor is defined and not at back, moves cursor one step toward
// back of this List, if cursor is defined and at back, cursor becomes
// undefined, if cursor is undefined does nothing.

void prepend(List L, int data){
	if(L == NULL){
		printf("List Error: calling prepend() on NULL List reference\n");
		exit(1);
	}
	if(length(L) == 0){
		L->front = L->back = newNode(data);
	}
	else{
		Node N = newNode(data);
		L->front->prev = N;
		N->next = L->front;
		L->front = N;
	}
	L->length++;
} // Insert new element into this List. If List is non-empty,
// insertion takes place before front element.

void append(List L, int data){
	if(L == NULL){
		printf("List Error: calling append() on NULL List reference\n");
		exit(1);
	}
	if(length(L)==0){
		L->front = L->back = newNode(data);
	}
	else{
		Node N = newNode(data);
		L->back->next = N;
		N->prev =L->back;
		L->back = N;
	}
	L->length++;
} // Insert new element into this List. If List is non-empty,
// insertion takes place after back element.

void insertBefore(List L, int data){
	if(L == NULL){
		printf("List Error: calling insertBefore() on NULL List reference\n");
		exit(1);
	}
	if(length(L) == 0){
		L->front = L->back = newNode(data);
		L->length++;
	}
	else if(index(L) == 0){
		prepend(L,data);
	}
	else{
		Node N = newNode(data);
		N->next = L->cursor;
		N->prev = L->cursor->prev;
		N->prev->next = N;
		N->next->prev = N;
		L->length++;
	}
} // Insert new element before cursor.
// Pre: length()>0, index()>=0

void insertAfter(List L, int data){
	if(L == NULL){
		printf("List Error: calling insertAfter() on NULL List reference\n");
		exit(1);
	}
	if(length(L) == 0){
		L->front = L->back = newNode(data);
		L->length++;
	}
	else if(index(L) == length(L)-1){
		append(L,data);
	}
	else{
		Node N = newNode(data);
		N->next = L->cursor->next;
		N->prev = L->cursor;
		N->prev->next = N;
		N->next->prev = N;
		L->length++;
	}
} // Inserts new element after cursor.
// Pre: length()>0, index()>=0

void deleteFront(List L){
	Node N;
	
	if(L == NULL){
		printf("List Error: calling deleteFront() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1){
		printf("List Error: calling deleteFront() on empty List reference\n");
		exit(1);
	}
	else if(length(L) == 1){
		N = L->front;
		L->front = L->back = L->cursor = NULL;
		L->length--;
		freeNode(&N);
	}
	else{
		N = L->front;
		if(index(L) == 0){
			L->cursor = NULL;
		}
		L->front = L->front->next;
		L->front->prev = NULL;
		L->length--;
		freeNode(&N);
	}
} // Deletes the front element. Pre: length()>0

void deleteBack(List L){
	Node N;
	
	if(L == NULL){
		printf("List Error: calling deleteBack() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1){
		printf("List Error: calling deleteBack() on empty List reference\n");
		exit(1);
	}
	else if(length(L) == 1){
		N = L->back;
		L->front = L->back = L->cursor = NULL;
		L->length--;
		freeNode(&N);
	}
	else{
		N = L->back;
		if(index(L) == length(L)-1){
			L->cursor = NULL;
		}
		L->back = L->back->prev;
		L->back->next = NULL;
		L->length--;
		freeNode(&N);
	}
} // Deletes the back element. Pre: length()>0

void delete(List L){
	Node N;
	if(L == NULL){
		printf("List Error: calling delete() on NULL List reference\n");
		exit(1);
	}
	if(length(L) < 1 || index(L) < 0){
		printf("List Error: calling delete() on empty List reference\n");
		exit(1);
	}
	else if(length(L) == 1){
		N = L->front;
		L->front = L->back = L->cursor = NULL;
		L->length--;
		freeNode(&N);
	}
	else if(L->cursor == L->front){
		deleteFront(L);
		L->cursor = NULL;
	}
	else if(L->cursor == L->back){
		deleteBack(L);
		L->cursor = NULL;
	}
	else{
		N = L->cursor;
		L->cursor->prev->next = L->cursor->next;
		L->cursor->next->prev = L->cursor->prev;
		L->cursor = NULL;
		L->length--;
		freeNode(&N);
	}
} // Deletes cursor element, making cursor undefined.
// Pre: length()>0, index()>=0

// Other methods

void printList(FILE* out, List L){
	if(L == NULL){
		printf("List Error: calling toString() on NULL List reference\n");
		exit(1);
	}
	Node N = L->front;
	while(N != NULL){
		if(N->next == NULL){
			fprintf(out,"%d",N->data);
		}
		else{
			fprintf(out,"%d ",N->data);
		}
		N = N->next;
	}
} // Overrides Object's toString method. Returns a String
// representation of this List consisting of a space
// separated sequence of integers, with front on left.

List copyList(List L){
	if(L == NULL){
		printf("List Error: calling copy() on NULL List reference\n");
		exit(1);
	}List A = newList();
	A->cursor = NULL;
	Node N = L->front;
	while(N != NULL){
		append(A, N->data);
		N = N->next;
	}
	return A;
} // Returns a new List representing the same integer sequence as this
// List. The cursor in the new list is undefined, regardless of the
// state of the cursor in this List. This List is unchanged.