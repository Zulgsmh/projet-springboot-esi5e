package com.itis.course.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "Traduction")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Traduction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;

    @OneToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @OneToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;




}
