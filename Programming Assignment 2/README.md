/*
 * Jessica Pan
 * PA2
 * CMPS101
 * 10/14/18
 *
*/

Lex.c: 
	Lex.c is a client program, using a double pointer array, that takes in an input file and reorganizes the elements in the file into lexicographical order.
	It then prints the new order into an output file and frees all heap blocks created in the memory. During the process of coding Lex.c I referred to the examples 
	on our class webpage: FileIO.c.

List.c: 
	List.c is the implementation file of List ADT. It creates a doubly linked list and contains functions that allows us to manipulate 
	the nodes and elements in the doubly linked list. During the process of coding this assignment I also referred to examples on our class webpage: Queue.c and Stack.c.

List.h: 
	List.h is the header file to the List ADT and it defines all the functions, variables and constats that'll be included in List.c.

ListClient.c: 
	ListClient is a script that tests the List.c and List.h interface. It allows us to check for flaws and edge cases in our List.c and List.h file.
	This file was not altered since we were told not to by our professor, who provided us with this file. 
	ListClient isn't the best script to run against List.c and List.h, as it doesn't check every case possible, so it's best that you create 
	your own test cases in a seperate file and run it with List.c and List.h.
	
Makefile:
    Makefile compiles the files of this assignment, cleans certain files in the directory and submits them as well. This file makes sure 
	that your code in your files function properly. 

README:
	README provides a description of each the different files used in this assignment. 
	It's also the file that you're currently reading right now.
	
