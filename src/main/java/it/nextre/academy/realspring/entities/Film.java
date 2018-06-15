package it.nextre.academy.realspring.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue()
    @Column(name="idfilm")
    private long id;

    @NotNull(message="Titolo non valido")
    @Column(length=255)
    @Size(min=3, max=255, message="Lunghezza titolo non valida")
//    @Column(name="titolo")
    private String titolo;

//    @Column(name="regista")
    private String regista;

    @Column(length=4)
    @Min(value=1870, message="Anno troppo piccolo")
    @Max(value=2300, message="Anno troppo grande")
//    @Size(min=1870, max=2300, message="Anno non valido")
//    @Column(name="anno")
    private int anno;


}// end class
