package com.itis.course.Service;

import com.itis.course.Enum.PublicationStatus;
import com.itis.course.Model.Publication;
import com.itis.course.Model.Users;
import com.itis.course.Repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Publication> getAllByPublicationTypeId(long id) {
        return publicationRepository.findAllByPublicationType_Id(id);
    }

    @Transactional
    public void validatePublication(long id, Users user){
        Optional<Publication> publication = publicationRepository.findById(id);
        if(publication.isPresent()) {
            publication.get().setStatus(PublicationStatus.Valide);
            publication.get().setValidateBy(user);
        }
    }

    @Transactional
    public void unValidatePublication(long id, Users user) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if(publication.isPresent()) {
            publication.get().setStatus(PublicationStatus.NonValide);
            publication.get().setValidateBy(user);
        }
    }



}
