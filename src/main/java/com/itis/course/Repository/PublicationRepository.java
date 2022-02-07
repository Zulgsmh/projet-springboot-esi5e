package com.itis.course.Repository;

import com.itis.course.Model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {


    List<Publication> findAllByPublicationType_Id(long id);
}
