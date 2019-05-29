//Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
//FindComponents.c - Reads in data for a Graph from a file, gives data on Graph based on DFS

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Graph.h"
#include"List.h"

#define MAX_LEN 160

#define INF -1
#define UNDEF -1
#define NIL 0

int main(int argc, char * argv[]){
	FILE *in, *out;
	char line[MAX_LEN];
	char* token;
	int order, u, v, components, i, parent;
	Graph G, T;
	List L;
	L = newList();
	
	if(argc != 3){
		printf("Usage: Lex <input file> <output file>\n"); //error message
		exit(1); //exit
	}
	
	// open files for reading and writing 
	
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	
	if( in==NULL ){
		printf("Unable to open file %s for reading\n", argv[1]);
		exit(1);
	}
	
	if( out==NULL ){
		printf("Unable to open file %s for writing\n", argv[2]);
		exit(1);
	}
	
	//read each line of input file
	
	//first, read first integer, the order
	fgets(line, MAX_LEN, in);
	order = atoi(line);
	
	G = newGraph(order);
	
	fprintf(out,"Adjacency list representation of G:\n");
	
	//then, read all adjacent vertices and add them to the Graph
	while(fgets(line, MAX_LEN, in) != NULL){
		token = strtok(line, " \n");
		
		u = atoi(token);
		token = strtok (NULL, " \n");
		v = atoi(token);
		
		if(u == 0 && v == 0){
			printGraph(out,G);
			break;
		}
		else{
			addArc(G,u,v);
		}
	}
	
	for(int i = 1; i <= order; i++){
		append(L,i);
	}
	
	//run DFS on G, then on G transpose
	DFS(G,L);
	
	T = transpose(G);
	
	DFS(T,L);
	
	//check each L for a parent, if none, start of a strong component
	
	moveFront(L);
	components = 0;
	
	while(index(L) != -1){
		if(getParent(T,get(L)) == NIL){
			components++;
		}
		moveNext(L);
	}
	
	fprintf(out,"\n\nG contains %d strongly connected components:", components);
	
	moveBack(L);
	i = 1;
	
	//increment backwards thru list
	//when a parentless vertex is found, print vertex, print forward 
	//until another parentless vertex is reached or list index is null
	
	while(index(L) != -1){
		if(getParent(T,get(L)) == NIL){
			fprintf(out,"\n");
			parent = get(L);
			fprintf(out,"Component %d: ", i);
			fprintf(out,"%d",get(L));
			moveNext(L);
			while(index(L) != -1 && getParent(T,get(L)) != NIL){
				fprintf(out," %d",get(L));
				moveNext(L);
			}
			if(index(L) == -1){
				moveBack(L);
			}
			while(get(L) != parent){
				movePrev(L);
			}
			i++;
		}
		movePrev(L);
	}
	
	freeGraph(&G);
	freeGraph(&T);
	freeList(&L);
	
	fclose(in);
	fclose(out);
	
	return(0);
}