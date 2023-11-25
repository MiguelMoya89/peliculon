package com.example.peliculon.repositorio;

import com.example.peliculon.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RepositorioPeliculas extends JpaRepository<Pelicula, Long> {

public ArrayList<Pelicula>findAll();//Recuperar todas las peliculas
public Pelicula findById(long id);//Recuperar por ID
public Pelicula save(Pelicula pelicula);

public ArrayList<Pelicula>findByNacionalidad(String nacionalidad);




}
