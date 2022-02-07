package com.itis.course.Service;

import com.itis.course.Model.PublicationType;
import com.itis.course.Repository.PublicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationTypeService {

    @Autowired
    PublicationTypeRepository publicationTypeRepository;

    public List<PublicationType> getAllPublicationType(){
        return publicationTypeRepository.findAll();
    }
    public void createPublicationType(PublicationType publicationType){publicationTypeRepository.save(publicationType);}
    public void deleteOne(long id) { publicationTypeRepository.deleteById(id);}
    public Optional<PublicationType> getCategoryById(long id){return publicationTypeRepository.findById(id);}
    public Optional<PublicationType> getCategoryByName(String name){return publicationTypeRepository.findByName(name);}
}
