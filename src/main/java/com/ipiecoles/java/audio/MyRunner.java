package com.ipiecoles.java.audio;


import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;


    @Override
    public void run(String... strings) throws Exception {
        Connection connexion = initConnection();
        Statement statement = connexion.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist WHERE id = '6' LIMIT 10");
        while ( resultSet.next() ) {
            print(resultSet.getString("name"));
        }
        // Pour afficher le nombre d'albums total
        System.out.println(albumRepository.count());


        // Vérifier si un artiste exsite ou pas
        if (artistRepository.existsByName("Madonna") == true){
                System.out.println("Madonna existe déjà");
            }
            else {
                System.out.println("Madonna n'existe pas, on créé !");
        }

        // Pour afficher l'artiste en fonction de son ID
        //System.out.println(albumRepository.findById(1));

        // rechercher un album en fonction de son nom
        //Album t = albumRepository.findByTitle("Facelift");
        //print("Le nom de l'album est : " + t);

        // récupérer de manière paginée les artistes dont le nom contient une chaîne
        //de caractère sans prendre en compte la casse

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.ASC, "id");
        Page<Artist> artists = artistRepository.findByNameContainingIgnoreCase("black", pageRequest);
        print("Les résultats sont : " + artists);

        artists.forEach(MyRunner::print);


    }

    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/audio?serverTimezone=UTC";
        String user = "root";
        String pwd = "";

        java.sql.Connection connexion = null;

        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
        } catch ( java.sql.SQLException e ) {
            //Problème de connexion à la base !
            print(e.getMessage());
        }
        return connexion;
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
