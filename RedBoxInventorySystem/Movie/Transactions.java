package RedBoxInventorySystem.Movie;

import RedBoxInventorySystem.Common.BinarySearchTree;
import RedBoxInventorySystem.Storage.InventoryOperations;

public class Transactions 
{
    //Local variables 
    private BinarySearchTree<Movie> movieTree;
    private InventoryOperations storage;
    private boolean isError;

    public Transactions()
    {
        //Local variables 
        movieTree = new BinarySearchTree<>();
        storage = new InventoryOperations();

        //Calls the method that will read the inventory file to create the tree 
        storage.readFile(movieTree);
    }

    //Will either add a new movie or will update the amount of copies of an existing movie 
    public void Add(String title, int addCopies)
    {
        //Create an object
        Movie newMovie = new Movie(title, addCopies, 0);

        //Will check to see if the movie exist and if not will add the new movie to the tree
        if(movieTree.search(newMovie) == null)
        {
            movieTree.insert(newMovie);
            storage.updateFile(movieTree);
        }
        //Update the existing movie 
        else
        {
            Movie temp = movieTree.search(newMovie);
            temp.setAvailable(temp.getAvailable() + newMovie.getAvailable());
            storage.updateFile(movieTree);
        }
    }

    //Will remove copies of an existing movie or remove the movie under certain conditions 
    public boolean Remove(String title, int removeCopies)
    {
        //Local variables
        isError = false;
        Movie newMovie = new Movie(title, 0, 0);
    
        //Search to check if the movie exists 
        if(movieTree.search(newMovie) != null)
        {
            //Will find the movie and put it in a temp to modify it 
            Movie temp = movieTree.search(newMovie);
            //Will see if it has adequate amount of copies and will remove the amount listed 
            if(temp.getAvailable() > removeCopies)
            {
                temp.setAvailable(temp.getAvailable() - removeCopies);
                //Update file 
                storage.updateFile(movieTree);
            }

            //The copies needed to remove are equal to number of copies
            //This will remove the movie if and only if the amount rented is also zero else will proceed by removing all the movies
            else if(temp.getAvailable() == removeCopies)
            {
                if(temp.getRented() > 0)
                {
                    temp.setAvailable(0);
                }
                else
                {
                    movieTree.remove(temp);
                }
                //Update file 
                storage.updateFile(movieTree);
            }
            else
            {
                //This case will occur if the movie does not exist 
                System.out.println("Trying to remove to non-existent movies");
                isError = true;
            }
        }
        else
        {
            System.out.println("The Movie does not exist"); 
            isError = true; 
        }
        //Will return true the if there exist an error 
        if( isError == true)
        {
            return isError;
        }

        return isError; 

    }

    //Will rent a movie if it exist 
    public boolean Rent(String title)
    {
        //Local variables 
        isError = false;
        Movie newMovie = new Movie(title, 0, 0);

        //Will see if the movie exist 
        if(movieTree.search(newMovie) != null)
        {
            //If the movie exist then will see if there are enough movies to take away
            Movie temp = movieTree.search(newMovie);
            if(temp.getAvailable() > 0)
            {
                temp.setAvailable(temp.getAvailable()-1);
                temp.setRented(temp.getRented()+1);
                storage.updateFile(movieTree);
            }
            else
            {
                //Will show if there are no movies available 
                System.out.println("There are no available movies to rent out ");
                isError = true;
            }
        }
        else
        {
            //Will show if the movie does not exist 
            System.out.println("The Movie does not exist");
            isError = true;
        }

        return isError; 
    }

    //Will return a rented movie 
    public boolean Return(String title)
    {
        //Local variables 
        isError = false;
        Movie newMovie = new Movie(title, 0, 0);
        //Will see if the movie exist 
        if(movieTree.search(newMovie) != null)
        {
            //If the movie exist then it will create a temp 
            Movie temp = movieTree.search(newMovie);
            //Checks to see if there are any movies to return 
            if(temp.getRented() > 0)
            {
                temp.setRented(temp.getRented()-1);
                temp.setAvailable(temp.getAvailable()+1);
                storage.updateFile(movieTree);
            }
            else
            {
                //Returns if there are no movies to return 
                System.out.println("There are no rented movies to return");
                isError = true;
            }
        }
        else
        {
            //Returns if the movie does not exist 
            System.out.println("The Movie does not exist");
            isError = true; 
        }

        return isError; 
    }

    //Will print the tree 
    public void printTree()
    {
       movieTree.inOrderTraverseTree();
    }

    //Write to kisok
    public void kiosk()
    {
        storage.WriteToKiosk(movieTree);
    }

    //Return the movie tree (is for unit test )
    public BinarySearchTree<Movie> getMovieTree()
    {
        return movieTree;
    }

    //Return the size of the tree 
    public int getSize()
    {
        return movieTree.getSize();
    }
}
