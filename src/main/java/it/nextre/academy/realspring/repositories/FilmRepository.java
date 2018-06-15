package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FilmRepository extends CrudRepository<Film, Long> {

    List<Film> findAllByTitoloContains(String titolo);
    List<Film> findAllByTitoloContainsOrderByTitoloAsc(String titolo);
    List<Film> findAllByRegistaContains(String regista);
    List<Film> findAllByRegistaContainsOrderByRegistaAsc(String regista);
    List<Film> findAllByAnno(int anno);
    List<Film> findAllByAnnoOrderByAnno(int anno);

}// end class
