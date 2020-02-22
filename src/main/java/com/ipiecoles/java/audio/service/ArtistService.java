package com.ipiecoles.java.audio.service;


import com.ipiecoles.java.audio.exception.ConflictException;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class ArtistService {

    public static final int PAGE_SIZE_MIN = 10;
    public static final int PAGE_SIZE_MAX = 100;
    public static final int PAGE_MIN = 0;
    private static final String PAGE_VALID_MESSAGE = "La taille de la page doit être comprise entre 10 et 100";

    @Autowired
    private ArtistRepository artistRepository;


    public <T extends Artist> T creerArtist(@Valid T e) throws ConflictException {
        if(artistRepository.existsByName(e.getName()) == true) {
            throw new ConflictException("L'artiste du nom de " + e.getName() + " existe déjà !");
        }
        return artistRepository.save(e);
    }


}