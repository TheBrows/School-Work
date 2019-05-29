//Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
//Graph.c - a Graph ADT, consists of Lists based on number of vertices in the graph

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>
#include"Graph.h"

#define INF -1
#define UNDEF -1
#define NIL 0

/*** Constructors-Destructors ***/

typedef struct GraphObj{
	int* color; //0 = white, 1 = grey, 2 = black
	int* parent; //parents of each vertex, to be filled in upon calling BFS
	int* distance; //distance from source to vertex, to be filled in upon calling BFS
	int* discover;
	int* finish;
	List* adjacency; //Lists of vertices adjacent to each vertex
	int order; //number of vertices
	int size; //number of edges
	int source; //most recently used source from BFS
	int time;
} GraphObj;

//Function newGraph() returns a Graph pointing to a newly created GraphObj representing a graph having
//n vertices and no edges
Graph newGraph(int n){
	Graph G;
	G = malloc(sizeof(GraphObj));
	G->order = n;
	G->size = 0;
	G->source = 0;
	G->time = 0;
	G->parent = malloc(sizeof(int) * (n+1));
	for(int i = 1; i < n+1; i++){
		G->parent[i] = NIL;
	}
	G->distance = malloc(sizeof(int) * (n+1));
	for(int i = 1; i < n+1; i++){
		G->distance[i] = INF;
	}
	G->discover = malloc(sizeof(int) * (n+1));
	for(int i = 1; i < n+1; i++){
		G->discover[i] = UNDEF;
	}
	G->finish = malloc(sizeof(int) * (n+1));
	for(int i = 1; i < n+1; i++){
		G->finish[i] = UNDEF;
	}
	G->color = malloc(sizeof(int) * (n+1));
	for(int i = 1; i < n+1; i++){
		G->color[i] = NIL;
	}
	G->adjacency = malloc(sizeof(List) * (n+1)); 
	for(int i = 1; i < n+1; i++){
		G->adjacency[i] = newList();
	}
	//stays blank until addEdge is called
	return(G);
}

//freeGraph() frees all dynamic memory associated with the Graph
//*pG, then sets the handle *pG to NULL
void freeGraph(Graph* pG){
	if(pG!=NULL && *pG!=NULL) { 
		for(int i = 1; i <= getOrder(*pG); i++) { 
			List L = (*pG)->adjacency[i];
			freeList(&L);
		}
		free((*pG)->adjacency);
		free((*pG)->parent);
		free((*pG)->distance);
		free((*pG)->color);
		free((*pG)->discover);
		free((*pG)->finish);
		free(*pG);
		*pG = NULL;
	}
}

/*** Access functions ***/

int getOrder(Graph G){
	if(G == NULL){
		printf("Graph Error: calling getOrder() on NULL Graph reference\n");
		exit(1);
	}
	return G->order;
}

int getSize(Graph G){
	if(G == NULL){
		printf("Graph Error: calling getSize() on NULL Graph reference\n");
		exit(1);
	}
	return G->size;
}

//getSource() returns the source vertex most recently used in function BFS(), or NIL if
//BFS() has not yet been called
int getSource(Graph G){
	if(G == NULL){
		printf("Graph Error: calling getSource() on NULL Graph reference\n");
		exit(1);
	}
	return G->source; //returns most recent source vertex or NIL
}

//getParent() will return the parent of vertex u in the BreadthFirst
//tree created by BFS(), or NIL if BFS() has not yet been called
//Precondition: 1 <= u <= getOrder(G)
int getParent(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: calling getParent() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getParent() on NULL vertex\n");
		exit(1);
	}
	
	return G->parent[u]; //returns parent vertex or NIL
}

//getDist() returns the distance from the most recent BFS source to vertex u,
//or INF if BFS() has not yet been called
//Precondition: 1 <= u <= getOrder(G)
int getDist(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: calling getDist() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getDist() on NULL vertex\n");
		exit(1);
	}
	
	return G->distance[u]; //returns distance or INF
}

//getPath() appends to the List L the vertices of a shortest path in G from source to u, or appends to L the
//value NIL if no such path exists
//Precondition: getSource(G)!=NIL, 1 <= u <= getOrder(G)
void getPath(List L, Graph G, int u){
	if(G == NULL){
		printf("Graph Error: calling getPath() on NULL Graph reference\n");
		exit(1);
	}
	if(getSource(G) == NIL){
		printf("Graph Error: calling getPath() before BFS has been called\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getOrder() on NULL vertex\n");
		exit(1);
	}
	
	if(G->source == u){
		append(L,G->source);
	}
	else if(getParent(G,u) == NIL){
		append(L,NIL);
	}
	else{
		getPath(L,G,getParent(G,u));
		append(L,u);
	}
}

