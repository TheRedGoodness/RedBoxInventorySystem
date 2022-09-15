package RedBoxInventorySystem.Storage;

import java.util.Scanner;

import RedBoxInventorySystem.Common.ScannerFactory;
import RedBoxInventorySystem.Movie.Transactions;

public class TransactionOperation 
{
     //Local variables
    private final static String FILE_NAME = "transaction.log";

    public static void readFile()
    {

        //Transaction instance 
        Transactions newTran = new Transactions();
        newTran.printTree();

        //Scanner instance
        Scanner readTransactions = ScannerFactory.GetScannerInstance(FILE_NAME);

        //Local variables
        String[] transLineParts;
        String title;
        String transCommand; 
        int transQuantity; 
        boolean isError;
        
        //Will read the file and see what command it is and check to see if it is formatted correctly
        while(readTransactions.hasNextLine())
        {
            //Will split the next command line into their respective parts 
            transCommand = readTransactions.next(); //will get the commmand 
            transLineParts = readTransactions.nextLine().split(", "); //will split the command variables into seperate parts

            //Will send the parts into an array for easier evalutaion
            for(int i = 0 ; i < transLineParts.length ; i ++ )
            {
                transLineParts[i] = transLineParts[i].trim();
            }
            title = transLineParts[0].replace('"', ' ').trim();

            /*Will evaluate the command and its parts and execute the correct command */

            //Add command 
            if(transCommand.equalsIgnoreCase("add"))
            {
                transQuantity = Integer.parseInt(transLineParts[1]);
                newTran.Add(title, transQuantity);
                
            }
            //Remove command 
            if(transCommand.equalsIgnoreCase("remove"))
            {
                transQuantity = Integer.parseInt(transLineParts[1]); 
                isError = newTran.Remove(title, transQuantity);

                if(isError == true)
                {
                    ErrorOperations.ErrorFile(transCommand, title, transQuantity);
                }
            }
            //Rent command 
            if(transCommand.equalsIgnoreCase("rent"))
            {
                isError = newTran.Rent(title);

                if(isError == true)
                {
                    ErrorOperations.ErrorFile(transCommand, title);
                }
            }
            //Return command 
            if(transCommand.equalsIgnoreCase("return"))
            {
                isError = newTran.Return(title);

                if(isError == true)
                {
                    ErrorOperations.ErrorFile(transCommand, title);
                }
            }
        }
       newTran.kiosk();
    }
    
}
