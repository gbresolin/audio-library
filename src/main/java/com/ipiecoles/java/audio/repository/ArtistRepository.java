package com.ipiecoles.java.audio.repository;

import com.ipiecoles.java.audio.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    /*
    Récupérer de manière paginée les artistes dont le nom contient une chaîne
    de caractère sans prendre en compte la casse
     */
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Artist> findByName(String name);

    boolean existsByName(String name);

}