package com.ipiecoles.java.audio.repository;

import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    // récupérer de manière paginée les artistes dont le nom contient une chaîne
    //de caractère sans prendre en compte la casse
    Page<Artist> findByNameIgnoreCase(String name, Pageable pageable);

}