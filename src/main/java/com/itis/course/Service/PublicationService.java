package com.itis.course.Service;

import com.itis.course.Model.Publication;
import com.itis.course.Repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    public List<Publication> getAll(){
        return publicationRepository.findAll();
    }

    public Publication getOne(long id) {
        return publicationRepository.getOne(id);
    }

    public void create(Publication publication){
        publicationRepository.save(publication);
    }

    public void delete(long id){
        publicationRepository.deleteById(id);
    }





}
