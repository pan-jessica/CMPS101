#include <stdlib.h>
#include "Graph.h"

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA4
 * CMPS101
 * 11/11/18
 *
 *	Graph.c
 *	Implementation of Graph ADT in C
 *
*/

#define white 0
#define gray 1
#define black 2

typedef struct GraphObj{
	List* adjList; // neighboring vertices
	int* color; // ith element is the color (white, gray, black) of vertex i.
	int* parent; // ith element is the parent of vertex i
	int* dist; //  ith element is the distance from the (most recent) source to vertex i
	int order; // number of vertices (order of graph)
	int size; // number of edges (size of graph)
	int source; // root of BFS tree
	
}GraphObj;

typedef struct GraphObj* Graph;


/*** Constructors-Destructors ***/
// returns a Graph pointing to a newly created GraphObj representing a graph having n vertices and no edges
Graph newGraph(int n){
	if(n < 1){
		printf("Graph has no vertex if n < 1, invalid input.");
		exit(1);
	}
	Graph G = malloc(sizeof(GraphObj));
	G->adjList = malloc((n + 1)*sizeof(List)); 
	G->color = malloc((n + 1)*sizeof(int));
	G->dist = malloc((n + 1)*sizeof(int));
	G->parent = malloc((n + 1)*sizeof(int));
	G->order = n;
	G->size = 0;
	G->source = NIL;
	
	int i;
	G->adjList[0] = NULL; // only using indices 1 through n
	for(i = 1; i <= n; i++){
		G->adjList[i] = newList();
		G->color[i] = white;
		G->dist[i] = INF;
		G->parent[i] = NIL;
	}
	
	return G;
}

// frees all dynamic memory associated with the Graph *pG, then sets the handle *pG to NULL
void freeGraph(Graph* pG){
	if(*pG == NULL && pG == NULL){
		printf("freeGraph Err: you're freeing an already freed Graph.");
		exit(1);
	}
	int i;
	for(i = 1; i <= getOrder(*pG); i++){
		freeList(&((*pG)->adjList[i]));
	}
	free((*pG)->adjList);
	
	free((*pG)->color);
	free((*pG)->dist);
	free((*pG)->parent);
	
	free(*pG);
	*pG = NULL;
}


/*** Access functions ***/
int getOrder(Graph G){
	if(G == NULL){
		printf("getOrder Err: there's not graph passed.");
		exit(1);
	}
	return G->order;
}

int getSize(Graph G){
	if(G == NULL){
		printf("getSize Err: there's no graph passed.");
		exit(1);
	}
	return G->size;	
}

// returns the source vertex most recently used in function BFS(), or NIL if BFS() has not yet been called
int getSource(Graph G){
	if(G == NULL){
		printf("getSource Err: there's no graph passed.");
		exit(1);
	}
	return G->source;
}

// will return the parent of vertex u in the BreadthFirst tree created by BFS(), or NIL if BFS() has not yet been called. 
// precondition 1<= u <= getOrder(G)
int getParent(Graph G, int u){
	if(G == NULL){
		printf("getParent Err: there's no graph passed.");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("getParent Err: vertex u isn't within graph's vertex range.");
		exit(1);
	}
	return G->parent[u];	
}

// returns the distance from the most recent BFS source to vertex u, or INF if BFS() has not yet been called
// precondition 1<= u <= getOrder(G)
int getDist(Graph G, int u){
	if(G == NULL){
		printf("getDist Err: there's no graph passed.");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("getDist Err: vertex u isn't within graph's vertex range.");
		exit(1);
	}
	if(getSource(G) == NIL){
		return INF;
	}
	return G->dist[u];
}

