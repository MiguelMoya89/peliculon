package com.example.peliculon.controlador;


import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorio.RepositorioComentarios;
import com.example.peliculon.repositorio.RepositorioPeliculas;
import com.example.peliculon.servicios.ServicioComentarios;
import com.example.peliculon.servicios.ServicioPeliculas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class Principal {

    @Autowired
    ServicioPeliculas repositorioPeliculas;
    @Autowired
    ServicioComentarios repositorioComentarios;


    @GetMapping("/")



    public String inicio(Model model){

        ArrayList<Pelicula>cartelera = repositorioPeliculas.findAll();
        model.addAttribute("cartelera", cartelera);

        //model.addAttribute("cartelera",repositorioPeliculas.findAll());

        return "index";
    }

    //Esta es la dirección que tengo que escribir en el navegador
    //Esta es la dirección que tengo que escribir en el enlace de index.html
    //Lo que sale en el <a th:href="/pelicula/{id}>
    @GetMapping("/pelicula/{id}")
    public String peliculas (@PathVariable long id, Model model){

        Pelicula p = repositorioPeliculas.findById(id);
        //El nombre de "pelicula" es el que voy a usar en la vista detalle.html
        model.addAttribute("pelicula",p);
        model.addAttribute("comentarios",repositorioComentarios.findByPelicula(p));

        //El nombre que pongo en el return es el que tendrá el archivo .html
        return "pelicula";

    }
    //Introducir comentarios a BBDD
    @PostMapping("/pelicula/{id}/comentario")
    public String agregarComentario(@PathVariable long id, @RequestParam String comentario, Model model) {
        Pelicula p = repositorioPeliculas.findById(id);
        Comentario c = new Comentario();
        c.setContenido(comentario);
        c.setPelicula(p);
        repositorioComentarios.save(c);
        return "redirect:/pelicula/" + id;
    }

    @Transactional
    public void guardarComentario(Comentario comentario) {
        repositorioComentarios.save(comentario);
    }

}
