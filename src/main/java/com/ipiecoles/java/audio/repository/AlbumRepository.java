package com.ipiecoles.java.audio.repository;

import com.ipiecoles.java.audio.model.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer> {

    // rechercher un album en fonction de son nom
    Album findByTitle(String title);

    boolean existsByTitle(String title);

}
