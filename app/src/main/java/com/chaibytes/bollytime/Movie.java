package com.chaibytes.bollytime;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pdebadarshini on 7/8/16.
 */
public class Movie {

        @SerializedName("Id")
        private String id;

        @SerializedName("ImdbId")
        private String imdbId;

        @SerializedName("OriginalTitle")
        private String originalTitle;

        @SerializedName("Title")
        private String title;

        @SerializedName("Description")
        private String description;

        @SerializedName("TrailerLink")
        private String trailerLink;

        @SerializedName("TrailerEmbedCode")
        private String trailerEmbedCode;

        @SerializedName("Country")
        private String country;

        @SerializedName("Region")
        private String region;

        @SerializedName("Genre")
        private String genre;

        @SerializedName("RatingCount")
        private int ratingCount;

        @SerializedName("Rating")
        private float rating;

        @SerializedName("CensorRating")
        private String censorRating;

        @SerializedName("ReleaseDate")
        private String releaseDate;

        @SerializedName("Runtime")
        private int runtime;

        @SerializedName("Budget")
        private long budget;

        @SerializedName("Revenue")
        private long revenue;

        @SerializedName("PosterPath")
        private String posterPath;

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the imdbId
         */
        public String getImdbId() {
            return imdbId;
        }

        /**
         * @param imdbId the imdbId to set
         */
        public void setImdbId(String imdbId) {
            this.imdbId = imdbId;
        }

        /**
         * @return the originalTitle
         */
        public String getOriginalTitle() {
            return originalTitle;
        }

        /**
         * @param originalTitle the originalTitle to set
         */
        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return the trailerLink
         */
        public String getTrailerLink() {
            return trailerLink;
        }


        /**
         * @param trailerLink the trailerLink to set
         */
        public void setTrailerLink(String trailerLink) {
            this.trailerLink = trailerLink;
        }


        /**
         * @return the trailerEmbedCode
         */
        public String getTrailerEmbedCode() {
            return trailerEmbedCode;
        }


        /**
         * @param trailerEmbedCode the trailerEmbedCode to set
         */
        public void setTrailerEmbedCode(String trailerEmbedCode) {
            this.trailerEmbedCode = trailerEmbedCode;
        }


        /**
         * @return the country
         */
        public String getCountry() {
            return country;
        }

        /**
         * @param country the country to set
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * @return the region
         */
        public String getRegion() {
            return region;
        }

        /**
         * @param region the region to set
         */
        public void setRegion(String region) {
            this.region = region;
        }

        /**
         * @return the genre
         */
        public String getGenre() {
            return genre;
        }

        /**
         * @param genre the genre to set
         */
        public void setGenre(String genre) {
            this.genre = genre;
        }

        /**
         * @return the ratingCount
         */
        public int getRatingCount() {
            return ratingCount;
        }

        /**
         * @param ratingCount the ratingCount to set
         */
        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }

        /**
         * @return the rating
         */
        public float getRating() {
            return rating;
        }

        /**
         * @param rating the rating to set
         */
        public void setRating(float rating) {
            this.rating = rating;
        }

        /**
         * @return the censorRating
         */
        public String getCensorRating() {
            return censorRating;
        }

        /**
         * @param censorRating the censorRating to set
         */
        public void setCensorRating(String censorRating) {
            this.censorRating = censorRating;
        }

        /**
         * @return the releaseDate
         */
        public String getReleaseDate() {
            return releaseDate;
        }

        /**
         * @param releaseDate the releaseDate to set
         */
        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        /**
         * @return the runtime
         */
        public int getRuntime() {
            return runtime;
        }

        /**
         * @param runtime the runtime to set
         */
        public void setRuntime(int runtime) {
            this.runtime = runtime;
        }

        /**
         * @return the budget
         */
        public long getBudget() {
            return budget;
        }

        /**
         * @param budget the budget to set
         */
        public void setBudget(long budget) {
            this.budget = budget;
        }

        /**
         * @return the revenue
         */
        public long getRevenue() {
            return revenue;
        }

        /**
         * @param revenue the revenue to set
         */
        public void setRevenue(long revenue) {
            this.revenue = revenue;
        }

        /**
         * @return the posterPath
         */
        public String getPosterPath() {
            return posterPath;
        }

        /**
         * @param posterPath the posterPath to set
         */
        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
}
