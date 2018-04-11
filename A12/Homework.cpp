/// Name: Dalton Caron
/// Version: 11 April, 2018
/// Description: C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good.
#include <cstdio>
#include <cstdlib>
#include <cstring>

#define BUFF_SIZE 255 // Better pray no one enters an assignment with over 255 characters

typedef struct assignment {
	char *name;
	int month_due;
	int day_due;
	int hours;
	struct assignment *next;
} assignment_t; // linked structure to also represent the assignment, aliased to assignment_t for style

/// A wrapper function, so we do not hang out in the main method
void beginLoop();

/// Creates an assignment and adds it to the linked list, prompts user for inputs
void createAssignment();

/// Prompts the user for an assignment name and will delete assignments with the given name
void deleteAssignment();

/// Prints all of the assignments present in the linked list
void printAssignments();

/// Shorthand function to free a single assignment
void freeAssignment(assignment_t *assignment);

assignment_t *list; // the head of the linked list

/// No arguments, just the call to beginLoop
int main(int argc, char* argv[])
{
	beginLoop();	
}

void beginLoop()
{
	int input;
	do
	{
		printf("Enter the number for your choice:\n");
		printf("1. Enter a new assignment\n");
		printf("2. Delete an assignment\n");
		printf("3. Print out a list of assignments\n");
		printf("4. Quit\n");

		scanf("%d", &input);

		switch(input)
		{
			case 1: 
				createAssignment();
				break;
			case 2: 
				deleteAssignment();
				break;
			case 3: 
				printAssignments();
				break;
			case 4: return;
			default: break;
		}		
	} while(true);

	assignment_t *head = list;
	while(head != NULL)
	{
		assignment_t *remove = head;
		head = head->next;
		freeAssignment(remove);
	}
}

void createAssignment()
{
	assignment_t *new_doc = (assignment_t *)malloc(sizeof(assignment_t));
	new_doc->name = (char *)malloc(sizeof(char) * BUFF_SIZE);
	printf("What is the name of this assignment? ");

	while(scanf(" %[^\n]", new_doc->name) < 1)
	{
		printf("Please provide a valid input string\n");
	}
	
	printf("Due date information:\n");
	printf("\tWhat month is it due? ");
	while(scanf("%i", &(new_doc->month_due)) != 1)
	{
		printf("Please enter a valid non-negative integer ");	
	}

	printf("\tWhat day is it due? ");
	while(scanf("%i", &(new_doc->day_due)) != 1)
	{
		printf("Please enter a valid non-negative integer ");
	}	
	
	printf("How many hours will it take to complete? ");
	while(scanf("%i", &(new_doc->hours)) != 1)
	{
		printf("Please enter a valid non-negative integer ");
	}	

	if(list == NULL)
	{
		list = new_doc;	
	}
	else
	{
		assignment_t *itr = list;

		if(itr->month_due > new_doc->month_due || (itr->month_due > new_doc->month_due && itr->day_due > new_doc->day_due))
		{
			new_doc->next = itr;
			list = new_doc;
			return;		
		}

		while(itr->next != NULL)
		{
			if(itr->month_due < new_doc->month_due)
			{
				assignment_t *after = itr->next;
				itr->next = new_doc;
				new_doc->next = after;
				return;
			}
			if(itr->month_due == new_doc->month_due && itr->day_due < new_doc->day_due)
			{
				assignment_t *after = itr->next;
				itr->next = new_doc;
				new_doc->next = after;
				return;	
			}
			itr = itr->next;
		}
		itr->next = new_doc;
	}
}

void deleteAssignment()
{
	printf("What is the name of the assignment you wish to delete ");
	char *input_string = (char *)malloc(sizeof(char) * BUFF_SIZE);
	if(scanf("%s", input_string) != 1)
	{
		printf("The input string was invlaid\n");
		return;
	}	
	
	assignment_t *head = list;
	assignment_t *last = NULL;
	while(head != NULL)
	{
		if(strcmp(head->name, input_string) == 0)
		{
			if(last == NULL)
			{
				assignment_t *remove = head;
				list = head->next;
				freeAssignment(remove);				
			}
			else
			{
				last->next = head->next; 
				free(head);
			}
			printf("Deleted assignment\n");
			return;							
		}
		last = head;
		head = head->next;
	}
	printf("Failed to find an assignment with the name %s\n", input_string);	
}

void printAssignments()
{
	assignment_t *head = list;
	while(head != NULL)
	{
		printf("Due: %d/%d\t%s\t", head->day_due, head->month_due, head->name);
		if(head->hours > 1)
		{
			printf("%d hours\n", head->hours);
		}				
		else
		{
			printf("%d hour\n", head->hours);
		}
		head = head->next;
	}	
}

void freeAssignment(assignment_t *assignment)
{
	free(assignment->name);
	free(assignment);
}
