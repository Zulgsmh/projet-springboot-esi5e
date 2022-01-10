package com.itis.course.Service;

import com.itis.course.Model.Langue;
import com.itis.course.Repository.LangueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class LangueService {

    @Autowired
    LangueRepository langueRepository;

    public void create(Langue langue) {
        langueRepository.save(langue);
    }

    public List<Langue> listAll(){
        return langueRepository.findAll();
    }

    public void delete(long id){
        langueRepository.deleteById(id);
    }

    public Langue getOne(long id){
        return langueRepository.getOne(id);
    }



}
