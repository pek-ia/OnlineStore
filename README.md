# OnlineStore

## Year Up Java Week 3 Workshop

This solution is a lot like last week's NeighborhoodLibrary, but with a few improvements.  
It uses the Java Collection classes instead of fixed-length arrays for maintaining 
the shopping cart and inventory.  Using a HashMap for the inventory allows quick searches
based on the SKU.  For handling the menus, text blocks and the enhanced switch statement
make the code somewhat easier on the eyes.

The biggest change is that there is a greater degree of refactoring to move certain
repetitive workflows into separate static functions to improve readability and 
decrease repetition.

Still, this is a pretty old-school program, relying on static variables and methods.  
It would take some effort to scale this solution to support multiple users or 
shopping carts, additional UI screens, or better verification of input.

In the three screens of the CLI user interface, you can see that there is very stylized 
handling of output (heading, lists, menu, prompt for input)
and input (using the enhanced switch).  This hints at the possibility that the user 
interface itself could be refactored to be more general, less repetitive, and 
possibly object-oriented.
