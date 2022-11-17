package com.hibernate.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "movies")
public class Movie {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	    private String movieName;

	    private Integer releaseYear;

	    private String language;

		public Movie() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Movie(long id, String movieName, Integer releaseYear, String language) {
			super();
			this.id = id;
			this.movieName = movieName;
			this.releaseYear = releaseYear;
			this.language = language;
		}
		public Movie( String movieName, Integer releaseYear, String language) {
			super();
			this.movieName = movieName;
			this.releaseYear = releaseYear;
			this.language = language;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getMovieName() {
			return movieName;
		}

		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		public Integer getReleaseYear() {
			return releaseYear;
		}

		public void setReleaseYear(Integer releaseYear) {
			this.releaseYear = releaseYear;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		@Override
		public String toString() {
			return "Movie [id=" + id + ", movieName=" + movieName + ", releaseYear=" + releaseYear + ", language="
					+ language + "]";
		}
		
		

	    // standard constructor, getters, setters
	}
		

