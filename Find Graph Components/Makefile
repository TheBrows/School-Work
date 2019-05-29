#Paige Riola - priola - 101 Tantalo - PA5 - 6/08/2018
#Makefile for FindComponents

SOURCES        = README Makefile $(BASE_SOURCES) $(HEADERS) FindComponents.c GraphTest.c
ASSIGNMENT     = pa5
CRUZID         = priola
SUBMIT         = submit cmps101-pt.s18 $(ASSIGNMENT)

BASE_SOURCES   = Graph.c List.c
BASE_OBJECTS   = Graph.o List.o
HEADERS        = Graph.h List.h
COMPILE        = gcc -c -g -std=c99 -Wall
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full  --track-origins=yes

FindComponents : FindComponents.o $(BASE_OBJECTS)
	$(LINK) FindComponents FindComponents.o $(BASE_OBJECTS)

GraphTest : GraphTest.o $(BASE_OBJECTS)
	$(LINK) GraphTest GraphTest.o $(BASE_OBJECTS)

FindComponents.o : FindComponents.c $(HEADERS)
	$(COMPILE) FindComponents.c

GraphTest.o : GraphTest.c $(HEADERS)
	$(COMPILE) GraphTest.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) FindComponents GraphTest FindComponents.o GraphTest.o $(BASE_OBJECTS)

checkFind : FindComponents
	$(MEMCHECK) FindComponents in3 out3

checkClient : GraphTest
	$(MEMCHECK) GraphTest
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

checkSubmit:
	ls /afs/cats.ucsc.edu/class/cmps101-pt.s18/$(ASSIGNMENT)/$(CRUZID)
