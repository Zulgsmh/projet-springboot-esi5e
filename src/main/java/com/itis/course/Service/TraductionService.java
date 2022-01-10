package com.itis.course.Service;

import com.itis.course.Model.Traduction;
import com.itis.course.Repository.TraductionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TraductionService {

    @Autowired
    TraductionRepository traductionRepository;

    public List<Traduction> retrieveAll() {
        return traductionRepository.findAll();
    }

    public boolean create(Traduction traduction) {
        try{
            traductionRepository.save(traduction);
            log.info("traduction saved : {}", traduction.getText());
            return true;
        } catch (Exception e) {
            log.info("Error while saving traduction");
            return false;
        }
    }

    public void deleteOne(long id){
        traductionRepository.deleteById(id);
    }

    public Traduction getOne(long id) {
        return traductionRepository.getOne(id);
    }

}
