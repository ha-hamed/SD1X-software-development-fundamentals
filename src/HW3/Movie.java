package HW3;

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
	
	private String name;
	private ArrayList<Actor> actors;
	private double rating;
		
	// constructor
	public Movie(String name) {
		this.name = name;
		this.actors = new ArrayList<Actor>();
		this.rating = 0.0;
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Actor> getActors() {
		return actors;
	}
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(Movie movie) {
		if (this.rating > movie.getRating())
			return 1;
		else if (this.rating < movie.getRating())
			return -1;
		else 
			return 0;
	} 

}