int getDiscover(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: calling getDiscover() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getOrder() on NULL vertex\n");
		exit(1);
	}
	
	return G->discover[u];
}

int getFinish(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: calling getFinish() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getOrder() on NULL vertex\n");
		exit(1);
	}
	
	return G->finish[u];
}


/*** Manipulation procedures ***/

//makeNull() restores Graph to no-edge state
void makeNull(Graph G){
	if(G == NULL){
		printf("Graph Error: calling makeNULL() on NULL Graph reference\n");
		exit(1);
	}
	
	for(int i = 1; i <= getOrder(G); i++) { 
		clear(G->adjacency[i]);
	}
	
	G->size = 0;
}

//addEdge() inserts a new edge joining u to v, i.e. u is added to the 
//adjacency List of v, and v to the adjacency List of u. 
//Your program is required to maintain these lists in sorted order by increasing labels.
//Precondition: 1 <= u <= getOrder(G), 1 <= v <= getOrder(G)
void addEdge(Graph G, int u, int v){
	if(G == NULL){
		printf("Graph Error: calling addEdge() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getOrder() on NULL vertex u\n");
		exit(1);
	}
	if(v > getOrder(G) || v < 1){
		printf("Graph Error: calling getOrder() on NULL vertex v\n");
		exit(1);
	}
	
	//looking at adjacency[u]
	moveFront(G->adjacency[u]);
	while(index(G->adjacency[u]) != -1){
		if(get(G->adjacency[u]) == v){
			break;
		}
		else if(get(G->adjacency[u]) < v){
			moveNext(G->adjacency[u]);
		}
		else{
			insertBefore(G->adjacency[u],v);
			break;
		}
	}
	
	if(index(G->adjacency[u]) == -1){
		append(G->adjacency[u],v);
	}
	
	//looking at adjacency[v]
	moveFront(G->adjacency[v]);
	while(index(G->adjacency[v]) != -1){
		if(get(G->adjacency[v]) == u){
			break;
		}
		else if(get(G->adjacency[v]) < u){
			moveNext(G->adjacency[v]);
		}
		else{
			insertBefore(G->adjacency[v],u);
			break;
		}
	}
	
	if(index(G->adjacency[v]) == -1){
		append(G->adjacency[v],u);
	}
	
	G->size++;
}

//addArc() inserts a new directed edge from u to v, i.e. v is added to the 
//adjacency List of u (but not u to the adjacency List of v)
//Your program is required to maintain these lists in sorted order by increasing labels.
//Precondition: 1 <= u <= getOrder(G), 1 <= v <= getOrder(G)
void addArc(Graph G, int u, int v){
	if(G == NULL){
		printf("Graph Error: calling addArc() on NULL Graph reference\n");
		exit(1);
	}
	if(u > getOrder(G) || u < 1){
		printf("Graph Error: calling getOrder() on NULL vertex u\n");
		exit(1);
	}
	if(v > getOrder(G) || v < 1){
		printf("Graph Error: calling getOrder() on NULL vertex v\n");
		exit(1);
	}
	
	//looking at adjacency[u]
	moveFront(G->adjacency[u]);
	while(index(G->adjacency[u]) != -1){
		if(get(G->adjacency[u]) == v){
			break;
		}
		else if(get(G->adjacency[u]) < v){
			moveNext(G->adjacency[u]);
		}
		else{
			insertBefore(G->adjacency[u],v);
			break;
		}
	}
	
	if(index(G->adjacency[u]) == -1){
		append(G->adjacency[u],v);
	}
	
	G->size++;
}

//BFS() runs the BFS algorithm on the Graph G with source s, 
//setting the color, distance, parent, and source fields of G accordingly
void BFS(Graph G, int s){
	if(G == NULL){
		printf("Graph Error: calling BFS() on NULL Graph reference\n");
		exit(1);
	}
	if(s > getOrder(G) || s < 1){
		printf("Graph Error: calling getOrder() on NULL vertex s\n");
		exit(1);
	}
	
	for(int i = 1; i <= getOrder(G); i++){
		G->color[i] = 0;
		G->distance[i] = INF;
		G->parent[i] = NIL;
	}
	
	G->source = s;
	
	G->color[s] = 1;
	G->distance[s] = NIL;
	G->parent[s] = NIL;
	
	List Q = newList();
	append(Q,s);
	
	while(length(Q)!=0){
		moveFront(Q);
		int x = get(Q);
		deleteFront(Q);
		moveFront(G->adjacency[x]);
		while(index(G->adjacency[x]) != -1){
			int y = get(G->adjacency[x]);
			if(G->color[y] == 0){
				G->color[y] = 1;
				G->distance[y] = G->distance[x] + 1;
				G->parent[y] = x;
				append(Q,y);
			}
			moveNext(G->adjacency[x]);
		}
		G->color[x] = 2;
	}
	
	freeList(&Q);
}

