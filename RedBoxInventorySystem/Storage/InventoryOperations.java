package RedBoxInventorySystem.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import RedBoxInventorySystem.Common.BinarySearchTree;
import RedBoxInventorySystem.Common.ScannerFactory;
import RedBoxInventorySystem.Movie.Movie;

public class InventoryOperations 
{
    //local variables
    private final static String FILE_NAME = "Inventory.dat"; 
    private final static String KIOSK_FILE_NAME = "redbox_kiosk.txt";
 

    public void readFile(BinarySearchTree<Movie> movieTree)
    {
        //scanner instance
        Scanner readInventory = ScannerFactory.GetScannerInstance(FILE_NAME);
        //local variables 
        Movie newMovie;
        String movieLine;
        String[] movieLineParts;
        String title;
        int available;
        int rented;
        
        //will read the file and inserts it into the newMovie tree
        while(readInventory.hasNextLine())
        {
            movieLine = readInventory.nextLine();
            movieLineParts = movieLine.split(",");
            for(int i = 0 ; i < movieLineParts.length ; i ++ )
            {
                movieLineParts[i] = movieLineParts[i].trim();
            }
            title = movieLineParts[0].replace('"', ' ').trim();
            available = Integer.parseInt(movieLineParts[1]);
            rented = Integer.parseInt(movieLineParts[2]);
            newMovie = new Movie(title,available,rented);
            movieTree.insert(newMovie);
        }
    }
    //will update the file 
    public void updateFile(BinarySearchTree<Movie> movieTree)
    {
        //will create a file 
        File file = new File(FILE_NAME);
        //local variables 
        Movie[] movieArray = new Movie[movieTree.getSize()];
        movieTree.getElements(movieArray);

        try (PrintWriter writeFile = new PrintWriter(file))
        {
            //will go through the array list and print the new file 
            for(int i = 0; i < movieTree.getSize(); i++ )
            {
                writeFile.println("\"" + movieArray[i].getTitle() + "\", " + movieArray[i].getAvailable() + ", " + movieArray[i].getRented());     
            }

        }
         //will catch if the file doesnt open
        catch(FileNotFoundException ex)
        {
            System.out.println("Inventory.dat file not found");
        }  
    }
    //will write the data to the kiosk 
    public void WriteToKiosk(BinarySearchTree<Movie> movieTree)
    {
        //will create a new file 
        File file = new File(KIOSK_FILE_NAME); 
        //local variables 
        Movie[] movieArray = new Movie[movieTree.getSize()];
        movieTree.getElements(movieArray);
        //will print the data to the file 
        try(PrintWriter writeFile = new PrintWriter(file))
        {
            writeFile.write("Title                       Available               Rented\n" +
                            "-----------------------------------------------------------\n" ); 
            for(int i = 0; i < movieTree.getSize(); i++)
            {
                writeFile.print(movieArray[i].toString() );
            }
        }
        //file not found 
        catch(FileNotFoundException ex)
        {
            System.out.println("redbox_kiosk.txt file was not found"); 
        }
    }
}
