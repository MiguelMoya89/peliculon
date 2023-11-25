package com.example.peliculon.repositorio;

import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface RepositorioComentarios extends JpaRepository<Comentario, Long> {

    ArrayList<Comentario> findAll();

    Comentario findById(long id);

    public ArrayList<Comentario> findByPelicula(Pelicula pelicula);

    public Comentario save(Comentario comentario);
}
