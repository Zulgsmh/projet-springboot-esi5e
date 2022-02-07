package com.itis.course.ModelForm;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PublicationForm {

    @NotEmpty(message = "texte can not be empty")
    private String texte;

    private String status;

    @NotEmpty(message = "langue can not be empty")
    private String langue;

    @NotEmpty(message = "Tag can not be empty")
    private String tag;

    @NotEmpty(message = "Sens can not be empty")
    private String sens;

    @NotEmpty(message = "Source can not be empty")
    private String source;

    private String imageName;



}
