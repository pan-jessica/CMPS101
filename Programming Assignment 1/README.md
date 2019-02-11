Jessica Pan
PA1
CMPS101
October 8, 2018

In this submission, I have included:

List.java
   List is an ADT, written by me, that creates a doubly linked list and stores elements of a input file that is read by Lex.java.
   It consists of many different operations that allows us to manipulate the nodes and elements in the doubly linked list.
   It also provides the List implementation for Lex.java and ListClient.java. 
			   
ListClient.java
	ListClient is a script that tests the List.java interface. It allows us to check for flaws and edge cases in our List.java file.
	This file was not altered since we were told not to by our professor, who provided us with this file. 
	ListClient isn't the best script to run against List.java, as it doesn't check every case possible, so it's best that you create 
	your own test cases in a seperate file and run it with List.java.
	To execute the file with List.java on unix, perform these commands:
			javac List.java ListClient.java
			java ListClient
			
Lex.java
	Lex is a java file that contains the main code to sort an input file's lines of strings into lexicographic order 
	and it returns the lexicographic list of strings in order and in the form of a new text file.
		To execute Lex.java on unix, perform these commands:
			make
			javac Lex.java (if Lex.java compiles)
			./Lex infile1.txt outfile1.txt
			cat outfile1.txt

Makefile
   Makefile compiles the files of this assignment, cleans certain files in the directory and submits them as well. This file makes sure 
   that your code in your files function properly. This was file was provided by our professor and we were allowed to alter it.

README
   README provides a description of each the different files used in this assignment (it's also the file that you're currently reading right now).
   It also provides any commands that are used to run certain files in this assignment.
