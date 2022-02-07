package com.itis.course.Model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "Langue")
@Slf4j
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Langue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String langue;

    public Langue(String langue) {
        this.langue = langue;
    }

}
