#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define LEN 225

/*
 * Jessica Pan
 * CruzID: jeypan
 * PA2
 * CMPS101
 * 10/14/18
 *
 * Lex.c
 * Client program using List.h and List.c
 *
*/

int main (int argc, char* argv[]){
	FILE *in, *out;
	char str[LEN];
	char** token;
	
	if (argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		printf("Must need 2 arguements\n");
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
	
	//counting number of lines in inputfile
	int numberofLines = 0;
	while ((fgets(str, LEN, in)) != NULL){		
		numberofLines++;	
	}
	
	rewind(in);	//because at the end of in with fgets, move pointer back to 
	
	//double pointer array to store input file
	int n = 0;
	token = malloc(numberofLines*sizeof(char*)); 
	while(n < numberofLines){
		token[n] = malloc(LEN*sizeof(char));
		//placing values of inputfile into token array
		if ((fgets(str, LEN, in)) != NULL){
			//strncpy(token[n], str, sizeof(LEN)-1); //doesn't work properly
			strcpy(token[n], str);
			//printf("before the lexi %s", token[n]);
		}
		n++;
	}

	fclose(in);
	
	//forming Lexicographic order
	List A = newList();
	append(A,0);
	
	for (n = 1; n < numberofLines; n++){
		moveFront(A);
		while (index(A) >= 0){
			if (strcmp(token[n], token[get(A)]) < 0){
				if (index(A) == 0){
					prepend(A,n);
					break;	//breaking out of while loop and moves onto next value
				}
				insertBefore(A,n);
				break;	//breaking out of while loop and moves onto next value
			}
			moveNext(A);	//compare next element
		}
		if (index(A) == -1){	//cursor becomes NULL and index becomes undefined so just add element onto the end of List
			append(A,n);
		}		
	}
	
	moveFront(A);
	for (n = 0; n < numberofLines; n++){
		if (index(A) >= 0){
			//printf("%s", token[get(A)]);
			fputs(token[get(A)],out);
		}
		moveNext(A);
	}
	clear(A);
	freeList(&A);	//all of List A is free
	
	n = 0;
	while(n < numberofLines){
		//free each pointer array in token
		free(token[n]);
		n++;
	}
	free(token);	//all of double pointer token array is free
	
	
	// closing files
	fclose(out);
		
	return 0;
	
}

