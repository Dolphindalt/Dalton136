# A model make file
CC=g++
CFLAGS=-g -Wall -std=c++17
SOURCES=$(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)
EXE=delivering

make: $(SOURCES) $(EXE)

$(EXE): $(OBJECTS)
	@$(CC) $(OBJECTS) -o $@	

.cpp.o:
	@$(CC) -c $(CFLAGS) $< -o $@

clean:
	@rm -rf *.o $(EXE)
