/**
 * Description: This is program can only be ran on Saturday.
 * Author: Dalton Caron
 * Version: 4/4/2018
 */
#include <cmath>
#include <cstdlib>
#include <cstdio>
#define L(x) atoi(argv[x])
#define A template<typename T> T j(T a,T b,T c,T d) 
#define Z int
#define R return
//The distance function uses generic typing to handle all your bois.
A{R sqrt(pow(a-c,2)+pow(b-d,2));}
// Main function for taking arguments and calling distance.
Z main(Z argc, char *argv[])
{
	if(argc<5){printf("Distance x1 y1 x2 y2\n");R 0;}
	Z a=L(1),b=L(2),c=L(3),d=L(4);
	double r= j(a,b,c,d);
	printf("The distance between (%d, %d) and (%d, %d) is %.1lf\n",a,b,c,d,r);
	R 0;
}
