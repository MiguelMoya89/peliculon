package com.example.peliculon.controlador;


import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorio.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Crud {
    @Autowired
    RepositorioPeliculas repo;

    @GetMapping("/crud")
    public String listadoPeliculas(Model model){
        //El nombre de "peliculas" es el que voy a utilizar en la plantilla
        model.addAttribute("peliculas", repo.findAll());
        return "crud";
    }

    @GetMapping("/add")
    public String addPelicula(Model model){
        model.addAttribute("formPelicula", new Pelicula());
        return "form_add";
    }

    @PostMapping("/crud/save")
    public String guardarPelicula(@ModelAttribute("formPelicula") Pelicula nuevaPelicula){
        repo.save(nuevaPelicula);
        return "redirect:/add";
    }

    @GetMapping("/crud/update/{id}")
    public String muestraPelicula(@PathVariable long id, Model model) {
        Pelicula p = repo.findById(id);
        model.addAttribute("formPelicula", p);
        return "form_add";
    }


    @PostMapping("/crud/modificar")
    public String modificarPelicula(@ModelAttribute("formPelicula") Pelicula p) {
        repo.save(p);
        return "redirect:/crud";

    }
    @GetMapping("/crud/delete/{id}")
    public  String borrarPeliculas(@PathVariable long id,Model model){
        repo.deleteById(id);
        return "redirect:/crud";
    }
}