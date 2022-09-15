package RedBoxInventorySystem.Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
  ScannerFactory
*/

public abstract class ScannerFactory {

    private static Scanner scannerObject; 

    //Call this method to get a scanner to use 
    public static Scanner GetScannerInstance(String FILE_NAME)
    {
        File inputFile = new File(FILE_NAME);
        try
        {
            scannerObject = new Scanner(inputFile); 
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("The file was not found"); 
        }
        return scannerObject;
    } 

    //At the end of the program close the scanner 
    public static void CloseScannerInstance()
    {
        scannerObject.close();
        scannerObject = null; 
    } 
}