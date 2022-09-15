package RedBoxInventorySystem.Movie;

public class Movie implements Comparable<Movie>
{
    //local variables 
    private String title; 
    private int available; 
    private int rented; 

    //Constructor 
    public Movie(String title, int available, int rented)
    {
        //local variables given values
        this.title = title; 
        this.available = available;
        this.rented = rented; 
    }

    //Setter Methods
    public void setTitle(String newTitle)
    {
        title = newTitle; 
    }

    public void setAvailable(int newAvailable)
    {
        available = newAvailable; 
    }

    public void setRented(int newRented)
    {
        rented = newRented; 
    }

    //Getter Methods 
    public String getTitle()
    {
        return title; 
    }

    public int getAvailable()
    {
        return available; 
    }

    public int getRented()
    {
        return rented; 
    }

    //Compare Method to compare movies 
    @Override
    public int compareTo(Movie movie) 
    {
        //compares the title of the movie and returns an int 
        return getTitle().compareTo(movie.getTitle());
    }

    //Creates a string for the movie object 
    @Override
    public String toString()
    {
        //Create a stringbuilder    
        StringBuilder stringMovie = new StringBuilder();
        //Format the movie using left justification and spaces 
        stringMovie.append(String.format("%-28s", getTitle()));
        stringMovie.append(String.format("%-24s", getAvailable()));
        stringMovie.append(String.format("%-25s", getRented()));
        stringMovie.append("\n");
        //return the string 
        return stringMovie.toString();
    }
}
