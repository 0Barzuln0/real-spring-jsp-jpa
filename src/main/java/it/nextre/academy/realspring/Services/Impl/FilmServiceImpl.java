package it.nextre.academy.realspring.Services.Impl;

import it.nextre.academy.realspring.Services.FilmService;
import it.nextre.academy.realspring.controllers.api.FilmController;
import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.repositories.FilmRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("implfilm")
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    Logger log = Logger.getLogger(FilmController.class);


    public List<Film> getAll() {
        log.debug("FilmService -> getAll() called");
        return (List<Film>) filmRepository.findAll();
    }

    public Film findById(long id) {
        log.debug("FilmService -> findById() called with id: " + id);
        return filmRepository.findOne(id);
    }

    public List<Film> findByTitle(String titolo) {
        log.debug("FilmService -> findByTitle() called with titolo: " + titolo);
        return filmRepository.findAllByTitoloContainsOrderByTitoloAsc(titolo);
    }

    public List<Film> findByDirector(String regista) {
        log.debug("FilmService -> findByDirector() called with regista: " + regista);
        return filmRepository.findAllByRegistaContainsOrderByRegistaAsc(regista);
    }

    public List<Film> findByYear(int anno) {
        log.debug("FilmService -> findByYear() called with anno: " + anno);
        return filmRepository.findAllByAnnoOrderByAnno(anno);
    }

    public Film add(Film f) throws Exception {
        log.debug("FilmService -> add() called with film: " + f);
        if (f != null && f.getId() == 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            Film tmp = filmRepository.save(f);
            log.debug(tmp);
            return tmp;
        } else {
            throw new Exception("Malformed film data");
        }
    }

    public Film save(Film f) throws Exception {
        log.debug("FilmService -> save() called with film: " + f);
        if (f != null && f.getId() > 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            Film tmp = filmRepository.save(f);
            log.debug(tmp);
            return tmp;
        } else {
            throw new Exception("Malformed film data");
        }
    }

    public Boolean delete(Film f) throws Exception {
        log.debug("FilmService -> delete() called with film: " + f);
        if (f != null && f.getId() > 0) {
            filmRepository.delete(f);
            return true;
        } else {
            throw new Exception("Malformed film data");
        }
    }

}// end class
