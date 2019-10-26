package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	private Integer id;
	private String title;
	private String description;
	private Integer releaseYear;
	private String language;
	private Integer rentDuration;
	private Double rentRate;
	private Integer length;
	private Double replaceCost;
	private String rating;
	private String specialFeat;
	private List<Actor> actors;

	public Film() {
	}
	
	public Film(Integer id, String title, String description, Integer releaseYear, String language,
			Integer rentDuration, Double rentRate, Integer length, Double replaceCost, String rating, String specialFeat) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentDuration = rentDuration;
		this.rentRate = rentRate;
		this.length = length;
		this.replaceCost = replaceCost;
		this.rating = rating;
		this.specialFeat = specialFeat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getRentDuration() {
		return rentDuration;
	}

	public void setRentDuration(Integer rentDuration) {
		this.rentDuration = rentDuration;
	}

	public Double getRentRate() {
		return rentRate;
	}

	public void setRentRate(Double rentRate) {
		this.rentRate = rentRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Double getReplaceCost() {
		return replaceCost;
	}

	public void setReplaceCost(Double replaceCost) {
		this.replaceCost = replaceCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeat() {
		return specialFeat;
	}

	public void setSpecialFeat(String specialFeat) {
		this.specialFeat = specialFeat;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + ((rentDuration == null) ? 0 : rentDuration.hashCode());
		result = prime * result + ((rentRate == null) ? 0 : rentRate.hashCode());
		result = prime * result + ((replaceCost == null) ? 0 : replaceCost.hashCode());
		result = prime * result + ((specialFeat == null) ? 0 : specialFeat.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (rentDuration == null) {
			if (other.rentDuration != null)
				return false;
		} else if (!rentDuration.equals(other.rentDuration))
			return false;
		if (rentRate == null) {
			if (other.rentRate != null)
				return false;
		} else if (!rentRate.equals(other.rentRate))
			return false;
		if (replaceCost == null) {
			if (other.replaceCost != null)
				return false;
		} else if (!replaceCost.equals(other.replaceCost))
			return false;
		if (specialFeat == null) {
			if (other.specialFeat != null)
				return false;
		} else if (!specialFeat.equals(other.specialFeat))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", releaseYear=");
		builder.append(releaseYear);
		builder.append(", language=");
		builder.append(language);
		builder.append(", rentDuration=");
		builder.append(rentDuration);
		builder.append(", rentRate=");
		builder.append(rentRate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", replaceCost=");
		builder.append(replaceCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", specialFeat=");
		builder.append(specialFeat);
		return builder.toString();
	}

}
