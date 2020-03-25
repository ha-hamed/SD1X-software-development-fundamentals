package HW3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieDatabase {

	private ArrayList<Movie> movieList;
	private ArrayList<Actor> actorList;

	/**
	 * A constructor that just creates a new movieList and a new actorList.
	 * At the time of construction, both of these lists will be empty
	 */
	// constructor
	public MovieDatabase() {
		this.movieList = new ArrayList<>();
		this.actorList = new ArrayList<>();
	}
	
	// getters and setters
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	// methods 
	/**
	 * This method takes in the name of a movie that is not currently in the database along with a list of 
	 * actors for that movie.
	 * If the movie is already in the database, is ignored (this specification is an oversimplication).
	 * If the movie is a new movie, a movie object is created and added to the movieList.
	 * If any of the actors happen to be new, they will be added to the actorList.
	 * @param name
	 * @param actors
	 */
	public void addMovie(String name, String[] actors) {
		
		Movie newMovie = new Movie(name);
		
		if (!movieList.contains(newMovie)) {
			movieList.add(newMovie);
			for (String actor : actors) {
				Actor actorObj = new Actor(actor);
				if (actorList.contains(actorObj))
					actorObj = actorList.get(actorList.indexOf(actorObj));
				else
					actorList.add(actorObj);
				
				newMovie.getActors().add(actorObj);
				actorObj.getMovies().add(newMovie);
			}
		}
	}
	
	/**
	 * Add a rating for this movie. Assume that the name argument will definitely be a name that 
	 * is currently in the database
	 * @param name
	 * @param rating
	 */
	public void addRating(String name, double rating) {
		if (movieList.indexOf(new Movie(name)) != -1) 
			movieList.get(movieList.indexOf(new Movie(name))).setRating(rating);
	}
	
	/**
	 * Overwrite the current rating of a movie with this new rating. Again, 
	 * assume that the name argument will definitely be a name that is currently in the database.
	 * @param name
	 * @param newRating
	 */
	public void updateRating(String name, double newRating) {
		movieList.get(movieList.indexOf(new Movie(name))).setRating(newRating);
	}
	
	/**
	 * Returns the name of the actor that has the best average rating for their movies.
	 * in the case of a tie, return any one of the best actors
	 * @return
	 */
	public String getBestActor() {
		// use collections to sort to get the best actor by index - complexity O(1)
		Collections.sort(actorList);
		return actorList.get(actorList.size() -1).getName();
	}
	
	/**
	 * Returns the name of the movie with the highest rating.
	 * In the case of a tie, return any one of the best movies.
	 * @return
	 */
	public String getBestMovie() {
		Collections.sort(movieList);
		return movieList.get(movieList.size() -1).getName();
	}
	
	/**
	 * Main method
	 * 1. Create a new instance of a movieDatabase
	 * 2. Add all the movies in the file movies.txt
	 * 3. Go through the ratings of the movies in the file ratings.txt and add the ratings for the movies.
	 * 4. Now call the methods that you created and print out the name of the best actor and 
	 * the name of the highest movie.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// create a new instance of a movieDatabase
		MovieDatabase movieDatabase = new MovieDatabase();
		
		Map<String, List<String>> mapMovieToActors = new HashMap<>();
		
		// add all the movies in the file movies.txt
		try {
			Scanner scanner = new Scanner(new File("src/HW3/movies.txt"));
			while(scanner.hasNextLine()) {
	        	String[] movies = scanner.nextLine().split(", ");
				for (int i = 1; i < movies.length; i++) {
					if (!mapMovieToActors.containsKey(movies[i])) {
						mapMovieToActors.put(movies[i], new ArrayList<String>());
					}
					mapMovieToActors.get(movies[i]).add(movies[0]);
				}
			}
			scanner.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (String movie : mapMovieToActors.keySet()) {
        	List<String> actors = mapMovieToActors.get(movie);
        	movieDatabase.addMovie(movie, actors.toArray(new String[actors.size()]));
		}
		
		// go through the ratings of the movies in the file ratings.txt and add the ratings for the movies
		
		try {
			Scanner scanner = new Scanner(new File("src/HW3/ratings.txt"));
	        scanner.nextLine(); // skip header i.e. movie_name	critic_score
			while(scanner.hasNextLine()) {
				String[] ratings = scanner.nextLine().split("\t");
				movieDatabase.addRating(ratings[0], Double.parseDouble(ratings[1]));
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// print best actor and name of the highest movie
		System.out.println("Best actor: " + movieDatabase.getBestActor());
		System.out.println("Highest rated movie: " + movieDatabase.getBestMovie());
		
		System.exit(0);
		
	}
	
}
