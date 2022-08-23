package pl.recruitmenttask.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Insert song title")
    private String title;
    @ManyToMany
    private List<Album> album;
    @NotNull(message = "insert duration of song in seconds")
    private Integer duration;

    public Song(long id, String title, List<Album> album, Integer duration) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }

    public Song(){
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

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album=" + album +
                ", duration=" + duration +
                '}';
    }
}
