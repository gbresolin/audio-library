package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.exception.ConflictException;
import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    ) {
        return albumRepository.save(album);
    }


    /*
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    //@RequestMapping(value = "/{idArtist}/albums/{nomAlbum}/add", method = RequestMethod.GET)
    public Album addAlbumToArtist (
            @PathVariable("idArtist") Integer idArtist,
            @PathVariable("nomAlbum") String nomAlbum
    ){
        // Je récupère l'artiste à partir de son ID
        Artist artist = artistRepository.findById(idArtist).get();

        // Je récupère l'album' à partir de son nom
        Album album = (Album) albumRepository.findByTitle(nomAlbum);

        // Je lie l'album à l'artiste
        album.setArtist(artist);

        // On sauvegarde l'album
        return  albumRepository.save(album);
    }

     */



    // 8 - Suppression d'un album
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum (
            @PathVariable("id") Integer idAlbum){
        albumRepository.deleteById(idAlbum);
    }


}
