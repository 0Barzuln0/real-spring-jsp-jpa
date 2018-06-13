package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.Services.FilmService;
import it.nextre.academy.realspring.models.Film;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    Logger log = Logger.getLogger(FilmController.class);


    @GetMapping("/")
    public List<Film> getAll(){
        log.debug("getAll Film called");
        return filmService.getAll();
    }

    @GetMapping("/{idFilm}")
    public Film findById(@PathVariable("idFilm") Long id){
        log.debug("findById Film called");
        if(id != null){
            return filmService.findById(id);
        }
        return null;
    }

    @GetMapping("/titolo/{titoloFilm}")
    public List<Film> findByTitolo(@PathVariable("titoloFilm") String titolo){
        log.debug("findByTitolo Film called");
        if(titolo != null){
            return filmService.findByTitle(titolo);
        }
        return null;
    }

    @GetMapping("/regista/{registaFilm}")
    public List<Film> findByRegista(@PathVariable("registaFilm") String regista){
        log.debug("findByRegista Film called");
        if(regista != null){
            return filmService.findByDirector(regista);
        }
        return null;
    }

    @GetMapping("/anno/{annoFilm}")
    public List<Film> findByAnno(@PathVariable("annoFilm") Integer anno){
        log.debug("findByAnno Film called");
        if(anno != null){
            return filmService.findByYear(anno);
        }
        return null;
    }

    @PostMapping("/")
    public Film addFilm(@RequestBody Film f){
        log.debug("addFilm Film called");
        return filmService.add(f);
    }

    @PutMapping("/{idFilm}")
    public Film updateFilm(@RequestBody Film f, @PathVariable("idFilm") long id){
        log.debug("updateFilm Film called");
        if(f.getId() == id) {
            return filmService.save(f);
        }else{
            return new Film();
        }
    }

    @DeleteMapping("/{idFilm}")
    public Boolean deleteFilm(@RequestBody Film f, @PathVariable("idFilm") long id){
        log.debug("updateFilm Film called");
        if(f.getId() == id) {
            return filmService.delete(f);
        }else{
            return false;
        }
    }
}// end class
