package com.ipiecoles.java.audio.controller;


import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countArtists(){
        //Récupérer le nombre d'artistes et l'envoyer au client
        return artistRepository.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Artist getArtistByID(@PathVariable("id") Integer id){
        //Récupérer les informations de l'artiste par ID
        //return employeRepository.findById(id).get();
        Optional<Artist> a = artistRepository.findById(id);
        if(a.isPresent()) {
            return a.get();
        }
        throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas.");
    }




}
