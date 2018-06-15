package it.nextre.academy.realspring.Services.Impl;

import it.nextre.academy.realspring.Services.FilmService;
import it.nextre.academy.realspring.controllers.FilmController;
import it.nextre.academy.realspring.entities.Film;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("mockfilm")
public class FilmServiceMock implements FilmService {

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

    public Film add(Film f) throws Exception {
        log.debug("FilmService -> add() called with film: " + f);
        if(f != null && f.getId() == 0 && f.getTitolo() != null && f.getTitolo().length() > 0){
            long maxId = videoteca.stream()
                        .max((f1, f2) -> (int)(f1.getId()-f2.getId()))
                        .get()
                        .getId();
            f.setId(++maxId);
            log.debug(f);
            videoteca.add(f);
            return f;
        }else{
            throw new Exception("Malformed film data");
        }
    }

    public Film save(Film f) throws Exception {
        log.debug("FilmService -> save() called with film: " + f);
        if(f != null && f.getId() > 0 && f.getTitolo() != null && f.getTitolo().length() > 0){
            Optional<Film> tmp = videoteca.stream().filter(f1 -> f1.getId() == f.getId()).findFirst();
            if(tmp.isPresent()){
                tmp.get().setTitolo(f.getTitolo());
                tmp.get().setRegista(f.getRegista());
                tmp.get().setAnno(f.getAnno());
                log.debug(tmp.get());
                return tmp.get();
            }
        }else {
            throw new Exception("Malformed film data");
        }
        return new Film();
    }

    public Boolean delete(Film f) throws Exception {
        log.debug("FilmService -> delete() called with film: " + f);
        if(f != null && f.getId() > 0){
            return videoteca.removeIf(f1 -> f1.getId() == f.getId());
        }else{
            throw new Exception("Malformed film data");
        }
        //return false;
    }

}// end class
