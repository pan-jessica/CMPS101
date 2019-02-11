#include <stdlib.h>
#include <stdio.h>
#include "Graph.h"

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA4
 * CMPS101
 * 11/11/18
 *
 *	GraphTest.c
 *	Client program using Graph.h and Graph.c
 *
*/

int main(int argc, char* argv[]){
	int numVert = 10;
	Graph G = newGraph(numVert);	

	addEdge(G, 1, 2);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 2, 5);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 1, 10);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 6, 9);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 3, 2);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 4, 2);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 7, 1);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 5, 8);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 5, 7);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 10, 6);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 9, 4);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addEdge(G, 8, 3);
	printf("Size of Graph(edges): %d\n", getSize(G));
	
	printGraph(stdout, G);
	printf("Size of Graph(edges): %d\n", getSize(G));
	
	addArc(G, 6, 5);
	printGraph(stdout, G);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addArc(G, 2, 10);
	printGraph(stdout, G);
	printf("Size of Graph(edges): %d\n", getSize(G));
	addArc(G, 1, 1);
	printGraph(stdout, G);
	printf("Size of Graph(edges): %d\n", getSize(G));
	
	printf("Order of Graph(vertices): %d\n", getOrder(G));
	printf("Source of Graph: %d\n", getSource(G));
	printf("Dist of vertex 3: %d\n", getDist(G,3));
	printf("\n");
	
	BFS(G,5);
	printf("Source of Graph: %d\n", getSource(G));
	printf("Parent of vertex 5: %d\n", getParent(G,5));
	printf("Parent of vertex 3: %d\n", getParent(G,3));
	printf("Parent of vertex 2: %d\n", getParent(G,2));
	printf("Parent of vertex 8: %d\n", getParent(G,8));
	printf("Dist of vertex 3 from source: %d\n", getDist(G,3));
	printf("Dist of vertex 6 from source: %d\n", getDist(G,6));
	printf("Dist of vertex 8 from source: %d\n", getDist(G,8));
	printf("Dist of vertex 4 from source: %d\n", getDist(G,4));
	
	List path = newList();
	getPath(path, G, 3);
	printf("Path of vertex 3 from source(shortest path): ");
	printList(stdout, path);
	printf("\n");
	
	makeNull(G);
	//printGraph(stdout, G);
	printf("Size of Graph(edges): %d\n", getSize(G));
	printf("Order of Graph(vertices): %d\n", getOrder(G));
	printf("Source of Graph: %d\n", getSource(G));
	
	freeList(&path);
	freeGraph(&G);
	
	return 0;
	
}