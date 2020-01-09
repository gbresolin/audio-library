package com.ipiecoles.java.audio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Integer artistId;

    @Column(name = "Name")
    private String name;

    @OneToMany( mappedBy = "artist" )
    private List<Album> albums;

    // Constructeurs

    public Artist(){}

    public Artist(Integer artistId, List<Album> albums, String name) {
        this.artistId = artistId;
        this.albums = albums;
        this.name = name;
    }

// Getters & Setters

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Artist{");
        sb.append("id artiste='").append(artistId).append('\'');
        sb.append("nom artiste='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
