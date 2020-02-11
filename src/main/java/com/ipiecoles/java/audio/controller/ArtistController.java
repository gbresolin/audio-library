package com.ipiecoles.java.audio.controller;


import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "", params = "name")
    public Page<Artist> findArtistWithName(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty){

            return artistRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size, sortDirection, sortProperty));
    }




}
