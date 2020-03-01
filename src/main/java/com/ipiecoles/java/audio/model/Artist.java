package com.ipiecoles.java.audio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "ArtistId")
    private Integer id;

    //@Column(name = "Name")
    private String name;

    @OneToMany( mappedBy = "artist" )
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    // Constructeurs

    public Artist(){}

    public Artist(Integer id, String name, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

// Getters & Setters

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

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
        sb.append("id artiste='").append(id).append('\'');
        sb.append("nom artiste='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}