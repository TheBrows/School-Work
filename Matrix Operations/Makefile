# Paige Riola - priola - 101 Tantalo - PA3 - 5/14/18
# The Makefile for Sparse

MAINCLASS  = Sparse
JAVAC      = javac 
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
CRUZID     = priola
ASSIGNMENT = pa3
SUBMIT     = submit cmps101-pt.s18 $(ASSIGNMENT)

all: $(JARFILE)
	
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	ls /afs/cats.ucsc.edu/class/cmps101-pt.s18/$(ASSIGNMENT)/$(CRUZID)
