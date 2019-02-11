#ifndef LIST_H
#define LIST_H

#include <stdio.h>

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA2
 * CMPS101
 * 10/14/18
 *
 *	List.h
 *	Header file for List ADT
 *
*/

// Exported type --------------------------------------------------------------
typedef struct ListObj* List;

// Constructors-Destructors ---------------------------------------------------

// newList()
//returns a List which points to a new empty list object. 
List newList(void);

// freeList()
////frees all heap memory associated with its List* argument, and sets *pL to NULL.
void freeList(List* pL);


// Access functions -----------------------------------------------------------

// length()
// returns length of L
int length(List L);

// index()
// returns index of L if cursor is defined
int index(List L);

// front()
// returns the front element of L
int front(List L);

// back()
// returns the back element of L
int back(List L);

// get()
// returns the cursor element
int get(List L);

// equals()
// returns true (1) iff List and L are the same int sequence
int equals(List A, List B);


// Manipulation procedures ----------------------------------------------------

// clear()
// resets List to its original empty state
void clear(List L);

// moveFront()
// if List is non-empty, places cursor under the front element, otherwise does nothing
void moveFront(List L);

// moveBack()
//  If List is non-empty, places the cursor under the back element, otherwise does nothing.
void moveBack(List L);

// movePrev()
// If cursor is defined and not at front, moves cursor one step toward front of this List, if cursor is defined and at front, cursor becomes undefined, if cursor is undefined does nothing.
void movePrev(List L);

// moveNext()
// If cursor is defined and not at back, moves cursor one step toward back of this List, if cursor is defined and at back, cursor becomes undefined, if cursor is undefined does nothing.
void moveNext(List L);

// prepend()
// Insert new element into this List. If List is non-empty, insertion takes place before front element.
void prepend(List L, int data);

// append()
// Insert new element into this List. If List is non-empty, insertion takes place after back element.
void append(List L, int data);

// insertBefore()
// Insert new element before cursor.
// Pre: length()>0, index()>=0
void insertBefore(List L, int data);

// insertAfter()
// Inserts new element after cursor.
// Pre: length()>0, index()>=0
void insertAfter(List L, int data);

// deleteFront()
// Deletes the front element. Pre: length()>0
void deleteFront(List L);

// deleteBack()
// Deletes the back element. Pre: length()>0
void deleteBack(List L);

// delete()
// Deletes cursor element, making cursor undefined.
// Pre: length()>0, index()>=0
void delete(List L);


// Other operations -----------------------------------------------------------

// printList()
//  prints the L to the file pointed to by out, formatted as a space-separated string. 
void printList(FILE* out, List L);

// copyList()
// Returns a new List representing the same integer sequence as this
// List. The cursor in the new list is undefined, regardless of the
// state of the cursor in this List. This List is unchanged.
List copyList(List L);



#endif