// appends to the List L the vertices of a shortest path in G from source to u, or appends to L the value NIL if no such path exists
// precondition getSource(G)!=NIL, so BFS() must be called before getPath()
// precondition 1<= u <= getOrder(G)
void getPath(List L, Graph G, int u){
	if(G == NULL){
		printf("getPath Err: there's no graph passed.");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("getPath Err: vertex u isn't within graph's vertex range.");
		exit(1);
	}
	if(getSource(G) == NIL){
		printf("getPath Err: BFS() must be called before getPath()");
		exit(1);
	}

	if(u == G->source){
		append(L, u);
	}else if(G->parent[u] != NIL){
		getPath(L, G, G->parent[u]);
		append(L, u);
	}else{
		append(L, NIL);
	}
}

/*** Manipulation procedures ***/
// deletes all edges of G, restoring it to its original (no edge) state. 
void makeNull(Graph G){
	if(G == NULL){
		printf("makeNull Err: graph is already NULL.");
		exit(1);
	}
	int i;
	for(i = 1; i <= getOrder(G); i++){
		clear(G->adjList[i]);
	}
	// no edge state
	G->size = 0;
}

// inserts a new edge joining u to v, i.e. u is added to the adjacency List of v, and v to the adjacency List of u
// precondition that their two int arguments must lie in the range 1 to getOrder(G).
void addEdge(Graph G, int u, int v){
	if(G == NULL){
		printf("addEdge Err: graph is NULL.");
		exit(1);
	}
	if((1 > u || u > getOrder(G)) || (1 > v || v > getOrder(G))){
		printf("addEdge Err: vertices of new edge don't lie between the range of vertices of the graph.");
		exit(1);
	}
	
	// u to v and v to u
	
	// v to the adjacency List of u
	addArc(G, u, v);
	// u to the adjacency List of v
	addArc(G, v, u);
	G->size--;
	
}

// inserts a new directed edge from u to v, i.e. v is added to the adjacency List of u (but not u to the adjacency List of v)
// precondition that their two int arguments must lie in the range 1 to getOrder(G).
void addArc(Graph G, int u, int v){
	if(G == NULL){
		printf("addEdge Err: graph is NULL.");
		exit(1);
	}
	if((1 > u || u > getOrder(G)) || (1 > v || v > getOrder(G))){
		printf("addEdge Err: vertices of new edge don't lie between the range of vertices of the graph.");
		exit(1);
	}
	
	// v to the adjacency List of u --> u to v
	List L = G->adjList[u];
	if(length(L) == 0){
		append(L, v);
		G->size++;
		return;
	}
	moveFront(L);
	while(index(L) != -1){
		if(v < get(L)){
			insertBefore(L, v);
			break;
		}
		moveNext(L);
	}
	if(index(L) == -1){
		append(L, v);
	}
	G->size++;
}

// runs the BFS algorithm on the Graph G with source s, setting the color, distance, parent, and source fields of G accordingly
void BFS(Graph G, int s){
	for(int x = 0; x <= getOrder(G); x++){
		G->color[x] = white;
		G->parent[x] = NIL;
		G->dist[x] = INF;
	}
	G->source = s;
	G->color[s] = gray;
	G->dist[s] = 0;
	G->parent[s] = NIL;
	
	List Q = newList();
	append(Q, s);
	while(length(Q) > 0){
		int u = front(Q);
		deleteFront(Q);
		moveFront(G->adjList[u]);
		while(index(G->adjList[u]) != -1){
			int v = get(G->adjList[u]);
			if(G->color[v] == white){
				G->color[v] = gray;
				G->dist[v] = (G->dist[u]) + 1;
				G->parent[v] = u;
				append(Q, v);
			}
			moveNext(G->adjList[u]);
		}
		G->color[u] = black;
	}
	freeList(&Q);
}
	
/*** Other operations ***/
// prints the adjacency list representation of G to the file pointed to by out
void printGraph(FILE* out, Graph G){
	if(G == NULL){
		printf("printGraph Err: graph is NULL.");
		exit(1);
	}
	int i;
	for(i = 1; i <= getOrder(G); i++){
		List k = G->adjList[i];
		moveFront(k);
		fprintf(out, "%d: ", i);
		while(index(k) != -1){
			fprintf(out, "%d ", get(k));
			moveNext(k);
		}
		fprintf(out, "\n");
	}
}










