package it.nextre.academy.realspring.Services;

import it.nextre.academy.realspring.entities.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAll();
    Film findById(long id);
    List<Film> findByTitle(String titolo);
    List<Film> findByDirector(String regista);
    List<Film> findByYear(int anno);
    Film add(Film f) throws Exception;
    Film save(Film f) throws Exception;
    Boolean delete(Film f) throws Exception;
}
