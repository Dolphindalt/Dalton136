/// Description: This program is for guessing numbers, but more extreme.
/// Author: Dalton Caron
/// Version: 4/4/2018
#include <cstdio>
#include <cstdlib>
#include <time.h>
// The actual program
int main(int argc, char *argv[])
{
	srand(time(NULL));
	int n = rand() % 10 + 1, ga = 3, g;
	
	while(ga-- ^ 0)
	{	
		printf("Make a guess: ");
		scanf("%d", &g);
		if(g > n)
		{
			printf("The guess was too high!\n");
		}
		else if(g < n)
		{
			printf("The guess was too low!\n");
		}
		else
		{
			printf("You got it!\n");
			return 0;
		} 					
	}
	printf("You ran out of guesses!\n");
	return 0;
}
