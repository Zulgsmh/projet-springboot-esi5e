package com.itis.course.Model;


import com.itis.course.Enum.PublicationStatus;
import com.itis.course.Enum.Role;
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

    private PublicationStatus status;

    private String texte;

    @ManyToOne
    private Users auteur;
    private String source; // d'ou vient le proverbe
    private String sens; // explication du proverbe

    @OneToOne
    @JoinColumn(name = "publicationType_id")
    private PublicationType publicationType; // histoire / amour / mort ...

    @OneToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @OneToOne
    @JoinColumn(name = "validate_by_id")
    private Users validateBy; //must have specialist role

    @OneToMany(mappedBy = "publication", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Traduction> traductionList;

    private String imageAssociated;

    public Publication(PublicationStatus status, String texte, Users auteur, String source, String sens, PublicationType publicationType, Langue langue, Users validateBy) {
        this.status = status;
        this.texte = texte;
        this.auteur = auteur;
        this.source = source;
        this.sens = sens;
        this.publicationType = publicationType;
        this.langue = langue;
        this.validateBy = validateBy;
    }

    public Publication(PublicationStatus status, String texte, Users auteur, String source, String sens, PublicationType publicationType, Langue langue, Users validateBy, String imageAssociated) {
        this.status = status;
        this.texte = texte;
        this.auteur = auteur;
        this.source = source;
        this.sens = sens;
        this.publicationType = publicationType;
        this.langue = langue;
        this.validateBy = validateBy;
        this.imageAssociated = imageAssociated;
    }

}
