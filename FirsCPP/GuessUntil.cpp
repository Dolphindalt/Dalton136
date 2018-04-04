/// Description: Guess some random numbers.
/// Author: Dalton Caron
/// Version: 4/4/2018
#include <cstdio>
#include <cstdlib>
#include <time.h>
/// Guess numbers and shit
int main(int argc, char *argv[])
{
	srand(time(NULL));
	int number = rand() % 100 + 1;

	int guess = -1;
	do
	{
		printf("Enter a number between 1 and 100: ");
		scanf("%d", &guess);
		if(guess > number)
		{
			printf("The guess was too high!\n");
		}
		else if(guess < number)
		{
			printf("The guess was too low!\n");
		}
	} while(guess ^ number);
	printf("You got it!\n");	
}
