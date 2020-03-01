package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.exception.ConflictException;
import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    // 7 - Ajout d'un album
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    public Album createAlbum (
            @RequestBody Album album
    ) throws ConflictException {

        if(albumRepository.existsByTitle(album.getTitle()) == true){
            throw new ConflictException("L'album existe déjà");
        }
        if (album.getTitle().trim().isEmpty()) {
            throw new IllegalStateException ("L'album ne peut avoir une valeur NULL !");
        }
        return albumRepository.save(album);
    }


    // 8 - Suppression d'un album
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum (
            @PathVariable("id") Integer idAlbum){
        Optional<Album> a = albumRepository.findById(idAlbum);
        if(!a.isPresent()){
            throw new EntityNotFoundException("L'album d'identifiant " + idAlbum + " n'a pas été trouvé !");
        }
        albumRepository.deleteById(idAlbum);
    }

}
