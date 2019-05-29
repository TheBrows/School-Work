//Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
//Graph.h - Header for Graph.c

#include"List.h"

#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_

#define INF -1
#define UNDEF -1
#define NIL 0

/*** Constructors-Destructors ***/

typedef struct GraphObj* Graph;

Graph newGraph(int n);

void freeGraph(Graph* pG);


/*** Access functions ***/

int getOrder(Graph G);

int getSize(Graph G);

int getSource(Graph G);

int getParent(Graph G, int u);

int getDist(Graph G, int u);

void getPath(List L, Graph G, int u);

int getDiscover(Graph G, int u);

int getFinish(Graph G, int u);


/*** Manipulation procedures ***/

void makeNull(Graph G);

void addEdge(Graph G, int u, int v);

void addArc(Graph G, int u, int v);

void BFS(Graph G, int s);

void DFS(Graph G, List S);


/*** Other operations ***/

Graph transpose(Graph G);

Graph copyGraph(Graph G);

void printGraph(FILE* out, Graph G);

#endif