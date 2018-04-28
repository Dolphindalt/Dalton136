/// Name: Dalton Caron
/// Version: 4/25/2018
/// Description: Lab 14, so the declarations are in this header file instead.
#ifndef HOMEWORK_HPP
#define HOMEWORK_HPP

#define BUFF_SIZE 255 // Better pray no one enters an assignment with over 255 characters

typedef struct assignment {
	char *name;
	int month_due;
	int day_due;
	int hours;
	struct assignment *next; // Better [ray no one enter an assignment with over 255 characters
} assignment_t;

/// A warpper function, so we do not hang out in the main method
void beginLoop();

/// Creates an assignment and adds it to the linked list, prompts user for inputs
void createAssignment();

/// Prompts the user for an assignment name and will delete assignments with the given name
void deleteAssignment();

/// Prints out all of the assignments present in the linked list
void printAssignments();

/// Shorthand function to free a single assginment
void freeAssignment(assignment_t *assignment);

/// Inserts an assignment into the linked list
void insert(assignment_t *new_doc);

/// Reads assignments from a file, which is provided by the user
/// Regarding file creation, if the file entered does not exist, it will be created instead
int readText();

/// Writes all assignments to a file provided by the user
void writeText();

assignment_t *list; // the head of the linked list

char file_name[BUFF_SIZE];

#endif
