package com.ipiecoles.java.audio.controller;


import com.ipiecoles.java.audio.exception.ConflictException;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    // Compter les artistes
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countArtists(){
        //Récupérer le nombre d'artistes et l'envoyer au client
        return artistRepository.count();
    }

    // 1 - Afficher un artiste
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

    // 2 - Recherche par nom
    @RequestMapping(value = "", params = "name")
    public Page<Artist> findArtistWithName(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty){

            return artistRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size, sortDirection, sortProperty));
    }

    // 3 - Liste des artistes
    @GetMapping
    public Page<Artist> ListArtistByPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection){

        if (size <= 1 || size > 50)  {
            throw new IllegalArgumentException("La taille des pages doit être comprise entre 0 et 50.");
        }

        Long maxPage = artistRepository.count() / size;
        if (page < 0 || page >= maxPage){
            throw new IllegalArgumentException("La page " + page + " doit être comprise entre 0 et " + maxPage);
        }

        if(!Arrays.asList("id", "name").contains(sortProperty)){
            throw new IllegalArgumentException("La propriété de tri est incorrecte");
        }

        return artistRepository.findAll(PageRequest.of(page,size, sortDirection, sortProperty));
    }

    // 4 - Création d'un artiste
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = MediaType.APPLICATION_JSON_VALUE // MediaType.APPLICATION_JSON_VALUE = "application/json"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist (
            @RequestBody Artist artist
    ) throws ConflictException {
        if (artistRepository.findByName(artist.getName()) != null){
            throw new ConflictException("Un artiste existe déjà avec ce nom !");
        }
        return artistRepository.save(artist);
    }

    // 6 - Suppression d'un Artiste
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist (
            @PathVariable ("id") Integer idArtist){
        artistRepository.deleteById(idArtist);
    }





}
