/**********************************************************************
 *  readme template                                                   
 *  TSP Heuristics
 **********************************************************************/

Name: Keith Vertanen
Login: vertanen
Precept: P01


Partner's name (if any):
Partner's login:
Partner's precept (must be the same):

Hours to complete assignment (optional):


/**********************************************************************
 *  Explain how you implemented the nearest insertion heuristic.
 **********************************************************************/


/**********************************************************************
 *  Explain how you implemented the smallest insertion heuristic.
 *  It's sufficient to list only the differences between this
 *  heuristic and the nearest insertion heuristic.
 **********************************************************************/



/**********************************************************************
 *  Fill in the distances computed by your heuristics.                
 **********************************************************************/

data file          nearest neighbor     smallest increase      extra credit
-----------------------------------------------------------------------------
tsp10.txt              1566.136              1655.746
tsp100.txt             7389.930              4887.219  
tsp1000.txt           27868.711             17265.628
usa13509.txt          77449.979             45074.777




/**********************************************************************
 *  Estimate the running time (in seconds) of your two heuristics as
 *  a function of N, using an expression of the form: a * N^b seconds.
 *  DESCRIBE HOW YOU DETERMINED YOUR ANSWER. You are encouraged to use
 *  more than 3 data points. You may use TSPTimer.java as described
 *  in the checklist.
 **********************************************************************/
             nearest                 smallest
   N = 10000   1.091  1.082  1.039    1.850  1.863  1.869 
   N = 20000   4.061  4.002  4.021    7.331  7.425  7.442
   N = 40000  18.664 20.097 20.095   31.873 35.373 33.621 
    
nearest:    t = 1.225E-08 * N^2
smallest:   t = 2.101E-08 * N^2

/**********************************************************************
 *  Explain why you used a circular linked list instead of an array.  
 **********************************************************************/


/**********************************************************************
 *  If you did the extra credit, explain your heuristic, and how
 *  you went about implementing it.
 **********************************************************************/



/**********************************************************************
 *  If you did the extra credit, give instructions here for 
 *  running it.
 **********************************************************************/



/**********************************************************************
 *  List whatever help (if any) that you received, and the names of  
 *  any students with whom you collaborated.                          
 **********************************************************************/






/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
