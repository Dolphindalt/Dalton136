CC=javac
SRCS=$(wildcard *.java)

all:
	$(CC) $(SRCS)

clean:
	rm -f *.class