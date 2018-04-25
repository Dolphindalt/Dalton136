/// Name: Dalton Caron
/// Version: 4/25/2018
/// Description: Lab 14, so the declarations are in this header file instead.
#ifndef HOMEWORK_HPP
#define HOMEWORK_HPP

#include <cstdint>

typedef struct assignment {
	char *name;
	int16_t month_due;
	int16_t day_due;
	int16_t hours;
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

assignment_t *list; // the head of the linked list

#endif