void Visit(Graph G, List S, int x){
	G->color[x] = 1;
	G->time++;
	G->discover[x] = G->time;
	moveFront(G->adjacency[x]);
	while(index(G->adjacency[x]) != -1){
		int y = get(G->adjacency[x]);
		if(G->color[y] == 0){
			G->parent[y] = x;
			Visit(G,S,y);
		}
		moveNext(G->adjacency[x]);
	}
	G->color[x] = 2;
	prepend(S,x);
	G->time++;
	G->finish[x] = G->time;
}

void DFS(Graph G, List S){
	if(G == NULL){
		printf("Graph Error: calling DFS() on NULL Graph reference\n");
		exit(1);
	}
	
	for(int i = 1; i <= getOrder(G); i++){
		G->color[i] = 0;
		G->parent[i] = NIL;
		G->discover[i] = UNDEF;
		G->finish[i] = UNDEF;
	}
	
	G->time = 0;
	
	List L = copyList(S);
	
	clear(S);
	
	//put cursor on n of control list
	//call front and delete front each time
	//when cursor is null, aka n is used, then finished
	
	while(length(L) > 0){
		int frontt = front(L);
		deleteFront(L);
		if(G->color[frontt] == 0){
			Visit(G,S,frontt);
		}
	}
	
	freeList(&L);
}

/*** Other operations ***/

//printGraph() prints the adjacency list
//representation of G to the file pointed to by out
void printGraph(FILE* out, Graph G){
	if(G == NULL){
		printf("Graph Error: calling printGraph() on NULL Graph reference\n");
		exit(1);
	}
	
	for(int i = 1; i <= getOrder(G); i++){
		fprintf(out,"%d: ",i);
		printList(out,G->adjacency[i]);
		if(i != getOrder(G)){
			fprintf(out,"\n");
		}
	}
}

Graph transpose(Graph G){
	if(G == NULL){
		printf("Graph Error: calling transpose() on NULL Graph reference\n");
		exit(1);
	}
	
	Graph H;
	H = malloc(sizeof(GraphObj));
	H->order = G->order;
	H->size = G->size;
	H->source = G->source;
	H->time = G->time;
	H->parent = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->parent[i] = G->parent[i];
	}
	H->distance = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->distance[i] = G->distance[i];
	}
	H->discover = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->discover[i] = G->discover[i];
	}
	H->finish = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->finish[i] = G->finish[i];
	}
	H->color = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->color[i] = G->color[i];
	}
	
	H->adjacency = malloc(sizeof(List) * (G->order+1)); 
	for(int i = 1; i < G->order+1; i++){
		H->adjacency[i] = newList();
	}
	
	//reverse all arc directions of G onto H
	for(int i = 1; i < G->order+1; i++){
		moveFront(G->adjacency[i]);
		while(index(G->adjacency[i]) != -1){
			addArc(H,get(G->adjacency[i]),i);
			moveNext(G->adjacency[i]);
		}
	}
	return(H);
}

Graph copyGraph(Graph G){
	if(G == NULL){
		printf("Graph Error: calling copyGraph() on NULL Graph reference\n");
		exit(1);
	}
	
	Graph H;
	H = malloc(sizeof(GraphObj));
	H->order = G->order;
	H->size = G->size;
	H->source = G->source;
	H->time = G->time;
	H->parent = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->parent[i] = G->parent[i];
	}
	H->distance = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->distance[i] = G->distance[i];
	}
	H->discover = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->discover[i] = G->discover[i];
	}
	H->finish = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->finish[i] = G->finish[i];
	}
	H->color = malloc(sizeof(int) * (G->order+1));
	for(int i = 1; i < G->order+1; i++){
		H->color[i] = G->color[i];
	}
	H->adjacency = malloc(sizeof(List) * (G->order+1)); 
	for(int i = 1; i < G->order+1; i++){
		H->adjacency[i] = newList();
		H->adjacency[i] = G->adjacency[i];
	}
	return(H);
}