package it.nextre.academy.realspring.Services;

import it.nextre.academy.realspring.controllers.FilmController;
import it.nextre.academy.realspring.models.Film;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    Logger log = Logger.getLogger(FilmController.class);

    private List<Film> videoteca = new ArrayList<>();
    {
        videoteca.add(new Film(1,"300","Zack Snyder", 2007));
        videoteca.add(new Film(2,"Pacific Rim","Guillermo del Toro", 2013));
        videoteca.add(new Film(3,"Dunkirk","Christopher Nolan", 2017));
        videoteca.add(new Film(4,"Saw - L'enigmista","James Wan", 2005));
        videoteca.add(new Film(5,"Il Padrino","Francis Ford Coppola", 1972));
        videoteca.add(new Film(6,"Arancia Meccanica","Stanley Kubrick", 1972));
        videoteca.add(new Film(7,"Shining","Stanley Kubrick", 1980));
        videoteca.add(new Film(8,"Italiano medio","Maccio Capatonda", 2015));
    }

    public List<Film> getAll(){
//        List<Film> tmp = new ArrayList<>();
        log.debug("FilmService -> getAll() called");
        return videoteca;
    }

    public Film findById(long id){
        log.debug("FilmService -> findById() called with id: " + id);
        return videoteca.stream()
                .filter(f->f.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public List<Film> findByTitle(String titolo){
        log.debug("FilmService -> findByTitle() called with titolo: " + titolo);
        return videoteca.stream()
                .filter(f->f.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Film> findByDirector(String regista){
        log.debug("FilmService -> findByDirector() called with regista: " + regista);
        List<Film> tmp = videoteca.stream()
                .filter(f->f.getRegista().toLowerCase().contains(regista.toLowerCase()))
                .collect(Collectors.toList());
        return tmp;
    }

    public List<Film> findByYear(int anno){
        log.debug("FilmService -> findByYear() called with anno: " + anno);
        List<Film> tmp = videoteca.stream()
                .filter(f->f.getAnno() == anno)
                .collect(Collectors.toList());
        return tmp;
    }

}// end class
