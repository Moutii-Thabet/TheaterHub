package com.moutii.TheaterHub.moviemanagement.director;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,String> {
    boolean existsByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);
}
