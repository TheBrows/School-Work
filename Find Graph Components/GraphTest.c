// Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
// GraphTest.c - a test for the Graph ADT

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

int main(int argc, char* argv[]){
	Graph G = newGraph(8);
	Graph H;
	List L = newList();
	
	addArc(G,1,2);
	addArc(G,2,3);
	addArc(G,2,6);
	addArc(G,3,4);
	addArc(G,4,3);
	addArc(G,4,8);
	addArc(G,5,1);
	addArc(G,5,6);
	addArc(G,6,7);
	addArc(G,7,6);
	addArc(G,7,8);
	addArc(G,8,8);
	
	for(int i = 1; i <= 8; i++){
		append(L,i);
	}
	
	printGraph(stdout,G);
	
	DFS(G,L);
	
	H = transpose(G);
	
	printGraph(stdout,H);
	
	DFS(G,L);
	
	printf("\norder: %d",getOrder(G));
	printf("\nsize: %d",getSize(G));
	printf("\nparent 1: %d",getParent(G,1));
	printf("\ndiscover 4: %d",getDiscover(G,4));
	printf("\nfinish 4: %d",getFinish(G,4));
	
	printf("\n");
	
	printList(stdout,L);
	
	freeGraph(&G);
	freeGraph(&H);
	freeList(&L);
	
	return(0);
}