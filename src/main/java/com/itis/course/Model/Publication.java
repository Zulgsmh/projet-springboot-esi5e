package com.itis.course.Model;

import com.itis.course.Enum.TypePublication;
import com.mysql.cj.jdbc.Blob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Publication")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int status;
    private String texte;

    @ManyToOne
    private Users auteur;
    private String source; // d'ou vient le proverbe
    private String sens; // explication du proverbe

    @Column
    @ElementCollection(targetClass = TypePublication.class)
    private List<TypePublication> publicationType; // histoire / amour / mort ...

    @OneToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @OneToOne
    @JoinColumn(name = "validate_by_id")
    private Users validateBy; //must have specialist role

    @Lob
    @Column(name = "Pdf", length = Integer.MAX_VALUE, nullable = true)
    private byte[] document; // pdf

    // TODO : IMPLEMENT IMAGE UPLOAD FOR EACH PUBLICATION
    // private Blob image;

    public Publication(int status, String texte, Users auteur, String source, String sens, List<TypePublication> publicationType, Langue langue, Users validateBy) {
        this.status = status;
        this.texte = texte;
        this.auteur = auteur;
        this.source = source;
        this.sens = sens;
        this.publicationType = publicationType;
        this.langue = langue;
        this.validateBy = validateBy;
    }

    public Publication(int status, String texte, Users auteur, String source, String sens, List<TypePublication> publicationType, Langue langue, Users validateBy, byte[] document) {
        this.status = status;
        this.texte = texte;
        this.auteur = auteur;
        this.source = source;
        this.sens = sens;
        this.publicationType = publicationType;
        this.langue = langue;
        this.validateBy = validateBy;
        this.document = document;
    }

}
