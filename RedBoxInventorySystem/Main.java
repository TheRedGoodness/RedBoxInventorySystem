//Paul Montano 
package RedBoxInventorySystem;

import RedBoxInventorySystem.Storage.TransactionOperation;
import RedBoxInventorySystem.Test.AppTest;


public class Main
{
    public static void main(String[] args) 
    {
        /*This will run the main operations*/
       TransactionOperation.readFile();

       /*This will run the Unit Test and test the different cases*/
        AppTest test = new AppTest();
        //test.AddTitle();
        //test.RemoveTitle();
        //test.RentTitle();
        //test.ReturnTitle();
    }

}