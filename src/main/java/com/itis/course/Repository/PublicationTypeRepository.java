package com.itis.course.Repository;

import com.itis.course.Model.PublicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationTypeRepository extends JpaRepository<PublicationType, Long> {

    Optional<PublicationType> findByName(String name);
}
