/// Name: Dalton Caron
/// Version: 11 April, 2018
/// Description: C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good. C is good.
#include "Homework.hpp"

#include <cstdio>
#include <cstdlib>
#include <cstring>

/// No arguments, just the call to beginLoop
int main(int argc, char* argv[])
{
	beginLoop();	
}

void beginLoop()
{
	if(!readText())
		return;

	int input;
	int n = 1;
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
			case 4: n = 0; break;
			default: break;
		}		
	} while(n);

	writeText();

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

	insert(new_doc);
}

void deleteAssignment()
{
	printf("What is the name of the assignment you wish to delete ");
	char *input_string = (char *)malloc(sizeof(char) * BUFF_SIZE);
	if(scanf(" %[^\n]", input_string) != 1)
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
		printf("Due: %i/%i\t%s\t", head->day_due, head->month_due, head->name);
		if(head->hours > 1)
		{
			printf("%i hours\n", head->hours);
		}				
		else
		{
			printf("%i hour\n", head->hours);
		}
		head = head->next;
	}	
}

void freeAssignment(assignment_t *assignment)
{
	free(assignment->name);
	free(assignment);
}

void insert(assignment_t *new_doc)
{
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

int readText()
{
	printf("Please specify a file name: ");
	scanf("%s", file_name);

	FILE *fptr = NULL;
	fptr = fopen(file_name, "a+");

	if(fptr == NULL)
	{
		printf("You must provide a valid file!\n");
		return 0;
	}

	char name[BUFF_SIZE];
	int month_due, day_due, hours;

	while(fscanf(fptr, "%i %i %i %[^\n]", &month_due, &day_due, &hours, name) == 4)
	{
		assignment_t *new_asm = (assignment_t *)malloc(sizeof(assignment_t));
		new_asm->name = (char *)malloc(sizeof(char) * BUFF_SIZE);
		strcpy(new_asm->name, name);
		new_asm->month_due = month_due;
		new_asm->day_due = day_due;
		new_asm->hours = hours;
		insert(new_asm);
	}

	fclose(fptr);
	return 1;
}

void writeText()
{
	FILE *fptr = NULL;
	fptr = fopen(file_name, "w");

	assignment_t *current = list;
	while(current != NULL)
	{
		fprintf(fptr, "%i %i %i %s\n", current->month_due, current->day_due, current->hours, current->name);
		current = current->next;
	}
}