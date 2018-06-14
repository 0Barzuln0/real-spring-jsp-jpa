package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.Services.FilmService;
import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    ResponseHelper responseHelper;

    Logger log = Logger.getLogger(FilmController.class);


    @GetMapping("/")
    public ResponseEntity getAll(){
        log.debug("getAll Film called");
        return responseHelper.ok(filmService.getAll());
    }

    @GetMapping("/{idFilm}")
    public ResponseEntity findById(@PathVariable("idFilm") Long id){
        log.debug("findById Film called");
        if(id != null){
            return responseHelper.ok(filmService.findById(id));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @GetMapping("/titolo/{titoloFilm}")
    public ResponseEntity findByTitolo(@PathVariable("titoloFilm") String titolo){
        log.debug("findByTitolo Film called");
        if(titolo != null){
            return responseHelper.ok(filmService.findByTitle(titolo));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @GetMapping("/regista/{registaFilm}")
    public ResponseEntity findByRegista(@PathVariable("registaFilm") String regista){
        log.debug("findByRegista Film called");
        if(regista != null){
            return responseHelper.ok(filmService.findByDirector(regista));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @GetMapping("/anno/{annoFilm}")
    public ResponseEntity findByAnno(@PathVariable("annoFilm") Integer anno){
        log.debug("findByAnno Film called");
        if(anno != null){
            return responseHelper.ok(filmService.findByYear(anno));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @PostMapping("/")
    public ResponseEntity addFilm(@RequestBody Film f){
        log.debug("addFilm Film called");
        try {
            return responseHelper.ok(filmService.add(f));
        } catch (Exception e) {
            return responseHelper.badRequest(e.getMessage());
        }
    }

    @PutMapping("/{idFilm}")
    public ResponseEntity updateFilm(@RequestBody Film f, @PathVariable("idFilm") long id){
        log.debug("updateFilm Film called");
        if(f.getId() == id) {
            try {
                return responseHelper.ok(filmService.save(f));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        }
        return responseHelper.badRequest("Invalid input data, Invalid ID");
    }

    @DeleteMapping("/{idFilm}")
    public ResponseEntity deleteFilm(@RequestBody Film f, @PathVariable("idFilm") long id){
        log.debug("updateFilm Film called");
        if(f.getId() == id) {
            try {
                return responseHelper.ok(filmService.delete(f));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        }
        return responseHelper.badRequest("Invalid input data, Invalid ID");
    }

}// end class
