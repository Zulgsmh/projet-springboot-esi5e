package com.itis.course.Repository;

import com.itis.course.Model.Traduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraductionRepository extends JpaRepository<Traduction, Long> {

    List<Traduction> findAllByPublication_Id(long id);

}