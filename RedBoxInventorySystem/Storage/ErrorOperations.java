package RedBoxInventorySystem.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import RedBoxInventorySystem.Common.ScannerFactory;

public class ErrorOperations 
{
    private final static String FILE_NAME = "error.log";

    //will read the file and and add new errors to the file 
    public static void ErrorFile(String command, String title, int quantity)
    {
        // create the file 
        File file = new File(FILE_NAME);
        //local variables
        Scanner read = ScannerFactory.GetScannerInstance(FILE_NAME);
        ArrayList<String> list = new ArrayList<String>();
        //will read the file and add the lines into the list 
        while(read.hasNextLine())
        {        
            list.add(read.nextLine());
        }
        //will print the list into the file and add a new one 
        try (PrintWriter writeFile = new PrintWriter(file))
        {
          
            while(!list.isEmpty())
            {
                writeFile.write(list.get(0) + "\n");
                list.remove(0);
            }
            writeFile.write("Invalid transaction: "+ command + " " + title + ", " + quantity + "\n");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("error.log file not found");
        }
    }


    //will read a file  and add the error to the file
    public static void ErrorFile(String command, String title)
    {
        // create the file 
        File file = new File(FILE_NAME);
        //local variables
        Scanner read = ScannerFactory.GetScannerInstance(FILE_NAME);
        ArrayList<String> list = new ArrayList<String>();
         //will read the file and add the lines into the list 
        while(read.hasNextLine())
        {        
            list.add(read.nextLine());
        }
        //will print the list into the file and add a new one
        try (PrintWriter writeFile = new PrintWriter(file))
        {
          
            while(!list.isEmpty())
            {
                writeFile.write(list.get(0) + "\n");
                list.remove(0);
            }
            writeFile.write("Invalid transaction: "+ command + " " + title + "\n");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("error.log file not found");
        }
    }
    
}
