/*
 * Jessica Pan
 * CruzID: jeypan
 * PA5
 * CMPS101
 * 12/1/18
 *
 *	README
 *	Provides descriptions of all the files in this assignment
 *
*/

FindComponents.c
	FindComponents.c implements the top level client and main program for this assignment. It passes a List to the function DFS() in Graph.c,
	so this file is also a client of of List module. FindComponents reads and inputfile and writes the adjacency list of the graph and its 
	strongly connected components to an output file.

Graph.c
	Graph.c is the implementation file of Graph ADT. It creates a graph by building the graph's adjacency list. It also allows for us to 
	add undirected and directed edges, as well as manipulating the graph. This includes: taking the transpose, making a copy and performing
	Depth-First Search on the graph.

Graph.h
	Graph.h is the header file to the Graph ADT and it defines all the functions, variables and constants that'll be included in Graph.c.

List.c: 
	List.c is the implementation file of List ADT. It creates a doubly linked list and contains functions that allows us to manipulate 
	the nodes and elements in the doubly linked list. During the process of coding this assignment I also referred to examples on our class webpage: Queue.c and Stack.c.

List.h: 
	List.h is the header file to the List ADT and it defines all the functions, variables and constants that'll be included in List.c.
	
Makefile:
    Makefile compiles the files of this assignment, cleans certain files in the directory and submits them as well. This file makes sure 
	that your code in your files function properly. 

README:
	README provides a description of each the different files used in this assignment. 
	It's also the file that you're currently reading right now.
	
