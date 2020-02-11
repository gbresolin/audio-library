package com.ipiecoles.java.audio.exception;

import com.ipiecoles.java.audio.model.Artist;

public class ArtistException extends Throwable {
    public static final String ID = "L'identifiant passé ne correspond pas à l'identifiant de l'employé : ";

    public ArtistException(String message, Artist artist, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ", artiste : " + artist.toString());
        System.out.println(this.getMessage());
    }

    public ArtistException(String message) {
        super(message);
        System.out.println(this.getMessage());
    }
}
