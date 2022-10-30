package com.highradius.cv_training.Sakila_film.dto;

public class FilmDto {
	
	public String title;
	public String description;
	public String release_year;
	public String rating;
    public String special_features;
    public String language_id;
    public String director;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecial_features() {
		return special_features;
	}
	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
    
    @Override
    public String toString() {
    	return "FilmDto [title= " +title+ ",description= " +description+ " ,release_year= "+release_year+",rating= "+rating+""
    			+ ",special_features = "+special_features+",language_id= "+language_id+",director="+director+"]";
    }

}
