#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"
#include "Graph.h"

#define LEN 225

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA4
 * CMPS101
 * 11/11/18
 *
 *	FindPath.c
 *	Top Client using Graph.c and Graph.h
 *
*/

int main(int argc, char* argv[]){
	FILE *in, *out;
	
	if(argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		printf("Must need 2 arguments\n");
		exit(1);
	}
	
	//open input file
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	
	if (in == NULL){
		printf("Unable to open file %s for reading\n", argv[1]);
		exit(1);
	}
	if (out == NULL){
		printf("Unable to open file %s for writing\n", argv[2]);
		exit(1);
	}
	
	int numVert;
	//if(fgets(str, LEN, in) != NULL){
		fscanf(in, "%d", &numVert); 
	//}
	
	// make graph with number of vertices
	Graph G = newGraph(numVert);
	
	// adding in the edges
	int u, v;
	while(fgetc(in) != EOF){
		fscanf(in, "%d %d", &u, &v);
		if(u == 0 && v == 0){
			break;
		}
		addEdge(G, u, v);
	}
	printGraph(out, G);
	fprintf(out, "\n");
	
	int src, dest;
	List p = newList();
	while(fgetc(in) != EOF){
		fscanf(in, "%d %d", &src, &dest);
		if(src == 0 && dest == 0){
			break;
		}
		BFS(G,src);
		int distance = getDist(G, dest);
		
		if(distance != INF){
			getPath(p, G, dest);
			int pLen = length(p) - 1;
			
			fprintf(out, "The distance from %d to %d is %d\n", src, dest, pLen);
			fprintf(out, "A shortest %d-%d path is: ", src, dest);
			printList(out, p);
			fprintf(out, "\n\n");
		}else{
			fprintf(out, "The distance from %d to %d is infinity\n", src, dest);
			fprintf(out, "No %d-%d path exists\n\n", src, dest);
		}
		clear(p);
	}
	freeList(&p);
	freeGraph(&G);
	
	fclose(in);
	fclose(out);
	
	return 0;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	