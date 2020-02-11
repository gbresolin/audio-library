package com.ipiecoles.java.audio.model;


import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@Column(name = "AlbumId")
    private Integer id;

    //@Column(name = "Title")
    private String title;


    @ManyToOne
    @JoinColumn(name = "artistId")

    private Artist artist;

    // Constructeurs

    public Album() {}

    public Album(Integer id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

// Getters & Setters


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Album{");
        sb.append("titre album='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
