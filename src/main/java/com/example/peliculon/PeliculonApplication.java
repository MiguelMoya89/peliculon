package com.example.peliculon;

import com.example.peliculon.modelo.Comentario;
import com.example.peliculon.modelo.Pelicula;
import com.example.peliculon.repositorio.RepositorioComentarios;
import com.example.peliculon.repositorio.RepositorioPeliculas;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class PeliculonApplication {

	@Autowired
	RepositorioPeliculas repositorioPeliculas;
	@Autowired
	RepositorioComentarios repositorioComentarios;

	public static void main(String[] args) {

		SpringApplication.run(PeliculonApplication.class, args);
	}

	@Bean
	CommandLineRunner ponPeliculas() {
		return args -> {

			Faker faker = new Faker(new Locale("es-ES"));
			if (repositorioPeliculas.findAll().size() < 10) { // este metodo añade campos de forma aleatoria hasta la cantidad indicada en este caso 10
				for (int i = 1; i <= 10; i++) {
					Pelicula p = new Pelicula();
					p.setTitulo(faker.book().title());
					p.setImagen(i + ".jpeg");
					p.setSinopsis(faker.chuckNorris().fact());
					p.setFechaEstreno(LocalDate.now());
					p.setNacionalidad(faker.country().countryCode2());
					p.setFecha(LocalDate.now());
					p.setTrailer("https://www.youtube.com/embed/a_426RiwST8?si=BDxxgic4pJzkwbv5");

					repositorioPeliculas.save(p);

					for(int ii=0; ii<3; ii++){
						Comentario c=new Comentario();
						c.setTitulo(faker.backToTheFuture().character());
						c.setContenido(faker.backToTheFuture().quote());
						c.setFecha(LocalDate.now());
						c.setPelicula(p);

						repositorioComentarios.save(c);


					}
				}
			}
		};
	}
}
