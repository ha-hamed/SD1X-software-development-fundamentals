package HW3;

import java.util.ArrayList;

public class Actor implements Comparable<Actor> {

	private String name;
	private ArrayList<Movie> movies;
	
	public String getName() {
		return name;
	}
	
	// constructor
	public Actor(String name) {
		this.name = name;
		this.movies = new ArrayList<Movie>();
	}
	
	// getters and setters
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	// methods
	
	/**
	 * Helper method to get actors rating
	 * @return
	 */
	public double getActorRating() {
		double ratingSum = 0.0;
		for (Movie movie: movies) {
			ratingSum += movie.getRating();
		}
		return ratingSum / movies.size();
	}
	
	@Override
	public int compareTo(Actor actor) {
		double rating = this.getActorRating();
		if (rating > actor.getActorRating())
			return 1;
		else if (rating < actor.getActorRating())
			return -1;
		else
			return 0;
	}
	
	
}
