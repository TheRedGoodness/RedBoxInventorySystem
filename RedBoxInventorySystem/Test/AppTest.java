package RedBoxInventorySystem.Test;

import RedBoxInventorySystem.Movie.Movie;
import RedBoxInventorySystem.Movie.Transactions;

public class AppTest 
{

   //This is the unit test to check the ADD METHOD  
   public boolean AddTitle()
   {
      //Creates a new instance 
      Transactions newTran = new Transactions();

      /* ===========================================================*/
      /*                        ADD Test                            */
      /* ===========================================================*/ 
      //Will add Test3 to the Inventory
      newTran.Add("Test3", 10);
      
      //Does the test 
      Movie test = new Movie("Test3", 10, 0);
      boolean resultTest = true;
      boolean result = newTran.getMovieTree().search(test).getTitle().equals("Test3"); 
      boolean expected = true;

      //Validate the test 
      if(expected != result)
      {
         System.out.println("Movie was not added");
         resultTest = false;
      }

      /* ===========================================================*/
      /*                        Transaction Test2                   */
      /* ===========================================================*/ 
      //Does the test 
      newTran.Add("Test3", 10);
      result = newTran.getMovieTree().search(test).getAvailable() == 20; 
      expected = true;

      //Validate the test 
      if(expected != result)
      {
         System.out.println("Movie did not get more copies");
         resultTest = false;
      } 

      return resultTest;
   }

   //This is the unit test to check the remove method  
   public boolean RemoveTitle()
   {
      //Creates a new instance 
      Transactions newTran = new Transactions();

      /* ===========================================================*/
      /*                        Transaction Test                    */
      /* ===========================================================*/ 
      //Does the test 
      boolean result = newTran.Remove("Test", 1);
      boolean test = true;
      boolean expected = false;

      //Validate the test  
      if(expected != result)
      {
         System.out.println("The movie copy was not removed");
         test = false;
      }
      
      /* ===========================================================*/
      /*                        Transaction Test2                   */
      /* ===========================================================*/ 
      //Does the test 
      result = newTran.Remove("Test", 100);
      expected = true;

      //Validate the test 
      if(expected != result)
      {
        System.out.println("The movie copy was not stopped");
        test = false;
      }

      return test;

   }
   
   
   //This is the unit test to check the RENT METHOD  
   public boolean RentTitle()
   {
      //Creates a new instance
      Transactions newTran = new Transactions();

      /* ===========================================================*/
      /*                        Rent Test                           */
      /* ===========================================================*/ 
      //Does the test 
      boolean test = true;
      boolean expected = false;
      boolean result = newTran.Rent("Test");

      //Validate the test
      if(expected != result)
      {
         System.out.println("The movie was not rented");
         test = false;
      }

      /* ===========================================================*/
      /*                        Rent Test2                           */
      /* ===========================================================*/ 
      //Does the test 
      expected = true;
      result = newTran.Rent("Test2");
     
      //Validate the test
      if(expected != result)
      {
         System.out.println("The movie was rented when none is available");
         test = false;
      }
      return test;

   }
   
   
   //This is the unit test to check the RETURN METHOD  
   public boolean ReturnTitle()
   {

      //Creating a new instance 
      Transactions newTran = new Transactions();
      
      /* ===========================================================*/
      /*                        Return Test                         */
      /* ===========================================================*/  
      //Does the test 
      boolean test = true;
      boolean expected = false;
      boolean result = newTran.Return("Test");
      
      //Validate the test 
      if(expected != result)
      {
         System.out.println("The movie was not returned");
         test = false;
      }

      /* ===========================================================*/
      /*                        Return Test 2                       */
      /* ===========================================================*/  
      //Does the test 
      expected = true;
      result = newTran.Return("Test2");

      //Validate the test 
      if(expected != result)
      {
         System.out.println("The movie was returned");
         test = false;
      }

      return test;
   }
}
