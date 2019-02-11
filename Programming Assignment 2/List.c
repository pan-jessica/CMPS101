#include <stdlib.h>
#include "List.h"

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA2
 * CMPS101
 * 10/14/18
 *
 *	List.c
 *	Implementation file of List ADT written in C.
 *
*/

// Structs ---------------------------------------------------------------------

typedef struct NodeObj {	//private NodeObj type
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

typedef NodeObj* Node;	//private Node type

typedef struct ListObj {	//private ListObj type
	Node front;
	Node back;
	Node cursor;
	int index;
	int length;
} ListObj;


// Constructors-Destructors ---------------------------------------------------

// newNode()
// private and returns referenct to new Node object. Initializes data and next fiels
Node newNode(int data){
	Node N = NULL; //incase there's anything already inside
	N = malloc(sizeof(NodeObj));
	N->data = data;
	N->next = NULL;
	N->prev = NULL;
	return (N);
}

// newList()
//returns a List which points to a new empty list object.
List newList(void) {	 //used Queue.c example for format
	List L = NULL;	//again incase anything's inside
	L = malloc(sizeof(ListObj));
	L->front = L->back = NULL;
	L->cursor = NULL;
	L->index = -1;
	L->length = 0;
	return(L);
}

// freeNode()
// frees each node
void freeNode(Node* pN){
	if (pN != NULL && *pN != NULL){
		free(*pN);
		*pN = NULL;
	}
}

// freeList()
//frees all heap memory associated with its List* argument, and sets *pL to NULL.
void freeList(List* pL) {	//used Queue.c example for format
	if (pL != NULL && *pL != NULL){
		while (length(*pL) > 0){		// free each node before freeing whole list
			deleteFront(*pL);
		}
		free(*pL);
		*pL = NULL;
	}
}


// Access functions -----------------------------------------------------------

int length(List L) {
	//calling length on NULL List => rmbr to make err msg
	if (L == NULL) {
		printf("List Error: calling length on NULL List reference\n");
		exit(1); 
	}
	return L->length;
}

int index(List L){
	//calling length on NULL List => rmbr to make err msg
	if (L == NULL){
		printf("List Error: calling index on NULL List reference\n");
		exit(1); 
	}
	if (L->back == NULL){
		printf("List Error: calling index on empty List reference\n");
		return -1;
	}
	return L->index;
}

int front(List L){  //Pre: L->length>0
	//calling length on NULL List => rmbr to make err msg
	if (L == NULL){	
		printf("List Error: calling front on NULL List reference\n");
		exit(1);
	}
	if (L->back == NULL){
		printf("List Error: calling front on empty List reference\n");
		return -1;
	}
	return L->front->data;
}

int back(List L){  //Pre: L->length >0
	//calling length on NULL List => rmbr to make err msg
	if (L == NULL){	
		printf("List Error: calling back on NULL List reference\n");
		exit(1);
	}
	if (L->back == NULL){
		printf("List Error: calling back on empty List reference\n");
		return -1;
	}
	return L->back->data;
}

// Pre: L->length >0, L->index >=0
int get(List L){  
	//calling length on NULL List => rmbr to make err msg
	if (L == NULL){	
		printf("List Error: calling get on NULL List reference\n");
		exit(1);
	}
	if (L->back == NULL && L->cursor == NULL){
		printf("List Error: calling get on empty List reference and NULL cursor\n");
		return -1;
	}
	return L->cursor->data;
}

int equals(List A, List B){	//used Queue.c example for format
	//returns 1 for List and L are the same integer sequence and 0 if not the same.
	Node aList = NULL;
	Node bList = NULL;
	int eq = 0;
	
	if (A == NULL || B == NULL){
		printf("List error: calling equals on NULL List reference\n");
		exit(1);
	}
	
	eq = (A->length == B->length);
	aList = A->front;
	bList = B->front;
	while (eq && aList != NULL){
		eq = (aList->data == bList->data);
		aList = aList->next;
		bList = bList->next;
	}
	
	return eq;
	
}


// Manipulation procedures ----------------------------------------------------
void clear(List L){
	if (L == NULL){
		printf("List Error: calling clear on NULL List reference\n");
		exit(1);
	}
	if (L != NULL && L->length > 0){
		while (L->length > 0) {
			deleteFront(L);	//length decreases as we call deleteFront
		}
		
		L->front = NULL;
		L->back = NULL;
		L->cursor = NULL;
		L->index = -1;
		L->length = 0;
	}	
}

void moveFront(List L){
	if (L == NULL){
		printf("List Error: calling moveFront on NULL List reference\n");
		exit(1);
	}
	if (L->back != NULL){
		L->index = 0;
		L->cursor = L->front;
	}
}

void moveBack(List L) {
	if (L == NULL){
		printf("List Error: calling moveBack on NULL List reference\n");
		exit(1);
	}
	if (L->back != NULL){
		L->index = L->length - 1;
		L->cursor = L->back;
	}
}

void movePrev(List L){
	if (L == NULL){
		printf("List Error: calling movePrev on NULL List reference\n");
		exit(1);
	}
	if (L->cursor != NULL){
		if (L->cursor != L->front){
			L->cursor = L->cursor->prev;
			L->index--;
		}else{
			L->cursor = NULL;
			L->index = -1;
		}
	}
}

void moveNext(List L){
	if (L == NULL){
		printf("List Error: calling moveNext on NULL List reference\n");
		exit(1);
	}
	if (L->cursor != NULL){
		if (L->cursor != L->back){
			L->cursor = L->cursor->next;
			L->index++;
		}else{
			L->cursor = NULL;
			L->index = -1;
		}
	}
}
	
void prepend(List L, int data){
	if (L == NULL){
		printf("List Error: calling prepend on NULL List reference\n");
		exit(1);
	}
	Node newFront = newNode(data);
	if (L->length == 0){
		L->front = L->back = newFront;
		if (L->cursor != NULL){
			L->index++;
		}
	}else{	//L->back != NULL or L->length > 0
		newFront->next = L->front;
		L->front->prev = newFront;
		L->front = newFront;
		L->index++;
	}
	L->length++;
}	
	
void append(List L, int data){
	if (L == NULL){
		printf("List Error: calling append on NULL List reference\n");
		exit(1);
	}
	Node newBack = newNode(data);
	if (L->length == 0){
		L->front = L->back = newBack;
	}else{ 		//L->back != NULL or L->length > 0
		newBack->prev = L->back;
		L->back->next = newBack;
		L->back = newBack;
	}
	L->length++;
}
		
//Preconditions: L->length  > 0, L->index  >= 0
void insertBefore(List L, int data){
	if (L == NULL){
		printf("List Error: calling insertBefore on NULL List reference\n");
		exit(1);
	}
	if (L->length <= 0 || L->cursor == NULL){  //there has to be an element||cursor in the List first
		printf("List Error: calling insertBefore on empty List reference and NULL cursor\n");
		return;
	}
	
	Node newEle = newNode(data);
	if (L->cursor == L->front){ 
		prepend(L, data);
	}else{ 		//L->length > 0 && L->cursor !=	NULL
		newEle->prev = L->cursor->prev;
		newEle->next = L->cursor;
		L->cursor->prev->next = newEle;
		L->cursor->prev = newEle;
		L->length++;
		L->index++;
	}
}

//Preconditions: L->length  > 0, L->index  >= 0
void insertAfter(List L, int data){
	if (L == NULL){
		printf("List Error: calling insertAfter on NULL List reference\n");
		exit(1);
	}
	if (L->length <= 0 || L->cursor == NULL) {
		printf("List Error: calling insertAfter on empty List reference and NULL cursor\n");
		return;
	}
	
	Node newEle = newNode(data);
	if (L->cursor == L->back && L->index == L->length - 1){
		append(L, data);
	}else{ 		 //(L->length > 0 & L->cursor != NULL
		newEle->prev = L->cursor;
		newEle->next = L->cursor->next;
		L->cursor->next = newEle;
		L->length++;
	}
}
	
//Preconditions: L->length  > 0
void deleteFront(List L){
	if (L == NULL){
		printf("List Error: calling deleteFront on NULL List reference\n");
		exit(1);
	}
	
	if (L->back != NULL){
		Node temp = NULL;
		temp = L->front;
		if (L->front == L->back){ //length = 1
			L->front = L->back = NULL;
			L->cursor = NULL;
			L->index = -1;
			
		}else if(L->cursor == L->front){	//cursor at front
			L->front = L->front->next;
			L->front->prev = NULL;
			L->cursor = NULL;
			L->index = -1;
			
		}else {		
			L->front = L->front->next;	//cursor anywhere else
			L->front->prev = NULL;
			if(L->cursor != NULL){
				L->index--;
			}
		}
		
		freeNode(&temp);
		
		L->length--;
	}
}	
		
//Preconditions: L->length  > 0
void deleteBack(List L){
	if (L == NULL){
		printf("List Error: calling deleteBack on NULL List reference\n");
		exit(1);
	}
	
	if (L->back != NULL){
		Node temp = NULL;
		temp = L->back;
		if (L->front == L->back){	//length = 1
			L->front = L->back = NULL;
			L->cursor = NULL;
			L->index = -1;
			
		}else if (L->cursor == L->back){	// cursor at back
			L->back = L->back->prev;
			L->back->next = NULL;
			L->cursor = NULL;
			L->index = -1;
			
		}else{					//length > 1 basically the rest
			L->back = L->back->prev;
			if (L->back != NULL){
				L->back->next = NULL;
			}
		}
		
		freeNode(&temp);
		
		L->length--;
	}
}

//Preconditions: L->length  > 0, L->index  >= 0
void delete(List L){
	if (L == NULL){
		printf("List Error: calling delete on NULL List reference\n");
		exit(1);
	}
	
	if (L->length > 0 && L->cursor != NULL){
		if (L->cursor == L->front){
			deleteFront(L);
		}else if (L->cursor == L->back){
			deleteBack(L);
		}else{
			Node temp = NULL;
			temp = L->cursor;
			L->cursor->next->prev = L->cursor->prev;
			L->cursor->prev->next = L->cursor->next;
			
			freeNode(&temp);
			
			L->cursor = NULL;
			L->length--;
		}
		L->index = -1;
	}
}	
	
// Other operations -----------------------------------------------------------
void printList(FILE* out, List L){	
	// prints the L to the file pointed to by out, formatted as a space-separated string.
	if (L == NULL){
		printf("List Error: calling printList on NULL List reference\n");
		exit(1);
	}
	
	Node temp = NULL;
	temp = L->front;
	while(temp != NULL){	//possible check to see if out=NULL
		fprintf(out, "%d ", temp->data);
		temp = temp->next;
	}
	freeNode(&temp);
}

List copyList(List L){
	List sameList = newList();
	if (L->back != NULL){
		Node temp = L->front;
		while (temp != NULL){
			append(sameList, temp->data);
			temp = temp->next;
		}
		freeNode(&temp);
	}
	
	sameList->cursor = NULL;
	sameList->index = -1;
	
	return sameList;
}


