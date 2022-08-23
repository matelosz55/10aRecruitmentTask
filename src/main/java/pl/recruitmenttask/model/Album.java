package pl.recruitmenttask.model;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Insert album title")
    private String title;
    @ManyToOne
    @NotNull(message = "Insert artist")
    private Artist artist;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Set release date")
    private LocalDate releaseDate;
    private String comments;
    private Integer numberOfTracks;


    public Album(long id, String title, Artist artist, LocalDate releaseDate, String comments, Integer numberOfTracks) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.comments = comments;
        this.numberOfTracks = numberOfTracks;
    }

    public Album(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(Integer numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", releaseDate=" + releaseDate +
                ", comments='" + comments + '\'' +
                ", numberOfTracks=" + numberOfTracks +
                '}';
    }
}
